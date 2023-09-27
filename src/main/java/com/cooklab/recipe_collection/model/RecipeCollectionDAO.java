package com.cooklab.recipe_collection.model;

import java.util.List;

public interface RecipeCollectionDAO {
	public void insert(RecipeCollectionVO recipeCollectionVO);
    public void update(RecipeCollectionVO recipeCollectionVO);
    public void delete(Integer collectionNo );
    public RecipeCollectionVO findByPrimaryKey(Integer collectionNo );
    public List<RecipeCollectionVO> getAll();
}
