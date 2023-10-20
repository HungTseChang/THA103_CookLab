package com.cooklab.recipe_step.model;

import java.util.List;

import com.cooklab.recipe.model.RecipeVO;

public interface RecipeStepDAO {
	public int insert(RecipeStepVO recipeStepVO);

	public boolean update(RecipeStepVO recipeStepVO);

	public boolean delete(RecipeVO recipeVO);

	public RecipeStepVO findByPrimaryKey(RecipeVO recipeVO);

	public List<RecipeStepVO> getAll();
}
