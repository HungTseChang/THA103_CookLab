package com.cooklab.article_picture.model;

import java.sql.Date;

public class ArticlePictureVO implements java.io.Serializable {
	private Integer articlePictureNo;
	private Integer articleNo;
	private byte[] picture;
	private Date createdTimestamp;
	public Integer getArticlePictureNo() {
		return articlePictureNo;
	}
	public void setArticlePictureNo(Integer articlePictureNo) {
		this.articlePictureNo = articlePictureNo;
	}
	public Integer getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
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
