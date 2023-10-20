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
	public void update(ArticleVO ArticleVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 ArticleVO   ArticleVO1 = null;
		try {
			session.beginTransaction();

	   ArticleVO1  = session.get(ArticleVO.class,ArticleVO.getArticleNo());
       	if(ArticleVO1 != null) {
			ArticleVO1.setArticleCategoryNo(ArticleVO.getArticleCategoryNo());
			ArticleVO1.setArticleTitle(ArticleVO.getArticleTitle());
			ArticleVO1.setMemberId(ArticleVO.getMemberId());
			ArticleVO1.setArticleStatus(ArticleVO.getArticleStatus());
			ArticleVO1.setArticleContent(ArticleVO.getArticleContent());
			ArticleVO1.setArticleCount(ArticleVO.getArticleCount());
			ArticleVO1.setViewCount(ArticleVO.getViewCount());
       		
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
	
	
	public static void main(String[] args) {

		ArticleHBDAO dao = new ArticleHBDAO();
		// 新增
		// 新增
//		ArticleVO ArticleVO1 = new ArticleVO();
//		ArticleVO1.setArticleCategory(1);
//		ArticleVO1.setArticleTitle("今天大家吃甚麼?");
//		ArticleVO1.setMemberId(Integer.valueOf(1));
//		ArticleVO1.setArticleStatus(Byte.valueOf((byte) 1));
//		ArticleVO1.setArticleContent("今年好霉氣全無財錦進門養豬各各大老鼠各各瘟做酒缸缸好做醋滴滴酸");
//		ArticleVO1.setArticleCount(Integer.valueOf(0));
//		ArticleVO1.setViewCount(Integer.valueOf(0));
//		dao.insert(ArticleVO1);

////			// 修改
//		    ArticleVO ArticleVO2 = new ArticleVO();
//			ArticleVO2.setArticleNo(Integer.valueOf(2));
//			ArticleVO2.setArticleCategory(2);
//			ArticleVO2.setArticleTitle("明天大家吃甚麼11?");
//			ArticleVO2.setMemberId(Integer.valueOf(1) );
//			ArticleVO2.setArticleStatus(Byte.valueOf((byte) 0)) ;
//			ArticleVO2.setArticleContent("下雨天留客天留我不留") ;
//			ArticleVO2.setArticleCount(Integer.valueOf(10)) ;
//			ArticleVO2.setViewCount(Integer.valueOf(10)) ;
//			dao.update(ArticleVO2);
//
//////			// 刪除
//			dao.delete(3);
//
//
//////
//////			// 查詢
			ArticleVO ArticleVO3 = dao.findByPrimaryKey(2);
			System.out.println(ArticleVO3.getMemberVO().getMemberAccount());
//			System.out.print(ArticleVO3.getArticleNo() + ",");
//			System.out.print(ArticleVO3.getArticleCategory() + ",");
//			System.out.print(ArticleVO3.getArticleTitle() + ",");
//			System.out.print(ArticleVO3.getMemberId() + ",");
//			System.out.print(ArticleVO3.getArticleStatus() + ",");
//			System.out.print(ArticleVO3.getArticleContent() + ",");
//			System.out.print(ArticleVO3.getArticleCount()+ ",");
//			System.out.print(ArticleVO3.getViewCount()+",");
//			System.out.print(ArticleVO3.getLastEditTimestamp() + ",");
//			System.out.println(ArticleVO3.getCreatedTimestamp() + ",");
//
//			System.out.println("---------------------");
//////
//////			// 查詢
//		List<ArticleVO> list = dao.getAll();
//		for (ArticleVO aArticleVO : list) {
//			System.out.print(aArticleVO.getArticleNo() + ",");
//			System.out.print(aArticleVO.getArticleCategory() + ",");
//			System.out.print(aArticleVO.getArticleTitle() + ",");
//			System.out.print(aArticleVO.getMemberId() + ",");
//			System.out.print(aArticleVO.getArticleStatus() + ",");
//			System.out.print(aArticleVO.getArticleContent() + ",");
//			System.out.print(aArticleVO.getArticleCount() + ",");
//			System.out.print(aArticleVO.getViewCount() + ",");
//			System.out.print(aArticleVO.getLastEditTimestamp() + ",");
//			System.out.println(aArticleVO.getCreatedTimestamp() + ",");
//			System.out.println("---------------------");
//		}
//	
	
	
	
	
	
}}
