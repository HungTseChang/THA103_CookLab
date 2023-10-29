package com.cooklab.article_reaction.model;

import java.util.List;

public interface ArticleReactionDAO {
    public void insert(ArticleReactionVO ArticleReactionVO);
    
    public void saveOrUpdate(ArticleReactionVO ArticleReactionVO);
    
    public void delete(Integer articleReactionNo);
    
    public ArticleReactionVO findByPrimaryKey(Integer articleReactionNo);
    
    public ArticleReactionVO findByTwoCol(Integer memberId , Integer articleNo);
    
    public Long allCount (Integer articleNo , Byte statuts);
    
    public List<ArticleReactionVO> getAll();
}
