package com.cooklab.recipe_comments.model;

import java.sql.Date;

public class RecipeCommentsVO  implements java.io.Serializable{
//	
	private Integer recipeCommentsNo;//食譜留言編號
	private Integer recipeNo;		//食譜編號
	private Integer memberId;		//會員編號
	private String commentContent;	//留言內文
	private Integer reportComments;//檢舉次數
	private Date lastEditTimestamp;
	private Date createdTimestamp;	//留言時間
	
	public Integer getRecipeCommentsNo() {
		return recipeCommentsNo;
	}
	public void setRecipeCommentsNo(Integer recipeCommentsNo) {
		this.recipeCommentsNo = recipeCommentsNo;
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

	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Integer getReportComments() {
		return reportComments;
	}
	public void setReportComments(Integer reportComments) {
		this.reportComments = reportComments;
	}
	
	public Date getLastEditTimestamp() {
		return lastEditTimestamp;
	}
	public void setLastEditTimestamp(Date lastEditTimestamp) {
		this.lastEditTimestamp = lastEditTimestamp;
	}
	public Date getcreatedTimestamp() {
		return createdTimestamp;
	}
	public void setcreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	@Override
	public String toString() {
		return "RecipeCommentsVO [recipeCommentsNo=" + recipeCommentsNo + ", recipeNo=" + recipeNo + ", memberId="
				+ memberId + ", commentContent=" + commentContent + ", reportComments=" + reportComments
				+ ", lastEditTimestamp=" + lastEditTimestamp + ", createdTimestamp=" + createdTimestamp + "]";
	}

	
	
}
