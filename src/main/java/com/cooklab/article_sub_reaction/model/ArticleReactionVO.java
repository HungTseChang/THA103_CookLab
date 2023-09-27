package com.cooklab.article_sub_reaction.model;
import java.sql.Date;

public class ArticleReactionVO  implements java.io.Serializable{
	private Integer articleSubReactionNo;
	private Integer memberId;
	private Integer articleSubNo;
	private Byte statuts;
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
	public Byte getStatuts() {
		return statuts;
	}
	public void setStatuts(Byte statuts) {
		this.statuts = statuts;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	
	
	
	


}
