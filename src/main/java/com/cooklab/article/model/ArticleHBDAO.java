package com.cooklab.article.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cooklab.article.model.ArticleJDBCDAOIm;
import com.cooklab.article.model.ArticleVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.HibernateUtil;

public class ArticleHBDAO implements ArticleDAO {
	
	private SessionFactory factory;
	
	public ArticleHBDAO(SessionFactory factory) {
		this.factory = factory;
	}
	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public void insert(ArticleVO ArticleVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();

			session.save(ArticleVO);
//			session.getTransaction().commit();
//			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} 

	}

//============================insert完結=======================================
	@Override
	public boolean update(ArticleVO articleVO) {
		
		try {
			getSession().update(articleVO);
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//
//		try {
//			session.saveOrUpdate(articleVO);
//			transaction.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} 

	}

//=================================update結束========================================
	@Override
	public void delete(Integer articleNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			session.beginTransaction();
			ArticleVO ArticleVO2 = session.get(ArticleVO.class, articleNo);
			if (ArticleVO2 != null) {
				session.delete(ArticleVO2);
			}
//			session.getTransaction().commit();
//			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

//===============================delete結束======================================
	@Override
	public ArticleVO findByPrimaryKey(Integer articleNo) {
		return getSession().get(ArticleVO.class, articleNo);
	}
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		ArticleVO ArticleVO3 = null;
//		try {
////			session.beginTransaction();
//			ArticleVO3 = session.get(ArticleVO.class, articleNo);
//			session.getTransaction().commit();
////			session.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		return ArticleVO3;
//	}

//===========================findByPrimaryKey結束==============================================
	@Override
	public List<ArticleVO> getAll() {
		
//		return getSession().createQuery("from  ArticleVO",ArticleVO.class).list();
		return getSession().createQuery("from  ArticleVO",ArticleVO.class).list();
		
//		List<ArticleVO> list1 = new ArrayList<ArticleVO>();
//
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
////			session.beginTransaction();
//
//			list1 = session.createQuery("from ArticleVO", ArticleVO.class).list();
////			
//
//			session.getTransaction().commit();
////			session.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} 
//		return list1;

	}

//	=============================getAll結束==================================================

//	@Override
//	public void updateArticleStatus(Integer articleNo, Byte articleStatus) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			session.get(ArticleVO.class, articleNo);
//			
//			session.update
//			
//			session.getTransaction().commit();
//			session.close();
//		
////			session.beginTransaction();
////
////          session.save(ArticleVO);
////			session.getTransaction().commit();
////			session.close();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} 
//		
//	}
//	=============================updateArticleStatus結束==================================================
//
//	@Override
//	public void updateViewCount(Integer articleNo, Integer viewCount) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			
//			ArticleVO a1 = session.get(ArticleVO.class, articleNo);
//			
//			a1.setViewCount(viewCount);
//			
//			session.getTransaction().commit();
//			session.close();
//			
//	 
//			
//		}catch(Exception e){
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		
//	}
//	=============================updateArticleStatus結束==================================================
	@Override
	public List <ArticleVO> findByStatus(Byte articleStatus) {
		
		return getSession().createQuery("from  ArticleVO where articleStatus = :articleStatus",ArticleVO.class).setParameter("articleStatus", articleStatus).list();
	}
	public static void main(String[] args) {

	}

}
