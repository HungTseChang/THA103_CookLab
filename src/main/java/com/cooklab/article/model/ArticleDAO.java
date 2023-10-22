package com.cooklab.article.model;

import java.util.List;

public interface ArticleDAO {
    public void insert(ArticleVO ArticleVO);
    public void update(ArticleVO ArticleVO);
    
    //下方為更新單一欄位而設定
    public void updateArticleStatus(Integer articleNo, Byte articleStatus);
    
    public void updateViewCount(Integer articleNo, Integer viewCount);
    
    public void delete(Integer articleNo);
    public ArticleVO findByPrimaryKey(Integer articleNo);
    public List<ArticleVO> getAll();
}
