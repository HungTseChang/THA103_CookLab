package com.cooklab.article_sub_picture.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="article_sub_picture") 
public class ArticleSubPictureVO implements java.io.Serializable {
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "article_sub_picture_no")  
	private Integer ArticleSubPictureNo;
	
	@Column(name = "article_sub_no")  
     private Integer articleSubNo;
	
	@Column(name = "picture")  
    private byte[] picture;
	
	@Column(name = "article_sub_picture_no")  
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
