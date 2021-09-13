package com.atguigu.jaskson;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/9 0009 - 9:48
 * @Description: com.atguigu.jaskson
 * @version: 1.0
 */


public class Test {

//    RequestContextHolder.
        public static void main(String[] args) {

            String num="65";
            System.out.println( num.toUpperCase());
            System.out.println( num.toLowerCase());

            ServletRequestAttributes requestAttributes =
                    (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            if(requestAttributes!=null){
                HttpServletRequest request = requestAttributes.getRequest();
                System.out.println(request.getLocalAddr());
                System.out.println(request.getRemoteAddr());

            }
        }

//    ServletRequestAttributes
}
