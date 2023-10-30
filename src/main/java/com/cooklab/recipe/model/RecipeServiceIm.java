package com.cooklab.recipe.model;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.cooklab.hashtag.model.HashtagHDAOIm;
import com.cooklab.members.model.MembersVO;
import com.cooklab.product.model.ProductHDAOrRecipeTest;
import com.cooklab.product.model.ProductVO;
import com.cooklab.recipe.RecipeCreateDTO;
import com.cooklab.recipe.RecipeCreateDTO.IngredientDTO;
import com.cooklab.recipe.RecipeCreateDTO.StepDTO;
import com.cooklab.recipe.RecipeUpdateDTO;
import com.cooklab.recipe_hashtag.model.RecipeHashtagVO;
import com.cooklab.recipe_ingredient.model.RecipeIngredientVO;
import com.cooklab.recipe_kitchenware.model.RecipeKitchenwareVO;
import com.cooklab.recipe_step.model.RecipeStepVO;
import com.cooklab.util.HibernateUtil;

public class RecipeServiceIm implements RecipeService {
	private RecipeDAO dao;

	public RecipeServiceIm() {
		dao = new RecipeHDAOIm(HibernateUtil.getSessionFactory());
	}

	@Override
	public RecipeVO addRecipe(MembersVO members, String recipeName, byte[] coverImage, String introduction,
			String additionalExplanation, String region, Byte recipeStatus, Byte recipeQuantity) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setMembers(members);
		recipeVO.setRecipeName(recipeName);
		recipeVO.setCoverImage(coverImage);
		recipeVO.setIntroduction(introduction);
		recipeVO.setAdditionalExplanation(additionalExplanation);
		recipeVO.setRegion(region);
		recipeVO.setRecipeStatus(recipeStatus);
		recipeVO.setRecipeQuantity(recipeQuantity);
		dao.insert(recipeVO);

