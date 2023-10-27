package com.cooklab.members;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.member_order.model.MemberOrderVO;

public class MemberOrderOverViewDTO {
	private Integer orderNo;
	private byte orderStatus;
	private Integer totalOrderAmount;
	private Integer checkoutAmount;
	private String shippingAddress;
	private Timestamp createdTimestamp;

	List<String> orderDetail = new ArrayList<>();
	List<String> product= new ArrayList<>();
	
	public MemberOrderOverViewDTO(MemberOrderVO memberOrderVO) {
		this.orderNo = memberOrderVO.getOrderNo();
		this.orderStatus = memberOrderVO.getOrderStatus();
		this.totalOrderAmount = memberOrderVO.getTotalOrderAmount();
		this.checkoutAmount = memberOrderVO.getCheckoutAmount();
		this.shippingAddress = memberOrderVO.getShippingAddress();
		this.createdTimestamp = memberOrderVO.getCreatedTimestamp();
		
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer order_no) {
		this.orderNo = order_no;
	}

	public byte getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(byte orderStatus) {
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

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<String> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<String> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public List<String> getProduct() {
		return product;
	}

	public void setProduct(List<String> product) {
		this.product = product;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	
}
