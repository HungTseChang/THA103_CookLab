package com.cooklab.recipe_ingredient.model;

import java.sql.Timestamp;

public class RecipeIngredientVO implements java.io.Serializable {
	private Integer recipeIngredientNo;
	private Integer recipeNo;
	private Integer productNo;
	private String textLabel;
	private String ingredientQuantity;
	private Timestamp createdTimestamp;
	
	
	public RecipeIngredientVO(){
	}
	
	public RecipeIngredientVO(Integer recipeIngredientNo, Integer recipeNo, Integer productNo, String textLabel,
			String ingredientQuantity, Timestamp createdTimestamp) {
		super();
		this.recipeIngredientNo = recipeIngredientNo;
		this.recipeNo = recipeNo;
		this.productNo = productNo;
		this.textLabel = textLabel;
		this.ingredientQuantity = ingredientQuantity;
		this.createdTimestamp = createdTimestamp;
	}

	public Integer getRecipeIngredientNo() {
		return recipeIngredientNo;
	}

	public void setRecipeIngredientNo(Integer recipeIngredientNo) {
		this.recipeIngredientNo = recipeIngredientNo;
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

	public String getIngredientQuantity() {
		return ingredientQuantity;
	}

	public void setIngredientQuantity(String ingredientQuantity) {
		this.ingredientQuantity = ingredientQuantity;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "RecipeIngredientVO [recipeIngredientNo=" + recipeIngredientNo + ", recipeNo=" + recipeNo
				+ ", productNo=" + productNo + ", textLabel=" + textLabel + ", ingredientQuantity=" + ingredientQuantity
				+ ", createdTimestamp=" + createdTimestamp + "]";
	}


}
