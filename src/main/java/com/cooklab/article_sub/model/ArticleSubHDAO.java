package com.cooklab.article_sub.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.HibernateUtil;

public class ArticleSubHDAO  implements ArticleSubDAO{
	
	
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public ArticleSubHDAO(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(ArticleSubVO articleSubVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			session.save(articleSubVO);
			
//			session.getTransaction().commit();
//			session.close();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void update(ArticleSubVO articleSubVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			session.update(articleSubVO);
			
//			session.getTransaction().commit();
//			session.close();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		
	}

	@Override
	public void delete(Integer articleSubNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			session.delete(articleSubNo);
			
//			session.getTransaction().commit();
//			session.close();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public ArticleSubVO findByPrimaryKey(Integer articleSubNo) {
		return getSession().get(ArticleSubVO.class, articleSubNo);
		
		
	}

	@Override
	public List<ArticleSubVO> getAll() {
		return getSession().createQuery("from ArticleSubVO", ArticleSubVO.class).list();
		
		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			
//			List<ArticleSubVO> list = session.createQuery("from ArticleSubVO ",ArticleSubVO.class).
//					list();
//			
//			session.getTransaction().commit();
//			return list;
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		return null;
	}

	
	
	
}
