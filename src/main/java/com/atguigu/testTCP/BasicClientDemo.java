package com.atguigu.testTCP;

import org.aopalliance.intercept.Invocation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;

import static com.atguigu.testTCP.IOUtil.toByteArray;

/**
 * @Auther: zhaoss
 * @Date: 2021/8/3 0003 - 08 - 03 - 10:53
 * @Description: com.atguigu.testTCP
 * @version: 1.0
 */


public class BasicClientDemo {
//    public static void main(String[] args) {
//        Socket socket;
//        BufferedOutputStream out;
//        BufferedInputStream in;
//        try {
//            socket = new Socket("127.0.0.1", 80);
//            out = new BufferedOutputStream(socket.getOutputStream());
//            in = new BufferedInputStream(socket.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//        Object[] params = new Object[2];
//        Class[] paramTypes = new Class[2];
//        Invocation invocation = new Invocation(BasicClientDemo.class.getName(), "main", paramTypes, params);
//        byte[] invocationBytes = toByteArray(invocation);
//        int length = invocationBytes.length;
//        try {
//            System.out.println("from client:" + length);
//            out.write(ByteBuffer.allocate(4).putInt(length).array());
//            out.flush();
//            out.write(invocationBytes);
//            out.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                out.flush();
//                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
