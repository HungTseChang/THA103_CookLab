package com.cooklab.recipe_step.model;

import java.util.List;

import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.HibernateUtil;

public class RecipeStepService {
	private RecipeStepDAO dao;

	public RecipeStepService() {
		dao = new RecipeStepHDAOIm(HibernateUtil.getSessionFactory());
	}

	public RecipeStepVO addRecipeStep(RecipeVO recipe, Integer step, Integer stepTime, byte[] stepImg,
			String stepContent) {

		RecipeStepVO recipeStepVO = new RecipeStepVO();

		recipeStepVO.setRecipe(recipe);
		recipeStepVO.setStep(step);
		recipeStepVO.setStepTime(stepTime);
		recipeStepVO.setStepImg(stepImg);
		recipeStepVO.setStepContent(stepContent);
		
		dao.insert(recipeStepVO);

		return recipeStepVO;
	}

//	public RecipeStepVO updateRecipeStep(RecipeVO recipe, Integer step, Integer stepTime, byte[] stepImg,
//			String stepContent) {
//
//		RecipeStepVO recipeStepVO = new RecipeStepVO();
//		return recipeStepVO;
//	}

	public void deleteRecipeStep(RecipeVO recipeVO) {
		dao.delete(recipeVO);
	}

//	public RecipeStepVO getOneRecipeIngredient(RecipeVO recipeVO) {
//		return dao.findByPrimaryKey(recipeVO);
//	}
//
//	public List<RecipeStepVO> getAll() {
//		return dao.getAll();
//	}
}
