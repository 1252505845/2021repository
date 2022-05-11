package com.atguigu.testSocket.proxy.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: LongYuan
 * @Date: 2022/1/13 0013 - 11:00
 * @Description: com.atguigu.testSocket.proxy.config
 * @version: 1.0
 */

@Data
public class TcpTransportProperties {

    /**
     * key --响应码
     * value--接口（数组）
     */
    @Getter
    @Setter
    private Map<String,String[]> successCodeMap=new HashMap<>();
    /**
     * 主机
     */
    private String host;
    /**
     * 端口号
     */
    private String port;
    /**
     * socket 接受缓冲区大小
     */
    private int receiveBufferSize;
    /**
     * socket 发送缓冲区大小
     */
    private String sendBufferSize;
    /**
     * socket 连接超时时间
     */
    private int timeout;
    /**
     * socket关闭等待时间（秒）
     */
    private int linger;
    /**
     * 是否长链接
     */
    private boolean keepAlive;
    /**
     * 是否立即发送，
     */
    private boolean noDelay;
}
