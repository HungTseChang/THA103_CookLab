package com.cooklab.article.model;

import java.util.List;

public interface ArticleDAO {
    public void insert(ArticleVO ArticleVO);
    public void update(ArticleVO ArticleVO);
    public void delete(Integer articleNo);
    public ArticleVO findByPrimaryKey(Integer articleNo);
    public List<ArticleVO> getAll();
}
