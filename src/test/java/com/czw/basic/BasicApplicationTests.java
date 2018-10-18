package com.czw.basic;

import com.czw.basic.mq.active.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    public void testActivemq(){
        for (int i = 0; i < 3 ; i++) {
            producer.sendMessage("=================测试消息推送=============");
        }
        while(true){

        }
    }

}
