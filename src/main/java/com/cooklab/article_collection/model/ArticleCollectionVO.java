package com.cooklab.article_collection.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article_collection")
public class ArticleCollectionVO implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_collection_no", insertable = false ,updatable = false)
	private Integer articleCollectionNo;
	@Column(name = "article_no")
	private Integer articleNo;
	@Column(name = "member_id")
	private Integer memberId;
	@Column(name = "created_timestamp", insertable = false ,updatable = false)
	private Timestamp createdTimestamp;
	
	public Integer getArticleCollectionNo() {
		return articleCollectionNo;
	}
	public void setArticleCollectionNo(Integer articleCollectionNo) {
		this.articleCollectionNo = articleCollectionNo;
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
	public Timestamp getCreateTimestamp() {
		return createdTimestamp;
	}
	public void setCreateTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	@Override
	public String toString() {
		return "Article_collectionVO [articleCollectionNo=" + articleCollectionNo + ", articleNo=" + articleNo
				+ ", memberId=" + memberId + ", createTimestamp=" + createdTimestamp + "]";
	}
	
	
}
