package com.tangwan.jedis;

import com.tangwan.jedisutils.RedisCacheUtil;
import com.tangwan.jedisutils.RedisCacheUtilImpl;
import org.junit.Test;

public class TestRedisCacheUtil {
	@Test
	public void testSet(){
		RedisCacheUtil jedis = new RedisCacheUtilImpl();
		jedis.set("tangwan", "tangwan");
		System.out.println(jedis.get("tangwan"));
	}
}
