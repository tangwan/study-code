package com.tangwan.radix;

import org.junit.Test;

/**
 * 进制转换,10进制Long到任意进制
 * @FileName:RadixConvert.java
 * @Author: tangwan
 * @Date: 2016年5月27日 上午10:46:53
 * @since: JDK 1.8
 */
public class RadixConvert {
	@Test
	public void test16Radix() {
		// 把十进制15转成16进制
		String str32 = Long.toString(15, 16).toUpperCase();
		System.out.println(str32);
	}

	@Test
	public void test32Radix() {
		// 把十进制150转成32进制
		String str32 = Long.toString(150, 32).toUpperCase();
		System.out.println(str32);
	}
}
