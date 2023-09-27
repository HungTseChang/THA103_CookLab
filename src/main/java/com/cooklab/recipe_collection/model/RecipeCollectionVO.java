package com.cooklab.recipe_collection.model;

import java.sql.Timestamp;

public class RecipeCollectionVO implements java.io.Serializable {
	private Integer collectionNo;
	private Integer recipeNo;
	private Integer memberId;
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
