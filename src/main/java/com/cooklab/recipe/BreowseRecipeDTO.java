package com.cooklab.recipe;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_hashtag.model.RecipeHashtagVO;
import com.cooklab.recipe_ingredient.model.RecipeIngredientVO;
import com.cooklab.recipe_kitchenware.model.RecipeKitchenwareVO;
import com.cooklab.recipe_step.model.RecipeStepVO;

public class BreowseRecipeDTO {
	private String recipeName;
	private String coverImage;
	private String introduction;
	private String additionalExplanation;
	private Byte recipeQuantity;
	private Timestamp createdTimestamp;
	List<IngredientDTO> ingredient = new ArrayList<>();
	List<String> kitchenware = new ArrayList<>();
	List<StepDTO> step = new ArrayList<>();
	List<String> recipeHashtag = new ArrayList<>();

	public BreowseRecipeDTO() {
	}

	public BreowseRecipeDTO(RecipeVO recipeVO) {

		this.recipeName = recipeVO.getRecipeName();
		this.coverImage = Base64.getEncoder().encodeToString(recipeVO.getCoverImage());
		this.introduction = recipeVO.getIntroduction();
		this.additionalExplanation = recipeVO.getAdditionalExplanation();
		this.recipeQuantity = recipeVO.getRecipeQuantity();
		this.createdTimestamp = recipeVO.getCreatedTimestamp();
		for (RecipeIngredientVO recipeIngredient : recipeVO.getIngredient()) {
			this.ingredient.add(new IngredientDTO(recipeIngredient));
		}
		for (RecipeKitchenwareVO recipeKitchenwareVO : recipeVO.getKitchenware()) {
			this.kitchenware
					.add(recipeKitchenwareVO.getProduct() != null ? recipeKitchenwareVO.getProduct().getProductName()
							: recipeKitchenwareVO.getTextLabel());
		}
		for(RecipeStepVO recipeStepVO : recipeVO.getStep()) {
			this.step.add(new StepDTO(recipeStepVO));
		}
		for(RecipeHashtagVO recipeHashtagVO: recipeVO.getHashtag()) {
			this.recipeHashtag.add(recipeHashtagVO.getHashtag().getHashtagName());
		}

	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAdditionalExplanation() {
		return additionalExplanation;
	}

	public void setAdditionalExplanation(String additionalExplanation) {
		this.additionalExplanation = additionalExplanation;
	}

	public Byte getRecipeQuantity() {
		return recipeQuantity;
	}

	public void setRecipeQuantity(Byte recipeQuantity) {
		this.recipeQuantity = recipeQuantity;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public List<IngredientDTO> getIngredient() {
		return ingredient;
	}

	public void setIngredient(List<IngredientDTO> ingredient) {
		this.ingredient = ingredient;
	}

	public List<String> getKitchenware() {
		return kitchenware;
	}

	public void setKitchenware(List<String> kitchenware) {
		this.kitchenware = kitchenware;
	}

	public List<StepDTO> getStep() {
		return step;
	}

	public void setStepDTO(List<StepDTO> step) {
		this.step = step;
	}

	public List<String> getRecipeHashtag() {
		return recipeHashtag;
	}

	public void setRecipeHashtag(List<String> recipeHashtag) {
		this.recipeHashtag = recipeHashtag;
	}

	public class IngredientDTO {
		private String ingredient;
		private String ingredientQuantity;

		public IngredientDTO() {
		}

		public IngredientDTO(RecipeIngredientVO recipeIngredientVO) {
			this.ingredient = recipeIngredientVO.getProduct() != null ? recipeIngredientVO.getProduct().getProductName()
					: recipeIngredientVO.getTextLabel();
			this.ingredientQuantity = recipeIngredientVO.getIngredientQuantity();
		}

		public String getIngredient() {
			return ingredient;
		}

		public void setIngredient(String ingredient) {
			this.ingredient = ingredient;
		}

		public String getIngredientQuantity() {
			return ingredientQuantity;
		}

		public void setIngredientQuantity(String ingredientQuantity) {
			this.ingredientQuantity = ingredientQuantity;
		}

	}

	public class StepDTO {
		private String stepImg;
		private Integer stepTime;
		private String stepContent;
		public StepDTO() {

		}
		public StepDTO(RecipeStepVO recipeStepVO) {
			this.stepImg = recipeStepVO.getStepImg()!=null? Base64.getEncoder().encodeToString(recipeStepVO.getStepImg()):null;
			this.stepTime = recipeStepVO.getStepTime();
			this.stepContent = recipeStepVO.getStepContent();	
		}
		
		
	}

}
