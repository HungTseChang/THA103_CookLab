package com.cooklab.article.model;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.cooklab.article.model.ArticleJDBCDAOIm;
import com.cooklab.article.model.ArticleVO;
import com.cooklab.util.HibernateUtil;

public class ArticleHBDAO implements ArticleDAO {
	@Override
	public void insert(ArticleVO ArticleVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

           	session.save(ArticleVO);
			
           	
           	session.getTransaction().commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
	

	}

//============================insert完結=======================================
	@Override
	public void update(ArticleVO ArticleVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 ArticleVO   ArticleVO1 = null;
		try {
			session.beginTransaction();

	   ArticleVO1  = session.get(ArticleVO.class,ArticleVO.getArticleNo());
       	if(ArticleVO1 != null) {
//			ArticleVO1.setArticleCategory(ArticleVO.getArticleCategory());
//			ArticleVO1.setArticleTitle(ArticleVO.getArticleTitle());
//			ArticleVO1.setMemberId(ArticleVO.getMemberId());
//			ArticleVO1.setArticleStatus(ArticleVO.getArticleStatus());
//			ArticleVO1.setArticleContent(ArticleVO.getArticleContent());
//			ArticleVO1.setArticleCount(ArticleVO.getArticleCount());
//			ArticleVO1.setViewCount(ArticleVO.getViewCount());
       		
       	}
			session.getTransaction().commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
	}
	
//=================================update結束========================================
@Override
	public void delete(Integer articleNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
    	ArticleVO ArticleVO2 = session.get(ArticleVO.class, articleNo);
      	if(ArticleVO2 != null) {
       		session.delete(ArticleVO2);
       	}
		session.getTransaction().commit();
		session.close();
		
	} catch (Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	} finally {
//		HibernateUtil.shutdown();
	}
	}

//===============================delete結束======================================
	@Override
	public ArticleVO findByPrimaryKey(Integer articleReportNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		ArticleVO ArticleVO3 =null;
		try {
			session.beginTransaction();
			 ArticleVO3 =session.get(ArticleVO.class, articleReportNo);
			 session.getTransaction().commit();
			 session.close();
	} catch (Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	} finally {
//		HibernateUtil.shutdown();
	}
		return ArticleVO3;
	}

//===========================findByPrimaryKey結束==============================================
	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> list1 = new ArrayList<ArticleVO>();

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			list1 = session.createQuery("from ArticleVO",ArticleVO.class).list();						
			session.getTransaction().commit();
			session.close();

	} catch (Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	} finally {
//		HibernateUtil.shutdown();
	}
		return list1;

	}
	
	
	
//	=============================getAll結束==================================================
	
	
	public static void main(String[] args) {

		ArticleHBDAO dao = new ArticleHBDAO();

	
	
	
	
	}
}
