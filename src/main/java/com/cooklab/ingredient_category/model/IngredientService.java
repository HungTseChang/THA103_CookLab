package com.cooklab.ingredient_category.model;

import java.util.List;



public class IngredientService {
	
	
	private IngredientCategoryDAO dao;

	public IngredientService() {
		// TODO Auto-generated constructor stub
		dao = new IngredientCategoryJDBCDAOIm();
	}

	public List<IngredientCategoryVO> getAll() {
		return dao.getAll();
	}
	
}
