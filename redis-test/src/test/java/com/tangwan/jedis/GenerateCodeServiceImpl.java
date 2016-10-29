package com.tangwan.jedis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ERP业务单号和流水号生成(生产环境);改进版
 * @FileName:GenerateCodeServiceImpl.java
 * @Description: TODO()  
 * @Author: tangwan
 * @Date: 2016年6月2日 上午11:36:31
 * @since:  JDK 1.8
 */
public class GenerateCodeServiceImpl {
	private Jedis jedis;

	@Before
	public void setup() {
		jedis = new Jedis("172.29.1.100", 6379);
		// 权限认证(不设置密码不需要)
		// jedis.auth("123456");
	}

	/**
	 * 测试切到redis来生成业务流水
	 * @MethodName：generateCode
	 * @Description: TODO()
	 * @Author: tangwan
	 * @Date：2016年3月20日下午1:51:30
	 * @param distributorId
	 * @param codeType
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public String generateCode(String distributorId, String codeType, int length) {
		// 流水Long型
		Long incr = jedis.hincrBy("ZGTX_ERP_SWIFT_CODE", codeType + distributorId, 1);
		// 把10进制数字转成32进制
		String str32 = Long.toString(incr, 32);
		// 编码不能包含I O U Z
		for (int i = 0; i < length; i++) {
			if (str32.contains("i") || str32.contains("o") || str32.contains("u") || str32.contains("z")) {
				str32 = jumpLenth("ZGTX_ERP_SWIFT_CODE",codeType + distributorId, str32);
			} else {
				break;
			}
		}
		// 判断流水号32进制压缩后长度,不够位的前面补0,流水位数多的返回"",表示今日流水用完,调用方需要为业务生成UUID来替代
		if (str32.length() <= length) {
			StringBuffer t = new StringBuffer();
			for (int i = 0; i < length - str32.length(); i++) {
				t.append("0");
			}
			t.append(str32);
			str32 = t.toString();
		} else {
			System.out.println("今日流水已用完");
			return "";
		}
		str32 = str32.toUpperCase();
		return str32;
	}

	/**
	 * 测试切到redis来生成业务编码
	 * @MethodName：generateBusinessCode
	 * @Description: TODO()
	 * @Author: tangwan
	 * @Date：2016年3月20日下午1:43:34
	 * @param distributorId
	 * @param codeType
	 * @param lapLength
	 * @return
	 * @throws Exception
	 */
	public String generateBusinessCode(String distributorId, String codeType, int lapLength) {
		// 获取日期8位值
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(new Date());
		// 如果存在今天的单号则,步长加1即可
		Long incr = jedis.hincrBy("ZGTX_ERP_BUSINESS_CODE_"+str, codeType + str + distributorId, 1);
		// 设置key 2天过期
		jedis.expire("ZGTX_ERP_BUSINESS_CODE_"+str, 60 * 60 * 48);
		// 把10进制数字转成32进制
		String str32 = Long.toString(incr, 32);
		// 编码不能包含I O U Z
		for (int i = 0; i < lapLength; i++) {
			if (str32.contains("i") || str32.contains("o") || str32.contains("u") || str32.contains("z")) {
				str32 = jumpLenth("ZGTX_ERP_BUSINESS_CODE_"+str,codeType + str + distributorId, str32);
			} else {
				break;
			}
		}
		// 判断流水号32进制压缩后长度,不够位的前面补0,位数多的返回"",表示今日流水用完,调用方需要为业务生成UUID来替代
		if (str32.length() <= lapLength) {
			StringBuffer t = new StringBuffer();
			for (int i = 0; i < lapLength - str32.length(); i++) {
				t.append("0");
			}
			t.append(str32);
			str32 = t.toString();
		} else {
			System.out.println("今日流水已用完");
			return "";
		}
		String businessCode = (codeType + str + distributorId + str32).toUpperCase();
		return businessCode;
	}

	/**
	 * 跳跃增加步长,跳过I O U Z字母 @Method：()
	 * @param redisKey
	 * @param str32
	 * @return
	 */
	private String jumpLenth(String mapKey,String redisKey, String str32) {
		Long incr = null;
		int size = str32.length();
		int indexOfI = str32.indexOf("i");
		if (indexOfI != -1) {
			long mi = ((Integer) (size - 1 - indexOfI)).longValue();
			incr = jedis.hincrBy(mapKey, redisKey, 1 << (5 * mi));
			str32 = Long.toString(incr, 32);
			size = str32.length();
		}
		int indexOfO = str32.indexOf("o");
		if (indexOfO != -1) {
			long mi = ((Integer) (size - 1 - indexOfO)).longValue();
			incr = jedis.hincrBy(mapKey, redisKey, 1 << (5 * mi));
			str32 = Long.toString(incr, 32);
			size = str32.length();
		}
		int indexOfU = str32.indexOf("u");
		if (indexOfU != -1) {
			long mi = ((Integer) (size - 1 - indexOfU)).longValue();
			incr = jedis.hincrBy(mapKey, redisKey, 1 << (5 * mi));
			str32 = Long.toString(incr, 32);
			size = str32.length();
		}
		int indexOfZ = str32.indexOf("z");
		if (indexOfZ != -1) {
			long mi = ((Integer) (size - 1 - indexOfZ)).longValue();
			incr = jedis.hincrBy(mapKey, redisKey, 1 << (5 * mi));
			str32 = Long.toString(incr, 32);
		}
		return str32;
	}

	@Test
	public void testGenerateBusinessCode() {
		// redisKey=ZGTX_ERP_BUSINESS_CODE_20160602 mapKey=SB20160602DEA480
		for (int i = 0; i < 100; i++) {
			String str = generateBusinessCode("DEA480", "SB", 4);
			System.out.println(str);
			if(str.contains("I") || str.contains("O") || str.contains("U") || str.contains("Z")){
				break;
			}
		}
	}

	@Test
	public void testGenerateCode() {
		// redisKey=ZGTX_ERP_SWIFT_CODE mapKey=SBDEA480
		for (int i = 0; i < 100; i++) {
			System.out.println(generateCode("DEA480", "SB", 4));
		}
	}
}
