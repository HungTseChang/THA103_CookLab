package com.cooklab.recipe_comments.model;

import java.util.*;

public interface RecipeCommentsDAO {
    public int insert(RecipeCommentsVO recipeCommentsVO);
    public boolean update(RecipeCommentsVO recipeCommentsVO);
    public boolean delete(Integer recipeCommentsNo);
    public RecipeCommentsVO findByPrimaryKey(Integer recipeCommentsNo);
    public List<RecipeCommentsVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
