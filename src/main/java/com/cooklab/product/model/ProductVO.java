package com.cooklab.product.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_no", updatable = false, insertable = false)
	private Integer productNo;
	
	@Column(name = "product_name")
	private String productName;
	
	private byte[]  productPicture;
	
	

	public byte[] getProductPicture() {
		return productPicture;
	}

	public void setProductPicture(byte[] productPicture) {
		this.productPicture = productPicture;
	}

	@Column(name = "sale_qty")
	private Integer saleQty;
	
	@Column(name = "product_dec")
	private String productDec;
	
	@Column(name = "product_introduction")
	private String productIntroduction;
	
	@Column(name = "product_price")
	private Integer productPrice;
	
	@Column(name = "offsale_time")
	private Timestamp offsaleTime;
	
	@Column(name = "shelf_time")
	private Timestamp shelfTime;
	
	@Column(name = "storage_qty")
	private Integer storageQty;
	
	@Column(name = "ingredient_category_no")
	private Integer ingredientCategoryNo;
	
	@Column(name = "kitchenware_category_no")
	private Integer kitchenwareCategoryNo;
	
	@Column(name = "search_count")
	private Integer searchCount;
	
	@Column(name = "created_timestamp", updatable = false, insertable = false)
	private Timestamp createdTimestamp;

	public ProductVO() {
	}

	public ProductVO(Integer productNo, String productName, Integer saleQty, String productDec,
			String productIntroduction, Integer productPrice, Timestamp offsaleTime, Timestamp shelfTime,
			Integer storageQty, Integer ingredientCategoryNo, Integer kitchenwareCategoryNo, Integer searchCount,
			Timestamp createdTimestamp) {
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

	public Integer getProductNo() {
		return productNo;
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

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "ProductVO [productNo=" + productNo + ", productName=" + productName + ", saleQty=" + saleQty
				+ ", productDec=" + productDec + ", productIntroduction=" + productIntroduction + ", productPrice="
				+ productPrice + ", offsaleTime=" + offsaleTime + ", shelfTime=" + shelfTime + ", storageQty="
				+ storageQty + ", ingredientCategoryNo=" + ingredientCategoryNo + ", kitchenwareCategoryNo="
				+ kitchenwareCategoryNo + ", searchCount=" + searchCount + ", createdTimestamp=" + createdTimestamp
				+ "]";
	}

}
