package com.cooklab.recipe_comments.model;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import com.cooklab.util.HibernateUtil;

public class RecipeCommentsHibernateTest {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		//新增 =============================================================
		
//		try {
//			session.beginTransaction();
//			
//			RecipeCommentsVO rcr = new RecipeCommentsVO();
//			rcr.setRecipeNo(3);
//			rcr.setMemberId(2);
//			rcr.setCommentContent("食譜留言區測試99");
//			rcr.setReportComments(3);
//			
//			Date currentDate = new Date();
//	        Timestamp timestamp = new Timestamp(currentDate.getTime());
//			rcr.setLastEditTimestamp(timestamp);
//			
//			session.save(rcr);
//			
//			session.getTransaction().commit();
//			
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		} finally {
//			HibernateUtil.shutdown();
//		}
		
		// 修改 =======================================================
//		try {
//			session.beginTransaction();
//
//			RecipeCommentsVO rc = new RecipeCommentsVO();
//			rc.setRecipeNo(3);
//			rc.setMemberId(2);
//			rc.setCommentContent("檢舉留222");
//			rc.setReportComments(2);
//			rc.setRecipeCommentsNo(6);
//
//			session.saveOrUpdate(rc);
//
//			session.getTransaction().commit();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
		// 刪除 =======================================================
//		try {
//			session.beginTransaction();
//
//			RecipeCommentsVO rc = session.get(RecipeCommentsVO.class, 6);
//			if (rc != null)
//				session.delete(rc);
//
//			session.getTransaction().commit();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
		// 查詢一筆 =======================================================
//		try {
//			session.beginTransaction();
//			
//			RecipeCommentsVO rc = session.get(RecipeCommentsVO.class, 3);
//			System.out.println(rc);
//
//			session.getTransaction().commit();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
		// 查詢全部 =======================================================
		try {
			session.beginTransaction();
			
			// 多筆資料查詢可用 hibernate 的 list() 或 JPA 的 getResultList()
			List<RecipeCommentsVO> list = session.createQuery("from RecipeCommentsVO", RecipeCommentsVO.class).list();
			
			System.out.println(list);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
	}
}
