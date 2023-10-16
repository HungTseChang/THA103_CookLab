package com.cooklab.article_category.model;

import java.util.List;

public interface ArticleCategoryDAO {
	public void insert (ArticleCategoryVO artVO);
	public void update (ArticleCategoryVO artVO);
	
	public ArticleCategoryVO  findByPrimaryKey(Integer articleCategoryNo);
	public List<ArticleCategoryVO>getAll();
}
