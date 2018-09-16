package com.czw.basic.juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 两个线程交换数据
 * @author Administrator
 *
 */
public class ExchangerDemo {
	
	public static void main(String[] args) {

	//交换器  
	Exchanger<String> ec = new Exchanger<String>();
		
	//线程池
	ExecutorService threadPool = Executors.newCachedThreadPool();
	
	
	threadPool.execute(new Runnable() {
		
		public void run() {
			try {
				String	exchange = ec.exchange("小乔");
				System.out.println("绑架者用小乔交互回:" + exchange);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	});
	
	
	
	threadPool.execute(new Runnable() {
			
			public void run() {
				try {
					String	exchange = ec.exchange("1000");
					System.out.println("大乔用1000交互回:" + exchange);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	});
	
	threadPool.shutdown();
	
	}

}
