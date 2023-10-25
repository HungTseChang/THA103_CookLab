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
		if (dao.hasAssociatedProducts(kitchenwareCategory.getKitchenwareCategoryNo())) {
			// 有关联的产品，返回错误消息
			return "已有商品使用中，请勿删除";
		} else {
			// 没有关联的产品，执行删除操作
			dao.delete(kitchenwareCategory);
			return "删除成功";
		}
	}
	
	public KitchenwareCategoryVO findByName(KitchenwareCategoryVO kitchenwareCategory){
		return dao.findByName(kitchenwareCategory);
	}
}
