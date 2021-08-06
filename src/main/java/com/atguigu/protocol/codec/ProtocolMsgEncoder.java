package com.atguigu.protocol.codec;

import com.atguigu.protocol.ProtocolPackage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * @Auther: LongYuan
 * @Date: 2021/8/4 0004 - 17:43
 * @Description: com.atguigu.protocol.codec
 * @version: 1.0
 * 解码器
 */
public class ProtocolMsgEncoder extends MessageToByteEncoder<ProtocolPackage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ProtocolPackage msg, ByteBuf out) throws Exception {
        System.out.println("解码器encode start---");
        out.writeInt(msg.getLen());
        out.writeBytes(msg.getContent());
        System.out.println("解码器encode end---");
    }
}


