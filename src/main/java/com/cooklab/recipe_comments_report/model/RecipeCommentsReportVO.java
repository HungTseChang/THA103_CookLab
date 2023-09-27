package com.cooklab.recipe_comments_report.model;

import java.sql.Date;

public class RecipeCommentsReportVO implements java.io.Serializable{
	private Integer recipeReportNo;
	private Integer memberId;
	private Integer recipeCommentsNo;
	private String reportingCommentsReason;
	private Byte reportingStatus;
	private Date createTimestamp;
	
	public Integer getRecipeReportNo() {
		return recipeReportNo;
	}
	public void setRecipeReportNo(Integer recipeReportNo) {
		this.recipeReportNo = recipeReportNo;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getRecipeCommentsNo() {
		return recipeCommentsNo;
	}
	public void setRecipeCommentsNo(Integer recipeCommentsNo) {
		this.recipeCommentsNo = recipeCommentsNo;
	}
	public String getReportingCommentsReason() {
		return reportingCommentsReason;
	}
	public void setReportingCommentsReason(String reportingCommentsReason) {
		this.reportingCommentsReason = reportingCommentsReason;
	}

	public Byte getReportingStatus() {
		return reportingStatus;
	}
	public void setReportingStatus(Byte reportingStatus) {
		this.reportingStatus = reportingStatus;
	}
	public Date getCreateTimestamp() {
		return createTimestamp;
	}
	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	@Override
	public String toString() {
		return "RecipeCommentsReportVO [recipeReportNo=" + recipeReportNo + ", memberId=" + memberId
				+ ", recipeCommentsNo=" + recipeCommentsNo + ", reportingCommentsReason=" + reportingCommentsReason
				+ ", reportingStatus=" + reportingStatus + ", createTimestamp=" + createTimestamp + "]";
	}
	

	
	
	
}
