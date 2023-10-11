package com.cooklab.recipe_ingredient.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "recipe_ingredient")
public class RecipeIngredientVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_ingredient_no", updatable = false)
	private Integer recipeIngredientNo;
	@Column(name = "recipe_no")
	private Integer recipeNo;
	@Column(name = "product_no")
	private Integer productNo;
	@Column(name = "text_label")
	private String textLabel;
	@Column(name = "ingredient_quantity")
	private String ingredientQuantity;
	@Column(name = "created_timestamp", insertable = false, updatable = false)
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
