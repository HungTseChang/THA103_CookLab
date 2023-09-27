package com.cooklab.recipe_reaction.model;

import java.sql.Date;

public class RecipeReactionVO implements java.io.Serializable{
	
	private Integer recipeNo;
	private Integer memberId;
	private Byte recipeReactionStatus;
	private Date createTimestamp;
	
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
	public Byte getRecipeReactionStatus() {
		return recipeReactionStatus;
	}
	public void setRecipeReactionStatus(Byte recipeReactionStatus) {
		this.recipeReactionStatus = recipeReactionStatus;
	}
	public Date getCreateTimestamp() {
		return createTimestamp;
	}
	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	@Override
	public String toString() {
		return "RecipeReactionVO [recipeNo=" + recipeNo + ", memberId=" + memberId + ", recipeReactionStatus="
				+ recipeReactionStatus + ", createTimestamp=" + createTimestamp + "]";
	}
	
	
}
