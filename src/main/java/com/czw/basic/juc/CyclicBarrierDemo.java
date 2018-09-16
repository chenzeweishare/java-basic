package com.czw.basic.juc;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 循环屏障
 * 等待其他线程一起到达执行某件事情(屏障), 而且可以循环
 * 真正意义上的模拟并发
 * @author Administrator
 *
 */
public class CyclicBarrierDemo {
	
	public static void main(String[] args) {
		
		// 3人聚餐
		CyclicBarrier cb = new CyclicBarrier(3);
		//执行重要事情前的 额外事情
//		CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println("一起拍个照片");
//			}
//		});
		
		
		// 用户线程池
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 3; i++) {
			
		   int user = i + 1;
		   
		   Runnable r =  new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//每个人来的时间不一样
				try {
					Thread.sleep((long) (Math.random() * 10000));
					System.out.println(user + "到达地点, 当前已经有" + (cb.getNumberWaiting() + 1 ) + "到达...." );
					//其他人没来
					cb.await();
					System.out.println("人员全部到了, 准备吃饭");
					Thread.sleep((long) (Math.random() * 10000));
					System.out.println(user + "各自回家");
 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		threadPool.execute(r);
		}
		threadPool.shutdown();
		
	}

}
