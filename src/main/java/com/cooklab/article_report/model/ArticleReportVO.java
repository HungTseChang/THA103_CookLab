package com.cooklab.article_report.model;

import java.sql.Date;


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
	@Column(name = "article_report_no")  
		private Integer articleReportNo;
	
	@Column(name = "article_no")  
		private Integer articleNo;
	
	@Column(name = "article_id")  
		private Integer reporterId;
		
	@Column(name = "reporting_reason")  
		private String reportingReason;
		
	@Column(name = "reporting_status")  
		private Byte reportingStatus ;
	
	@Column(name = "created_timestamp")  
		private Date createdTimestamp;
		

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
		public Date getCreatedTimestamp() {
			return createdTimestamp;
		}
		public void setCreatedTimestamp(Date createdTimestamp) {
			this.createdTimestamp = createdTimestamp;
		}
		
		
		
}
