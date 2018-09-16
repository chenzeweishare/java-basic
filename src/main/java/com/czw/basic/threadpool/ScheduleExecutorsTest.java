package com.czw.basic.threadpool;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorsTest {

    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService=Executors.newScheduledThreadPool(1);

        //等同
        ScheduledExecutorService scheduledExecutorService2= new ScheduledThreadPoolExecutor(1);

        //参数一, 要执行的任务
        //参数二, 延迟第一次执行任务的时间
        //参数三, 一个终止之间的延迟, 执行并开始下一个
        //参数四, 时间单位
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("悟空是只猴子1");
            }
        },0,5, TimeUnit.SECONDS);


        //参数三, 连续执行之间的时间
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("悟空是只猴子2");
                    throw  new RuntimeException();
                } catch (RuntimeException e) {
                    //e.printStackTrace();
                }

            }
        },4,5, TimeUnit.SECONDS);
    }
}
