package com.tangwan.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 测试从队列消费消息
 * @FileName:MQRecieveTest.java
 * @Description: TODO()  
 * @Author: tangwan
 * @Date: 2016年2月27日 上午10:43:46
 * @since:  JDK 1.8
 */
public class SellorderRecieveTest {
	//队列名
	private static final String TASK_QUEUE_NAME = "TEST_TEST_TEST";

	public static void recieve() throws Exception{

		ConnectionFactory factory = new ConnectionFactory();
		
		factory.setHost("172.29.1.103");
		//不填用户名密码,用默认的guest账户
		factory.setUsername("ec_odc");
		factory.setPassword("123456");
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// 指定队列持久化
		channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
		System.out.println("Waiting for messages. To exit press CTRL+C");

		// 指定该消费者同时只接收一条消息
		channel.basicQos(1);

		QueueingConsumer consumer = new QueueingConsumer(channel);

		// 打开消息应答机制
		channel.basicConsume(TASK_QUEUE_NAME, false, consumer);

		int count = 1;
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println("Received " + message +" 收取"+count+"次");
			count += 1;
			// 返回接收到消息的确认信息
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
	}
}