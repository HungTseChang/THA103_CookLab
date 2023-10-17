package com.cooklab.article_sub_report.model;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cooklab.article.model.ArticleVO;
import com.cooklab.article_sub.model.ArticleSubVO;
import com.cooklab.members.model.MembersVO;


@Entity
@Table(name = "article_sub_report")

public class ArticleSubReportVO  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="article_sub_report_no")
	private Integer articleSubReportNo;
	
	@Column (name="article_sub_no")
	private Integer articleSubNo;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_sub_no", referencedColumnName = "article_sub_no", insertable = false, updatable = false)
	private ArticleSubVO articleSub;
	
	@Column (name="reporter_id")
	private Integer reporterId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reporter_id", referencedColumnName = "member_id", insertable = false, updatable = false)
	private MembersVO  members;
	
	@Column (name="reporting_reason")
	private String reportingReason;
	
	@Column (name="reporting_status")
	private byte reportingStatus;
	
	@Column (name="reporting_answer")
	private String reportingAnswer;
	
	@Column (name="created_timestamp" ,insertable = false)
	private Timestamp createdTimestamp;
	
	public ArticleSubReportVO () {
		
	}
	
	
	
	public ArticleSubReportVO( Integer articleSubNo, Integer reporterId,
			String reportingReason, byte reportingStatus ,String reportingAnswer ) {
		super();
		this.articleSubReportNo = articleSubReportNo;
		this.articleSubNo = articleSubNo;
		this.reporterId = reporterId;
		this.reportingReason = reportingReason;
		this.reportingStatus = reportingStatus;
		this.reportingAnswer = reportingAnswer;
		
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



	public String getReportingAnswer() {
		return reportingAnswer;
	}



	public void setReportingAnswer(String reportingAnswer) {
		this.reportingAnswer = reportingAnswer;
	}



	@Override
	public String toString() {
		return "ArticleSubReportVO [articleSubReportNo=" + articleSubReportNo + ", articleSubNo=" + articleSubNo
				+ ", reporterId=" + reporterId + ", reportingReason=" + reportingReason + ", reportingStatus="
				+ reportingStatus + ", reportingAnswer=" + reportingAnswer + ", createdTimestamp=" + createdTimestamp
				+ "]";
	}
	
	
	
	
}
