package com.atguigu.protocol.codec;




import com.atguigu.protocol.ProtocolPackage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;


import java.util.List;


/**
 * @Auther: LongYuan
 * @Date: 2021/8/4 0004 - 17:41
 * @Description: com.atguigu.protocol.codec
 * @version: 1.0
 *
 * 编码器
 */

public class ProtocolMsgDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("编码器decode  start--");
        int len = in.readInt();
        byte[] bytes = new byte[len];
        in.readBytes(bytes);
        ProtocolPackage pack = new ProtocolPackage(len, bytes);
        out.add(pack);
        System.out.println("编码器decode end---");
    }
}


