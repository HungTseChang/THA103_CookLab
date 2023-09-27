package com.cooklab.kitchenware_category.model;

import java.sql.Date;

public class KitchenwareCategoryVO implements java.io.Serializable {
	private Integer kitchenwareCategoryNo;
	private String categoryName;

	// 無參數建構子
	public KitchenwareCategoryVO() {
	}

	// 有參數的建構子(方法覆載)
	public KitchenwareCategoryVO(Integer kitchenwareCategoryNo, String categoryName) {
		this.kitchenwareCategoryNo = kitchenwareCategoryNo;
		this.categoryName = categoryName;
	}

	// getter與setter方法
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

}
