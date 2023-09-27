package com.cooklab.order_detail.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetailVO implements Serializable{

	@Id
	@Column(name = "order_detail_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderDetailNo ;
	
	@Column(name = "order_no")
	private Integer orderNo ;
	
	@Column(name = "product_no")
	private Integer productNo;
	
	@Column(name = "order_qty")
	private Integer orderQty;
	
	@Column(name = "created_timestamp ", insertable = false, updatable = false)
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
