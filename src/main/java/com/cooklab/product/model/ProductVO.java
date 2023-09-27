package com.cooklab.product.model;

import java.sql.Timestamp;
import java.util.Date;

public class ProductVO implements java.io.Serializable {
	private Integer productNo;
	private String productName;
	private Integer saleQty;
	private String productDec;
	private String productIntroduction;
	private Integer productPrice;
	private Timestamp offsaleTime;
	private Timestamp shelfTime;
	private Integer storageQty;
	private Integer ingredientCategoryNo;
	private Integer kitchenwareCategoryNo;
	private Integer searchCount;
	private Date createdTimestamp;
	// 無參數建構子
	public ProductVO() {
	}
	
	

	// 有參數的建構子(方法覆載)


	// getter與setter方法
	public Integer getProductNo() {
		return productNo;
	}

	public ProductVO(Integer productNo, String productName, Integer saleQty, String productDec,
			String productIntroduction, Integer productPrice, Timestamp offsaleTime, Timestamp shelfTime,
			Integer storageQty, Integer ingredientCategoryNo, Integer kitchenwareCategoryNo, Integer searchCount,
			Date createdTimestamp) {
		super();
		this.productNo = productNo;
		this.productName = productName;
		this.saleQty = saleQty;
		this.productDec = productDec;
		this.productIntroduction = productIntroduction;
		this.productPrice = productPrice;
		this.offsaleTime = offsaleTime;
		this.shelfTime = shelfTime;
		this.storageQty = storageQty;
		this.ingredientCategoryNo = ingredientCategoryNo;
		this.kitchenwareCategoryNo = kitchenwareCategoryNo;
		this.searchCount = searchCount;
		this.createdTimestamp = createdTimestamp;
	}



	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getSaleQty() {
		return saleQty;
	}

	public void setSaleQty(Integer saleQty) {
		this.saleQty = saleQty;
	}

	public String getProductDec() {
		return productDec;
	}

	public void setProductDec(String productDec) {
		this.productDec = productDec;
	}

	public String getproductIntroduction() {
		return productIntroduction;
	}

	public void setproductIntroduction(String productIntroduction) {
		this.productIntroduction = productIntroduction;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Timestamp getOffsaleTime() {
		return offsaleTime;
	}

	public void setOffsaleTime(Timestamp offsaleTime) {
		this.offsaleTime = offsaleTime;
	}

	public Timestamp getShelfTime() {
		return shelfTime;
	}

	public void setShelfTime(Timestamp shelfTime) {
		this.shelfTime = shelfTime;
	}

	public Integer getStorageQty() {
		return storageQty;
	}

	public void setStorageQty(Integer storageQty) {
		this.storageQty = storageQty;
	}

	public Integer getIngredientCategoryNo() {
		return ingredientCategoryNo;
	}

	public void setIngredientCategoryNo(Integer ingredientCategoryNo) {
		this.ingredientCategoryNo = ingredientCategoryNo;
	}

	public Integer getKitchenwareCategoryNo() {
		return kitchenwareCategoryNo;
	}

	public void setKitchenwareCategoryNo(Integer kitchenwareCategoryNo) {
		this.kitchenwareCategoryNo = kitchenwareCategoryNo;
	}

	public Integer getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(Integer searchCount) {
		this.searchCount = searchCount;
	}

	public String getProductIntroduction() {
		return productIntroduction;
	}

	public void setProductIntroduction(String productIntroduction) {
		this.productIntroduction = productIntroduction;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	
}
