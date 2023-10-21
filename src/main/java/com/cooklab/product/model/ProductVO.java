package com.cooklab.product.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cooklab.ingredient_category.model.IngredientCategoryVO;
import com.cooklab.kitchenware_category.model.KitchenwareCategoryVO;

@Entity
@Table(name = "product")
public class ProductVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_no", updatable = false, insertable = false)
	private Integer productNo;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_picture" ,columnDefinition = "longblob")
	private byte[]  productPicture;
	
	@Column(name = "sale_qty")
	private Integer saleQty;
	
	@Column(name = "product_dec",columnDefinition = "longtext")
	private String productDec;
	
	@Column(name = "product_introduction" ,columnDefinition = "longtext")
	private String productIntroduction;
	
	@Column(name = "product_price")
	private Integer productPrice;
	
	@Column(name = "offsale_time")
	private Timestamp offsaleTime;
	
	@Column(name = "shelf_time")
	private Timestamp shelfTime;
	
	@Column(name = "storage_qty")
	private Integer storageQty;
	
	@ManyToOne
	@JoinColumn(name="ingredient_category_no",referencedColumnName="ingredient_category_no", insertable = false ,updatable = false)
	private IngredientCategoryVO ingredientCategory;
	
	
	@Column(name = "ingredient_category_no")
	private Integer ingredientCategoryNo;
	
	@ManyToOne
	@JoinColumn(name="kitchenware_category_no",referencedColumnName="kitchenware_category_no", insertable = false ,updatable = false)
	private KitchenwareCategoryVO KitchenwareCategory;
	
	
	@Column(name = "kitchenware_category_no")
	private Integer kitchenwareCategoryNo;
	
	@Column(name = "search_count")
	private Integer searchCount;
	
	@Column(name = "created_timestamp", updatable = false, insertable = false)
	private Timestamp createdTimestamp;

	
	
	
	public IngredientCategoryVO getIngredientCategory() {
		return ingredientCategory;
	}



	public void setIngredientCategory(IngredientCategoryVO ingredientCategory) {
		this.ingredientCategory = ingredientCategory;
	}



	public KitchenwareCategoryVO getKitchenwareCategory() {
		return KitchenwareCategory;
	}



	public void setKitchenwareCategory(KitchenwareCategoryVO kitchenwareCategory) {
		KitchenwareCategory = kitchenwareCategory;
	}



	public ProductVO() {
	}

	

	public byte[] getProductPicture() {
		return productPicture;
	}

	public void setProductPicture(byte[] productPicture) {
		this.productPicture = productPicture;
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
