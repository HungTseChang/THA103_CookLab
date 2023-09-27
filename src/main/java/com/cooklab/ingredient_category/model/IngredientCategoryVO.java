package com.cooklab.ingredient_category.model;

public class IngredientCategoryVO implements java.io.Serializable {
	private Integer ingredientCategoryNo;
	private String categoryName;

	// 無參數建構子
	public IngredientCategoryVO() {
	}

	// 有參數的建構子(方法覆載)
	public IngredientCategoryVO(Integer ingredientCategoryNo, String categoryName) {
		super();
		this.ingredientCategoryNo = ingredientCategoryNo;
		this.categoryName = categoryName;
	}

	// getter與setter方法
	public Integer getIngredientCategoryNo() {
		return ingredientCategoryNo;
	}

	public void setIngredientCategoryNo(Integer ingredientCategoryNo) {
		this.ingredientCategoryNo = ingredientCategoryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
