package com.cooklab.recipe.model;

import java.sql.Timestamp;
import java.util.List;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.RecipeCreateDTO;
import com.cooklab.recipe.RecipeUpdateDTO;

public interface RecipeService {
	public RecipeVO addRecipe(MembersVO memberVO, String recipeName, byte[] coverImage, String introduction,
			String additionalExplanation, String region, Byte recipeStatus, Byte recipeQuantity);

	public RecipeVO updateRecipe(Integer recipeNo, MembersVO members, String recipeName, byte[] coverImage,
			String introduction, String additionalExplanation, String region, Byte recipeStatus, Integer reportCount,
			Integer viewCount, Byte recipeQuantity, Timestamp lastEditTimestamp);

	public void deleteRecipe(Integer recipeNo);

	public RecipeVO getOneRecipe(Integer recipeNo);
	
	public Integer createRecipe(MembersVO memberVO,RecipeCreateDTO createRecipeDTO);
	
	public Integer updateRecipe(MembersVO memberVO,RecipeVO recipeVO,RecipeUpdateDTO recipeUpdateDTO);

	public List<RecipeVO> getAll();

	public List<RecipeVO> getBySearch(String cloumn, boolean desc, Integer offset, Integer limit, String search);

	public long getCount(String search);
	
	public boolean  updateViewCount(String recipeNo,String viewCount);
}
