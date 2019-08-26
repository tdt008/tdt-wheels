package com.tdt.wheel.rpc.test;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author qrc
 * @description TODO
 * @date 2019/8/26
 */
public class NettyClient {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new StringDecoder());
                            channel.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            ChannelFuture sync = bootstrap.connect("127.0.0.1", 50071).sync();
            sync.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }


    private static class NettyClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("interfaces",
                    "interface=com.tdt.wheel.rpc.test.HelloService&method=sayHello&parameter=com.tdt.wheel.rpc.test.TestBean");
            JSONObject param = new JSONObject();
            JSONObject bean = new JSONObject();
            bean.put("age", 20);
            bean.put("name", "张三");
            param.put("com.tdt.wheel.rpc.test.TestBean", bean);
            jsonObject.put("parameter", param);
            jsonObject.put("requestId", 3);
            System.out.println("发送给服务端JSON为：" + jsonObject.toJSONString());
            String msg = jsonObject.toJSONString() + "$$";
            ByteBuf byteBuf = Unpooled.buffer(msg.getBytes().length);
            byteBuf.writeBytes(msg.getBytes());
            ctx.writeAndFlush(byteBuf);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("收到消息:" + msg);
        }
    }
}
