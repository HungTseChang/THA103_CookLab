package com.cooklab.article_sub.model;

import java.util.*;



public interface ArticleSubDAO {
	public void insert(ArticleSubVO articleSubVO);
    public void update(ArticleSubVO articleSubVO);
    public void delete(Integer articleSubNo);
    public ArticleSubVO findByPrimaryKey(Integer articleSubNo);
    public List<ArticleSubVO> getAll();

//  public List<MembersVO> getAll(Map<String, String[]> map); 
}


