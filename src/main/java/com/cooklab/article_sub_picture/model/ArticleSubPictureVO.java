package com.cooklab.article_sub_picture.model;

import java.sql.Date;

public class ArticleSubPictureVO implements java.io.Serializable {
	private Integer ArticleSubPictureNo;
	private Integer articleSubNo;
	private byte[] picture;
	private Date createdTimestamp;

	public Integer getArticleSubPictureNo() {
		return ArticleSubPictureNo;
	}
	public void setArticleSubPictureNo(Integer ArticleSubPictureNo) {
		this.ArticleSubPictureNo = ArticleSubPictureNo;
	}
	public Integer getArticleSubNo() {
		return articleSubNo;
	}
	public void setArticleSubNo(Integer articleSubNo) {
		this.articleSubNo = articleSubNo;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	

}
