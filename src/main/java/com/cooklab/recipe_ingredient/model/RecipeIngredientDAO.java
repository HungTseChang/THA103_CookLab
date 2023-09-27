package com.cooklab.recipe_ingredient.model;

import java.util.List;

public interface RecipeIngredientDAO {
	public void insert(RecipeIngredientVO recipeIngredientVO);
    public void update(RecipeIngredientVO recipeIngredientVO);
    public void delete(Integer recipeIngredientNo);
    public RecipeIngredientVO findByPrimaryKey(Integer recipeIngredientNo);
    public List<RecipeIngredientVO> getAll();
}
