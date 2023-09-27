package com.cooklab.article_collection.model;

import java.util.*;

public interface ArticleCollectionDAO {

    public void insert(ArticleCollectionVO articleCollectionVO);
    public void update(ArticleCollectionVO articleCollectionVO);
    public void delete(Integer articleCollectionNo);
    public ArticleCollectionVO findByPrimaryKey(Integer articleCollectionNo);
    public List<ArticleCollectionVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
