package com.czw.basic;

import com.czw.basic.async.AsyncClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicApplicationTests {


    @Autowired
    private AsyncClient client;

//    @Autowired
//    private Producer producer;
//
//    @Test
//    public void testMQ(){
//        for (int i = 0; i < 3 ; i++) {
//            producer.sendMessage("=================测试消息推送=============");
//        }
//        while(true){
//
//        }
//    }


    @Test
    public void testAsync(){
        try {
            client.testSimpleFuture();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
