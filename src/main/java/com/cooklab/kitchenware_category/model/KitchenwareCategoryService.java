package com.cooklab.kitchenware_category.model;

import java.util.List;



public class KitchenwareCategoryService {
	
	
	private KitchenwareCategoryDAO dao;
	
	public KitchenwareCategoryService() {
		// TODO Auto-generated constructor stub
		dao = new KitchenwareCategoryJDBCDAOIm();
	}

	
	public List<KitchenwareCategoryVO> getAll() {
		return dao.getAll();
	}
}
