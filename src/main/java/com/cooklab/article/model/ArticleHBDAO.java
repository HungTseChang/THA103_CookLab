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
			HibernateUtil.shutdown();
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
//			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {// chatGpt 最後在finally設定session.close();比較好
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
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
		HibernateUtil.shutdown();
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
//			System.out.println(
//					"ArticleReportNo :"+ArticleVO3.getArticleReportNo()+"\n  "
//				+ "ArticleNo :"+ArticleVO3.getArticleNo()+"\n"
//				+"ReporterId :"+ ArticleVO3.getReporterId()+"\n"
//				+"ReportingReason :"+ArticleVO3.getReportingReason()+"\n"
//				+"ReportingStatus :"+ArticleVO3.getReportingStatus()+"\n"
//				+"tCreatedTimestamp :"+ArticleVO3.getCreatedTimestamp()
//				+"\n"+"============================================="
//						); 
//    
		session.getTransaction().commit();
		session.close();
	} catch (Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	} finally {
		HibernateUtil.shutdown();
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
//			for(int i=0; i<list1.size();i++) {
//				System.out.println(
//					"ArticleReportNo :"+list1.get(i).getArticleReportNo()+"\n  "
//				+ "ArticleNo :"+list1.get(i).getArticleNo()+"\n"
//				+"ReporterId :"+ list1.get(i).getReporterId()+"\n"
//				+"ReportingReason :"+list1.get(i).getReportingReason()+"\n"
//				+"ReportingStatus :"+list1.get(i).getReportingStatus()+"\n"
//				+"tCreatedTimestamp :"+list1.get(i).getCreatedTimestamp()
//				+"\n"+"============================================="
//						); 
//			}
			
			
			
		session.getTransaction().commit();
		session.close();

	} catch (Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	} finally {
		HibernateUtil.shutdown();
	}
		return list1;

	}
	
	
	
//	=============================getAll結束==================================================
	
	
	@Override
	public void updateArticleStatus(Integer articleNo, Byte articleStatus) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ArticleVO a1 = session.get(ArticleVO.class, articleNo);
			
			a1.setArticleStatus(articleStatus);
			
			session.getTransaction().commit();
			session.close();
		
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} 
		
	}
//	=============================updateArticleStatus結束==================================================

	@Override
	public void updateViewCount(Integer articleNo, Integer viewCount) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			ArticleVO a1 = session.get(ArticleVO.class, articleNo);
			
			a1.setViewCount(viewCount);
			
			session.getTransaction().commit();
			session.close();
			
	 
			
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
//	=============================updateArticleStatus結束==================================================

	public static void main(String[] args) {

		ArticleHBDAO dao = new ArticleHBDAO();
		
	
	
	
	
	
}}
