package com.cooklab.recipe.model;

import java.util.*;

public interface RecipeDAO  {
	public void insert(RecipeVO recipeVO);
    public void update(RecipeVO recipeVO);
    public void delete(Integer recipeNo);
    public RecipeVO findByPrimaryKey(Integer recipeNo);
    public List<RecipeVO> getAll();
}
