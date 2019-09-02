package com.lzp.hth.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.Message;

import javax.jms.*;
import java.io.IOException;

/**
 * 消息消费者
 * @Author LiZePing
 * @date2019/9/2 12:29
 */
public class JmsConsumer {

    //定义activeMQ的连接地址
    private static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    //定义发送消息的队列名称
    private static final String QUEUE_NAME = "MyMessage";

    public static void main(String[] args) throws JMSException, IOException {
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
        //创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);

        consumer.setMessageListener(new MessageListener() {

            public void onMessage(javax.jms.Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("获取消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });


//        consumer.setMessageListener((javax.jms.Message message) -> {
//            TextMessage textMessage = (TextMessage) message;
//            try {
//                System.out.println("获取消息：" + textMessage.getText());
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        });

        //7. 关闭连接
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }
}
