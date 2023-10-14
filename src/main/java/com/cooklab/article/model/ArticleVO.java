package com.cooklab.article.model;
import com.cooklab.article_category.model.ArticleCategoryVO;
import com.cooklab.members.model.*;

import java.io.Console;
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

import com.cooklab.members.model.MembersVO;
@Entity
@Table(name="article") 
public class ArticleVO implements java.io.Serializable {
	
	
	@Id //下面那一行的屬性為PK;
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "article_no" , insertable = false, updatable = false)  
	private Integer articleNo;
	
	@Column(name = "article_title")  
	private String articleTitle;
	
	@Column(name = "member_id")  
	private Integer memberId;
	
	@Column(name = "created_timestamp" , insertable = false, updatable = false)  
	private Timestamp createdTimestamp;
	
	@Column(name = "article_status")  
	private Byte articleStatus;
	
	@Column(name = "article_content" ,columnDefinition="longtext")
	private String articleContent;

	@Column(name = "article_count")
	private Integer articleCount;
	
	@Column(name = "view_count")
	private Integer viewCount;
	
	@Column(name = "last_edit_timestamp" , insertable = false, updatable = false)  
	private Timestamp lastEditTimestamp;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="article_category" ,referencedColumnName = "article_category_no")
	private ArticleCategoryVO articleCategoryVO;
	
//	@Column(name = "article_category")  
//	private Integer articleCategory;
	
	

	
	public ArticleCategoryVO getArticleCategoryVO() {
		return articleCategoryVO;
	}
	public void setArticleCategoryVO(ArticleCategoryVO articleCategoryVO) {
		this.articleCategoryVO = articleCategoryVO;
	}
	public ArticleVO(Integer articleCategory, String articleTitle, Integer memberId, Byte articleStatus,
			String articleContent, Integer articleCount, Integer viewCount) {
		super();
//		this.articleCategory = articleCategory;
		this.articleTitle = articleTitle;
		this.memberId = memberId;
		this.articleStatus = articleStatus;
		this.articleContent = articleContent;
		this.articleCount = articleCount;
		this.viewCount = viewCount;
	}
	public ArticleVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
	}
//	public Integer getArticleCategory() {
//		return articleCategory;
//	}
//	public void setArticleCategory(Integer articleCategory) {
//		this.articleCategory = articleCategory;
//	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public Byte getArticleStatus() {
		return articleStatus;
	}
	public void setArticleStatus(Byte articleStatus) {
		this.articleStatus = articleStatus;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public Integer getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	public Timestamp getLastEditTimestamp() {
		return lastEditTimestamp;
	}
	public void setLastEditTimestamp(Timestamp lastEditTimestamp) {
		this.lastEditTimestamp = lastEditTimestamp;
	}

	public MembersVO getMembersVO() {
		MembersJDBCDAO mbjdbc = new MembersJDBCDAO();
		MembersVO MembersVO1 =mbjdbc.findByPrimaryKey(memberId);
		return MembersVO1;
		
	}

	
}
