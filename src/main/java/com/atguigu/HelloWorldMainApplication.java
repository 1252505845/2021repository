package com.atguigu;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 *  @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 */
@SpringBootApplication
public class HelloWorldMainApplication {

    public static void main(String[] args) {
        InetAddress addr=null;
        try {
             addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("HostAddress---"+addr.getHostAddress());
        System.out.println("HostName---"+addr.getHostName());
//        System.out.println("Local HostAddress:
//                "+addr.getHostAddress());
//                String hostname = addr.getHostName();
//        System.out.println("Local host name: "+hostname);
        // Spring应用启动起来
        SpringApplication.run(HelloWorldMainApplication.class,args);
    }
}
