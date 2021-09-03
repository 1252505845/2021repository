package com.atguigu.juc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/2 0002 - 15:31
 * @Description: com.atguigu.juc
 * @version: 1.0
 */

@Slf4j
@Component
public class MyTask {

    public static Random random = new Random();

    /**
     * 无返回值的调用
     * @throws Exception
     */
    @Async("taskExecutor")
    public void doTaskOne() throws Exception {
        log.info(Thread.currentThread().getName()+"开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info(Thread.currentThread().getName()+"完成任务一，耗时：" + (end - start) + "毫秒");
    }

    @Async("taskExecutor")
    public Future<String> doTaskTwo() throws Exception {
        log.info(Thread.currentThread().getName()+"开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info(Thread.currentThread().getName()+"完成任务二，耗时：" + (end - start) + "毫秒");

        return  new AsyncResult<>("uusysy");
    }

    @Async("taskExecutor")
    public void doTaskThree() throws Exception {
        log.info(Thread.currentThread().getName()+"开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info(Thread.currentThread().getName()+"完成任务三，耗时：" + (end - start) + "毫秒");
    }
}
