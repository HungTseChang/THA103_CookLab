package com.cooklab.recipe;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.cooklab.recipe_ingredient.model.RecipeIngredientVO;
import com.cooklab.recipe_step.model.RecipeStepVO;

public class RecipeCreateDTO {
	private String recipeName;
	private String coverImage;
	private String introduction;
	private Byte recipeQuantity;
	private String additionalExplanation;
	private String region;
	List<IngredientDTO> ingredient = new ArrayList<>();
	List<String> kitchenware = new ArrayList<>();
	List<StepDTO> step = new ArrayList<>();
	List<String> recipeHashtag = new ArrayList<>();

	public RecipeCreateDTO() {
	}

	public String getRecipeName() {
		return recipeName;
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

	public Byte getRecipeQuantity() {
		return recipeQuantity;
	}

	public void setRecipeQuantity(Byte recipeQuantity) {
		this.recipeQuantity = recipeQuantity;
	}

	public String getAdditionalExplanation() {
		return additionalExplanation;
	}

	public void setAdditionalExplanation(String additionalExplanation) {
		this.additionalExplanation = additionalExplanation;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
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

	public void setStep(List<StepDTO> step) {
		this.step = step;
	}

	public List<String> getRecipeHashtag() {
		return recipeHashtag;
	}

	public void setRecipeHashtag(List<String> recipeHashtag) {
		this.recipeHashtag = recipeHashtag;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
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
			this.stepImg = recipeStepVO.getStepImg() != null
					? Base64.getEncoder().encodeToString(recipeStepVO.getStepImg())
					: null;
			this.stepTime = recipeStepVO.getStepTime();
			this.stepContent = recipeStepVO.getStepContent();
		}

		public String getStepImg() {
			return stepImg;
		}

		public void setStepImg(String stepImg) {
			this.stepImg = stepImg;
		}

		public Integer getStepTime() {
			return stepTime;
		}

		public void setStepTime(Integer stepTime) {
			this.stepTime = stepTime;
		}

		public String getStepContent() {
			return stepContent;
		}

		public void setStepContent(String stepContent) {
			this.stepContent = stepContent;
		}

	}

	@Override
	public String toString() {
		return "CreateRecipeDTO [recipeName=" + recipeName + ", coverImage=" + coverImage + ", introduction="
				+ introduction + ", additionalExplanation=" + additionalExplanation + ", recipeQuantity="
				+ recipeQuantity + " , ingredient=" + ingredient + ", kitchenware=" + kitchenware + ", step=" + step
				+ ", recipeHashtag=" + recipeHashtag + "]";
	}

}
