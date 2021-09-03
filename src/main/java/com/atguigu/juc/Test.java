package com.atguigu.juc;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Future;

/**
 * @Auther: LongYuan
 * @Date: 2021/9/2 0002 - 15:35
 * @Description: com.atguigu.juc
 * @version: 1.0
 */

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    MyTask myTask;

    @org.junit.Test
    public void  testThreadPool(){
        System.out.println("主线程"+Thread.currentThread().getName());
        try {
            myTask.doTaskOne();
            Future<String> future = myTask.doTaskTwo();
            System.out.println("返回值--"+future.get());
            myTask.doTaskThree();
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
