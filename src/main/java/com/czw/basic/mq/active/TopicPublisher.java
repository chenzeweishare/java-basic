package com.czw.basic.mq.active;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 生产者, 启动本地activeMq(bin\win64\activemq.bat)
 */
public class TopicPublisher {

    /**
     * 默认用户名
     */
    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    /**
     * 默认密码
     */
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    /**
     * 默认连接地址
     */
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        try {
            //创建连接
            Connection connection = connectionFactory.createConnection();
            //开启连接
            connection.start();
            //创建会话，不需要事务
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建 Topic，用作消费者订阅消息
            Topic myTestTopic = session.createTopic("activemq-topic-test1");
            //消息生产者
            MessageProducer producer = session.createProducer(myTestTopic);
            //默认重试次数是多少
            //ack的确认方式

            for (int i = 1; i <= 10; i++) {
                TextMessage message = session.createTextMessage("发送消息 " + i);
                producer.send(myTestTopic, message);
            }

            //关闭资源
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
