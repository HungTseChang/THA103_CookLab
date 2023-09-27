package com.cooklab.recipe_kitchenware.model;

import java.util.List;

public interface RecipeKitchenwareDAO {
	public void insert(RecipeKitchenwareVO recipeKitchenwareVO);
    public void update(RecipeKitchenwareVO recipeKitchenwareVO);
    public void delete(Integer recipeKitchenwareNo);
    public RecipeKitchenwareVO findByPrimaryKey(Integer recipeKitchenwareNo);
    public List<RecipeKitchenwareVO> getAll();
}
