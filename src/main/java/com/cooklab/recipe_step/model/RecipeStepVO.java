package com.cooklab.recipe_step.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cooklab.recipe.model.RecipeVO;

@Entity
@Table(name = "recipe_step")
@IdClass(RecipeStepVO.CompositeDetail.class)
public class RecipeStepVO implements java.io.Serializable{
	@Id
	@ManyToOne
	@JoinColumn(name = "recipe_no", referencedColumnName = "recipe_no" , insertable = false, updatable = false)
	private RecipeVO recipe;
	@Id
	@Column(name = "step")
	private Integer step;
	@Column(name = "step_time")
	private Integer stepTime;
	@Column(name = "step_img", columnDefinition = "LONGBLOB")
	private byte[] stepImg;
	@Column(name = "step_content")
	private String stepContent;
	@Column(name = "created_timestamp", insertable = false, updatable = false)
	private Timestamp createdTimestamp;
	
	public RecipeStepVO() {	
	} 
	

	
	public RecipeStepVO(RecipeVO recipe, Integer step, Integer stepTime, byte[] stepImg, String stepContent,
			Timestamp createdTimestamp) {
		super();
		this.recipe = recipe;
		this.step = step;
		this.stepTime = stepTime;
		this.stepImg = stepImg;
		this.stepContent = stepContent;
		this.createdTimestamp = createdTimestamp;
	}

	public RecipeVO getRecipe() {
		return recipe;
	}

	public void setRecipe(RecipeVO recipe) {
		this.recipe = recipe;
	}

	public void setCompositeKey(CompositeDetail key) {
		this.recipe = key.getRecipe();
		this.step = key.getStep();
	}
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	public Integer getStepTime() {
		return stepTime;
	}
	public void setStepTime(Integer stepTime) {
		this.stepTime = stepTime;
	}
	public byte[] getStepImg() {
		return stepImg;
	}
	public void setStepImg(byte[] stepImg) {
		this.stepImg = stepImg;
	}
	public String getStepContent() {
		return stepContent;
	}
	public void setStepContent(String stepContent) {
		this.stepContent = stepContent;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "RecipeStepVO [ step=" + step + ", stepTime=" + stepTime + ", stepImg="
				+ Arrays.toString(stepImg) + ", stepContent=" + stepContent + ", createdTimestamp=" + createdTimestamp
				+ "]";
	}
	
	static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;

		private RecipeVO recipe;
		private Integer step;
		
		// 一定要有無參數建構子
		public CompositeDetail() {
			super();
		}

		public CompositeDetail(RecipeVO recipe, Integer step) {
			super();
			this.recipe = recipe;
			this.step = step;
		}

		public RecipeVO getRecipe() {
			return recipe;
		}

		public void setRecipe(RecipeVO recipe) {
			this.recipe = recipe;
		}

		public Integer getStep() {
			return step;
		}

		public void setStep(Integer step) {
			this.step = step;
		}

		// 一定要 override 此類別的 hashCode() 與 equals() 方法！
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
			result = prime * result + ((step == null) ? 0 : step.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;

			if (obj != null && getClass() == obj.getClass()) {
				CompositeDetail compositeKey = (CompositeDetail) obj;
				if (recipe.equals(compositeKey.recipe) && step.equals(compositeKey.step)) {
					return true;
				}
			}

			return false;
		}
	}
	
}
