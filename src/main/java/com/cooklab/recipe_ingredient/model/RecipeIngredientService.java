package com.cooklab.recipe_ingredient.model;

import com.cooklab.product.model.ProductVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.HibernateUtil;

public class RecipeIngredientService {
	private RecipeIngredientDAO dao;

	public RecipeIngredientService() {
		dao = new RecipeIngredientHDAOIm(HibernateUtil.getSessionFactory());
	}

	public RecipeIngredientVO addRecipeIngredient(RecipeVO recipe, ProductVO product, String textLabel,String ingredientQuantity) {

		RecipeIngredientVO recipeIngredientVO = new RecipeIngredientVO();

		recipeIngredientVO.setRecipe(recipe);
		recipeIngredientVO.setProduct(product);
		recipeIngredientVO.setTextLabel(textLabel);
		recipeIngredientVO.setIngredientQuantity(ingredientQuantity);
		dao.insert(recipeIngredientVO);
		
		return recipeIngredientVO;
	}

//	public RecipeIngredientVO updateRecipeIngredient(RecipeVO recipe, ProductVO product, String textLabel,String ingredientQuantity) {
//
//		RecipeIngredientVO recipeIngredientVO = new RecipeIngredientVO();
//		return recipeIngredientVO;
//	}

	public void deleteRecipe(RecipeVO recipeVO) {
		dao.delete(recipeVO);
	}

//	public RecipeIngredientVO getOneRecipeIngredient(Integer recipeNo) {
//		return dao.findByPrimaryKey(recipeNo);
//	}
//
//	public List<RecipeIngredientVO> getAll() {
//		return dao.getAll();
//	}
}


