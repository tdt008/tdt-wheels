package com.tdt.wheel.rpc.server;/**
 * Created by rc on 2019/8/25.
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;
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
        super.channelRead(ctx, msg);
    }
}
