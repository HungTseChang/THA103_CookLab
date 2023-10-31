package com.cooklab.recipe_hashtag.model;

import java.util.*;

import com.cooklab.recipe.model.RecipeVO;


public interface RecipeHashtagDAO {
    public int insert(RecipeHashtagVO recipeHashtagVO);
    public boolean update(RecipeHashtagVO recipeHashtagVO);
    public int delete(RecipeVO recipeVO);
    public RecipeHashtagVO findByPrimaryKey(Integer repiceHashTagNo);
    public List<RecipeHashtagVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
