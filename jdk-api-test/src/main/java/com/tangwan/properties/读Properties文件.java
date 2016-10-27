package com.tangwan.properties;

import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class 读Properties文件 {
	public static void main(String[] args) {
		readProperties1();
		readProperties2();
	}
	
	//方法一:Properties类
	private static void readProperties1(){
		Properties property = new Properties();
		try {
			InputStream input = new 读Properties文件().getClass().getClassLoader().getResourceAsStream("test-rabbitmq.properties");
			property.load(input);
			System.out.println("获取test.rabbitmq.server.host的value:"+property.getProperty("test.rabbitmq.server.host"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//方法二:ResourceBundle类
	private static void readProperties2(){
		ResourceBundle conf = ResourceBundle.getBundle("test-rabbitmq");
		String SERVER_HOST = conf.getString("test.rabbitmq.server.host");
		System.out.println("获取test.rabbitmq.server.host的value:"+SERVER_HOST);
	}
}
