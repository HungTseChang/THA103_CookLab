package com.cooklab.recipe_kitchenware.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "recipe_kitchenware")
public class RecipeKitchenwareVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_kitchenware_no", updatable = false)
	private Integer recipeKitchenwareNo;
	@Column(name = "recipe_no")
	private Integer recipeNo;
	@Column(name = "product_no")
	private Integer productNo;
	@Column(name = "text_label")
	private String textLabel;
	@Column(name = "created_timestamp", insertable = false, updatable = false)
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
