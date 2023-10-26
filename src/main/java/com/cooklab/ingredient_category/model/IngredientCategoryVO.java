package com.cooklab.ingredient_category.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cooklab.product.model.ProductVO;

@Entity
@Table(name = "ingredient_category")
public class IngredientCategoryVO implements java.io.Serializable {
	
	@OneToMany(mappedBy="ingredientCategory", cascade=CascadeType.ALL)
	private Set<ProductVO>product ;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ingredient_category_no", updatable = false, insertable = false)
	private Integer ingredientCategoryNo;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "created_timestamp", updatable = false, insertable = false)
	private Timestamp createdTimestamp;

	public IngredientCategoryVO() {
	}
	
	
	

	public Set<ProductVO> getProduct() {
		return product;
	}




	public void setProduct(Set<ProductVO> product) {
		this.product = product;
	}




	public IngredientCategoryVO(Integer ingredientCategoryNo, String categoryName, Timestamp createdTimestamp) {
		super();
		this.ingredientCategoryNo = ingredientCategoryNo;
		this.categoryName = categoryName;
		this.createdTimestamp = createdTimestamp;
	}

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

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "IngredientCategoryVO [ingredientCategoryNo=" + ingredientCategoryNo + ", categoryName=" + categoryName
				+ ", createdTimestamp=" + createdTimestamp + "]";
	}

}
