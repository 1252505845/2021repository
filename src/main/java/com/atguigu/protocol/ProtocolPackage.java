package com.atguigu.protocol;

/**
 * @Auther: LongYuan
 * @Date: 2021/8/4 0004 - 17:46
 * @Description: com.atguigu.protocol
 * @version: 1.0
 *
 * 自定义数据包
 */


public class ProtocolPackage {
    private int len;
    private byte[] content;

    public ProtocolPackage() {
    }

    public ProtocolPackage(int len, byte[] content) {
        this.len = len;
        this.content = content;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}


