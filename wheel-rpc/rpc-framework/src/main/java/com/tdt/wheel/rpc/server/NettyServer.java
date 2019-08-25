package com.tdt.wheel.rpc.server;/**
 * Created by rc on 2019/8/25.
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author qrc
 * @description NettyServer
 * @date 2019/8/25
 */
public class NettyServer {
    /**
     * 负责调用方法的handler
     */
    private RpcInvokeHandler rpcInvokeHandler;

    public NettyServer() {

    }

    public int init(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ByteBuf delimiter = Unpooled.copiedBuffer("$$".getBytes());
                        // 设置按照分隔符“&&”来切分消息，单条消息限制为 1MB
                        // 设置按照分隔符“&&”来切分消息，单条消息限制为 1MB
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024 * 1024, delimiter));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast().addLast(rpcInvokeHandler);
                    }
                });
        ChannelFuture sync = serverBootstrap.bind(port).sync();
        System.out.println("启动NettyService，端口为：" + port);
        return port;
    }
}
