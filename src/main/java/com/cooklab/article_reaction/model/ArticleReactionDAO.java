package com.cooklab.article_reaction.model;

import java.util.List;

public interface ArticleReactionDAO {
    public void insert(ArticleReactionVO ArticleReactionVO);
    
    public void update(ArticleReactionVO ArticleReactionVO);
    
    public void delete(Integer articleReactionNo);
    public ArticleReactionVO findByPrimaryKey(Integer articleReactionNo);
    
    public ArticleReactionVO findByTwoCol(Integer memberId , Integer articleNo);
    
    public List<ArticleReactionVO> getAll();
}
