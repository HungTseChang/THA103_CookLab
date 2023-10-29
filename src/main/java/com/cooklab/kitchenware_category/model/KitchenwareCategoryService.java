package com.cooklab.kitchenware_category.model;

import java.util.List;

import com.cooklab.util.HibernateUtil;

public class KitchenwareCategoryService {

	private KitchenwareCategoryDAO dao;

	public KitchenwareCategoryService() {
		// TODO Auto-generated constructor stub
		dao = new KitchenwareCategoryHDAOIm(HibernateUtil.getSessionFactory());
	}

	public List<KitchenwareCategoryVO> getAll() {
		return dao.getAll();
	}

	public void update(KitchenwareCategoryVO kitchenwareCategory) {
		dao.update(kitchenwareCategory);
	}

	public void insert(KitchenwareCategoryVO kitchenwareCategory) {
		dao.insert(kitchenwareCategory);
	}

	public String deleteCategory(KitchenwareCategoryVO kitchenwareCategory) {
			return dao.delete(kitchenwareCategory);
	}
	
	public KitchenwareCategoryVO findByName(KitchenwareCategoryVO kitchenwareCategory){
		return dao.findByName(kitchenwareCategory);
	}
}
