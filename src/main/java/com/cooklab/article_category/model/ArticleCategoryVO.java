package com.cooklab.article_category.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cooklab.article.model.ArticleVO;



@Entity
@Table(name="article_category") 
public class ArticleCategoryVO {
	@Id //下面那一行的屬性為PK;
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "article_category_no" , insertable = false, updatable = false)  
	private Integer articleCategoryNo;
	
	@Column(name = "article_category" , updatable = false)  
	private String articleCategory;
	
	@Column(name = "category_status")  
	private Byte categoryStatus;
	
	@Column(name = "created_timestamp" , insertable = false, updatable = false)  
	private Timestamp createdTimestamp;
	
	@OneToMany(mappedBy = "articleCategoryVO")
	private Set<ArticleVO> Articles;

	
	public Set<ArticleVO> getArticles() {
		return Articles;
	}

	public void setArticles(Set<ArticleVO> articles) {
		Articles = articles;
	}

	public ArticleCategoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getArticleCategoryNo() {
		return articleCategoryNo;
	}

	public void setArticleCategoryNo(Integer articleCategoryNo) {
		this.articleCategoryNo = articleCategoryNo;
	}

	public String getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(String articleCategory) {
		this.articleCategory = articleCategory;
	}

	public Byte getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(Byte categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "ArticleCategoryVO [articleCategoryNo=" + articleCategoryNo + ", articleCategory=" + articleCategory
				+ ", categoryStatus=" + categoryStatus + ", createdTimestamp=" + createdTimestamp + "]";
	}
	
	
	
}
