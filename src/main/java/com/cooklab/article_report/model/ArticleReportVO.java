package com.cooklab.article_report.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="article_report") 
public class ArticleReportVO implements java.io.Serializable{
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "article_report_no" , insertable = false, updatable = false)  
		private Integer articleReportNo;
	
	@Column(name = "article_no")  
		private Integer articleNo;
	
	@Column(name = "reporter_id")  
		private Integer reporterId;
		
	@Column(name = "reporting_reason")  
		private String reportingReason;
	
	@Column(name = "reporting_status")  
		private Byte reportingStatus ;
	
	@Column(name = "reporting_answer")  
	private String reportingAnswer;
	
	@Column(name = "created_timestamp" , insertable = false, updatable = false)  
		private Timestamp createdTimestamp;
		

		public ArticleReportVO(Integer articleNo, Integer reporterId, String reportingReason, Byte reportingStatus) {
		super();
		this.articleNo = articleNo;
		this.reporterId = reporterId;
		this.reportingReason = reportingReason;
		this.reportingStatus = reportingStatus;
	}
		public ArticleReportVO(Integer articleReportNo, Integer articleNo, Integer reporterId, String reportingReason,
			Byte reportingStatus, Timestamp createdTimestamp) {
		super();
		this.articleReportNo = articleReportNo;
		this.articleNo = articleNo;
		this.reporterId = reporterId;
		this.reportingReason = reportingReason;
		this.reportingStatus = reportingStatus;
		this.createdTimestamp = createdTimestamp;
	}
		
//		public ArticleReportVO(Integer articleReportNo, Object  createdTimestamp) {
//			super();
//			this.articleReportNo = articleReportNo;
//			this.createdTimestamp = java.sql.Timestamp.valueOf(createdTimestamp.toString());
//		為了避免 hibernate的查單一參數的指令Query query() = session.createQuery("select createdTimestamp from ArticleReportVO")
//		因為回傳時會以婦類別的Date回傳 所以用Object接 然後用toString轉成字串 然後再切換成日期
//		轉換方式請看 JavaEx_Additional  datetime.DateTimeConverter
//		}
		public ArticleReportVO() {
		super();
		// TODO Auto-generated constructor stub
	}
		
		public Integer getArticleReportNo() {
			return articleReportNo;
		}
		public void setArticleReportNo(Integer articleReportNo) {
			this.articleReportNo = articleReportNo;
		}
		public Integer getArticleNo() {
			return articleNo;
		}
		public void setArticleNo(Integer articleNo) {
			this.articleNo = articleNo;
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
		
		
		
}
