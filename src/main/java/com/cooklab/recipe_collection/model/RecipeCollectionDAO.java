package com.cooklab.recipe_collection.model;

import java.util.List;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.model.RecipeVO;

public interface RecipeCollectionDAO {
	public int insert(RecipeCollectionVO recipeCollectionVO);

	public boolean update(RecipeCollectionVO recipeCollectionVO);

	public boolean delete(Integer recipeCollectionNo);

	public RecipeCollectionVO findByPrimaryKey(Integer collectionNo);

	public List<RecipeCollectionVO> getAll();

	public RecipeCollectionVO findByMemberAndRecipe(MembersVO membersVO, RecipeVO recipeVO);
}
