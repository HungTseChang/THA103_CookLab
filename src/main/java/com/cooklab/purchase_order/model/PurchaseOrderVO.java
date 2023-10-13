package com.cooklab.purchase_order.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.cooklab.purchase_order_detail.model.PurchaseOrderDetailVO;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrderVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchase_order_no", updatable = false, insertable = false)
	private Integer purchaseOrderNo;

	@Column(name = "purchase_order_date")
	private Date purchaseOrderDate;

	@Column(name = "purchase_order_supplier")
	private String purchaseOrderSupplier;

	@Column(name = "purchase_order_total")
	private Integer purchaseOrderTotal;

	@Column(name = "created_timestamp", updatable = false, insertable = false)
	private Timestamp createdTimestamp;

	@OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
	@OrderBy("id asc")
	private Set<PurchaseOrderDetailVO> purchaseOrderDetail;

	public PurchaseOrderVO() {
	}

	public Set<PurchaseOrderDetailVO> getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}

	public void setPurchaseOrderDetail(Set<PurchaseOrderDetailVO> purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
	}

	public PurchaseOrderVO(Integer purchaseOrderNo, Date purchaseOrderDate, String purchaseOrderSupplier,
			Integer purchaseOrderTotal, Timestamp createdTimestamp) {
		super();
		this.purchaseOrderNo = purchaseOrderNo;
		this.purchaseOrderDate = purchaseOrderDate;
		this.purchaseOrderSupplier = purchaseOrderSupplier;
		this.purchaseOrderTotal = purchaseOrderTotal;
		this.createdTimestamp = createdTimestamp;
	}

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

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "PurchaseOrderVO [purchaseOrderNo=" + purchaseOrderNo + ", purchaseOrderDate=" + purchaseOrderDate
				+ ", purchaseOrderSupplier=" + purchaseOrderSupplier + ", purchaseOrderTotal=" + purchaseOrderTotal
				+ ", createdTimestamp=" + createdTimestamp + "]";
	}

}
