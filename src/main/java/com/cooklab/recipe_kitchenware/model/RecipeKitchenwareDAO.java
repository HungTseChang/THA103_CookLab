package com.cooklab.recipe_kitchenware.model;

import java.util.List;

import com.cooklab.recipe.model.RecipeVO;

public interface RecipeKitchenwareDAO {
	public int insert(RecipeKitchenwareVO recipeKitchenwareVO);
    public boolean update(RecipeKitchenwareVO recipeKitchenwareVO);
    public boolean delete(RecipeVO recipe);
    public RecipeKitchenwareVO findByPrimaryKey(Integer recipeKitchenwareNo);
    public List<RecipeKitchenwareVO> getAll();
}
