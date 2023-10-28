package com.cooklab.recipe_collection.model;

import java.util.List;

import com.cooklab.members.model.MembersVO;
import com.cooklab.product.model.ProductVO;
import com.cooklab.recipe.model.RecipeVO;

public interface RecipeCollectionSrevice {
	public Integer addRecipeCollection(RecipeVO recipeVO,  MembersVO membersVO);

	public RecipeCollectionVO updateRecipeCollection(Integer recipeCollectionNo, RecipeVO recipeVO,
			ProductVO productVO);
	public boolean deleteRecipeCollection(Integer RecipeCollectionNo);

	public RecipeCollectionVO getOneRecipeCollection(Integer recipeCollectionNo);

	public List<RecipeCollectionVO> getAll();
	
	public RecipeCollectionVO findByMemberAndRecipe(RecipeVO recipeVO,  MembersVO membersVO);

}
