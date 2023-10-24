package com.cooklab.article_category.model;

import java.util.List;


import com.cooklab.article_category.model.*;
import com.cooklab.util.HibernateUtil;

public class ArticleCategoryService {
	private ArticleCategoryDAO dao;
	
	public ArticleCategoryService () {
		dao = new ArticleCategoryHDAOIm(HibernateUtil.getSessionFactory());
	}
	
	
	public void add (ArticleCategoryVO articleVO) {
		dao.insert(articleVO);
	}
	
	public void  updateArt(ArticleCategoryVO articleVO) {
		dao.update(articleVO);
	}

	public ArticleCategoryVO getOne (Integer articleCategoryNo) {
		return dao.findByPrimaryKey(articleCategoryNo);
	}


	public List<ArticleCategoryVO> getAll() {
		return dao.getAll();
	}
	
	
}
