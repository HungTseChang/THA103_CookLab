package com.cooklab.recipe_comments.model;

import java.util.*;

public interface RecipeCommentsDAO {
    public void insert(RecipeCommentsVO recipeCommentsVO);
    public void update(RecipeCommentsVO recipeCommentsVO);
    public void delete(Integer recipe_no);
    public RecipeCommentsVO findByPrimaryKey(Integer recipe_no);
    public List<RecipeCommentsVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
