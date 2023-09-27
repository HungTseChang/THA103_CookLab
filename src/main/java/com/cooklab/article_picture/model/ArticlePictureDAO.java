package com.cooklab.model.article_picture;
	import java.util.List;

public interface ArticlePictureDAO {
	    public void insert(ArticlePictureVO ArticlePictureVO);
	    public void update(ArticlePictureVO ArticlePictureVO);
	    public void delete(Integer articlePictureNo);
	    public ArticlePictureVO findByPrimaryKey(Integer articlePictureNo);
	    public List<ArticlePictureVO> getAll();
	}


