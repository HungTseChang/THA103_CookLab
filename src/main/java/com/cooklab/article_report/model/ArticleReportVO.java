package com.cooklab.model.article_report;

import java.sql.Date;

public class ArticleReportVO implements java.io.Serializable{
		private Integer articleReportNo;
		private Integer articleNo;
		private Integer reporterId;
		private String reportingReason;
		private Byte reportingStatus ;
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
