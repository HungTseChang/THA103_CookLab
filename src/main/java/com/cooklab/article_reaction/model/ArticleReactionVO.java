package com.cooklab.article_reaction.model;
import java.sql.Date;

public class ArticleReactionVO  implements java.io.Serializable{
	private Integer articleReactionNo;
	private Integer memberId;
	private Integer articleNo;
	private Byte statuts;
	private Date createdTimestamp;
	public Integer getArticleReactionNo() {
		return articleReactionNo;
	}
	public void setArticleReactionNo(Integer articleReactionNo) {
		this.articleReactionNo = articleReactionNo;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
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
