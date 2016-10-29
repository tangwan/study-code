package com.tangwan.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 向指定的MQ队列中发送消息
 * @Description: TODO(用一句话描述该文件做什么)
 * @Author: tangwan
 * @Date: 2015年10月26日
 * @since: JDK 1.8
 */
public class MQSender {
	private String host;
	private String username;
	private String password;
	private String exchangeName;
	private String queueName;
	private EventTemplate eventTemplate;

	public MQSender(String host, String username, String password, String exchangeName, String queueName) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.exchangeName = exchangeName;
		this.queueName = queueName;
		init();
	}

	public MQSender(String host, String username, String password) {
		this.host = host;
		this.username = username;
		this.password = password;
		init();
	}

	public void init() {
		EventConfig config = new EventConfig(host);
		config.setUsername(username);
		config.setPassword(password);
		eventTemplate = new EventTemplate(config);
	}

	public void send(Object o) {
		//把消息Object转成json
		String jsonString = JSONObject.toJSONString(o, SerializerFeature.WriteMapNullValue);
		try {
			eventTemplate.send(exchangeName, queueName, jsonString.getBytes("utf-8"));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
}
