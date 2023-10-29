package com.cooklab.ingredient_category.model;

import java.util.List;

import com.cooklab.util.HibernateUtil;

public class IngredientService {

	private IngredientCategoryDAO dao;

	public IngredientService() {
		// TODO Auto-generated constructor stub
		dao = new IngredientHDAOIm(HibernateUtil.getSessionFactory());
	}

	public List<IngredientCategoryVO> getAll() {
		return dao.getAll();
	}

	public List<Object[]> getIngredientAndKitchenwareTags() {
		return dao.getIngredientAndKitchenwareTags();
	}

	public void update(IngredientCategoryVO ingredientCategory) {
		dao.update(ingredientCategory);
	}

	public void insert(IngredientCategoryVO ingredientCategory) {
		dao.insert(ingredientCategory);
	}

	public void delete(IngredientCategoryVO ingredientCategory) {
		dao.delete(ingredientCategory);
	}

	public String deleteCategory(IngredientCategoryVO ingredientCategory) {

		return dao.delete(ingredientCategory);

	}

	public IngredientCategoryVO findByName(IngredientCategoryVO ingredientCategory) {
		return dao.findByName(ingredientCategory);
	}

}
