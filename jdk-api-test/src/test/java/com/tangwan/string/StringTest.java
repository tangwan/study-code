package com.tangwan.string;


import org.junit.Test;

/**
 * 测试String类不常见和没用过的API
 * @FileName:StringTest.java
 * @Author: tangwan
 * @Date: 2016年6月19日 下午8:47:40
 * @since:  JDK 1.8
 */
public class StringTest {
	@Test
	public void TestStringFormate(){
		String name = "tangwan";
		Integer age = 22;
		System.out.println(String.format("姓名:%s  年龄:%s", name,age));
	}
}
