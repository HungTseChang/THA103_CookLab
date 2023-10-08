package com.cooklab.kitchenware_category.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kitchenware_category")
public class KitchenwareCategoryVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kitchenware_category_no", updatable = false, insertable = false)
	private Integer kitchenwareCategoryNo;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "created_timestamp", updatable = false, insertable = false)
	private Timestamp createdTimestamp;

	public KitchenwareCategoryVO() {
	}

	public KitchenwareCategoryVO(Integer kitchenwareCategoryNo, String categoryName, Timestamp createdTimestamp) {
		super();
		this.kitchenwareCategoryNo = kitchenwareCategoryNo;
		this.categoryName = categoryName;
		this.createdTimestamp = createdTimestamp;
	}

	public Integer getKitchenwareCategoryNo() {
		return kitchenwareCategoryNo;
	}

	public void setKitchenwareCategoryNo(Integer kitchenwareCategoryNo) {
		this.kitchenwareCategoryNo = kitchenwareCategoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "KitchenwareCategoryVO [kitchenwareCategoryNo=" + kitchenwareCategoryNo + ", categoryName="
				+ categoryName + ", createdTimestamp=" + createdTimestamp + "]";
	}

}
