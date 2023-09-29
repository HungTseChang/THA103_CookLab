package com.cooklab.recipe_comments_report.model;

import org.hibernate.Session;
import com.cooklab.Util.HibernateUtil;
public class RecipeCommentsReportHibernateTest {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//新增 =============================================================
		
		try {
			session.beginTransaction();
			
			RecipeCommentsReportVO rcr = new RecipeCommentsReportVO();
			rcr.setMemberId(2);
			rcr.setRecipeCommentsNo(2);
			rcr.setReportingCommentsReason("食譜留言檢舉測試2");
			rcr.setReportingStatus((byte)3);
			
			session.save(rcr);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}
		//刪除 =============================================================

//		try {
//			session.beginTransaction();
//			
//			RecipeCommentsReportVO dept = session.get(RecipeCommentsReportVO.class, 6);
//			if (dept != null)
//				session.delete(dept);
//			
//			session.getTransaction().commit();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
//		
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
		//查詢 =============================================================
		
		
	}

}
