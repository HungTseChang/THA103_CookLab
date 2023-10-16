package com.cooklab.recipe_kitchenware.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cooklab.product.model.ProductVO;
import com.cooklab.recipe.model.RecipeVO;
@Entity
@Table(name = "recipe_kitchenware")
public class RecipeKitchenwareVO implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_kitchenware_no", updatable = false)
	private Integer recipeKitchenwareNo;	//食譜使用廚具編號(PK)
	@ManyToOne
	@JoinColumn(name = "recipe_no", referencedColumnName = "recipe_no")
	private RecipeVO recipe;				//食譜編號(FK)
	@ManyToOne
	@JoinColumn(name = "product_no", referencedColumnName = "product_no")
	private ProductVO product;				//商品編號(FK)
	@Column(name = "text_label")
	private String textLabel;				//純文字標籤
	@Column(name = "created_timestamp", insertable = false, updatable = false)
	private Timestamp createdTimestamp;		//建立時間

	public RecipeKitchenwareVO() {
	}

	public Integer getRecipeKitchenwareNo() {
		return recipeKitchenwareNo;
	}

	public void setRecipeKitchenwareNo(Integer recipeKitchenwareNo) {
		this.recipeKitchenwareNo = recipeKitchenwareNo;
	}

	public RecipeVO getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeVO recipe) {
		this.recipe = recipe;
	}

	public ProductVO getProduct() {
		return product;
	}

	public void setProduct(ProductVO product) {
		this.product = product;
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
		return "RecipeKitchenwareVO [recipeKitchenwareNo=" + recipeKitchenwareNo + ", recipe=" + recipe
				+ ", product=" + product + ", textLabel=" + textLabel + ", createdTimestamp=" + createdTimestamp
				+ "]";
	}

}
