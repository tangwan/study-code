package com.tangwan.utils;

public class EventConfig {
	private final static int DEFAULT_PORT = 5672;
	private final static String DEFAULT_USERNAME = "guest";
	private final static String DEFAULT_PASSWORD = "guest";
	private final static int DEFAULT_PROCESS_THREAD_NUM = Runtime.getRuntime().availableProcessors() * 2;
	private static final int PREFETCH_SIZE = 1;
	private String serverHost;
	private int port = DEFAULT_PORT;
	private String username = DEFAULT_USERNAME;
	private String password = DEFAULT_PASSWORD;
	private String virtualHost;
	private int connectionTimeout = 0;
	private int eventMessageConsumer;
	private int prefetchSize;
	
	public EventConfig(String serverHost) {
		this(serverHost, DEFAULT_PORT, DEFAULT_USERNAME, DEFAULT_PASSWORD, null, 0, DEFAULT_PROCESS_THREAD_NUM, DEFAULT_PROCESS_THREAD_NUM);
	}

	public EventConfig(String serverHost, int port, String username, String password, String virtualHost, int connectionTimeout, int eventMessageConsumer, int prefetchSize) {
		this.serverHost = serverHost;
		this.port = port > 0 ? port : DEFAULT_PORT;
		this.username = username;
		this.password = password;
		this.virtualHost = virtualHost;
		this.connectionTimeout = connectionTimeout > 0 ? connectionTimeout : 0;
		this.eventMessageConsumer = eventMessageConsumer > 0 ? eventMessageConsumer : DEFAULT_PROCESS_THREAD_NUM;
		this.prefetchSize = prefetchSize > 0 ? prefetchSize : PREFETCH_SIZE;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public void setEventMessageConsumer(int eventMessageConsumer) {
		this.eventMessageConsumer = eventMessageConsumer;
	}

	public void setPrefetchSize(int prefetchSize) {
		this.prefetchSize = prefetchSize;
	}

	public String getServerHost() {
		return serverHost;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVirtualHost() {
		return virtualHost;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public int getEventMessageConsumer() {
		return eventMessageConsumer;
	}

	public int getPrefetchSize() {
		return prefetchSize;
	}
}
