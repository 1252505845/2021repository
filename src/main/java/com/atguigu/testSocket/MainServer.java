package com.atguigu.testSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @Auther: zhaoss
 * @Date: 2021/8/3 0003 - 08 - 03 - 11:23
 * @Description: com.atguigu.testSocket
 * @version: 1.0
 */

public class MainServer {

    /**
     * server port
     */
    private static int port = 8080;
    public static void main(String[] args) throws IOException {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);
            while (true) {
                Socket socket = ss.accept();
                while (true) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (true) {
                        String msg = reader.readLine();
                        if (msg != null && !"".equals(msg)) {
                            if (msg.contains(":")) {
                                System.out.println("请求参数: " + msg.substring(0, msg.indexOf(":")));
                                System.out.println("请求值: " + msg.substring(msg.indexOf(":")));
                            } else {
                                String[] httpInfos = msg.split(" ");
                                System.out.println(Arrays.toString(httpInfos));
                                System.out.println("请求方式： " + httpInfos[0]);
                                System.out.println("请求地址：" + httpInfos[1]);
                                System.out.println("http版本 ：" + httpInfos[2]);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ss.close();
        }
    }


}

