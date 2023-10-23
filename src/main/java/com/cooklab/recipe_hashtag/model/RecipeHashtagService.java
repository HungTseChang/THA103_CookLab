package com.cooklab.recipe_hashtag.model;

import com.cooklab.hashtag.model.HashtagVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.HibernateUtil;

public class RecipeHashtagService {

	private RecipeHashtagDAO dao;

	public RecipeHashtagService() {
		dao = new RecipeHashtagHDAOIm(HibernateUtil.getSessionFactory());
	}

	public RecipeHashtagVO addRecipeHashtag(HashtagVO hashtagVO, RecipeVO recipeVO) {

		RecipeHashtagVO recipeHashtagVO = new RecipeHashtagVO();
		recipeHashtagVO.setHashtag(hashtagVO);
		recipeHashtagVO.setRecipe(recipeVO);
		dao.insert(recipeHashtagVO);
		
		return recipeHashtagVO;
	}
//	public RecipeIngredientVO updateRecipeIngredient(RecipeVO recipe, ProductVO product, String textLabel,String ingredientQuantity) {
//
//		RecipeIngredientVO recipeIngredientVO = new RecipeIngredientVO();
//		return recipeIngredientVO;
//	}
//	public void deleteRecipe(RecipeVO recipeVO) {
//		dao.delete(recipeVO);
//	}

//	public RecipeIngredientVO getOneRecipeIngredient(Integer recipeNo) {
//		return dao.findByPrimaryKey(recipeNo);
//	}

//	public List<HashtagVO> getAll() {
//		return dao.getAll();
//	}

}
