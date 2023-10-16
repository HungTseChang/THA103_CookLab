package com.cooklab.recipe_hashtag.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cooklab.hashtag.model.HashtagVO;
import com.cooklab.recipe.model.RecipeVO;

@Entity
@Table(name = "recipe_hashtag")
public class RecipeHashtagVO implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_hashtag_no", insertable = false ,updatable = false)
	private Integer repiceHashTagNo;		//食譜使用標籤(PK)
	@ManyToOne
	@JoinColumn(name = "hashTag_no", referencedColumnName = "hashTag_no")
	private HashtagVO hashTag;
	@ManyToOne
	@JoinColumn(name = "recipe_no", referencedColumnName = "recipe_no")
	private RecipeVO recipe;
	@Column(name = "created_timestamp",insertable = false ,updatable = false)
	private Timestamp createdTimestamp;
	
	public Integer getRepiceHashTagNo() {
		return repiceHashTagNo;
	}
	
	public void setRepiceHashTagNo(Integer repiceHashTagNo) {
		this.repiceHashTagNo = repiceHashTagNo;
	}

	public HashtagVO getHashTag() {
		return hashTag;
	}
	
	public void setHashTag(HashtagVO hashTag) {
		this.hashTag = hashTag;
	}
	
	public RecipeVO getRecipe() {
		return recipe;
	}
	
	public void setRecipe(RecipeVO recipe) {
		this.recipe = recipe;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "RecipeHashtagVO [repiceHashTagNo=" + repiceHashTagNo + ", hashTag=" + hashTag + ", recipe=" + recipe
				+ ", createdTimestamp=" + createdTimestamp + "]";
	}

}
