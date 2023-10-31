package com.cooklab.recipe_report.model;

import java.util.*;


public interface RecipeReoprtDAO_interface {
    public void insert(RecipeReportVO recipeReportVO);
    public void update(RecipeReportVO recipeReportVO);
    public void delete(Integer recipeReportNo);
    public RecipeReportVO findByPrimaryKey(Integer recipeReportNo);
    public List<RecipeReportVO> getAll();

}


