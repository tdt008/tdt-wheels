package com.tdt.wheel.rpc.server;/**
 * Created by rc on 2019/8/25.
 */

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qrc
 * @description RpcInvokeHandler
 * @date 2019/8/25
 */
public class RpcInvokeHandler extends ChannelInboundHandlerAdapter {
    /**
     * 接口方法唯一标识对应的Method对象
     */
    private Map<String, Method> interfaceMethods;
    /**
     * 接口对应的实现类
     */
    private Map<Class, Object> interfaceToInstance;

    /**
     * 线程池，随意写的，不要吐槽
     */
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            10,
            50,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            new ThreadFactory() {
                AtomicInteger m = new AtomicInteger(0);

                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "IO-thread-" + m.incrementAndGet());
                }
            });

    public RpcInvokeHandler(List<ServiceConfig> serviceConfigs, Map<String, Method> interfaceMethods) {
        this.interfaceToInstance = new ConcurrentHashMap<>();
        this.interfaceMethods = interfaceMethods;
        for (ServiceConfig config : serviceConfigs) {
            interfaceToInstance.put(config.getType(), config.getInstance());
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            String message = (String) msg;
            // 这里拿到的是一串JSON数据，解析为Request对象，
            // 事实上这里解析网络数据，可以用序列化方式，定一个接口，可以实现JSON格式序列化，或者其他序列化
            // 但是demo版本就算了。
            System.out.println("接收到消息：" + msg);
            RpcRequest rpcRequest = RpcRequest.parse(message, ctx);
            threadPoolExecutor.execute(new RpcInvokeTask(rpcRequest));
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生了异常..." + cause);
        cause.printStackTrace();
        ctx.close();
    }

    public class RpcInvokeTask implements Runnable {

        private RpcRequest rpcRequest;

        public RpcInvokeTask(RpcRequest rpcRequest) {
            this.rpcRequest = rpcRequest;
        }

        @Override
        public void run() {
            try {
                /*
                 * 数据大概是这样子的
                 * {"interfaces":"interface=com.study.rpc.test.producer.HelloService&method=sayHello¶meter=com
                 * .study.rpc.test.producer.TestBean","requestId":"3","parameter":{"com.study.rpc.test.producer
                 * .TestBean":{"age":20,"name":"张三"}}}
                 */
                // 这里希望能拿到每一个服务对象的每一个接口的特定声明
                String interfaceIdentity = rpcRequest.getInterfaceIdentity();
                Method method = interfaceMethods.get(interfaceIdentity);

                Map<String, String> map = string2Map(interfaceIdentity);
                String interfaceName = map.get("interface");
                Class<?> interfaceClass = Class.forName(interfaceName);
                Object o = interfaceToInstance.get(interfaceClass);

                String parameterString = map.get("parameter");
                Object result;
                if (parameterString != null) {
                    String[] paramterTypeClass = parameterString.split(",");
                    Map<String, Object> parameterMap = rpcRequest.getParameterMap();
                    Object[] parameterInstance = new Object[paramterTypeClass.length];
                    for (int i = 0; i < paramterTypeClass.length; i++) {
                        String paramterTypeClazz = paramterTypeClass[i];
                        parameterInstance[i] = parameterMap.get(paramterTypeClazz);
                    }
                    result = method.invoke(o, parameterInstance);
                } else {
                    result = method.invoke(o);
                }

                // 写回响应
                ChannelHandlerContext ctx = rpcRequest.getCtx();
                String requestId = rpcRequest.getRequestId();
                RpcResponse response = RpcResponse.create(
                        JSONObject.toJSONString(result),
                        interfaceIdentity,
                        requestId);

                String s = JSONObject.toJSONString(response) + "$$";
                ByteBuf byteBuf = Unpooled.copiedBuffer(s.getBytes());
                ctx.writeAndFlush(byteBuf);
                System.out.println("响应给客户端：" + s);
            } catch (Exception e) {

            }
        }

        public Map<String, String> string2Map(String str) {
            String[] split = str.split("&");
            Map<String, String> map = new HashMap<>(16);
            for (String s : split) {
                String[] split1 = s.split("=");
                map.put(split1[0], split1[1]);
            }
            return map;
        }
    }


}
