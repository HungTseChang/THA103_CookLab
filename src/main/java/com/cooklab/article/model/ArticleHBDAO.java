package com.cooklab.article.model;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.cooklab.article.model.ArticleJDBCDAOIm;
import com.cooklab.article.model.ArticleVO;
import com.cooklab.util.HibernateUtil;

public class ArticleHBDAO implements ArticleDAO {
	@Override
	public void insert(ArticleVO articleVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			
           	session.save(articleVO);
			
           	
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
	public void update(ArticleVO articleVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(articleVO);
	   		
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
	public List<ArticleVO> getAll() {//處理這段我花了5個小時.....人生啊....
		List<ArticleVO> list1 = new ArrayList<ArticleVO>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
//			String hql = "SELECT new com.cooklab.article.model.ArticleVO(a.articleNo, a.articleTitle, "
//					+ "a.memberId, a.createdTimestamp, a.articleStatus, a.articleContent,"
//					+ " a.articleCount, a.viewCount, a.lastEditTimestamp, a.articleCategory) " +
//                    "FROM ArticleVO a ORDER BY a.articleNo DESC";
//			Query<ArticleVO> query = session.createQuery(hql, ArticleVO.class);
//			
//			list1 = query.getResultList();
			
	        Criteria c1 = session.createCriteria(ArticleVO.class);
	        c1.addOrder(Order.desc("articleNo"));
	        list1 = c1.list();

	    

//			list1 = session.createQuery("from ArticleVO",ArticleVO.class).list();						
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
	
	
	@Override
	public void updateArticleStatus(Integer articleNo, Byte articleStatus) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ArticleVO articleVO = session.get(ArticleVO.class, articleNo);
			
      	if(articleVO != null) {
      		articleVO.setArticleStatus(articleStatus);
       	}
		session.getTransaction().commit();
		session.close();
		
	} catch (Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	} 
	}
}
	
//=======================updateArticleStatus 方法結束================================
	
	
	

