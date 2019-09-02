package com.lzp.hth.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试类
 *
 * @Author LiZePing
 * @date2019/9/2 17:13
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
    }

    /**
     *  测试生产者的代码
     *
     *  ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("producer.xml");
     *
     *         ProduceService produceService = applicationContext.getBean(ProduceService.class);
     *
     *         //进行消息发送
     *         for (int i = 1; i < 11; i++) {
     *             produceService.sendMessage("发送第"+ i +"条信息");
     *         }
     *         //当消息发送完毕，关闭容器
     *         applicationContext.close();
     *
     *
     *
     */
}
