package com.atguigu.protocol;

import com.atguigu.protocol.codec.ProtocolMsgDecoder;
import com.atguigu.protocol.codec.ProtocolMsgEncoder;
import com.atguigu.protocol.handler.ServerChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务器端
 */
public class TcpServer {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ProtocolMsgDecoder());
                            pipeline.addLast(new ProtocolMsgEncoder());
                            pipeline.addLast(new ServerChannelHandler());
                        }
                    });

            ChannelFuture channelFuture =
                    bootstrap.bind(9898).sync();
            System.out.println("服务器启动成功...");
            channelFuture.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}

