package com.cooklab.article_category.model;

import java.util.List;


import com.cooklab.article_category.model.*;

public class ArticleCategoryService {
	private ArticleCategoryDAO dao;
	
	public ArticleCategoryService () {
		dao = new ArticleCategoryHDAOIm();
	}
	
	
	public void add (ArticleCategoryVO articleVO) {
		dao.insert(articleVO);
	}
	
	public void  updateArt(ArticleCategoryVO articleVO) {
		dao.update(articleVO);
	}




	public List<ArticleCategoryVO> getAll() {
		return dao.getAll();
	}
	
	
}
