package com.tangwan.dto;

/**
 * 组装销售订单参数
 * @FileName:SellorderMessage.java
 * @Description: TODO()  
 * @Author: tangwan
 * @Date: 2016年5月26日 下午3:00:50
 * @since:  JDK 1.8
 */
public class SellorderMessage {
	public SellorderDto getSellorder(){
		SellorderDto sellorderDto = new SellorderDto();
		sellorderDto.setCarrierType(1);
		sellorderDto.setCashMethod(2);
		sellorderDto.setComment("销售订单");
		sellorderDto.setDeliverType(2);
		sellorderDto.setDiscount(0.0);
		sellorderDto.setDistributorId("DEA480");
		sellorderDto.setPaymentTime(System.currentTimeMillis());
		sellorderDto.setFactoryName("测试修理厂");
		return sellorderDto;
	}
}
