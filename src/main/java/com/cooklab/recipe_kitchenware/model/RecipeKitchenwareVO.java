package com.cooklab.recipe_kitchenware.model;

import java.sql.Timestamp;

public class RecipeKitchenwareVO implements java.io.Serializable {
	private Integer recipeKitchenwareNo;
	private Integer recipeNo;
	private Integer productNo;
	private String textLabel;
	private Timestamp createdTimestamp;

	public RecipeKitchenwareVO() {
	}

	public RecipeKitchenwareVO(Integer recipeKitchenwareNo, Integer recipeNo, Integer productNo, String textLabel,
			Timestamp createdTimestamp) {
		super();
		this.recipeKitchenwareNo = recipeKitchenwareNo;
		this.recipeNo = recipeNo;
		this.productNo = productNo;
		this.textLabel = textLabel;
		this.createdTimestamp = createdTimestamp;
	}

	public Integer getRecipeKitchenwareNo() {
		return recipeKitchenwareNo;
	}

	public void setRecipeKitchenwareNo(Integer recipeKitchenwareNo) {
		this.recipeKitchenwareNo = recipeKitchenwareNo;
	}

	public Integer getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(Integer recipeNo) {
		this.recipeNo = recipeNo;
	}

	public Integer getProductNo() {
		return productNo;
	}

	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}

	public String getTextLabel() {
		return textLabel;
	}

	public void setTextLabel(String textLabel) {
		this.textLabel = textLabel;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "RecipeKitchenwareVO [recipeKitchenwareNo=" + recipeKitchenwareNo + ", recipeNo=" + recipeNo
				+ ", productNo=" + productNo + ", textLabel=" + textLabel + ", createdTimestamp=" + createdTimestamp
				+ "]";
	}

}
