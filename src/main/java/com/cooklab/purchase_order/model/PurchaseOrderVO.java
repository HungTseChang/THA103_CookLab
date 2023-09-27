package com.cooklab.purchase_order.model;

import java.sql.Date;

public class PurchaseOrderVO implements java.io.Serializable {
	private Integer purchaseOrderNo;
	private Date purchaseOrderDate;
	private String purchaseOrderSupplier;
	private Integer purchaseOrderTotal;

	// 無參數建構子
	public PurchaseOrderVO() {
	}

	// 有參數的建構子(方法覆載)
	public PurchaseOrderVO(Integer purchaseOrderNo, Date purchaseOrderDate, String purchaseOrderSupplier,
			Integer purchaseOrderTotal) {
		super();
		this.purchaseOrderNo = purchaseOrderNo;
		this.purchaseOrderDate = purchaseOrderDate;
		this.purchaseOrderSupplier = purchaseOrderSupplier;
		this.purchaseOrderTotal = purchaseOrderTotal;
	}

	// getter與setter方法
	public Integer getPurchaseOrderNo() {
		return purchaseOrderNo;
	}

	public void setPurchaseOrderNo(Integer purchaseOrderNo) {
		this.purchaseOrderNo = purchaseOrderNo;
	}

	public Date getPurchaseOrderDate() {
		return purchaseOrderDate;
	}

	public void setPurchaseOrderDate(Date purchaseOrderDate) {
		this.purchaseOrderDate = purchaseOrderDate;
	}

	public String getPurchaseOrderSupplier() {
		return purchaseOrderSupplier;
	}

	public void setPurchaseOrderSupplier(String purchaseOrderSupplier) {
		this.purchaseOrderSupplier = purchaseOrderSupplier;
	}

	public Integer getPurchaseOrderTotal() {
		return purchaseOrderTotal;
	}

	public void setPurchaseOrderTotal(Integer purchaseOrderTotal) {
		this.purchaseOrderTotal = purchaseOrderTotal;
	}

}
