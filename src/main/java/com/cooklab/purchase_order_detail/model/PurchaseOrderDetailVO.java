package com.cooklab.purchase_order_detail.model;

import java.sql.Date;

public class PurchaseOrderDetailVO {
	private Integer purchaseOrderDetailNo;
	private String productName;
	private Integer productQty;
	private Date expiredDate;
	private Integer purchaseOrderNo;
	private Integer productNo;
	private Integer purchaseOrderPrice;

	// 無參數建構子
	public PurchaseOrderDetailVO() {
	}

	public PurchaseOrderDetailVO(Integer purchaseOrderDetailNo, String productName, Integer productQty,
			Date expiredDate, Integer purchaseOrderNo, Integer productNo, Integer purchaseOrderPrice) {
		this.purchaseOrderDetailNo = purchaseOrderDetailNo;
		this.productName = productName;
		this.productQty = productQty;
		this.expiredDate = expiredDate;
		this.purchaseOrderNo = purchaseOrderNo;
		this.productNo = productNo;
		this.purchaseOrderPrice = purchaseOrderPrice;
	}

	public Integer getPurchaseOrderDetailNo() {
		return purchaseOrderDetailNo;
	}

	public void setPurchaseOrderDetailNo(Integer purchaseOrderDetailNo) {
		this.purchaseOrderDetailNo = purchaseOrderDetailNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductQty() {
		return productQty;
	}

	public void setProductQty(Integer productQty) {
		this.productQty = productQty;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Integer getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(Integer purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public Integer getProductNo() {
		return productNo;
	}

	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}

	public Integer getPurchaseOrderPrice() {
		return purchaseOrderPrice;
	}

	public void setPurchaseOrderPrice(Integer purchaseOrderPrice) {
		this.purchaseOrderPrice = purchaseOrderPrice;
	}

}
