package com.cooklab.article_sub_report.model;
import java.sql.Date;
import java.sql.Timestamp;

public class ArticleSubReportVO implements java.io.Serializable {
	private Integer articleSubReportNo;
	private Integer articleSubNo;
	private Integer reporterId;
	private String reportingReason;
	private Integer reportingStatus;
	private Timestamp createdTimestamp;
	
	public ArticleSubReportVO () {
		
	}
	
	
	
	public ArticleSubReportVO( Integer articleSubNo, Integer reporterId,
			String reportingReason, Integer reportingStatus) {
		super();
		this.articleSubReportNo = articleSubReportNo;
		this.articleSubNo = articleSubNo;
		this.reporterId = reporterId;
		this.reportingReason = reportingReason;
		this.reportingStatus = reportingStatus;
		
	}



	public Integer getArticleSubReportNo() {
		return articleSubReportNo;
	}
	public void setArticleSubReportNo(Integer articleSubReportNo) {
		this.articleSubReportNo = articleSubReportNo;
	}
	public Integer getArticleSubNo() {
		return articleSubNo;
	}
	public void setArticleSubNo(Integer articleSubNo) {
		this.articleSubNo = articleSubNo;
	}
	public Integer getReporterId() {
		return reporterId;
	}
	public void setReporterId(Integer reporterId) {
		this.reporterId = reporterId;
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
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	@Override
	public String toString() {
		return "ArticleSubReportVO [articleSubReportNo=" + articleSubReportNo + ", articleSubNo=" + articleSubNo
				+ ", reporterId=" + reporterId + ", reportingReason=" + reportingReason + ", reportingStatus="
				+ reportingStatus + ", createdTimestamp=" + createdTimestamp + "]";
	}
	
	
	
}
