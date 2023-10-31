package com.cooklab.recipe_ingredient.model;

import java.util.List;

import com.cooklab.recipe.model.RecipeVO;

public interface RecipeIngredientDAO {
	public int insert(RecipeIngredientVO recipeIngredientVO);
    public boolean update(RecipeIngredientVO recipeIngredientVO);
    public int delete(RecipeVO recipe);
    public RecipeIngredientVO findByPrimaryKey(Integer recipeIngredientNo);
    public List<RecipeIngredientVO> getAll();
}
