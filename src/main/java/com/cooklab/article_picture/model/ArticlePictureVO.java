package com.cooklab.article_picture.model;

import java.sql.Date;
import java.sql.Timestamp;

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
	
	@Column(name = "picture" ,columnDefinition="longblob")  
	private byte[] picture;
	
	@Column(name = "created_timestamp", insertable = false, updatable = false)  
	private Timestamp createdTimestamp;
	
	
	
	
	public ArticlePictureVO(Integer articlePictureNo, Integer articleNo, byte[] picture, Timestamp createdTimestamp) {
		super();
		this.articlePictureNo = articlePictureNo;
		this.articleNo = articleNo;
		this.picture = picture;
		this.createdTimestamp = createdTimestamp;
	}
	public ArticlePictureVO() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Timestamp date) {
		this.createdTimestamp = date;
	}

	

}
