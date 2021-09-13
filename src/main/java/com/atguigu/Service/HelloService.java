package com.atguigu.Service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/10 0010 - 11:23
 * @Description: com.atguigu
 * @version: 1.0
 */


@Service
public class HelloService {

    @Async("taskExecutor")
    public Future<String>   hello(String message){
        String  as="service-hello";
        return new AsyncResult(as);
    }

}
