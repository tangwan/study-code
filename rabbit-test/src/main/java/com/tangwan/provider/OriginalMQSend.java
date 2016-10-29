package com.tangwan.provider;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.util.concurrent.TimeoutException;

/**
 * 最原始的测试MQ发送消息到队列
 * @FileName:MQSendTest.java
 * @Description: TODO()  
 * @Author: tangwan
 * @Date: 2016年2月27日 上午10:43:28
 * @since:  JDK 1.8
 */
public class OriginalMQSend {
	//队列名
	private static final String TASK_QUEUE_NAME = "TEST_TEST_TEST";

	public static void send() throws java.io.IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("172.29.1.103");
		// 不填用户名密码,用默认的guest账户,默认guest只能本机连接
		factory.setUsername("ec_odc");
		factory.setPassword("123456");
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// 指定队列持久化(队列名,持久化,独有,自动删除,其他参数Map)
		channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

		String message = "Hello World! 测试MQ发送!!!";

		//发送1万条数据到队列
		long start = System.currentTimeMillis();
		for(int i = 0;i<1000;i++){
			channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
			//压力测试时关闭输出控制台
			System.out.println("Sent " + message + "  第"+i+"条");
		}
		long end = System.currentTimeMillis();
		System.out.println("共用时"+(end-start) +"ms");
		
		//释放资源
		channel.close();
		connection.close();
	}
}
