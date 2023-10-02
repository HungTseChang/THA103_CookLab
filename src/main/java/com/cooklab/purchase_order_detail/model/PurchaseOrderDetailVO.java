package com.cooklab.purchase_order_detail.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchase_order_detail")
public class PurchaseOrderDetailVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_no", updatable = false, insertable = false)
	private Integer orderDetailNo;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_qty")
	private Integer productQty;

	@Column(name = "expired_date")
	private Date expiredDate;

	@Column(name = "purchase_order_no")
	private Integer purchaseOrderNo;

	@Column(name = "product_no")
	private Integer productNo;

	@Column(name = "purchase_order_price")
	private Integer purchaseOrderPrice;

	@Column(name = "created_timestamp", updatable = false, insertable = false)
	private Timestamp createdTimestamp;

	public PurchaseOrderDetailVO() {
	}

	public PurchaseOrderDetailVO(Integer orderDetailNo, String productName, Integer productQty, Date expiredDate,
			Integer purchaseOrderNo, Integer productNo, Integer purchaseOrderPrice, Timestamp createdTimestamp) {
		super();
		this.orderDetailNo = orderDetailNo;
		this.productName = productName;
		this.productQty = productQty;
		this.expiredDate = expiredDate;
		this.purchaseOrderNo = purchaseOrderNo;
		this.productNo = productNo;
		this.purchaseOrderPrice = purchaseOrderPrice;
		this.createdTimestamp = createdTimestamp;
	}

	public Integer getOrderDetailNo() {
		return orderDetailNo;
	}

	public void setOrderDetailNo(Integer orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
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

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "PurchaseOrderDetailVO [orderDetailNo=" + orderDetailNo + ", productName=" + productName
				+ ", productQty=" + productQty + ", expiredDate=" + expiredDate + ", purchaseOrderNo=" + purchaseOrderNo
				+ ", productNo=" + productNo + ", purchaseOrderPrice=" + purchaseOrderPrice + ", createdTimestamp="
				+ createdTimestamp + "]";
	}

}
