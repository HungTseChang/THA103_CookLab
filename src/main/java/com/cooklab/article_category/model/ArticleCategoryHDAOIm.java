package com.cooklab.article_category.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.article_category.model.ArticleCategoryVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.*;

public  class ArticleCategoryHDAOIm implements ArticleCategoryDAO {
	
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public ArticleCategoryHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
		
	@Override
	public void insert(ArticleCategoryVO artVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//				session.beginTransaction();
				session.save(artVO);
				
//				session.getTransaction().commit();
//				session.close();
				
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public void update(ArticleCategoryVO artVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//				session.beginTransaction();
				session.update(artVO);
//				session.getTransaction().commit();
//				session.close();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public ArticleCategoryVO findByPrimaryKey(Integer articleCategoryNo) {

		return getSession().get(ArticleCategoryVO.class, articleCategoryNo);
		
		
//		try {
//				session.beginTransaction();
//				ArticleCategoryVO artVo = session.createQuery("from ArticleCategoryVO where articleCategoryNo=" +
//				articleCategoryNo,ArticleCategoryVO.class).uniqueResult();
//				
//				session.getTransaction().commit();
//				return artVo;
//		}catch(Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		
//		return null;
	}

	@Override
	public List<ArticleCategoryVO> getAll() {

		return getSession().createQuery("from ArticleCategoryVO", ArticleCategoryVO.class).list();
	
	}
}
