package com.cooklab.article_category.model;

import java.util.List;
import java.util.Map;

import com.cooklab.article_category.model.ArticleCategoryVO;

public interface ArticleCategoryDAO {
	
	int insert(ArticleCategoryVO articleCategoryVO);

	int update(ArticleCategoryVO articleCategoryVO);
	
	int delete(Integer articleCategoryNo);
	 
	ArticleCategoryVO getById(Integer articleCategoryNo);
	
	List<ArticleCategoryVO> getAll(Integer articleCategoryNo);
	
	
	
	

}
