package com.cooklab.recipe_comments_report.model;

import java.util.List;

import org.hibernate.Session;
import com.cooklab.util.HibernateUtil;
public class RecipeCommentsReportHibernateTest {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//新增 =============================================================
//		try {
//			session.beginTransaction();
//			
//			RecipeCommentsReportVO rcr = new RecipeCommentsReportVO();
//			rcr.setMemberId(2);
//			rcr.setRecipeCommentsNo(2);
//			rcr.setReportingCommentsReason("食譜留言檢舉測試2");
//			rcr.setReportingStatus((byte)3);
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
		//刪除 =============================================================
//		try {
//			session.beginTransaction();
//			
//			RecipeCommentsReportVO rcr = session.get(RecipeCommentsReportVO.class, 7);
//			if (rcr != null)
//				session.delete(rcr);
//			
//			session.getTransaction().commit();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
////		
		//修改 =============================================================
//		try {
//			session.beginTransaction();
//			
//			RecipeCommentsReportVO rcr = new RecipeCommentsReportVO();
//			rcr.setMemberId(2);
//			rcr.setRecipeCommentsNo(2);
//			rcr.setReportingCommentsReason("食譜留言檢舉測試2");
//			rcr.setReportingStatus((byte)3);
//			rcr.setRecipeReportNo(3);
//			
//			session.saveOrUpdate(rcr);
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
//			RecipeCommentsReportVO rcr = session.get(RecipeCommentsReportVO.class, 3);
//			System.out.println(rcr);
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
//		try {
//			session.beginTransaction();
//			
//			// 多筆資料查詢可用 hibernate 的 list() 或 JPA 的 getResultList()
//			List<RecipeCommentsReportVO> list = session.createQuery("from RecipeCommentsReportVO", RecipeCommentsReportVO.class).list();
//			
//			System.out.println(list);
//			session.getTransaction().commit();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
	}

}
