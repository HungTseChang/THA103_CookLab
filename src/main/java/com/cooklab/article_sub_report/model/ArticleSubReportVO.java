package com.cooklab.article_sub_report.model;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "article_sub_report")

public class ArticleSubReportVO  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="article_sub_report_no")
	private Integer articleSubReportNo;
	
	@Column (name="article_sub_no")
	private Integer articleSubNo;
	
	@Column (name="reporter_id")
	private Integer reporterId;
	
	@Column (name="reporting_reason")
	private String reportingReason;
	
	@Column (name="reporting_status")
	private byte reportingStatus;
	
	@Column (name="created_timestamp" ,insertable = false)
	private Timestamp createdTimestamp;
	
	public ArticleSubReportVO () {
		
	}
	
	
	
	public ArticleSubReportVO( Integer articleSubNo, Integer reporterId,
			String reportingReason, byte reportingStatus) {
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
	public byte getReportingStatus() {
		return reportingStatus;
	}
	public void setReportingStatus(byte reportingStatus) {
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
