package com.tangwan.utils;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.util.StringUtils;

public class EventTemplate {
	private CachingConnectionFactory connectionFactory;
	private RabbitTemplate rabbitTemplate;
	private Bindings bindings;
	private MessageConverter serializerMessageConverter = new SerializerMessageConverter();

	public EventTemplate(EventConfig config) {
		if (config == null) {
			throw new IllegalArgumentException("Config can not be null.");
		}
		CachingConnectionFactory connectionFactory = ConnectionHelper.initConnectionFactory(config);
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
		this.bindings = new Bindings(rabbitAdmin);
		this.rabbitTemplate = new RabbitTemplate(connectionFactory);
		this.rabbitTemplate.setMessageConverter(serializerMessageConverter);
	}
	
	public EventTemplate(RabbitTemplate rabbitTemplate,
			Bindings bindings) {
		this.rabbitTemplate = rabbitTemplate;
		this.bindings = bindings;
	}
	
	public synchronized void destroy() throws Exception {
		if(this.connectionFactory != null){
			this.connectionFactory.destroy();
		}
		this.bindings.destroy();
	}

	public void send(String exchangeName, String queueName, Object eventContent) throws Throwable {
		if (StringUtils.isEmpty(queueName) || StringUtils.isEmpty(exchangeName) || eventContent == null) {
			throw new IllegalArgumentException(
					"queueName or exchangeName can not be empty, or eventContent can not be null.");
		}
		if (!bindings.beBinded(exchangeName, queueName)){
			bindings.declareBinding(exchangeName, queueName);
		}
		try {
			rabbitTemplate.convertAndSend(exchangeName, queueName, eventContent);
		} catch (AmqpException e) {
			throw e;
		}
	}
}
