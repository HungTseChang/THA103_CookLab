package com.cooklab.recipe_collection.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "recipe_collection")
public class RecipeCollectionVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "collection_no", updatable = false)
	private Integer collectionNo;
	@Column(name = "recipe_no")
	private Integer recipeNo;
	@Column(name = "member_id")
	private Integer memberId;
	@Column(name = "created_timestamp", insertable = false, updatable = false)
	private Timestamp createdTimestamp;

	public RecipeCollectionVO() {
	}

	public RecipeCollectionVO(Integer collectionNo, Integer recipeNo, Integer memberId, Timestamp createdTimestamp) {
		super();
		this.collectionNo = collectionNo;
		this.recipeNo = recipeNo;
		this.memberId = memberId;
		this.createdTimestamp = createdTimestamp;
	}

	public Integer getCollectionNo() {
		return collectionNo;
	}

	public void setCollectionNo(Integer collectionNo) {
		this.collectionNo = collectionNo;
	}

	public Integer getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(Integer recipeNo) {
		this.recipeNo = recipeNo;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "RecipeCollectionVO [collectionNo=" + collectionNo + ", recipeNo=" + recipeNo + ", memberId=" + memberId
				+ ", createdTimestamp=" + createdTimestamp + "]";
	}
}
