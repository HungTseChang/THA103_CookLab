package com.cooklab.order_detail.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderDetailVO implements Serializable{

	private Integer orderDetailNo ;
	private Integer orderNo ;
	private Integer productNo;
	private Integer orderQty;
	private Timestamp createdTimestamp;
	

	public Integer getOrderDetailNo() {
		return orderDetailNo;
	}



	public void setOrderDetailNo(Integer orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
	}



	public Integer getOrderNo() {
		return orderNo;
	}



	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}



	public Integer getProductNo() {
		return productNo;
	}



	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}



	public Integer getOrderQty() {
		return orderQty;
	}



	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}


	@Override
	public String toString() {
		return "OrderDetailVO [orderDetailNo=" + orderDetailNo + ", orderNo=" + orderNo + ", productNo=" + productNo
				+ ", orderQty=" + orderQty + ", createdTimestamp=" + createdTimestamp + "]";
	}



	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}



	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}



	public OrderDetailVO() {
		// TODO Auto-generated constructor stub
	}

}
