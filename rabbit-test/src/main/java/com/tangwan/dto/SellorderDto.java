package com.tangwan.dto;

import java.io.Serializable;

/**
 * 销售订单信息
 * @FileName:SellorderDto.java
 * @Description: TODO()
 * @Author: tangwan
 * @Date: 2016年5月26日 下午2:55:34
 * @since: JDK 1.8
 */
public class SellorderDto implements Serializable {
	private static final long serialVersionUID = -5614672454529869703L;
	private String orderId; // 交易订单号
	private Integer payMethod; // 支付方式（0-在线支付，1货到付款）
	private Integer cashMethod; // 货到付款付款方式（0,-POS,1-现金）
	private Double orderPrice; // 订单金额
	private Double discount; // 促销优惠金额
	private Double shipCost; // 交易订单运费
	private long orderCreateTime; // 订单提交时间
	private long paymentTime; // 订单支付时间
	private Integer invoiceFlag; // 是否开具发票
	private Double payableAmount; // 订单应付金额（实际付款金额）
	private Integer orderState; // 订单状态
	private Integer deliverType; // 送货类型
	private Integer orderSrc; // （订单来源：0 汽配城，1正品基地）
	private String garageId; // 修理厂ID
	private String garageName; // 修理厂名称
	private String garageSrvstationId; // 修理厂服务站ID
	private String garageSrvstationName; // 修理厂服务站名
	private String factoryId; // 厂家ID
	private String factoryName; // 厂家名称
	private String distributorId; // 经销商ID
	private String distributorName; // 经销商名称
	private String distributorSrvstationId; // 经销商服务站ID
	private String distributorSrvstationName; // 经销商服务站名
	private String storeId; // 店铺Id
	private Integer carrierType; // (承运商类型（0 诸葛物流、 1 第三方物流)
	private String comment; // 备注
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}
	public Integer getCashMethod() {
		return cashMethod;
	}
	public void setCashMethod(Integer cashMethod) {
		this.cashMethod = cashMethod;
	}
	public Double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getShipCost() {
		return shipCost;
	}
	public void setShipCost(Double shipCost) {
		this.shipCost = shipCost;
	}
	public long getOrderCreateTime() {
		return orderCreateTime;
	}
	public void setOrderCreateTime(long orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}
	public long getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(long paymentTime) {
		this.paymentTime = paymentTime;
	}
	public Integer getInvoiceFlag() {
		return invoiceFlag;
	}
	public void setInvoiceFlag(Integer invoiceFlag) {
		this.invoiceFlag = invoiceFlag;
	}
	public Double getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(Double payableAmount) {
		this.payableAmount = payableAmount;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public Integer getDeliverType() {
		return deliverType;
	}
	public void setDeliverType(Integer deliverType) {
		this.deliverType = deliverType;
	}
	public Integer getOrderSrc() {
		return orderSrc;
	}
	public void setOrderSrc(Integer orderSrc) {
		this.orderSrc = orderSrc;
	}
	public String getGarageId() {
		return garageId;
	}
	public void setGarageId(String garageId) {
		this.garageId = garageId;
	}
	public String getGarageName() {
		return garageName;
	}
	public void setGarageName(String garageName) {
		this.garageName = garageName;
	}
	public String getGarageSrvstationId() {
		return garageSrvstationId;
	}
	public void setGarageSrvstationId(String garageSrvstationId) {
		this.garageSrvstationId = garageSrvstationId;
	}
	public String getGarageSrvstationName() {
		return garageSrvstationName;
	}
	public void setGarageSrvstationName(String garageSrvstationName) {
		this.garageSrvstationName = garageSrvstationName;
	}
	public String getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getDistributorId() {
		return distributorId;
	}
	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
	}
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	public String getDistributorSrvstationId() {
		return distributorSrvstationId;
	}
	public void setDistributorSrvstationId(String distributorSrvstationId) {
		this.distributorSrvstationId = distributorSrvstationId;
	}
	public String getDistributorSrvstationName() {
		return distributorSrvstationName;
	}
	public void setDistributorSrvstationName(String distributorSrvstationName) {
		this.distributorSrvstationName = distributorSrvstationName;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public Integer getCarrierType() {
		return carrierType;
	}
	public void setCarrierType(Integer carrierType) {
		this.carrierType = carrierType;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "SellorderDto [orderId=" + orderId + ", payMethod=" + payMethod + ", cashMethod=" + cashMethod + ", orderPrice=" + orderPrice + ", discount=" + discount + ", shipCost=" + shipCost + ", orderCreateTime=" + orderCreateTime + ", paymentTime=" + paymentTime + ", invoiceFlag=" + invoiceFlag + ", payableAmount=" + payableAmount + ", orderState=" + orderState + ", deliverType=" + deliverType + ", orderSrc=" + orderSrc + ", garageId=" + garageId + ", garageName=" + garageName + ", garageSrvstationId=" + garageSrvstationId + ", garageSrvstationName=" + garageSrvstationName + ", factoryId=" + factoryId + ", factoryName=" + factoryName + ", distributorId=" + distributorId + ", distributorName=" + distributorName + ", distributorSrvstationId=" + distributorSrvstationId
				+ ", distributorSrvstationName=" + distributorSrvstationName + ", storeId=" + storeId + ", carrierType=" + carrierType + ", comment=" + comment + "]";
	}
}
