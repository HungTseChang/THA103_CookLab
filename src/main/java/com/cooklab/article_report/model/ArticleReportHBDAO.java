package com.cooklab.article_report.model;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.cooklab.article.model.ArticleHBDAO;
import com.cooklab.article.model.ArticleVO;
import com.cooklab.util.HibernateUtil;

public class ArticleReportHBDAO implements ArticleReportDAO {
	@Override
	public void insert(ArticleReportVO ArticleReportVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

           	session.save(ArticleReportVO);
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
	public void update(ArticleReportVO ArticleReportVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

       	ArticleReportVO ArticleReportVO1  = session.get(ArticleReportVO.class,ArticleReportVO.getArticleReportNo());
       	if(ArticleReportVO1 != null) {
       		ArticleReportVO1.setArticleNo(ArticleReportVO.getArticleNo());
       		ArticleReportVO1.setReporterId(ArticleReportVO.getReporterId());
       		ArticleReportVO1.setReportingReason(ArticleReportVO.getReportingReason());
       		ArticleReportVO1.setReportingStatus(ArticleReportVO.getReportingStatus());
       		ArticleReportVO1.setReportingAnswer(ArticleReportVO.getReportingAnswer());
       		
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
	public void delete(Integer articleReportNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
    	ArticleReportVO ArticleReportVO2 = session.get(ArticleReportVO.class, articleReportNo);
      	if(ArticleReportVO2 != null) {
       		session.delete(ArticleReportVO2);
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
	public ArticleReportVO findByPrimaryKey(Integer articleReportNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		ArticleReportVO ArticleReportVO3 =null;
		try {
			session.beginTransaction();
			 ArticleReportVO3 =session.get(ArticleReportVO.class, articleReportNo);
			System.out.println(
					"ArticleReportNo :"+ArticleReportVO3.getArticleReportNo()+"\n  "
				+ "ArticleNo :"+ArticleReportVO3.getArticleNo()+"\n"
				+"ReporterId :"+ ArticleReportVO3.getReporterId()+"\n"
				+"ReportingReason :"+ArticleReportVO3.getReportingReason()+"\n"
				+"ReportingStatus :"+ArticleReportVO3.getReportingStatus()+"\n"
				+"tCreatedTimestamp :"+ArticleReportVO3.getCreatedTimestamp()
				+"\n"+"============================================="
						); 
    
		session.getTransaction().commit();
		session.close();
	} catch (Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	} finally {
		HibernateUtil.shutdown();
	}
		return ArticleReportVO3;
	}

//===========================findByPrimaryKey結束==============================================
	@Override
	public List<ArticleReportVO> getAll() {
		List<ArticleReportVO> list1 = new ArrayList<ArticleReportVO>();

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			list1 = session.createQuery("from ArticleReportVO",ArticleReportVO.class).list();
			for(int i=0; i<list1.size();i++) {
				System.out.println(
					"ArticleReportNo :"+list1.get(i).getArticleReportNo()+"\n  "
				+ "ArticleNo :"+list1.get(i).getArticleVO().getArticleNo()+"\n"
				+"ReporterId :"+ list1.get(i).getMembersVO().getMemberId() +"\n"
				+"ReportingReason :"+list1.get(i).getReportingReason()+"\n"
				+"ReportingStatus :"+list1.get(i).getReportingStatus()+"\n"
				+"tCreatedTimestamp :"+list1.get(i).getCreatedTimestamp()
				+"\n"+"============================================="
						); 
			}
			
			
			
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

		ArticleReportHBDAO dao = new ArticleReportHBDAO();
		// 新增
//		ArticleReportVO ArticleReportVO1 = new ArticleReportVO();
//		ArticleReportVO1.setArticleNo(Integer.valueOf(1));
//		ArticleReportVO1.setReporterId(Integer.valueOf(1));
//		ArticleReportVO1.setReportingReason("排版還是好累");
//		ArticleReportVO1.setReportingStatus(Byte.valueOf((byte) 1));
//		ArticleReportVO1.setReportingAnswer("朕已閱");
//		System.out.print(ArticleReportVO1.getArticleReportNo() + ",");
//		System.out.print(ArticleReportVO1.getArticleNo() + ",");
//		System.out.print(ArticleReportVO1.getReporterId() + ",");
//		System.out.print(ArticleReportVO1.getReportingReason() + ",");
//		System.out.print(ArticleReportVO1.getReportingStatus() + ",");
//		System.out.print(ArticleReportVO1.getReportingAnswer() + ",");
//		System.out.println(ArticleReportVO1.getCreatedTimestamp() + ",");
//		System.out.println("---------------------");
//		dao.insert(ArticleReportVO1);

//		// 修改
//	ArticleReportVO ArticleReportVO2 = new ArticleReportVO();
//	ArticleReportVO2.setArticleReportNo(Integer.valueOf(6));
//	ArticleReportVO2.setArticleNo(Integer.valueOf(1));
//	ArticleReportVO2.setReporterId(Integer.valueOf(1));
//	ArticleReportVO2.setReportingReason("排版真的好累ZSSSZ");
//	ArticleReportVO2.setReportingStatus(Byte.valueOf((byte) 0));
//	ArticleReportVO2.setReportingAnswer("依然不准休息");
//	dao.update(ArticleReportVO2);

////		// 刪除
//		dao.delete(2);
////
////		// 查詢
//		ArticleReportVO ArticleReportVO3 = dao.findByPrimaryKey(1);
//		System.out.print(ArticleReportVO3.getArticleReportNo() + ",");
//		System.out.print(ArticleReportVO3.getArticleNo() + ",");
//		System.out.print(ArticleReportVO3.getReporterId() + ",");
//		System.out.print(ArticleReportVO3.getReportingReason() + ",");
//		System.out.print(ArticleReportVO3.getReportingStatus() + ",");
//		System.out.print(ArticleReportVO3.getReportingAnswer() + ",");
//		System.out.println(ArticleReportVO3.getCreatedTimestamp() + ",");
//		System.out.println("---------------------");
//
////		// 查詢
	List<ArticleReportVO> list = dao.getAll();
	for (ArticleReportVO aArticleReportVO : list) {
		System.out.print(aArticleReportVO.getArticleReportNo()+ ",");
		System.out.print(aArticleReportVO.getArticleVO().getArticleNo() + ",");
		System.out.print(aArticleReportVO.getMembersVO().getMemberId() + ",");
		System.out.print(aArticleReportVO.getReportingReason() + ",");
		System.out.print(aArticleReportVO.getReportingStatus() + ",");
		System.out.print(aArticleReportVO.getReportingAnswer() + ",");
		System.out.println(aArticleReportVO.getCreatedTimestamp() + ",");
		System.out.println("---------------------");
	}
	
//		ArticleReportVO ArticleReportVO7 = dao.findByPrimaryKey(6);
//		ArticleReportVO7.setArticleNo(5);
//		dao.update(ArticleReportVO7);
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		System.out.println(ArticleReportVO7.getArticleNo());
//	
	
	
}}
