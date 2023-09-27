package com.cooklab.article_sub.model;

import java.util.Arrays;
import java.sql.Date;
import java.sql.Timestamp;

public class ArticleSubVO implements java.io.Serializable {
	private Integer articleSubNo;
	private Integer articleNo;
	private Integer memberId;
	private Timestamp createdTimestamp;
	private Integer articleSubStatus;
	private String articleSubContent;
	private Integer articleSubCount;
	private Timestamp lastEditTimeStampstame;

	public ArticleSubVO() {

	}

	public ArticleSubVO( Integer articleNo, Integer memberId, 
			Integer articleSubStatus, String articleSubContent, Integer articleSubCount
			) {
		super();

		this.articleNo = articleNo;
		this.memberId = memberId;
		this.articleSubStatus = articleSubStatus;
		this.articleSubContent = articleSubContent;
		this.articleSubCount = articleSubCount;

	}

	public Integer getArticleSubNo() {
		return articleSubNo;
	}

	public void setArticleSubNo(Integer articleSubNo) {
		this.articleSubNo = articleSubNo;
	}
	

	public Integer getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getArticleSubStatus() {
		return articleSubStatus;
	}

	public void setArticleSubStatus(Integer articleSubStatus) {
		this.articleSubStatus = articleSubStatus;
	}

	public String getArticleSubContent() {
		return articleSubContent;
	}

	public void setArticleSubContent(String articleSubContent) {
		this.articleSubContent = articleSubContent;
	}

	public Integer getArticleSubCount() {
		return articleSubCount;
	}

	public void setArticleSubCount(Integer articleSubCount) {
		this.articleSubCount = articleSubCount;
	}



	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	
	public Timestamp getLastEditTimeStampstame() {
		return lastEditTimeStampstame;
	}

	public void setLastEditTimeStampstame(Timestamp lastEditTimeStampstame) {
		this.lastEditTimeStampstame = lastEditTimeStampstame;
	}

	@Override
	public String toString() {
		return "ArticleSubVO [articleSubNo=" + articleSubNo + ", articleNo=" + articleNo + ", memberId=" + memberId
				+ ", createdTimestamp=" + createdTimestamp + ", articleSubStatus=" + articleSubStatus
				+ ", articleSubContent=" + articleSubContent + ", articleSubCount=" + articleSubCount
				+ ", lastEditTimeStampstame=" + lastEditTimeStampstame + "]";
	}

}
