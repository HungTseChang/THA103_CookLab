package com.cooklab.article_sub_picture.model;
	import java.util.List;

public interface ArticleSubPictureDAO {
	    public void insert(ArticleSubPictureVO ArticleSubPictureVO);
	    public void update(ArticleSubPictureVO ArticleSubPictureVO);
	    public void delete(Integer ArticleSubPictureNo);
	    public ArticleSubPictureVO findByPrimaryKey(Integer ArticleSubPictureNo);
	    public List<ArticleSubPictureVO> getAll();
	}


