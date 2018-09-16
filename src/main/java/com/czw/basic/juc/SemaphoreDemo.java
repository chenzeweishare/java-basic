package com.czw.basic.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 限流,  资源并发访问限制
 * @author Administrator
 *
 */
public class SemaphoreDemo {

	class SemaphoreRunnable implements Runnable {

		// 信号量
		private Semaphore semaphore;

		// 用户
		private int user;

		public SemaphoreRunnable(Semaphore semaphore, int user) {
			super();
			this.semaphore = semaphore;
			this.user = user;
		}

		@Override
		public void run() {

			try {
				// 获取许可
				semaphore.acquire();
				System.out.println("用户" + user + "进入窗口, 准备买票");
				Thread.sleep((long) (Math.random() * 1000));
				System.out.println("用户" + user + "买票完成, 即将离开");
				Thread.sleep((long) (Math.random() * 1000));
				System.out.println("用户" + user + "离开窗口");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
                //释放信号量
                semaphore.release();
            }
        }
	}

		private void execute() {
			// TODO Auto-generated method stub
			// 定义窗口个数
			final Semaphore semaphore = new Semaphore(1);

			// 用户线程池
			ExecutorService threadPool = Executors.newCachedThreadPool();

			for (int i = 0; i < 100; i++) {
				// 去买票
				threadPool.execute(new SemaphoreRunnable(semaphore, i + 1));
			}

			threadPool.shutdown();
		}


		public static void main(String[] args) {
			
			SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
			semaphoreDemo.execute();
	}
}
	
	
