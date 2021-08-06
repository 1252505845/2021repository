package com.atguigu.protocol.handler;


import com.atguigu.protocol.ProtocolPackage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


import java.util.UUID;

/**
 * @Auther: LongYuan
 * @Date: 2021/8/4 0004 - 17:44
 * @Description: com.atguigu.protocol.handler
 * @version: 1.0
 *
 * 服务器端handler
 */

public class ServerChannelHandler extends SimpleChannelInboundHandler<ProtocolPackage> {
    private int count = 0;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtocolPackage msg) throws Exception {
        System.out.println("服务器第"+(++count)+"次收到数据");
        System.out.println("服务器收到得数据长度："+msg.getLen());
        System.out.println("服务器收到得数据内容："+new String(msg.getContent(),CharsetUtil.UTF_8));

        writeToClient(ctx);
    }

    public void writeToClient(ChannelHandlerContext ctx){
        String info = UUID.randomUUID().toString();
        byte[] bytes = info.getBytes();
        int len = bytes.length;
        ProtocolPackage protocolPackage = new ProtocolPackage(len, bytes);
        ctx.writeAndFlush(protocolPackage);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }
}


