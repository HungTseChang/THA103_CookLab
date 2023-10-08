package com.cooklab.recipe_comments.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_comments")
public class RecipeCommentsVO implements java.io.Serializable{
//	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_comments_no", insertable = false ,updatable = false)
	private Integer recipeCommentsNo;//食譜留言編號
	
	@Column(name = "recipe_no")
	private Integer recipeNo;		//食譜編號
	
	@Column(name = "member_id")
	private Integer memberId;		//會員編號
	
	@Column(name = "comment_content")
	private String commentContent;	//留言內文
	
	@Column(name = "report_comments")
	private Integer reportComments;//檢舉次數
	
	@Column(name = "last_edit_timestamp")
	private Timestamp lastEditTimestamp;
	
	@Column(name = "created_timestamp", insertable = false ,updatable = false)
	private Timestamp createdTimestamp;	//留言時間
	
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
	
	public Timestamp getLastEditTimestamp() {
		return lastEditTimestamp;
	}
	public void setLastEditTimestamp(Timestamp lastEditTimestamp) {
		this.lastEditTimestamp = lastEditTimestamp;
	}
	public Timestamp getcreatedTimestamp() {
		return createdTimestamp;
	}
	public void setcreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	@Override
	public String toString() {
		return "RecipeCommentsVO [recipeCommentsNo=" + recipeCommentsNo + ", recipeNo=" + recipeNo + ", memberId="
				+ memberId + ", commentContent=" + commentContent + ", reportComments=" + reportComments
				+ ", lastEditTimestamp=" + lastEditTimestamp + ", createdTimestamp=" + createdTimestamp + "]";
	}

	
	
}
