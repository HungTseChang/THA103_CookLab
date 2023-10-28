package com.cooklab.recipe;

import java.sql.Timestamp;

import com.cooklab.recipe.model.RecipeVO;

public class RecipeOverviewDTO {
	private Integer recipeNo;
	private String recipeName;
	private String introduction;
	private Byte recipeQuantity;
	private Timestamp createdTimestamp;

	public RecipeOverviewDTO() {
	}

	public RecipeOverviewDTO(RecipeVO recipeVO) {
		this.recipeNo = recipeVO.getRecipeNo();
		this.recipeName = recipeVO.getRecipeName();
		this.introduction = recipeVO.getIntroduction();
		this.recipeQuantity = recipeVO.getRecipeQuantity();
		this.createdTimestamp = recipeVO.getCreatedTimestamp();
	}

	public Integer getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(Integer recipeNo) {
		this.recipeNo = recipeNo;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Byte getRecipeQuantity() {
		return recipeQuantity;
	}

	public void setRecipeQuantity(Byte recipeQuantity) {
		this.recipeQuantity = recipeQuantity;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

}
