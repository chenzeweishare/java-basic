package com.czw.basic.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auth czw
 * @date 2019-03-30
 * @time 14:44
 */
@Component
public class AsyncClient {

    @Autowired
    private AsyncServer server;


    public void test(){
        long start = System.currentTimeMillis();
        System.out.println("beginTime " + start);
        server.sayHello();
        System.out.println("useTime " + (System.currentTimeMillis() - start));
    }


    //开启3个任务+ @Async 8017
    //开启3个任务 不加@Async 12027
    //开启2个任务+ @Async 4015
    //开启2个任务 不加@Async 8015
    public void testFuture() throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("beginTime " + start);
        CompletableFuture<String> result1 = server.sayFuture();
        CompletableFuture<String> result2 = server.sayFuture();

        CompletableFuture.allOf(result1,result2).join();

        System.out.println(result1.get());
        System.out.println(result2.get());
        System.out.println("useTime " + (System.currentTimeMillis() - start));
    }

    /**
     * 不推荐使用, 具体原因待定
     * @return
     */
    public void testSimpleFuture() throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("beginTime " + start);
        Future<String> stringFuture = server.saySimpleFuture();
        System.out.println(stringFuture.get());
        System.out.println("useTime " + (System.currentTimeMillis() - start));
    }
}
