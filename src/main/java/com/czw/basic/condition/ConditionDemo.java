package com.czw.basic.condition;/*
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　永无BUG 　┣┓
 * 　　　　┃　　如来保佑　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┗┻┛　┗┻┛
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/****
 * 生产消费模式
 */
public class ConditionDemo {
  static Lock lock = new ReentrantLock();
  static Condition full = lock.newCondition();
  static Condition empty = lock.newCondition();
  private static   int i=0;
  public static void main(String[] args) {
      new Thread(()->{
        try {
          ConditionDemo.producer();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }).start();
    new Thread(()->{
      try {
        ConditionDemo.consumer();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();


  }

  /***
   * 生产者
   * @throws InterruptedException
   */
  public static void producer() throws InterruptedException {
    lock.lock();
    try {
      for (;;) {
          i++;
        System.out.println("增加:"+i);
          if(i>=30) {
            System.out.println("等待中...");
            Thread.sleep(1000);
            full.await();
            empty.signal();
          }
      }
    } finally {
      lock.unlock();
    }
  }

  /***
   * 消费者
   * @throws InterruptedException
   */
  public  static void consumer() throws InterruptedException {
    lock.lock();
    try {

      for (;;){
        i--;
        System.out.println("减去:"+i);
        if (i<=0){
          System.out.println("唤醒中...");
          Thread.sleep(1000);
          full.signal();
          empty.await();
        }
      }

    } finally {
      lock.unlock();
    }
  }
}
