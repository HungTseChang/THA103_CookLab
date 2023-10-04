package com.cooklab.recipe_report.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "recipe_report")

public class RecipeReportVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="recipe_report_no")
	private Integer recipeReportNo;
	
	@Column (name="member_id")
	private Integer memberId;
	
	@Column (name="recipe_no")
	private Integer recipeNo;
	
	@Column (name="reporting_reason")
	private String reportingReason;
	
	@Column (name="reporting_status")
	private byte reportingStatus;
	
	@Column (name="created_timestamp" ,insertable = false)
	private Timestamp credcreatedTimestamp;
	
	
	public RecipeReportVO() {}
	
	public RecipeReportVO(Integer memberId, Integer recipeNo, String reportingReason, byte reportingStatus) {
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
	public byte getReportingStatus() {
		return reportingStatus;
	}
	public void setReportingStatus(byte reportingStatus) {
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
