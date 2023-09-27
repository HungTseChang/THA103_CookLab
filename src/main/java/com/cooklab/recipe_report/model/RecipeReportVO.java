package com.cooklab.recipe_report.model;

import java.sql.Date;
import java.sql.Timestamp;

public class RecipeReportVO implements java.io.Serializable{
	private Integer recipeReportNo;
	private Integer memberId;
	private Integer recipeNo;
	private String reportingReason;
	private Integer reportingStatus;
	private Timestamp credcreatedTimestamp;
	
	
	public RecipeReportVO() {}
	
	public RecipeReportVO(Integer memberId, Integer recipeNo, String reportingReason, Integer reportingStatus) {
		super();
		this.memberId = memberId;
		this.recipeNo = recipeNo;
		this.reportingReason = reportingReason;
		this.reportingStatus = reportingStatus;
	}
	
	
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
	public Integer getRecipeNo() {
		return recipeNo;
	}
	public void setRecipeNo(Integer recipeNo) {
		this.recipeNo = recipeNo;
	}
	public String getReportingReason() {
		return reportingReason;
	}
	public void setReportingReason(String reportingReason) {
		this.reportingReason = reportingReason;
	}
	public Integer getReportingStatus() {
		return reportingStatus;
	}
	public void setReportingStatus(Integer reportingStatus) {
		this.reportingStatus = reportingStatus;
	}
	public Timestamp getCredcreatedTimestamp() {
		return credcreatedTimestamp;
	}
	public void setCredcreatedTimestamp(Timestamp credcreatedTimestamp) {
		this.credcreatedTimestamp = credcreatedTimestamp;
	}
	@Override
	public String toString() {
		return "RecipeReportVO [recipeReportNo=" + recipeReportNo + ", memberId=" + memberId + ", recipeNo=" + recipeNo
				+ ", reportingReason=" + reportingReason + ", reportingStatus=" + reportingStatus
				+ ", credcreatedTimestamp=" + credcreatedTimestamp + "]";
	}
	
	
	
	
}
