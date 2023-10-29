package com.cooklab.ingredient_category.model;

import java.util.List;



public interface IngredientCategoryDAO {
    public void insert(IngredientCategoryVO ingredientCategory);
    public void update(IngredientCategoryVO ingredientCategory);
    public String delete(IngredientCategoryVO ingredientCategory);
    public IngredientCategoryVO findByPrimaryKey(Integer ingredientCategoryNo);
    public List<IngredientCategoryVO> getAll();
    public List<Object[]> getIngredientAndKitchenwareTags();
    public boolean hasAssociatedProducts(Integer ingredientCategoryNo);
    public IngredientCategoryVO findByName(IngredientCategoryVO ingredientCategory);
}
