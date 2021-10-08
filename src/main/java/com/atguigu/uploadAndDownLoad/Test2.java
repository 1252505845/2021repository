package com.atguigu.uploadAndDownLoad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: LongYuan
 * @Date: 2021/10/2 0002 - 14:02
 * @Description: com.atguigu.uploadAndDownLoad
 * @version: 1.0
 */



public class Test2 {
    public static void main(String[] args) {
        List<Object> list = Arrays.asList("腾讯","百度","阿里巴巴");
        list.forEach(a->{
            System.out.println(a);
        });
    }
}