		return recipeVO;
	}

	@Override
	public RecipeVO updateRecipe(Integer recipeNo, MembersVO members, String recipeName, byte[] coverImage,
			String introduction, String additionalExplanation, String region, Byte recipeStatus, Integer reportCount,
			Integer viewCount, Byte recipeQuantity, Timestamp lastEditTimestamp) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setRecipeNo(recipeNo);
		recipeVO.setMembers(members);
		recipeVO.setRecipeName(recipeName);
		if (coverImage != null) {
			recipeVO.setCoverImage(coverImage);
		} else {
			coverImage = dao.findByPrimaryKey(recipeNo).getCoverImage();
			recipeVO.setCoverImage(coverImage);
		}
		recipeVO.setIntroduction(introduction);
		recipeVO.setAdditionalExplanation(additionalExplanation);
		recipeVO.setRegion(region);
		recipeVO.setRecipeStatus(recipeStatus);
		recipeVO.setReportCount(reportCount);
		recipeVO.setViewCount(viewCount);
		recipeVO.setRecipeQuantity(recipeQuantity);
		recipeVO.setLastEditTimestamp(lastEditTimestamp);
		dao.update(recipeVO);

		return recipeVO;
	}

	@Override
	public void deleteRecipe(Integer recipeNo) {
		dao.delete(recipeNo);
	}

	@Override
	public RecipeVO getOneRecipe(Integer recipeNo) {
		return dao.findByPrimaryKey(recipeNo);
	}

	@Override
	public List<RecipeVO> getAll() {
		return dao.getAll();
	}

	@Override
	public Integer createRecipe(MembersVO memberVO, RecipeCreateDTO recipeCreateDTO) {

		RecipeVO recipeVO = new RecipeVO();
		ProductHDAOrRecipeTest productHDAOIm = new ProductHDAOrRecipeTest(HibernateUtil.getSessionFactory());
		HashtagHDAOIm HashtagHDAOIm = new HashtagHDAOIm(HibernateUtil.getSessionFactory());

		recipeVO.setRecipeName(recipeCreateDTO.getRecipeName());
		recipeVO.setMembers(memberVO);
		recipeVO.setCoverImage(Base64.getDecoder().decode(recipeCreateDTO.getCoverImage()));
		recipeVO.setIntroduction(recipeCreateDTO.getIntroduction());
		recipeVO.setAdditionalExplanation(recipeCreateDTO.getAdditionalExplanation());
		recipeVO.setRegion(recipeCreateDTO.getRegion());
		recipeVO.setRecipeStatus((byte) 0);
		recipeVO.setRecipeQuantity(recipeCreateDTO.getRecipeQuantity());

		Set<RecipeIngredientVO> ingredientSet = new LinkedHashSet<>();
		for (IngredientDTO ingredientDTO : recipeCreateDTO.getIngredient()) {
			RecipeIngredientVO recipeIngredientVO = new RecipeIngredientVO();
			recipeIngredientVO.setRecipe(recipeVO);
			ProductVO productVO = productHDAOIm.findByProductName(ingredientDTO.getIngredient(),
					"ingredientCategoryNo");
			if (productVO != null)
				recipeIngredientVO.setProduct(productVO);
			recipeIngredientVO.setTextLabel(ingredientDTO.getIngredient());
			recipeIngredientVO.setIngredientQuantity(ingredientDTO.getIngredientQuantity());
			ingredientSet.add(recipeIngredientVO);
		}
		recipeVO.setIngredient(ingredientSet);

		Set<RecipeKitchenwareVO> kitchenwareSet = new LinkedHashSet<>();
		for (String recipeKitchenware : recipeCreateDTO.getKitchenware()) {
			RecipeKitchenwareVO recipeKitchenwareVO = new RecipeKitchenwareVO();
			recipeKitchenwareVO.setRecipe(recipeVO);
			ProductVO productVO = productHDAOIm.findByProductName(recipeKitchenware, "kitchenwareCategoryNo");
			if (productVO != null)
				recipeKitchenwareVO.setProduct(productVO);
			recipeKitchenwareVO.setTextLabel(recipeKitchenware);
			kitchenwareSet.add(recipeKitchenwareVO);
		}
		recipeVO.setKitchenware(kitchenwareSet);

		Set<RecipeStepVO> stepSet = new LinkedHashSet<>();
		Integer stepCount = 1;
		for (StepDTO stepDTO : recipeCreateDTO.getStep()) {
			RecipeStepVO recipeStepVO = new RecipeStepVO();
			recipeStepVO.setRecipe(recipeVO);
			recipeStepVO.setStep(++stepCount);
			recipeStepVO
					.setStepImg(stepDTO.getStepImg() != null ? Base64.getDecoder().decode(stepDTO.getStepImg()) : null);
			recipeStepVO.setStepTime(stepDTO.getStepTime());
			recipeStepVO.setStepContent(stepDTO.getStepContent());
			stepSet.add(recipeStepVO);
		}
		recipeVO.setStep(stepSet);

		Set<RecipeHashtagVO> HashtagSet = new LinkedHashSet<>();
		for (String recipeHashtag : recipeCreateDTO.getRecipeHashtag()) {
			RecipeHashtagVO recipeHashtagVO = new RecipeHashtagVO();
			recipeHashtagVO.setRecipe(recipeVO);
			recipeHashtagVO.setHashtag(HashtagHDAOIm.findByHashtagName(recipeHashtag));
			HashtagSet.add(recipeHashtagVO);
		}
		recipeVO.setHashtag(HashtagSet);

		return dao.insert(recipeVO);
	}

	@Override
	public Integer updateRecipe(MembersVO memberVO, RecipeUpdateDTO recipeUpdateDTO) {
		RecipeVO recipeVO = dao.findByPrimaryKey(recipeUpdateDTO.getRecipeNo());
		// 驗證是不是本人
		if (!recipeVO.getMembers().getMemberId().equals(memberVO.getMemberId())) {
			return null;
		}
		ProductHDAOrRecipeTest productHDAOIm = new ProductHDAOrRecipeTest(HibernateUtil.getSessionFactory());
		HashtagHDAOIm HashtagHDAOIm = new HashtagHDAOIm(HibernateUtil.getSessionFactory());
		recipeVO.setRecipeName(recipeUpdateDTO.getRecipeName());
		recipeVO.setCoverImage(Base64.getDecoder().decode(recipeUpdateDTO.getCoverImage()));
		recipeVO.setIntroduction(recipeUpdateDTO.getIntroduction());
		recipeVO.setAdditionalExplanation(recipeUpdateDTO.getAdditionalExplanation());
		recipeVO.setRegion(recipeUpdateDTO.getRegion());
		recipeVO.setRecipeQuantity(recipeUpdateDTO.getRecipeQuantity());
		Set<RecipeIngredientVO> ingredientSet = new LinkedHashSet<>();
		for (RecipeUpdateDTO.IngredientDTO ingredientDTO : recipeUpdateDTO.getIngredient()) {
			RecipeIngredientVO recipeIngredientVO = new RecipeIngredientVO();
			recipeIngredientVO.setRecipe(recipeVO);
			ProductVO productVO = productHDAOIm.findByProductName(ingredientDTO.getIngredient(),
					"ingredientCategoryNo");
			if (productVO != null)
				recipeIngredientVO.setProduct(productVO);
			recipeIngredientVO.setTextLabel(ingredientDTO.getIngredient());
			recipeIngredientVO.setIngredientQuantity(ingredientDTO.getIngredientQuantity());
			ingredientSet.add(recipeIngredientVO);
		}
		recipeVO.setIngredient(ingredientSet);

		Set<RecipeKitchenwareVO> kitchenwareSet = new LinkedHashSet<>();
		for (String recipeKitchenware : recipeUpdateDTO.getKitchenware()) {
			RecipeKitchenwareVO recipeKitchenwareVO = new RecipeKitchenwareVO();
			recipeKitchenwareVO.setRecipe(recipeVO);
			ProductVO productVO = productHDAOIm.findByProductName(recipeKitchenware, "kitchenwareCategoryNo");
			if (productVO != null)
				recipeKitchenwareVO.setProduct(productVO);
			recipeKitchenwareVO.setTextLabel(recipeKitchenware);
			kitchenwareSet.add(recipeKitchenwareVO);
		}
		recipeVO.setKitchenware(kitchenwareSet);

		Set<RecipeStepVO> stepSet = new LinkedHashSet<>();
		Integer stepCount = 1;
		for (RecipeUpdateDTO.StepDTO stepDTO : recipeUpdateDTO.getStep()) {
			RecipeStepVO recipeStepVO = new RecipeStepVO();
			recipeStepVO.setRecipe(recipeVO);
			recipeStepVO.setStep(++stepCount);
			recipeStepVO
					.setStepImg(stepDTO.getStepImg() != null ? Base64.getDecoder().decode(stepDTO.getStepImg()) : null);
			recipeStepVO.setStepTime(stepDTO.getStepTime());
			recipeStepVO.setStepContent(stepDTO.getStepContent());
			stepSet.add(recipeStepVO);
		}
		recipeVO.setStep(stepSet);

		Set<RecipeHashtagVO> HashtagSet = new LinkedHashSet<>();
		for (String recipeHashtag : recipeUpdateDTO.getRecipeHashtag()) {
			RecipeHashtagVO recipeHashtagVO = new RecipeHashtagVO();
			recipeHashtagVO.setRecipe(recipeVO);
			recipeHashtagVO.setHashtag(HashtagHDAOIm.findByHashtagName(recipeHashtag));
			HashtagSet.add(recipeHashtagVO);
		}
		recipeVO.setHashtag(HashtagSet);
		dao.update(recipeVO);
		return recipeVO.getRecipeNo();
	}

	@Override
	public List<RecipeVO> getBySearch(String cloumn, boolean desc, Integer offset, Integer limit, String search) {
		return dao.getBySearch(cloumn, desc, offset, limit, search);
	}

	@Override
	public long getCount(String search) {
		return dao.getCount(search);
	}

	@Override
	public boolean updateViewCount(String recipeNo, String viewCount) {
		RecipeVO recipeVO = dao.findByPrimaryKey(Integer.valueOf(recipeNo));
		recipeVO.setViewCount(recipeVO.getViewCount() + Integer.valueOf(viewCount));
		return dao.update(recipeVO);
	}

}
