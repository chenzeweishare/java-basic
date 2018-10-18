package com.czw.basic.mq.active;

import javax.jms.Destination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private final static Logger logger = LoggerFactory.getLogger(Consumer.class);


    @Autowired
	private JmsTemplate jmsTemplate;


    /**
	 * 发送消息，destination是发送到的队列，message是待发送的消息
	 * @param destination
	 * @param message
	 */
	public void sendMessage(Destination destination, final String message) {
		System.out.println(jmsTemplate.getDeliveryMode());
		jmsTemplate.convertAndSend(destination, message);
	}
	/**
	 * 发送消息，message是待发送的消息
	 * @param message
	 */
	public void sendMessage(final String message) {
        logger.info("==============生产者推送消息=======" + message);
		System.out.println(jmsTemplate.getDeliveryMode());
		jmsTemplate.convertAndSend("queue1",message);
	}
 
}