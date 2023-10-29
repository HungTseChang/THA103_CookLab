package com.cooklab.article.model;
import com.cooklab.article_category.model.ArticleCategoryVO;
import com.cooklab.article_collection.model.ArticleCollectionVO;
import com.cooklab.article_reaction.model.ArticleReactionVO;
import com.cooklab.article_sub.model.ArticleSubVO;
import com.cooklab.members.model.*;

import java.io.Console;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cooklab.members.model.MembersVO;
import com.cooklab.notify_center.model.NCType;
@Entity
@Table(name="article") 
public class ArticleVO implements java.io.Serializable {
	
	
	@Id //下面那一行的屬性為PK;
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "article_no" , insertable = false, updatable = false)  
	private Integer articleNo;
	
	
	@OneToMany(mappedBy="article")
	private Set<ArticleCollectionVO> articleC;
	
	
	@OneToMany(mappedBy="article")
	private Set<ArticleReactionVO> articleR;
	
	@OneToMany(mappedBy="article")
	private Set<ArticleSubVO> articleS;
	
	
	@Column(name = "article_title")  
	private String articleTitle;
	

	@ManyToOne
	@JoinColumn(name="member_id" ,referencedColumnName = "member_id"
	, insertable = false, updatable = false)
	private MembersVO members;
	
	@Column(name = "member_id")  
	private Integer memberId;
	
	@Column(name = "created_timestamp" , insertable = false, updatable = false)  
	private Timestamp createdTimestamp;
	
	@Column(name = "article_status")  
	private Byte articleStatus;
	
	@Column(name = "article_content"  , columnDefinition = "longtext")
	private String articleContent;

	@Column(name = "article_count")
	private Integer articleCount;
	
	@Column(name = "view_count")
	private Integer viewCount;
	
	@Column(name = "last_edit_timestamp" , insertable = false, updatable = false)  
	private Timestamp lastEditTimestamp;


	@ManyToOne
	@JoinColumn(name="article_category" ,referencedColumnName = "article_category_no" 
	, insertable = false, updatable = false)
	private ArticleCategoryVO articleCategory;
	
	@Column (name="article_category")
	private Integer articleCategoryNo;

//	
	

	
	public MembersVO getMembers() {
		return members;
	}
	public void setMembers(MembersVO members) {
		this.members = members;
	}
	
	
//	public ArticleVO(Integer articleCategory, String articleTitle, Integer memberId, Byte articleStatus,
//			String articleContent, Integer articleCount, Integer viewCount) {
//		super();
//		this.articleCategory = articleCategory;
//		this.articleTitle = articleTitle;
//		this.memberId = memberId;
//		this.articleStatus = articleStatus;
//		this.articleContent = articleContent;
//		this.articleCount = articleCount;
//		this.viewCount = viewCount;
//	}
	
	

	public ArticleVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	

	public Integer getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(Integer articleNo) {
		this.articleNo = articleNo;
	}
	public Set<ArticleCollectionVO> getArticleC() {
		return articleC;
	}
	public void setArticleC(Set<ArticleCollectionVO> articleC) {
		this.articleC = articleC;
	}
	public Set<ArticleReactionVO> getArticleR() {
		return articleR;
	}
	public void setArticleR(Set<ArticleReactionVO> articleR) {
		this.articleR = articleR;
	}
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	public Byte getArticleStatus() {
		return articleStatus;
	}
	public void setArticleStatus(Byte articleStatus) {
		this.articleStatus = articleStatus;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public Integer getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	public ArticleCategoryVO getArticleCategory() {
		return articleCategory;
	}
	public void setArticleCategory(ArticleCategoryVO articleCategory) {
		this.articleCategory = articleCategory;
	}
	public Integer getArticleCategoryNo() {
		return articleCategoryNo;
	}
	public void setArticleCategoryNo(Integer articleCategoryNo) {
		this.articleCategoryNo = articleCategoryNo;
	}
	public Timestamp getLastEditTimestamp() {
		return lastEditTimestamp;
	}
	public void setLastEditTimestamp(Timestamp lastEditTimestamp) {
		this.lastEditTimestamp = lastEditTimestamp;
	}

	public MembersVO getMembersVO() {
		MembersJDBCDAO mbjdbc = new MembersJDBCDAO();
		MembersVO MembersVO1 =mbjdbc.findByPrimaryKey(memberId);
		return MembersVO1;		
	}
	
	public Set<ArticleSubVO> getArticleS() {
		return articleS;
	}
	public void setArticleS(Set<ArticleSubVO> articleS) {
		this.articleS = articleS;
	}
	
	
	@Override
	public String toString() {
		return "ArticleVO [articleNo=" + articleNo 
				+ ", articleTitle=" + articleTitle + ", memberId=" + memberId
				+ ", createdTimestamp=" + createdTimestamp + ", articleStatus=" + articleStatus + ", articleContent="
				+ articleContent + ", articleCount=" + articleCount + ", viewCount=" + viewCount
				+ ", lastEditTimestamp=" + lastEditTimestamp + ", articleCategory=" + articleCategory
				+ ", articleCategoryNo=" + articleCategoryNo + "]";
	}

	public String getArticleStatusEnum() {
		ASEnum[] type = ASEnum.values();
	    for (ASEnum atype: type) {
	        if (atype.getValue() == this.articleStatus) {
	            return atype.getDesc();
	        }
	    }
	    return "無此項目";
	}
}
