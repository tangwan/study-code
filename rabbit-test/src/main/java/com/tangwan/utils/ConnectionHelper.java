package com.tangwan.utils;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.util.StringUtils;

public class ConnectionHelper {
	public static CachingConnectionFactory initConnectionFactory(EventConfig config) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(config.getServerHost());
		connectionFactory.setChannelCacheSize(config.getEventMessageConsumer());
		connectionFactory.setPort(config.getPort());
		connectionFactory.setUsername(config.getUsername());
		connectionFactory.setPassword(config.getPassword());
		if (!StringUtils.isEmpty(config.getVirtualHost())) {
			connectionFactory.setVirtualHost(config.getVirtualHost());
		}
		return connectionFactory;
	}
}
