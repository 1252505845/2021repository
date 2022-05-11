package com.atguigu.testSocket.proxy;

import javax.net.SocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Auther: LongYuan
 * @Date: 2022/1/13 0013 - 10:57
 * @Description: com.atguigu.testSocket.proxy
 * @version: 1.0
 */


public class NetUtils {

    private static SocketFactory socketFactory=SocketFactory.getDefault();


    public static void main(String[] args) {
        Socket socket =null;
        try {
            socket=  socketFactory.createSocket("host",8000);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            //要发送的字节数组
            byte[] bytes = new byte[4587];
            out.write(bytes);
            out.flush();
            byte[] resBytes = new byte[45454];

            int pos=0;
            byte[]tmp=new byte[1024];
            int len;
            while((len =in.read(tmp))>0){
                System.arraycopy(tmp,0,resBytes,pos,len);
                pos+=len;
            }
//            返回的字节数组
            //resBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }

//        socket.setKeepAlive();
        
      

    }
}
