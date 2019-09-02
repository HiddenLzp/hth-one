package com.lzp.hth.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * 生产者的实现
 * @Author LiZePing
 * @date2019/9/2 16:56
 */
public class ProduceServiceImpl implements ProduceService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(name = "queue")
    private Destination destination;

    /**
     * 发送消息
     * @param msg
     */
    @Override
    public void sendMessage(final String msg) {

        jmsTemplate.send(destination , new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
        System.out.println("现在发送消息为：" + msg);
    }
}
