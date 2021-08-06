package com.atguigu.protocol.handler;






import com.atguigu.protocol.ProtocolPackage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


import java.nio.charset.Charset;

/**
 * @Auther: LongYuan
 * @Date: 2021/8/4 0004 - 17:45
 * @Description: com.atguigu.protocol.handler
 * @version: 1.0
 */
public class TcpClientChannelHandler extends SimpleChannelInboundHandler<ProtocolPackage> {
    private int cnt = 0;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        Channel channel = ctx.channel();
        for (int i = 0; i < 5; i++) {
            String info = "天气好热呀,想吃冰淇淋 ["+i+"]";
            byte[] bytes = info.getBytes();
            int len = bytes.length;
            ProtocolPackage pck = new ProtocolPackage(len, bytes);
            ctx.writeAndFlush(pck);
        }

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtocolPackage msg) throws Exception {
        System.out.println("客户端第"+(++cnt)+"次收到数据");
        System.out.println("客户端收到得数据长度为："+msg.getLen());
        System.out.println("客户端收到得数据信息为："+new String(msg.getContent(), Charset.forName("utf-8")));
    }
}


