package com.czw.basic.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @auth czw
 * @date 2018-11-07
 * @time 10:58
 * 总结rate是在上一个任务开始时算时间  停顿2s    第一次11:00-11:02  第二次11:02-11:04
 * Delay是在上一个任务结束的时候开始算实际 停顿2s 第一次11:00-11:02 第二次11:04-11:06
 */
public class FixedRateAndFixedDelay {

    static Logger logger = LoggerFactory.getLogger(FixedRateAndFixedDelay.class);

    private static final AtomicLong l = new AtomicLong(0) ;

    private static final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(10);


    public static void main(String[] args) {

        /*
         * 使用scheduleAtFixedRate ， 该方法第三个参数表示在上一个个任务开始执行之后延迟
         * 多少秒之后再执行， 是从上一个任务开始时开始计算
         * 但是还是会等上一个任务执行完之后，下一个任务才开始执行，最后的结果，就是感觉延迟失去
         * 了作用
         *  */
        ScheduledFuture<?> sf1 = scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                long i =  l.getAndAdd(1) ;
                logger.info("start " + i);
                try {
                    TimeUnit.SECONDS.sleep(3) ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("end " + i);
            }
        }, 0, 3 , TimeUnit.SECONDS) ;

        /*
         * 使用<span style="color: rgb(54, 46, 43); line-height: 20px;">scheduleWithFixedDelay</span>， 该方法第三个参数表示在上一个个任务结束执行之后延迟
         * 多少秒之后再执行， 是从上一个任务结束时开始计算
         *  */
//        ScheduledFuture<?> sf2 = scheduler.scheduleWithFixedDelay(new Runnable() {
//            public void run() {
//                long i =  l.getAndAdd(1) ;
//                logger.info("start " + i);
//                try {
//                    TimeUnit.SECONDS.sleep(3) ;
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                logger.info("end " + i);
//            }
//        }, 0, 3, TimeUnit.SECONDS) ;



    }
}
