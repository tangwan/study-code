package com.tangwan.classpath;

import org.junit.Test;

import java.io.File;

/**
 * 测试获取工程路径和文件的磁盘绝对路径
 * @FileName:TestGetPath.java
 * @Description: TODO()  
 * @Author: tangwan
 * @Date: 2016年5月27日 上午10:47:04
 * @since:  JDK 1.8
 */
public class TestGetPath {
	@Test
	public void testGetPath1(){
		//获取class文件路径(/D:/ZGTX/WorkSpace/temp/jdk-api-test/target/test-classes/)
		String rootPath = TestGetPath.class.getClass().getResource("/").getFile().toString();
		System.out.println(rootPath);
		
		//获取class文件路径(D:\ZGTX\WorkSpace\temp\jdk-api-test\target\test-classes)
		File f = new File(TestGetPath.class.getClass().getResource("/").getPath());
		System.out.println(f);
		
		//取工程绝对路径
		System.out.println(System.getProperty("user.dir"));

		//取资源文件绝对路径(D:\ZGTX\WorkSpace\temp\jdk-api-test\test-rabbitmq.properties)
		File file = new File("test-rabbitmq.properties");
		System.out.println(file.getAbsolutePath());
		//取资源文件绝对路径(相对于工程路径)
		System.out.println(file.getPath());
	}
}
