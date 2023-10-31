package com.cooklab.recipe;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.cooklab.product.model.ProductVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_comments.RecipeCommentsDTO;
import com.cooklab.recipe_comments.model.RecipeCommentsVO;
import com.cooklab.recipe_hashtag.model.RecipeHashtagVO;
import com.cooklab.recipe_ingredient.model.RecipeIngredientVO;
import com.cooklab.recipe_kitchenware.model.RecipeKitchenwareVO;
import com.cooklab.recipe_step.model.RecipeStepVO;

public class RecipeBreowseDTO {
	private Integer recipeNo;
	private String recipeName;
	private Integer memberId;
	private String memberName;
	private String memberPicture;
	private String coverImage;
	private String introduction;
	private String additionalExplanation;
	private Byte recipeQuantity;
	private Timestamp createdTime;
	List<IngredientDTO> ingredient = new ArrayList<>();
	List<KitchenwareDTO> kitchenware = new ArrayList<>();
	List<StepDTO> step = new ArrayList<>();
	List<String> recipeHashtag = new ArrayList<>();
	List<RecipeCommentsDTO> comments = new ArrayList<>();

	public RecipeBreowseDTO() {
	}

	public RecipeBreowseDTO(RecipeVO recipeVO) {
		this.recipeNo=recipeVO.getRecipeNo();
		this.recipeName = recipeVO.getRecipeName();
		this.memberId = recipeVO.getMembers().getMemberId();
		this.memberName = recipeVO.getMembers().getMemberNickname();
		this.memberPicture = Base64.getEncoder().encodeToString(recipeVO.getMembers().getMemberPicture());
		this.coverImage = Base64.getEncoder().encodeToString(recipeVO.getCoverImage());
		this.introduction = recipeVO.getIntroduction();
		this.additionalExplanation = recipeVO.getAdditionalExplanation();
		this.recipeQuantity = recipeVO.getRecipeQuantity();
		this.createdTime = recipeVO.getCreatedTimestamp();
		for (RecipeIngredientVO recipeIngredient : recipeVO.getIngredient()) {
			this.ingredient.add(new IngredientDTO(recipeIngredient));
		}
		for (RecipeKitchenwareVO recipeKitchenwareVO : recipeVO.getKitchenware()) {
			this.kitchenware.add(new KitchenwareDTO(recipeKitchenwareVO));
		}
		for (RecipeStepVO recipeStepVO : recipeVO.getStep()) {
			this.step.add(new StepDTO(recipeStepVO));
		}
		for (RecipeHashtagVO recipeHashtagVO : recipeVO.getHashtag()) {
			this.recipeHashtag.add(recipeHashtagVO.getHashtag().getHashtagName());
		}
		for (RecipeCommentsVO recipeCommentsVO : recipeVO.getComments()) {
			this.comments.add(new RecipeCommentsDTO(recipeCommentsVO));
		}

	}

	public Integer getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(Integer recipeNo) {
		this.recipeNo = recipeNo;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPicture() {
		return memberPicture;
	}

	public void setMemberPicture(String memberPicture) {
		this.memberPicture = memberPicture;
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

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public List<IngredientDTO> getIngredient() {
		return ingredient;
	}

	public void setIngredient(List<IngredientDTO> ingredient) {
		this.ingredient = ingredient;
	}

	public List<KitchenwareDTO> getKitchenware() {
		return kitchenware;
	}

	public void setKitchenware(List<KitchenwareDTO> kitchenware) {
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

	public List<RecipeCommentsDTO> getComments() {
		return comments;
	}

	public void setComments(List<RecipeCommentsDTO> comments) {
		this.comments = comments;
	}

	public class IngredientDTO {
		private Integer productNo;
		private String ingredient;
		private String ingredientQuantity;

		public IngredientDTO() {
		}

		public IngredientDTO(RecipeIngredientVO recipeIngredientVO) {
			ProductVO productVO = recipeIngredientVO.getProduct();
			this.productNo = productVO != null ? productVO.getProductNo() : null;
			this.ingredient = recipeIngredientVO.getTextLabel();
			this.ingredientQuantity = recipeIngredientVO.getIngredientQuantity();
			System.out.println(productVO);
		}

		public Integer getProductNo() {
			return productNo;
		}

		public void setProductNo(Integer productNo) {
			this.productNo = productNo;
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

	public class KitchenwareDTO {
		private Integer productNo;
		private String kitchenware;

		public KitchenwareDTO() {
		}

		public KitchenwareDTO(RecipeKitchenwareVO recipeKitchenwareVO) {
			ProductVO productVO = recipeKitchenwareVO.getProduct();
			this.productNo = productVO != null ? productVO.getProductNo() : null;
			this.kitchenware = recipeKitchenwareVO.getTextLabel();
		}

		public Integer getProductNo() {
			return productNo;
		}

		public void setProductNo(Integer productNo) {
			this.productNo = productNo;
		}

		public String getKitchenware() {
			return kitchenware;
		}

		public void setKitchenware(String kitchenware) {
			this.kitchenware = kitchenware;
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

}
