package com.tangwan.math;

import org.junit.Test;

public class MathTest {
	@Test
	public void testPow(){
		Double pow = Math.pow(2, 32);
		System.out.println(pow);//4.294967296E9
		long longValue = pow.longValue();
		System.out.println(longValue);//4294967296
		//位移(左移,右移)
		System.out.println(1L<<32);//1*2^32
		System.out.println(32L<<1);
		System.out.println(1<<1);//1*2^1
		System.out.println(4>>1);//4*2^-1=4*1/2
		
		System.out.println(-4>>1);//保留符号位右移
		System.out.println(-1>>>1);//无符号右移,补码右移一位,首位补0,变成正数
	}
}
