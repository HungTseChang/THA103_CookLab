package com.cooklab.recipe_kitchenware.model;

import com.cooklab.product.model.ProductVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.HibernateUtil;

public class RecipeKitchenwareService {
	private RecipeKitchenwareDAO dao;

	public RecipeKitchenwareService() {
		dao = new RecipeKitchenwareHDAOIm(HibernateUtil.getSessionFactory());
	}

	public RecipeKitchenwareVO addRecipeKitchenware(RecipeVO recipe, ProductVO product, String textLabel) {

		RecipeKitchenwareVO recipeKitchenwareVO = new RecipeKitchenwareVO();

		recipeKitchenwareVO.setRecipe(recipe);
		recipeKitchenwareVO.setProduct(product);
		recipeKitchenwareVO.setTextLabel(textLabel);
		dao.insert(recipeKitchenwareVO);
		
		return recipeKitchenwareVO;
	}

//	public RecipeKitchenwareVO updateRecipeIngredient(RecipeVO recipe, ProductVO product, String textLabel) {
//
//		RecipeKitchenwareVO recipeIngredientVO = new RecipeKitchenwareVO();
//		return recipeIngredientVO;
//	}

	public void deleteRecipe(RecipeVO recipeVO) {
		dao.delete(recipeVO);
	}

//	public RecipeKitchenwareVO getOneRecipeIngredient(Integer recipeNo) {
//		return dao.findByPrimaryKey(recipeNo);
//	}
//
//	public List<RecipeKitchenwareVO> getAll() {
//		return dao.getAll();
//	}
}


