package com.cooklab.recipe.model;

import java.util.Date;
import java.util.List;
import com.cooklab.recipe.*;

public class RecipeService {
	private RecipeDAO dao;

	public RecipeService() {
		dao = new RecipeHDAOIm();
	}

	public RecipeVO addRecipe(Integer memberId, String recipeName, byte[] coverImage, String introduction,
			String additionalExplanation, String region, Byte recipeStatus, Integer reportCount, Integer viewCount,
			Byte recipeQuantity, Date lastEditTimestamp) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setMemberId(memberId);
		recipeVO.setRecipeName(recipeName);
		recipeVO.setCoverImage(coverImage);
		recipeVO.setIntroduction(introduction);
		recipeVO.setAdditionalExplanation(additionalExplanation);
		recipeVO.setRegion(region);
		recipeVO.setRecipeStatus(recipeStatus);
		recipeVO.setReportCount(reportCount);
		recipeVO.setViewCount(viewCount);
		recipeVO.setRecipeQuantity(recipeQuantity);
		recipeVO.setLastEditTimestamp(lastEditTimestamp);
		dao.insert(recipeVO);
		
		return recipeVO;
	}

	public RecipeVO updateRecipe(Integer recipeNo, Integer memberId, String recipeName, byte[] coverImage, String introduction,
			String additionalExplanation, String region, Byte recipeStatus, Integer reportCount, Integer viewCount,
			Byte recipeQuantity, Date lastEditTimestamp) {

		RecipeVO recipeVO = new RecipeVO();

		recipeVO.setRecipeNo(recipeNo);
		recipeVO.setMemberId(memberId);
		recipeVO.setRecipeName(recipeName);
		recipeVO.setCoverImage(coverImage);
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

	public void deleteRecipe(Integer recipeNo) {
		dao.delete(recipeNo);
	}

	public RecipeVO getOneRecipe(Integer recipeNo) {
		return dao.findByPrimaryKey(recipeNo);
	}

	public List<RecipeVO> getAll() {
		return dao.getAll();
	}
}
