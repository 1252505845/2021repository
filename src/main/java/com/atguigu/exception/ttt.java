package com.atguigu.exception;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/16 0016 - 8:46
 * @Description: com.atguigu.exception
 * @version: 1.0
 */


public class ttt {

    public static void main(String[] args) {
        byte[] by=null;
        try {
            new String(by,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
