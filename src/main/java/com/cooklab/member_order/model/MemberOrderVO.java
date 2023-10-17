package com.cooklab.member_order.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cooklab.members.model.MembersVO;

@Entity
@Table(name = "member_order")
public class MemberOrderVO implements Serializable{

	@Id
	@Column(name = "order_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderNo ;
	
	@Column(name = "member_id")
	private Integer memberId ;
//	=============WCC================
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
	private MembersVO  members;
//	=============WCC================	
	@Column(name = "order_status", columnDefinition = "tinyint")
	private byte orderStatus;  
//	==============WCC=============================
	@Column(name = "total_order_amount")
	private Integer totalOrderAmount;
	
	@Column(name = "checkout_amount")
    private Integer checkoutAmount;
	
	@Column(name = "promo_code_no ")
	private Integer promoCodeNo;
	
	@Column(name = "shipping_address")
	private String shippingAddress ;
	
	@Column(name = "created_timestamp ", insertable = false, updatable = false)
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
