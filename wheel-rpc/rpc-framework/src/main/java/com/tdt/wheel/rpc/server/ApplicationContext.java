package com.tdt.wheel.rpc.server;/**
 * Created by rc on 2019/8/25.
 */


import com.alibaba.fastjson.JSONObject;
import com.tdt.wheel.rpc.consumer.DefaultInvoker;
import com.tdt.wheel.rpc.consumer.Invoker;
import com.tdt.wheel.rpc.consumer.LoadBalancer;
import com.tdt.wheel.rpc.consumer.ReferenceConfig;
import com.tdt.wheel.rpc.test.NettyClient;
import io.netty.channel.ChannelHandlerContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author qrc
 * @description rpc应用程序上下文
 * @date 2019/8/25
 */
public class ApplicationContext<T> {

    /**
     * 负责生成requestId的类
     */
    private LongAdder requestIdWorker = new LongAdder();


    /**
     * 注册中心
     */
    private Registry registry;
    
    List<ServiceConfig> serviceConfigs;

    List<ReferenceConfig> referenceConfigs;

    private LoadBalancer loadBalancer;

    private Integer port;

    private Map<String, Method> interfaceMethods = new ConcurrentHashMap<>();

    private Map<Class, List<RegistryInfo>> interfacesMethodRegistryList = new ConcurrentHashMap<>();

    private Map<RegistryInfo, ChannelHandlerContext> channels = new ConcurrentHashMap<>();

    private Map<String, Invoker> inProgressInvoker = new ConcurrentHashMap<>();

    public ApplicationContext(String registryUrl, List<ServiceConfig> serviceConfigs,
                              List<ReferenceConfig> referenceConfigs, Integer port) throws Exception {
        // 1. 保存需要暴露的接口配置
        this.serviceConfigs = serviceConfigs;
        this.referenceConfigs = referenceConfigs;

        // 2: 实例化注册中心
        initRegistry(registryUrl);

        // 3: 将接口注册到注册中心，从注册中心获取接口，初始化服务接口列表
        RegistryInfo registryInfo = null;
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String hostName = addr.getHostName();
            String hostAddress = addr.getHostAddress();

            registryInfo = new RegistryInfo(hostName, hostAddress, port);
            doRegistry(registryInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 4：初始化Netty服务器，接受到请求，直接打到服务提供者的service方法中
        if (!this.serviceConfigs.isEmpty()) {
            // 需要暴露接口才暴露
            NettyServer nettyServer = new NettyServer(this.serviceConfigs, interfaceMethods);
            nettyServer.init(port);
        }
    }

    private void initRegistry(String registryUrl) {
        if (registryUrl.startsWith("zookeeper://")) {
            registryUrl = registryUrl.substring(12);
            registry = new ZookeeperRegistry(registryUrl);
        } else if (registryUrl.startsWith("multicast://")) {
            registry = new MulticastRegistry(registryUrl);
        }
    }

    private void doRegistry(RegistryInfo registryInfo) throws Exception {
        for (ServiceConfig config : serviceConfigs) {
            Class type = config.getType();
            registry.register(type, registryInfo);
            Method[] declaredMethods = type.getDeclaredMethods();
            for (Method method : declaredMethods) {
                String identify = InvokeUtils.buildInterfaceMethodIdentify(type, method);
                interfaceMethods.put(identify, method);
            }
        }

        for (ReferenceConfig config : referenceConfigs) {
            List<RegistryInfo> registryInfos = registry.fetchRegistry(config.getType());
            if (registryInfos != null) {
                interfacesMethodRegistryList.put(config.getType(), registryInfos);
                initChannel(registryInfos);
            }
        }
    }

    private void initChannel(List<RegistryInfo> registryInfos) throws Exception {
        for (RegistryInfo info : registryInfos) {
            if (!channels.containsKey(info)) {
                System.out.println("开始建立连接：" + info.getIp() + ", " + info.getPort());
                NettyClient client = new NettyClient(info.getIp(), info.getPort());
                client.setMessageCallback(new NettyClient.MessageCallback() {
                    @Override
                    public void onMessage(String message) {
                        // 这里收单服务端返回的信息，先压入队列
                        RpcResponse response = JSONObject.parseObject(message, RpcResponse.class);
                        response.offer(response);
                        synchronized (ApplicationContext.this) {
                            ApplicationContext.this.notifyAll();
                        }
                    }
                });

                // 等待连接建立
                ChannelHandlerContext ctx = client.getCtx();
                channels.put(info, ctx);
            }
        }
    }

    /**
     * 获取调用服务
     */
    public T getService(Class clazz) {
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String methodName = method.getName();
                        if ("equals".equals(methodName) || "hashCode".equals(methodName)) {
                            throw new IllegalAccessException("不能访问" + methodName + "方法");
                        }
                        if ("toString".equals(methodName)) {
                            return clazz.getName() + "#" + methodName;
                        }

                        // step 1: 获取服务地址列表
                        List<RegistryInfo> registryInfos = interfacesMethodRegistryList.get(clazz);
                        if (registryInfos == null) {
                            throw new RuntimeException("无法找到服务提供者");
                        }

                        // step 2： 负载均衡
                        RegistryInfo registryInfo  = loadBalancer.choose(registryInfos);

                        ChannelHandlerContext ctx = channels.get(registryInfo);
                        String identify = InvokeUtils.buildInterfaceMethodIdentify(clazz, method);
                        String requestId;
                        synchronized (ApplicationContext.this) {
                            requestIdWorker.increment();
                            requestId = String.valueOf(requestIdWorker.longValue());
                        }
                        Invoker invoker = new DefaultInvoker(method.getReturnType(), ctx, requestId, identify);
                        inProgressInvoker.put(identify + "#" + requestId, invoker);

                        return invoker.invoke(args);
                    }
                });
    }

}
