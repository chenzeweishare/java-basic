package com.czw.basic.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

/**
 * @auth czw
 * @date 2019-03-30
 * @time 14:44
 */

@Component
public class AsyncServer {

    @Async
    public void sayHello() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello, czw");
    }


    @Async
    public Future<String> saySimpleFuture() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<String>("hello, czw");
    }

    @Async
    public CompletableFuture<String> sayFuture() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("hello, czw");
    }
}
