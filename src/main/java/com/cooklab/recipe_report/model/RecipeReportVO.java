package com.cooklab.recipe_report.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.model.RecipeVO;



@Entity
@Table(name = "recipe_report")

public class RecipeReportVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="recipe_report_no")
	private Integer recipeReportNo;		//食譜檢舉編號(PK)
	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "member_id")
	private MembersVO members;			//會員編號(FK)
	@ManyToOne
	@JoinColumn(name = "recipe_no", referencedColumnName = "recipe_no")
	private RecipeVO recipe;			//食譜編號(FK)
	@Column (name="reporting_reason")
	private String reportingReason;		//檢舉理由
	@Column (name="reporting_status")
	private byte reportingStatus;		//檢舉狀態
	@Column (name="reporting_answer")
	private String reportingAnswer;		//檢舉回應

	@Column (name="created_timestamp" ,insertable = false)
	private Timestamp credcreatedTimestamp; //建立時間
	
	public RecipeReportVO(){
	}
	
	public RecipeReportVO(Integer recipeReportNo, MembersVO members, RecipeVO recipe, String reportingReason,
			byte reportingStatus, String reportingAnswer, Timestamp credcreatedTimestamp) {
		super();
		this.recipeReportNo = recipeReportNo;
		this.members = members;
		this.recipe = recipe;
		this.reportingReason = reportingReason;
		this.reportingStatus = reportingStatus;
		this.reportingAnswer = reportingAnswer;
		this.credcreatedTimestamp = credcreatedTimestamp;
	}

	public Integer getRecipeReportNo() {
		return recipeReportNo;
	}
	public void setRecipeReportNo(Integer recipeReportNo) {
		this.recipeReportNo = recipeReportNo;
	}

	public MembersVO getMembers() {
		return members;
	}

	public void setMembers(MembersVO members) {
		this.members = members;
	}

	public RecipeVO getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeVO recipe) {
		this.recipe = recipe;
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
	public String getReportingAnswer() {
		return reportingAnswer;
	}

	public void setReportingAnswer(String reportingAnswer) {
		this.reportingAnswer = reportingAnswer;
	}
	
	public Timestamp getCredcreatedTimestamp() {
		return credcreatedTimestamp;
	}
	
	public void setCredcreatedTimestamp(Timestamp credcreatedTimestamp) {
		this.credcreatedTimestamp = credcreatedTimestamp;
	}

	@Override
	public String toString() {
		return "RecipeReportVO [recipeReportNo=" + recipeReportNo + ", members=" + members + ", recipe=" + recipe
				+ ", reportingReason=" + reportingReason + ", reportingStatus=" + reportingStatus + ", reportingAnswer="
				+ reportingAnswer + ", credcreatedTimestamp=" + credcreatedTimestamp + "]";
	}


	
	
	
	
	
	
}
