package com.cooklab.member_order.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MemberOrderVO implements Serializable{

	private Integer orderNo ;
	private Integer memberId ;
	private Byte orderStatus; 
	private Integer totalOrderAmount;
    private Integer checkoutAmount;
	private Integer promoCodeNo;
	private String shippingAddress ;
	private Timestamp createdTimestamp;
	
	
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}


	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}


	public MemberOrderVO() {

	}


	public Integer getOrderNo() {
		return orderNo;
	}


	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}


	public Integer getMemberId() {
		return memberId;
	}


	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}


	public Byte getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(Byte orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Integer getTotalOrderAmount() {
		return totalOrderAmount;
	}


	public void setTotalOrderAmount(Integer totalOrderAmount) {
		this.totalOrderAmount = totalOrderAmount;
	}


	public Integer getCheckoutAmount() {
		return checkoutAmount;
	}


	public void setCheckoutAmount(Integer checkoutAmount) {
		this.checkoutAmount = checkoutAmount;
	}




	public Integer getPromoCodeNo() {
		return promoCodeNo;
	}


	public void setPromoCodeNo(Integer promoCodeNo) {
		this.promoCodeNo = promoCodeNo;
	}


	public String getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	@Override
	public String toString() {
		return "MemberOrderVO [orderNo=" + orderNo + ", memberId=" + memberId + ", orderStatus=" + orderStatus
				+ ", totalOrderAmount=" + totalOrderAmount + ", checkoutAmount=" + checkoutAmount + ", promoCodeNo="
				+ promoCodeNo + ", shippingAddress=" + shippingAddress + ", createdTimestamp=" + createdTimestamp
				+ "]";
	}


	


	

	

	
	
}
