package com.cooklab.recipe_hashtag.model;

import java.util.*;


public interface RecipeHashtagDAO {
    public void insert(RecipeHashtagVO recipeHashtagVO);
    public void update(RecipeHashtagVO recipeHashtagVO);
    public void delete(Integer repiceHashTagNo);
    public RecipeHashtagVO findByPrimaryKey(Integer repiceHashTagNo);
    public List<RecipeHashtagVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
