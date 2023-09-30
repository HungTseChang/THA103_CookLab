package com.cooklab.recipe_reaction.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_reaction")
@IdClass(com.cooklab.recipe_reaction.model.RecipeReactionVO.CompositeDetail.class)
public class RecipeReactionVO implements java.io.Serializable {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_no", insertable = false, updatable = false)
	private Integer recipeNo;
	@Id
	@Column(name = "member_id", insertable = false, updatable = false)
	private Integer memberId;
	@Column(name = "recipe_reaction_status")
	private Byte recipeReactionStatus;
	@Column(name = "created_timestamp", insertable = false, updatable = false)
	private Timestamp createdTimestamp;

	// 特別加上對複合主鍵物件的 getter / setter
	public CompositeDetail recipeNo() {
		return new CompositeDetail(recipeNo, memberId);
	}

	public void setCompositeKey(CompositeDetail key) {
		this.recipeNo = key.getOid();
		this.memberId = key.getBid();
	}

	public Integer getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(Integer recipeNo) {
		this.recipeNo = recipeNo;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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
		return "RecipeReactionVO [recipeNo=" + recipeNo + ", memberId=" + memberId + ", recipeReactionStatus="
				+ recipeReactionStatus + ", createTimestamp=" + createdTimestamp + "]";
	}

	// 需要宣告一個有包含複合主鍵屬性的類別，並一定要實作 java.io.Serializable 介面
	static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer recipeNo;
		private Integer memberId;

		// 一定要有無參數建構子
		public CompositeDetail() {
			super();
		}

		public CompositeDetail(Integer oid, Integer bid) {
			super();
			this.recipeNo = oid;
			this.memberId = bid;
		}

		public Integer getOid() {
			return recipeNo;
		}

		public void setOid(Integer oid) {
			this.recipeNo = oid;
		}

		public Integer getBid() {
			return memberId;
		}

		public void setBid(Integer bid) {
			this.memberId = bid;
		}

		// 一定要 override 此類別的 hashCode() 與 equals() 方法！
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
			result = prime * result + ((recipeNo == null) ? 0 : recipeNo.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;

			if (obj != null && getClass() == obj.getClass()) {
				CompositeDetail compositeKey = (CompositeDetail) obj;
				if (recipeNo.equals(compositeKey.recipeNo) && memberId.equals(compositeKey.memberId)) {
					return true;
				}
			}

			return false;
		}

	}

}
