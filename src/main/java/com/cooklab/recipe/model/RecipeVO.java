package com.cooklab.recipe.model;

import java.sql.Timestamp;
import java.util.Arrays;

public class RecipeVO implements java.io.Serializable {
	private Integer recipeNo;
	private String recipeName;
	private Integer memberId;
	private byte[] coverImage;
	private String introduction;
	private String additionalExplanation;
	private String region;
	private Byte recipeStatus;
	private Integer reportCount;
	private Integer viewCount;
	private Byte recipeQuantity;
	private Timestamp lastEditTimestamp;
	private Timestamp createdTimestamp;

	public RecipeVO() {
	}

	public RecipeVO(Integer recipeNo,String recipeName, Integer memberId, byte[] coverImage, String introduction,
			String additionalExplanation, String region, Byte recipeStatus, Integer reportCount, Integer viewCount,
			Byte recipeQuantity, Timestamp lastEditTimestamp, Timestamp createdTimestamp) {
		super();
		this.recipeNo = recipeNo;
		this.recipeName = recipeName;
		this.memberId = memberId;
		this.coverImage = coverImage;
		this.introduction = introduction;
		this.additionalExplanation = additionalExplanation;
		this.region = region;
		this.recipeStatus = recipeStatus;
		this.reportCount = reportCount;
		this.viewCount = viewCount;
		this.recipeQuantity = recipeQuantity;
		this.lastEditTimestamp = lastEditTimestamp;
		this.createdTimestamp = createdTimestamp;
	}
	
	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
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

	public byte[] getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(byte[] coverImage) {
		this.coverImage = coverImage;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAdditionalExplanation() {
		return additionalExplanation;
	}

	public void setAdditionalExplanation(String additionalExplanation) {
		this.additionalExplanation = additionalExplanation;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Byte getRecipeStatus() {
		return recipeStatus;
	}

	public void setRecipeStatus(Byte recipeStatus) {
		this.recipeStatus = recipeStatus;
	}

	public Integer getReportCount() {
		return reportCount;
	}

	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Byte getRecipeQuantity() {
		return recipeQuantity;
	}

	public void setRecipeQuantity(Byte recipeQuantity) {
		this.recipeQuantity = recipeQuantity;
	}

	public Timestamp getLastEditTimestamp() {
		return lastEditTimestamp;
	}

	public void setLastEditTimestamp(Timestamp lastEditTimestamp) {
		this.lastEditTimestamp = lastEditTimestamp;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "RecipeVO [recipeNo=" + recipeNo + ", recipeName=" + recipeName + ", memberId=" + memberId
				+ ", coverImage=" + Arrays.toString(coverImage) + ", introduction=" + introduction
				+ ", additionalExplanation=" + additionalExplanation + ", region=" + region + ", recipeStatus="
				+ recipeStatus + ", reportCount=" + reportCount + ", viewCount=" + viewCount + ", recipeQuantity="
				+ recipeQuantity + ", lastEditTimestamp=" + lastEditTimestamp + ", createdTimestamp=" + createdTimestamp
				+ "]";
	}




}
