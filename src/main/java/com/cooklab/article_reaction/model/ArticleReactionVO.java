package com.cooklab.article_reaction.model;
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
import com.cooklab.members.model.MembersVO;
@Entity  //宣告為和資料庫對應的表格類別
@Table(name="article_reaction") //若無宣告則會用class的名稱來找表格類別，所以非必要加，但一般還是會加上宣告
public class ArticleReactionVO  implements java.io.Serializable{
	
	@Id //下面那一行的屬性為PK;
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "article_reaction_no", insertable = false, updatable = false)  
	private Integer articleReactionNo;
	
	
	@ManyToOne
	@JoinColumn(name="member_id" ,referencedColumnName = "member_id"
			, insertable = false ,updatable = false)
	private MembersVO members;
	
	@Column(name = "member_id")  //若無宣告則會用屬性的名稱來找表格欄位，所以非必要加，但一般還是會加
	private Integer memberId;
	
	@ManyToOne
	@JoinColumn(name="article_no" ,referencedColumnName = "article_no"
			, insertable = false ,updatable = false)
	private  ArticleVO article ;
	
	@Column(name = "article_no")  //若無宣告則會用屬性的名稱來找表格欄位，所以非必要加，但一般還是會加
	private Integer articleNo;
	
	@Column(name = "statuts")  //若無宣告則會用屬性的名稱來找表格欄位，所以非必要加，但一般還是會加
	private Byte statuts;
	
	@Column(name = "created_timestamp" , insertable = false, updatable = false)  
	private Timestamp createdTimestamp;


	public ArticleReactionVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticleReactionVO(Integer articleReactionNo, Integer memberId, Integer articleNo, Byte statuts,
			Timestamp createdTimestamp) {
		super();
		this.articleReactionNo = articleReactionNo;
		this.memberId = memberId;
		this.articleNo = articleNo;
		this.statuts = statuts;
		this.createdTimestamp = createdTimestamp;
	}
	
	public ArticleReactionVO(Integer memberId, Integer articleNo, Byte statuts) {
		super();
		this.memberId = memberId;
		this.articleNo = articleNo;
		this.statuts = statuts;
	}
	
	
	
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
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public MembersVO getMembers() {
		return members;
	}

	public void setMembers(MembersVO members) {
		this.members = members;
	}

	public ArticleVO getArticle() {
		return article;
	}

	public void setArticle(ArticleVO article) {
		this.article = article;
	}

	@Override
	public String toString() {
		return "ArticleSubReactionVO [articleReactionNo=" + articleReactionNo + ", members=" + members + ", memberId="
				+ memberId + ", article=" + article + ", articleNo=" + articleNo + ", statuts=" + statuts
				+ ", createdTimestamp=" + createdTimestamp + "]";
	}

	
	
	


}
