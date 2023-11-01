package com.cooklab.article_sub_reaction.model;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cooklab.article.model.ArticleVO;
import com.cooklab.article_sub.model.ArticleSubVO;
import com.cooklab.members.model.MembersVO;
@Entity
@Table(name="article_sub_reaction")
public class ArticleSubReactionVO implements java.io.Serializable{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "article_sub_reaction_no", insertable = false, updatable = false) 
	private Integer articleSubReactionNo;
	
	@ManyToOne
	@JoinColumn(name="article_sub_no" ,referencedColumnName = "article_sub_no"
			, insertable = false ,updatable = false)
	private  ArticleSubVO articleSub;
	
	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
	private MembersVO members;
	
	@Column(name = "member_id")
	private Integer memberId;
	
	@Column(name = "article_sub_no")
	private Integer articleSubNo;
	
	@Column(name = "statuts")
	private Byte status;
	
	@Column(name = "created_timestamp" , insertable = false, updatable = false) 
	private Timestamp createdTimestamp;
	
	public ArticleSubReactionVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArticleSubReactionVO(Integer articleSubReactionNo, ArticleSubVO articleSub, MembersVO members,
			Integer memberId, Integer articleSubNo, Byte status, Timestamp createdTimestamp) {
		super();
		this.articleSubReactionNo = articleSubReactionNo;
		this.articleSub = articleSub;
		this.members = members;
		this.memberId = memberId;
		this.articleSubNo = articleSubNo;
		this.status = status;
		this.createdTimestamp = createdTimestamp;
	}
	
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
	public void setStatus(Byte status) {
		this.status = status;
	}
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	@Override
	public String toString() {
		return "ArticleSubReactionVO [articleSubReactionNo=" + articleSubReactionNo + ", memberId=" + memberId
				+ ", articleSubNo=" + articleSubNo + ", status=" + status + ", createdTimestamp=" + createdTimestamp
				+ "]";
	}
}
