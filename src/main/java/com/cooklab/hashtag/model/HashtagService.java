package com.cooklab.hashtag.model;

import java.util.List;

import com.cooklab.util.HibernateUtil;

public class HashtagService {
	private HashtagDAO dao;

	public HashtagService() {
		dao = new HashtagHDAOIm(HibernateUtil.getSessionFactory());
	}

//	public HashtagVO addHashtag(RecipeVO recipe, ProductVO product, String textLabel,String ingredientQuantity) {
//
//		RecipeIngredientVO recipeIngredientVO = new RecipeIngredientVO();
//
//		recipeIngredientVO.setRecipe(recipe);
//		recipeIngredientVO.setProduct(product);
//		recipeIngredientVO.setTextLabel(textLabel);
//		recipeIngredientVO.setIngredientQuantity(ingredientQuantity);
//		dao.insert(recipeIngredientVO);
//		
//		return recipeIngredientVO;
//	}
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
	public List<HashtagVO> getOfficalHashtag() {
		return dao.getOfficalHashtag();
	}

	public List<HashtagVO> getPopularHashtag(int n) {
		return dao.getPopularHashtag(n);
	}
	public HashtagVO findByHashtagName(String hashtagName) {
		return dao.findByHashtagName(hashtagName);
	}
}
