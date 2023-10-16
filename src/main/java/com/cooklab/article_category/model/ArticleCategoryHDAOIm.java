package com.cooklab.article_category.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.article_category.model.ArticleCategoryVO;
import com.cooklab.util.*;

public  class ArticleCategoryHDAOIm implements ArticleCategoryDAO {
	
		
	@Override
	public void insert(ArticleCategoryVO artVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
				session.beginTransaction();
				session.save(artVO);
				
				session.getTransaction().commit();
				session.close();
				
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public void update(ArticleCategoryVO artVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
				session.beginTransaction();
				session.update(artVO);
				session.getTransaction().commit();
				session.close();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public ArticleCategoryVO findByPrimaryKey(Integer articleCategoryNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
				session.beginTransaction();
				ArticleCategoryVO artVo = session.createQuery("from ArticleCategoryVO where articleCategoryNo=" +
				articleCategoryNo,ArticleCategoryVO.class).uniqueResult();
				
				session.getTransaction().commit();
				return artVo;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
	}

	@Override
	public List<ArticleCategoryVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
				session.beginTransaction();
				List<ArticleCategoryVO> list =session.createQuery("from ArticleCategoryVO", ArticleCategoryVO.class)
						.list();
				
				
				session.getTransaction().commit();
				return list;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
	}
	
	
	
	
}
