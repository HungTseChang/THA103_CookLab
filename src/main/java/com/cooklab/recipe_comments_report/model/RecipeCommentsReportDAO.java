package com.cooklab.recipe_comments_report.model;

import java.util.List;

public interface RecipeCommentsReportDAO {
    public void insert(RecipeCommentsReportVO recipeCommentsReportVO);
    public void update(RecipeCommentsReportVO recipeCommentsReportVO);
    public void delete(Integer recipeReportNo);
    public RecipeCommentsReportVO findByPrimaryKey(Integer recipeReportNo);
    public List<RecipeCommentsReportVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
