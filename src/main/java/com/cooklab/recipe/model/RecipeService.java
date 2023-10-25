package com.cooklab.recipe.model;

import java.sql.Timestamp;
import java.util.List;

import com.cooklab.members.model.MembersVO;
import com.cooklab.util.HibernateUtil;

public class RecipeService {
	private RecipeDAO dao;

	public RecipeService() {
		dao = new RecipeHDAOIm(HibernateUtil.getSessionFactory());
	}

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

	public void deleteRecipe(Integer recipeNo) {
		dao.delete(recipeNo);
	}

	public RecipeVO getOneRecipe(Integer recipeNo) {
		return dao.findByPrimaryKey(recipeNo);
	}

	public List<RecipeVO> getAll() {
		return dao.getAll();
	}
	public List<RecipeVO> getByPage(Integer offset,Integer limit) {
		return dao.getByPage(offset, limit) ;
	}

	public long getCount() {
		return dao.getCount();
	}
}
