package com.cooklab.recipe_comments.model;

import java.util.List;

import com.cooklab.members.model.MembersVO;
import com.cooklab.product.model.ProductVO;
import com.cooklab.recipe.model.RecipeVO;

public interface RecipeCommentsService {
	public RecipeCommentsVO addRecipeComments(RecipeVO recipeVO, MembersVO membersVO, String commentContent);

	public RecipeCommentsVO updateRecipeComments(Integer recipeCommentsNo, RecipeVO recipeVO, ProductVO productVO,
			String commentContent);

	public boolean deleteRecipeComments(Integer recipeCommentsNo);

	public RecipeCommentsVO getOneRecipeComments(Integer recipeCommentsNo);

	public List<RecipeCommentsVO> getAll();

}
