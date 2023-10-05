package com.cooklab.recipe_comments_report.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_comments_report")
public class RecipeCommentsReportVO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_report_no", insertable = false ,updatable = false)
	private Integer recipeReportNo;
	
	@Column(name = "member_id")
	private Integer memberId;
	
	@Column(name = "recipe_comments_no")
	private Integer recipeCommentsNo;
	
	@Column(name = "reporting_comments_reason")
	private String reportingCommentsReason;
	
	@Column(name = "reporting_status")
	private Byte reportingStatus;
	
	@Column(name = "reporting_answer")
	private String reportingAnswer;
	
	@Column(name = "created_timestamp", insertable = false ,updatable = false)
	private Timestamp createdTimestamp;
	
	public RecipeCommentsReportVO() {
		super();
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
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getReportingAnswer() {
		return reportingAnswer;
	}

	public void setReportingAnswer(String reportingAnswer) {
		this.reportingAnswer = reportingAnswer;
	}

	@Override
	public String toString() {
		return "RecipeCommentsReportVO [recipeReportNo=" + recipeReportNo + ", memberId=" + memberId
				+ ", recipeCommentsNo=" + recipeCommentsNo + ", reportingCommentsReason=" + reportingCommentsReason
				+ ", reportingStatus=" + reportingStatus + ", reportingAnswer=" + reportingAnswer
				+ ", createdTimestamp=" + createdTimestamp + "]";
	}
	

	

	
	
	
}
