package com.example.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 类名：${className}
 * 创建人：林平之
 * 创建时间：2017/8/16 0016
 */
public class Reqv {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
//        factory.setHost("192.168.1.122");
//        factory.setPort(5672);
//        factory.setUsername("zqxq");
//        factory.setPassword("zqxq1866");
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("manage");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
//        channel.bas
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }
    }
}
