package com.czw.basic.semaphoreTips;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//包装类
public class SemaphoreWarpper {

	private static final Logger log = LoggerFactory.getLogger(SemaphoreWarpper.class.getName());

	//默认20个进程
	private volatile int queueCount = 20;

	public SemaphoreWarpper(int queueCount) {
		this.queueCount = queueCount;
		semaphore = new Semaphore(this.queueCount);
	}

	protected Semaphore semaphore;

	//重入读写锁
	protected ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	//原子0
	protected AtomicInteger blockingCount = new AtomicInteger(0);


	//执行, 线程安全为什么还要加锁
	public <T> T execute(Callable<T> r, String methodName, int timeout) throws Exception {
		T obj = null;
		//获取读取锁，除非当前线程被中断。
		lock.readLock().lockInterruptibly();
		try {
			blockingCount.addAndGet(1);
			// 尝试锁定
			if (semaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS)) {
				blockingCount.addAndGet(-1);
				try {
					obj = r.call();
				} finally {
					semaphore.release();
				}
			} else {
				blockingCount.addAndGet(-1);
				if (log.isInfoEnabled()) {
					log.info("queueCount:" + getQueueCount() + ",BlockingCount:" + blockingCount.get());
				}
				throw new SemaphoreGetException("获取信号量失败!");
			}
		} finally {
			lock.readLock().unlock();
		}
		return obj;
	}

	/**
	 * 重置信号数量, 并不是释放, 只是再次创建
	 */
	public void resetSemaphore(int count) {
		lock.writeLock().lock();
		try {
			this.queueCount = count;
			semaphore = new Semaphore(count);
		} catch (Exception e) {
			log.error("重置信号数量操作异常.", e);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public AtomicInteger getBlockingCount() {
		return blockingCount;
	}

	public int getQueueCount() {
		return queueCount;
	}

}
