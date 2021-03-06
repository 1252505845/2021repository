package com.atguigu.testTCP;


import org.aopalliance.intercept.Invocation;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * @Auther: zhaoss
 * @Date: 2021/8/3 0003 - 08 - 03 - 10:46
 * @Description: com.atguigu.testTCP
 * @version: 1.0
 * socket 服务端
 */

public class BasicSeverDemo {

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(8089);
            System.out.println("server start!");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        while (!Thread.currentThread().isInterrupted()) {
            Socket socket;
            BufferedInputStream in;
            BufferedOutputStream out;
            try {
                //阻塞等待连接请求
                socket = server.accept();
                System.out.println("建立连接：" + socket.getInetAddress());
                in = new BufferedInputStream(socket.getInputStream());
                out = new BufferedOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("连接建立失败！");
                continue;
            }
            byte[] result;
            try {
                //阻塞等待接收请求数据
                byte[] lengthByte = IOUtil.readBytesFromInputStream(in, 4);
                //本次请求的长度
                int length = ByteBuffer.wrap(lengthByte).getInt();
                System.out.println("from server:" + length);
                //读取指定长度字节
                result = IOUtil.readBytesFromInputStream(in, length);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            //反序列化对象
            Invocation obj = null;
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(result);
                ObjectInputStream ois = new ObjectInputStream(bis);
                obj = (Invocation) ois.readObject();
                ois.close();
                bis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            //System.out.println(obj.getInterfaceName() + ":" + obj.getMethodName());
        }
    }
}
