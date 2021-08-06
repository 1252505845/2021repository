package com.atguigu.protocol;


import com.atguigu.protocol.codec.ProtocolMsgDecoder;
import com.atguigu.protocol.codec.ProtocolMsgEncoder;
import com.atguigu.protocol.handler.TcpClientChannelHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 *客户端
 */
public class TcpClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup executors = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(executors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ProtocolMsgEncoder());
                            pipeline.addLast(new ProtocolMsgDecoder());
                            pipeline.addLast(new TcpClientChannelHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9898)
                    .sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            executors.shutdownGracefully();
        }

    }
}





