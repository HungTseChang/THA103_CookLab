package com.cooklab.article_picture.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="article_picture") 
public class ArticlePictureVO implements java.io.Serializable {
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "article_picture_no" , insertable = false, updatable = false)  
	private Integer articlePictureNo;
	
	@Column(name = "article_no")  
	private Integer articleNo;
	
	@Column(name = "picture")  
	private byte[] picture;
	
	@Column(name = "created_timestamp", insertable = false, updatable = false)  
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
