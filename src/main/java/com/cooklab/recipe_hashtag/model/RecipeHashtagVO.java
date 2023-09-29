package com.cooklab.recipe_hashtag.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_hashtag")
public class RecipeHashtagVO implements java.io.Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_hashtag_no", insertable = false ,updatable = false)
	private Integer repiceHashTagNo;
	@Column(name = "hashtag_no")
	private Integer hashTagNo;
	@Column(name = "recipe_no")
	private Integer recipeNO;
	@Column(name = "created_timestamp",insertable = false ,updatable = false)
	private Timestamp createdTimestamp;
	
	public Integer getRepiceHashTagNo() {
		return repiceHashTagNo;
	}
	public void setRepiceHashTagNo(Integer repiceHashTagNo) {
		this.repiceHashTagNo = repiceHashTagNo;
	}
	public Integer getHashTagNo() {
		return hashTagNo;
	}
	public void setHashTagNo(Integer hashTagNo) {
		this.hashTagNo = hashTagNo;
	}
	public Integer getRecipeNO() {
		return recipeNO;
	}
	public void setRecipeNO(Integer recipeNO) {
		this.recipeNO = recipeNO;
	}
	public Timestamp getCreateTimestamp() {
		return createdTimestamp;
	}
	public void setCreateTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	@Override
	public String toString() {
		return "RecipeHashtagVO [repiceHashTagNo=" + repiceHashTagNo + ", hashTagNo=" + hashTagNo + ", recipeNO="
				+ recipeNO + ", createTimestamp=" + createdTimestamp + "]";
	}
	
	
}
