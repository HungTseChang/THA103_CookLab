package com.cooklab.recipe_collection.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.model.RecipeVO;
@Entity
@Table(name = "recipe_collection")
public class RecipeCollectionVO implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "collection_no", updatable = false)
	private Integer collectionNo;		//收藏編號(PK)
	@ManyToOne
	@JoinColumn(name = "recipe_no", referencedColumnName = "recipe_no")
	private RecipeVO recipe;		//食譜編號(FK)
	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "member_id")
	private MembersVO members;		//會員編號(FK)
	@Column(name = "created_timestamp", insertable = false, updatable = false)
	private Timestamp createdTimestamp; //建立時間

	public RecipeCollectionVO() {
	}

	public RecipeCollectionVO(Integer collectionNo, RecipeVO recipe, MembersVO members, Timestamp createdTimestamp) {
		super();
		this.collectionNo = collectionNo;
		this.recipe = recipe;
		this.members = members;
		this.createdTimestamp = createdTimestamp;
	}

	public Integer getCollectionNo() {
		return collectionNo;
	}

	public void setCollectionNo(Integer collectionNo) {
		this.collectionNo = collectionNo;
	}

	public RecipeVO getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeVO recipe) {
		this.recipe = recipe;
	}

	public MembersVO getMembers() {
		return members;
	}

	public void setMembers(MembersVO members) {
		this.members = members;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "RecipeCollectionVO [collectionNo=" + collectionNo + ", recipe=" + recipe + ", members=" + members
				+ ", createdTimestamp=" + createdTimestamp + "]";
	}
}
