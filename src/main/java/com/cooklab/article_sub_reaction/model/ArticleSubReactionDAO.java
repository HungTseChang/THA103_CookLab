package com.cooklab.article_sub_reaction.model;

import java.util.List;



public interface ArticleSubReactionDAO {
    public void insert(ArticleSubReactionVO articleSubReactionVO);
    public void update(ArticleSubReactionVO articleSubReactionVO);
    public void delete(Integer articleSubReactionNo);
    public ArticleSubReactionVO findByPrimaryKey(Integer articleSubReactionNo);
    public List<ArticleSubReactionVO> getAll();
    
    
    public ArticleSubReactionVO findByTwoCol(Integer memberId , Integer articleSubNo);
    public Long allCount (Integer articleSubNo , Byte statuts);
    public void saveOrUpdate(ArticleSubReactionVO articleSubReactionVO);
    
}
