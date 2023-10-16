package com.cooklab.article_report.model.model;

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
import javax.persistence.Transient;

import com.cooklab.article.model.ArticleJDBCDAOIm;
import com.cooklab.article.model.ArticleVO;
import com.cooklab.members.model.MembersJDBCDAO;
import com.cooklab.members.model.MembersVO;
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
		
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_no", referencedColumnName = "article_no", insertable = false, updatable = false)
	private ArticleVO ArticleVO;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reporter_id", referencedColumnName = "member_id", insertable = false, updatable = false)
	private MembersVO MembersVO;
	
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
//		public ArticleVO getArticleVO() {
//			return ArticleVO;
//		}
//		public void setArticleVO(ArticleVO articleVO) {
//			ArticleVO = articleVO;
//		}
//		public MembersVO getMembersVO() {
//			return MembersVO;
//		}
//		public void setMembersVO(MembersVO membersVO) {
//			MembersVO = membersVO;
//		}
		public ArticleVO getArticleVO() {
			ArticleJDBCDAOIm  dao = new ArticleJDBCDAOIm();
			ArticleVO	 ArticleVO1=    dao.findByPrimaryKey(articleNo);
			
			return ArticleVO1;
			
		}
		
		public MembersVO getMembersVO() {
			MembersJDBCDAO dao = new MembersJDBCDAO();
			MembersVO	 MembersVO1= dao.findByPrimaryKey(reporterId);
			
			return MembersVO1;
			
		}
		
		
}
