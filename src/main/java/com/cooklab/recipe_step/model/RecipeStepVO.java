package com.cooklab.recipe_step.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;

public class RecipeStepVO implements java.io.Serializable{
	private Integer recipeNo;
	private Integer step;
	private Integer stepTime;
	private byte[] stepImg;
	private String stepContent;
	private Timestamp createdTimestamp;
	
	public RecipeStepVO() {	
	} 
	
	public RecipeStepVO(Integer recipeNo, Integer step, Integer stepTime, byte[] stepImg, String stepContent,
			Timestamp createdTimestamp) {
		super();
		this.recipeNo = recipeNo;
		this.step = step;
		this.stepTime = stepTime;
		this.stepImg = stepImg;
		this.stepContent = stepContent;
		this.createdTimestamp = createdTimestamp;
	}

	public Integer getRecipeNo() {
		return recipeNo;
	}
	public void setRecipeNo(Integer recipeNo) {
		this.recipeNo = recipeNo;
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
		return "RecipeStepVO [recipeNo=" + recipeNo + ", step=" + step + ", stepTime=" + stepTime + ", stepImg="
				+ Arrays.toString(stepImg) + ", stepContent=" + stepContent + ", createdTimestamp=" + createdTimestamp
				+ "]";
	}
	
	
}
