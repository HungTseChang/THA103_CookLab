package com.cooklab.article.model;

import java.sql.Date;

public class ArticleVO implements java.io.Serializable {
	private Integer articleNo;
	private String articleCategory;
	private String articleTitle;
	private Integer memberId;
	private Date createdTimestamp;
	private Byte articleStatus;
	private String articleContent;
	private Integer articleCount;
	private Integer viewCount;
	private Date lastEditTimestamp;
	public Integer getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
	}
	public String getArticleCategory() {
		return articleCategory;
	}
	public void setArticleCategory(String articleCategory) {
		this.articleCategory = articleCategory;
	}
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
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
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
	public Date getLastEditTimestamp() {
		return lastEditTimestamp;
	}
	public void setLastEditTimestamp(Date lastEditTimestamp) {
		this.lastEditTimestamp = lastEditTimestamp;
	}


	
}
