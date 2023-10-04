package com.cooklab.article_category.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.article_category.model.ArticleCategoryVO;
import com.cooklab.util.*;

public  class ArticleCategoryDAOIm implements ArticleCategoryDAO {
	
	private SessionFactory factory;
	
	public ArticleCategoryDAOIm (SessionFactory factory) {
		this.factory =factory;
	}
	
	private Session getSession() {
		return factory.getCurrentSession();	
	}

	@Override
	public int insert(ArticleCategoryVO articleCategoryVO) {
		// TODO Auto-generated method stub
		return (Integer) getSession().save(articleCategoryVO);
	}

	@Override
	public int update(ArticleCategoryVO articleCategoryVO) {
		// TODO Auto-generated method stub
		try {
			getSession().update(articleCategoryVO);
			return 1;
		}catch(Exception e){
			return -1;
		}
	}

	@Override
	public int delete(Integer articleCategoryNo) {
		ArticleCategoryVO art = getSession().get(ArticleCategoryVO.class,articleCategoryNo);
		if (art != null) {
			getSession().delete(art);
			return 1;
		}else {
			return -1;
		}
		
	}

	@Override
	public ArticleCategoryVO getById(Integer articleCategoryNo) {
		// TODO Auto-generated method stub
		return getSession().get(ArticleCategoryVO.class, articleCategoryNo);
	}

	@Override
	public List<ArticleCategoryVO> getAll(Integer articleCategoryNo) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from article_category",ArticleCategoryVO.class).list();
	}

	
	
	
}
