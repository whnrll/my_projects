package com.example.mq.rabbit.producer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class ProducerDemo1 {
    private static final String EXCHANGE_DIRECT = "direct";
    private static final String EXCHANGE_FANOUT = "fanout";
    private static final String EXCHANGE_TOPIC = "topic";

    private static final String QUEUE_EMAIL = "queue_email";



    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();

        // 创建交换机
        exchangeDeclare(channel, "exchange_direct", "direct", true, false, false, null);

        // 创建队列
        queueDeclare(channel, "queue", false, true, false, null);

        // 交换机绑定队列
        queueBind(channel, "queue", "exchange_direct", "", null);

        // 发送消息
        String message = "你好，尊敬的用户，秒杀商品开抢了！";
        channel.basicPublish("exchange_direct","",null, message.getBytes(StandardCharsets.UTF_8));
        // 8.关闭资源
        channel.close();
        connection.close();
    }

    private static void sendMessageUseDirectExchange(Channel channel) {

    }

    private static MessageProperties messageProperties() {
        MessageProperties messageProperties = new MessageProperties();
        return messageProperties;
    }


    /**
     * 描述：创建交换机
     *
     * @param channel 通道
     * @param exchange 交换机名称
     * @param type 交换机类型 direct/fanout/topic/header
     * @param durable 交换机是否持久化
     * @param autoDelete 交换机是否自动删除
     * @param internal
     * @param arguments 其他参数
     * @throws IOException
     */
    private static void exchangeDeclare(Channel channel, String exchange, String type, boolean durable, boolean autoDelete, boolean internal, Map<String, Object> arguments) throws IOException {
        channel.exchangeDeclare(exchange, type,durable, autoDelete, internal, arguments);
    }

    /**
     * 描述：创建一个队列
     *
     * @param channel 通道
     * @param queue 队列名称
     * @param durable 队列里面的消息是否持久化。默认消息存储在内存中
     * @param exclusive 队列是否只允许多个消费者消费。
     * @param autoDelete 队列是否自动删除。最后一个消费者端开连接以后，该队列是否自动删除。
     * @param arguments 其他参数
     * @throws IOException
     */
    private static void queueDeclare(Channel channel, String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) throws IOException {
        channel.queueDeclare(queue,durable,exclusive,autoDelete,arguments);
    }

    /**
     * 描述：指定队列和交换机如何绑定
     *
     * @param channel 通道
     * @param queue 队列
     * @param exchange 交换机
     * @param bindingKey 绑定键
     * @param arguments
     * @throws IOException
     */
    private static void queueBind(Channel channel, String queue, String exchange, String bindingKey, Map<String, Object> arguments) throws IOException {
        channel.queueBind(queue, exchange, bindingKey, arguments);
    }

    private static Connection getConnection() throws IOException, TimeoutException {
        // 1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.0.162");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("itbaizhan");
        connectionFactory.setPassword("itbaizhan");
        connectionFactory.setVirtualHost("/");
        // 2.创建连接
        Connection connection = connectionFactory.newConnection();
        return connection;
    }


}
