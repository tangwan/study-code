package com.tangwan.provider;

import com.tangwan.dto.SellorderDto;
import com.tangwan.utils.MQSender;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

public class SendSellorder {
	private static final ResourceBundle conf = ResourceBundle.getBundle("test-rabbitmq");
	private static final String SERVER_HOST = conf.getString("test.rabbitmq.server.host");
	private static final String SERVER_USERNAME = conf.getString("test.rabbitmq.server.username");
	private static final String SERVER_PASSWORD = conf.getString("test.rabbitmq.server.password");
	private static final String QUEUE_NAME = conf.getString("test.rabbitmq.server.sellorder.queue");
	private static final String EXCHANGE_NAME = conf.getString("test.rabbitmq.server.sellorder.exchange");
	private MQSender sender;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		sender = new MQSender(SendSellorder.SERVER_HOST, SendSellorder.SERVER_USERNAME, SendSellorder.SERVER_PASSWORD);
	}

	// 测试向队列中发送订单
	@Test
	public void sendSellorder() {
		sender.setExchangeName(SendSellorder.EXCHANGE_NAME);
		sender.setQueueName(SendSellorder.QUEUE_NAME);
		for (int i = 0; i < 100; i++) {
			SellorderDto sellorder = getSellorder();
			sender.send(sellorder);
			System.out.println("第" + i + "个销售订单:" + sellorder.toString());
		}
		System.out.println("sender ok!");
	}

	private SellorderDto getSellorder() {
		SellorderDto sellorderDto = new SellorderDto();
		sellorderDto.setCarrierType(1);
		sellorderDto.setCashMethod(2);
		sellorderDto.setComment("销售订单");
		sellorderDto.setDeliverType(2);
		sellorderDto.setDiscount(0.0);
		sellorderDto.setDistributorId("DEA480");
		sellorderDto.setPaymentTime(System.currentTimeMillis());
		sellorderDto.setFactoryName("测试修理厂");
		return sellorderDto;
	}
}
