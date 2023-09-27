package com.cooklab.recipe_hashtag.model;

import java.sql.Date;

public class RecipeHashtagVO implements java.io.Serializable{
	private Integer repiceHashTagNo;
	private Integer hashTagNo;
	private Integer recipeNO;
	private Date createTimestamp;
	
	public Integer getRepiceHashTagNo() {
		return repiceHashTagNo;
	}
	public void setRepiceHashTagNo(Integer repiceHashTagNo) {
		this.repiceHashTagNo = repiceHashTagNo;
	}
	public Integer getHashTagNo() {
		return hashTagNo;
	}
	public void setHashTagNo(Integer hashTagNo) {
		this.hashTagNo = hashTagNo;
	}
	public Integer getRecipeNO() {
		return recipeNO;
	}
	public void setRecipeNO(Integer recipeNO) {
		this.recipeNO = recipeNO;
	}
	public Date getCreateTimestamp() {
		return createTimestamp;
	}
	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	@Override
	public String toString() {
		return "RecipeHashtagVO [repiceHashTagNo=" + repiceHashTagNo + ", hashTagNo=" + hashTagNo + ", recipeNO="
				+ recipeNO + ", createTimestamp=" + createTimestamp + "]";
	}
	
	
}
