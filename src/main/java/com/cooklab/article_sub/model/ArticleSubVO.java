package com.cooklab.article_sub.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article_sub")
public class ArticleSubVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="article_sub_no")
	private Integer articleSubNo;
	
	@Column (name="article_no")
	private Integer articleNo;
	
	@Column (name="member_id")
	private Integer memberId;
	
	
	
	@Column (name="article_sub_status")
	private Byte articleSubStatus;
	
	@Column (name="article_sub_content",columnDefinition = "longtext")
	private String articleSubContent;
	
	@Column (name="article_sub_count")
	private Integer articleSubCount;
	
	@Column (name="last_edit_timestamp" ,insertable = false)
	private Timestamp lastEditTimeStampstame;

	@Column (name="created_timestamp",insertable = false)
	private Timestamp createdTimestamp;
	
	public ArticleSubVO() {

	}
	
	public ArticleSubVO( Integer articleNo, Integer memberId, 
			Byte articleSubStatus, String articleSubContent, Integer articleSubCount
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

	public Byte getArticleSubStatus() {
		return articleSubStatus;
	}

	public void setArticleSubStatus(Byte articleSubStatus) {
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
