package com.tdt.wheel.rpc.test;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.ReferenceCountUtil;

/**
 * @author qrc
 * @description TODO
 * @date 2019/8/26
 */
public class NettyClient {

    private ChannelHandlerContext ctx;

    private MessageCallback messageCallback;

    public NettyClient(String ip, Integer port) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer("$$".getBytes());
                            channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024 * 1024, delimiter));
                            channel.pipeline().addLast(new StringDecoder());
                            channel.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            ChannelFuture sync = bootstrap.connect(ip, port).sync();
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void setMessageCallback(MessageCallback messageCallback) {
        this.messageCallback = messageCallback;
    }

    public ChannelHandlerContext getCtx() throws InterruptedException {
        System.out.println("等待连接成功...");
        if (ctx == null) {
            synchronized (this) {
                wait();
            }
        }
        return ctx;
    }

    private class NettyClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {
                String message = (String) msg;
                if (messageCallback != null) {
                    messageCallback.onMessage(message);
                }
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            NettyClient.this.ctx = ctx;
            System.out.println("连接成功：" + ctx);
            synchronized (NettyClient.this) {
                NettyClient.this.notifyAll();
            }
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
        }
    }


    public interface  MessageCallback {
        void onMessage(String message);
    }
}
