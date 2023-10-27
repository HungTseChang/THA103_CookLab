package com.cooklab.members;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_hashtag.model.RecipeHashtagVO;
import com.cooklab.recipe_step.model.RecipeStepVO;

public class MemberRecipeOverViewDTO {
	
	private	Integer recipeNo;
    private String coverImage;		//封面圖片
    private String recipeName;		//食譜名稱
    private String introduction;	//簡介
    private byte recipeStatus;		//食譜狀態
    private Integer toatalStepTime = 0;
	List<String> recipeHashtag = new ArrayList<>();
	List<StepDTO> step = new ArrayList<>();
	
	public MemberRecipeOverViewDTO() {
	}
    
	public MemberRecipeOverViewDTO(RecipeVO recipeVO) {
		super();
		this.recipeNo = recipeVO.getRecipeNo();
		this.coverImage = Base64.getEncoder().encodeToString(recipeVO.getCoverImage());
		this.recipeName = recipeVO.getRecipeName();
		this.introduction = recipeVO.getIntroduction();
		this.recipeStatus = recipeVO.getRecipeStatus();
		
		for(RecipeStepVO recipeStepVO: recipeVO.getStep()) {
			toatalStepTime = recipeStepVO.getStepTime() + toatalStepTime;
		}

		for(RecipeHashtagVO recipeHashtagVO: recipeVO.getHashtag()) {
			this.recipeHashtag.add(recipeHashtagVO.getHashtag().getHashtagName());
		}
	}

	public Integer getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(Integer recipeNo) {
		this.recipeNo = recipeNo;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public byte getRecipeStatus() {
		return recipeStatus;
	}

	public void setRecipeStatus(byte recipeStatus) {
		this.recipeStatus = recipeStatus;
	}

	public List<String> getRecipeHashtag() {
		return recipeHashtag;
	}

	public void setRecipeHashtag(List<String> recipeHashtag) {
		this.recipeHashtag = recipeHashtag;
	}
	public List<StepDTO> getStep() {
		return step;
	}

	public void setStepDTO(List<StepDTO> step) {
		this.step = step;
	}

	public class StepDTO {
		private Integer stepTime;
		public StepDTO() {

		}
		public StepDTO(RecipeStepVO recipeStepVO) {
			this.stepTime = recipeStepVO.getStepTime();
		}
		
		
	}

    

    
}
