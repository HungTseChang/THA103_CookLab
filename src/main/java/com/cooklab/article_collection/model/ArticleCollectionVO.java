package com.cooklab.article_collection.model;

import java.sql.Date;

public class ArticleCollectionVO implements java.io.Serializable{
	private Integer articleCollectionNo;
	private Integer articleNo;
	private Integer memberId;
	private Date createTimestamp;
	
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
	public Date getCreateTimestamp() {
		return createTimestamp;
	}
	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	@Override
	public String toString() {
		return "Article_collectionVO [articleCollectionNo=" + articleCollectionNo + ", articleNo=" + articleNo
				+ ", memberId=" + memberId + ", createTimestamp=" + createTimestamp + "]";
	}
	
	
}
