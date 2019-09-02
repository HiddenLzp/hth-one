package com.lzp.hth.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *  用于消息的创建类
 * @Author LiZePing
 * @date2019/9/2 12:19
 */
public class JmsProducer {

    //定义activeMQ的连接地址
    private static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    //定义发送消息的队列名称
    private static final String QUEUE_NAME = "MyMessage";

    public static void main(String[] args) throws JMSException {
        //1. 创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //2. 创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //3. 打开连接
        connection.start();
        //4. 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5. 创建队列目标
        Destination destination = session.createQueue(QUEUE_NAME);
        //创建一个生产者
        MessageProducer producer = session.createProducer(destination);
        //6. 创建模拟100个消息
        for (int i = 1; i < 101; i++) {
            TextMessage textMessage = session.createTextMessage("我发送message: " + i);
            //发送消息
            producer.send(textMessage);
            System.out.println("我现在发的消息是: "+textMessage.getText());
        }
        //7. 关闭连接
        producer.close();
        session.close();
        connection.close();
    }
}
