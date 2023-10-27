package com.cooklab.recipe.model;

import java.util.*;

public interface RecipeDAO {
	public int insert(RecipeVO recipeVO);

	public boolean update(RecipeVO recipeVO);

	public boolean delete(Integer recipeNo);

	public RecipeVO findByPrimaryKey(Integer recipeNo);

	public List<RecipeVO> getAll();

	public List<RecipeVO> getByPage(String cloumn, boolean desc, Integer offset, Integer limit);

	public long getCount();
}
