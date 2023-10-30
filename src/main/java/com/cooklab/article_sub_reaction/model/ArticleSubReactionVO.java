package com.cooklab.article_sub_reaction.model;
import java.sql.Date;

public class ArticleSubReactionVO  implements java.io.Serializable{
	private Integer articleSubReactionNo;
	private Integer memberId;
	private Integer articleSubNo;
	private Byte status;
	private Date createdTimestamp;
	public Integer getArticleSubReactionNo() {
		return articleSubReactionNo;
	}
	public void setArticleSubReactionNo(Integer articleSubReactionNo) {
		this.articleSubReactionNo = articleSubReactionNo;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getArticleSubNo() {
		return articleSubNo;
	}
	public void setArticleSubNo(Integer articleSubNo) {
		this.articleSubNo = articleSubNo;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatuts(Byte status) {
		this.status = status;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	
	
	
	


}
