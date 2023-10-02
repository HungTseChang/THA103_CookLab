package com.cooklab.ingredient_category.model;

import java.util.List;

public interface IngredientCategoryDAO {
    public void insert(IngredientCategoryVO ingredientCategory);
    public void update(IngredientCategoryVO ingredientCategory);
    public void delete(Integer ingredientCategoryNo);
    public IngredientCategoryVO findByPrimaryKey(Integer ingredientCategoryNo);
    public List<IngredientCategoryVO> getAll();
}
