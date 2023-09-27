package com.cooklab.recipe_reaction.model;

import java.util.*;

public interface RecipeReactionDAO {
    public void insert(RecipeReactionVO recipeReactionVO);
    public void update(RecipeReactionVO recipeReactionVO);
    public void delete(Integer recipeNo,Integer memberId);
    public RecipeReactionVO findByPrimaryKey(Integer recipeNo,Integer memberId);
    public List<RecipeReactionVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 

}
