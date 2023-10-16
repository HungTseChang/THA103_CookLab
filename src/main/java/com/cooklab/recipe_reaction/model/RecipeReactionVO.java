package com.cooklab.recipe_reaction.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.model.RecipeVO;

@Entity
@Table(name = "recipe_reaction")
@IdClass(RecipeReactionVO.CompositeDetail.class)
public class RecipeReactionVO implements java.io.Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "recipe_no", referencedColumnName = "recipe_no" , insertable = false, updatable = false)
	private RecipeVO recipe;
	@Id
	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "member_id")
	private MembersVO members;
	@Column(name = "recipe_reaction_status")
	private Byte recipeReactionStatus;
	@Column(name = "created_timestamp", insertable = false, updatable = false)
	private Timestamp createdTimestamp;

	// 特別加上對複合主鍵物件的 getter / setter
	public CompositeDetail recipeNo() {
		return new CompositeDetail(recipe, members);
	}

	public void setCompositeKey(CompositeDetail key) {
		this.recipe = key.getRecipe();
		this.members = key.getMembers();
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

	public Byte getRecipeReactionStatus() {
		return recipeReactionStatus;
	}

	public void setRecipeReactionStatus(Byte recipeReactionStatus) {
		this.recipeReactionStatus = recipeReactionStatus;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "RecipeReactionVO [recipeNo=" + recipe + ", memberId=" + members + ", recipeReactionStatus="
				+ recipeReactionStatus + ", createTimestamp=" + createdTimestamp + "]";
	}

	// 需要宣告一個有包含複合主鍵屬性的類別，並一定要實作 java.io.Serializable 介面
	static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;

		private RecipeVO recipe;
		private MembersVO members;

		// 一定要有無參數建構子
		public CompositeDetail() {
			super();
		}

		public CompositeDetail(RecipeVO recipe, MembersVO members) {
			super();
			this.recipe = recipe;
			this.members = members;
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

		// 一定要 override 此類別的 hashCode() 與 equals() 方法！
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((members == null) ? 0 : members.hashCode());
			result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;

			if (obj != null && getClass() == obj.getClass()) {
				CompositeDetail compositeKey = (CompositeDetail) obj;
				if (recipe.equals(compositeKey.recipe) && members.equals(compositeKey.members)) {
					return true;
				}
			}

			return false;
		}

	}

}
