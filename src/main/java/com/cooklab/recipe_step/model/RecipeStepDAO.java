package com.cooklab.recipe_step.model;

import java.util.List;

public interface RecipeStepDAO {
	public void insert(RecipeStepVO recipeStepVO);
    public void update(RecipeStepVO recipeStepVO);
    public void delete(Integer recipeNo);
    public RecipeStepVO findByPrimaryKey(Integer recipeNo);
    public List<RecipeStepVO> getAll();
}
