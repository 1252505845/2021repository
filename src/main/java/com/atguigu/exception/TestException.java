package com.atguigu.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/15 0015 - 21:45
 * @Description: com.atguigu.exception
 * @version: 1.0
 */

@Slf4j
public class TestException {

    public static void main(String[] args) {
        try {
            a();
            c1();
        } catch (BusinessException e) {
            log.error("main errorCode:{}",e.errorCode);
            log.error("main errorMsg :{}",e.errorMsg);
        }
    }

    public static void a(){
        a1();
        b1();
    }


    public  static void  a1(){
        try {
            throw  new BusinessException();
        } catch (BusinessException e) {
            e.printStackTrace();
            throw  new BusinessException("a1","a1的catch");
        }
    }

    public static void  b1(){
        try {
            throw  new BusinessException();
        } catch (BusinessException e) {
            e.printStackTrace();
            throw  new BusinessException("b1","b1的catch");
        }
    }

    public static void  c1(){
        try {
            throw  new BusinessException();
        } catch (BusinessException e) {
            e.printStackTrace();
            throw  new BusinessException("c1","c1的catch");
        }
    }


}
