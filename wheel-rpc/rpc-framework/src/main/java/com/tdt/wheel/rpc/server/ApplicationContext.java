package com.tdt.wheel.rpc.server;/**
 * Created by rc on 2019/8/25.
 */


import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qrc
 * @description rpc应用程序上下文
 * @date 2019/8/25
 */
public class ApplicationContext {
    /**
     * 注册中心
     */
    private Registry registry;
    
    List<ServiceConfig> serviceConfigs;

    private Map<String, Object> interfaceMethods = new ConcurrentHashMap<>();

    public ApplicationContext(String registryUrl, List<ServiceConfig> serviceConfigs) {
        // 1. 保存需要暴露的接口配置
        this.serviceConfigs = serviceConfigs;

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
            nettyServer = new NettyServer(this.serviceConfigs, interfaceMethods);
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
    }


}
