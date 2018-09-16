package com.czw.basic.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 等待其他一起到达执行某件事情. 模拟的是假的并发
 * @author Administrator
 *
 */
public class CountDownLatchDemo {

	public static void main(String[] args) {
		
		//到时计时器
		CountDownLatch latch =	new  CountDownLatch(3);
	
		new Thread(){
			public void run() {
				try {
					System.out.println("任务一正在执行.......");
					Thread.sleep((long) (Math.random() * 1000));
					System.out.println("任务一执行完毕.......");
					latch.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread(){
			public void run() {
				try {
					System.out.println("任务二正在执行.......");
					Thread.sleep((long) (Math.random() * 1000));
					System.out.println("任务二执行完毕.......");
					latch.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
		
		new Thread(){
			public void run() {
				try {
					System.out.println("任务3正在执行.......");
					Thread.sleep((long) (Math.random() * 10000));
					System.out.println("任务3执行完毕.......");
					latch.countDown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
	
	System.out.println("主线程开始执行任务..." +  Thread.currentThread().getName());
	
	
	try {
		latch.await();
		System.out.println("==================================");
		System.out.println("主线程执行任务完毕..." +  Thread.currentThread().getName());
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}

}
