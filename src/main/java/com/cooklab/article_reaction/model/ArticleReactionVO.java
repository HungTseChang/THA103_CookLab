package com.cooklab.article_reaction.model;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity  //宣告為和資料庫對應的表格類別
@Table(name="article_reaction") //若無宣告則會用class的名稱來找表格類別，所以非必要加，但一般還是會加上宣告
public class ArticleReactionVO  implements java.io.Serializable{
	
	@Id //下面那一行的屬性為PK;
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "article_reaction_no")  
	private Integer articleReactionNo;
	
	@Column(name = "member_id")  //若無宣告則會用屬性的名稱來找表格欄位，所以非必要加，但一般還是會加
	private Integer memberId;
	
	@Column(name = "article_no")  //若無宣告則會用屬性的名稱來找表格欄位，所以非必要加，但一般還是會加
	private Integer articleNo;
	
	@Column(name = "statuts")  //若無宣告則會用屬性的名稱來找表格欄位，所以非必要加，但一般還是會加
	private Byte statuts;
	
	@Column(name = "created_timestamp")  //若無宣告則會用屬性的名稱來找表格欄位，所以非必要加，但一般還是會加
	private Timestamp createdTimestamp;


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

	
	
	


}
