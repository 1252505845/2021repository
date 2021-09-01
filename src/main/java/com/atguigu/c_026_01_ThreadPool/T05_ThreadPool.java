/**
 * 线程池的概念
 */
package com.atguigu.c_026_01_ThreadPool;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T05_ThreadPool {
	public static void main(String[] args) throws InterruptedException {
		//固定大小的线程池
		ExecutorService service = Executors.newFixedThreadPool(10); //execute submit
		for (int i = 0; i <20 ; i++) {
			service.execute(() -> {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.println("当前工作线程"+Thread.currentThread().getName());
					send(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		System.out.println(service);
		
		service.shutdown();
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
	}

	public static  String send(String name){
		System.out.println("进入send方法"+name+"send---");
		int number = new Random().nextInt(10) + 1;
		String s = String.valueOf(number);

		return  s;
	}
}
