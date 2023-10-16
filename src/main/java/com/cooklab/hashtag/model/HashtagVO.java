 package com.cooklab.hashtag.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.cooklab.recipe_hashtag.model.RecipeHashtagVO;

@Entity
@Table(name = "hashtag")
public class HashtagVO implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hashtag_no", insertable = false ,updatable = false)
	private Integer hashtagNO;			//標籤編號(PK)
	
	@OneToMany(mappedBy = "hashtag", cascade = CascadeType.ALL)
	@OrderBy("hashtag_no asc")
	private Set<RecipeHashtagVO> recipeHashtag;		//食譜使用標籤關聯
	
	@Column(name = "hashtag_name")
	private String hashtangName;		//標籤名稱
	@Column(name = "search_count")
	private Integer searchCount;		//搜尋次數
	@Column(name = "use_count")
	private Integer useCount;			//使用次數
	@Column(name = "created_timestamp", insertable = false ,updatable = false)
	private Timestamp createdTimestamp;		//建立時間
	
	public Integer getHashtagNO() {
		return hashtagNO;
	}
	
	public void setHashtagNO(Integer hashtagNO) {
		this.hashtagNO = hashtagNO;
	}
	
	public Set<RecipeHashtagVO> getRecipeHashtag() {
		return recipeHashtag;
	}

	public void setRecipeHashtag(Set<RecipeHashtagVO> recipeHashtag) {
		this.recipeHashtag = recipeHashtag;
	}

	public String getHashtangName() {
		return hashtangName;
	}
	
	public void setHashtangName(String hashtangName) {
		this.hashtangName = hashtangName;
	}
	public Integer getSearchCount() {
		return searchCount;
	}
	
	public void setSearchCount(Integer searchCount) {
		this.searchCount = searchCount;
	}
	
	public Integer getUseCount() {
		return useCount;
	}
	
	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}
	

	
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "HashtagVO [hashtagNO=" + hashtagNO + ", hashtangName=" + hashtangName + ", searchCount=" + searchCount
				+ ", useCount=" + useCount + ", createdTimestamp=" + createdTimestamp + "]";
	}
	
}
