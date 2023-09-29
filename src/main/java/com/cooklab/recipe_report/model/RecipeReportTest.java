package com.cooklab.recipe_report.model;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.cooklab.notify_center.model.NotifyCenterVO;
import com.cooklab.util.HibernateUtil;


public class RecipeReportTest {
	public static void main(String[] args) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		
		try {			
			//新增資料  member_id, recipe_no, reporting_reason, reporting_status

			session.beginTransaction();
			
//			RecipeReportVO r1 =new RecipeReportVO();
//			r1.setMemberId(2);
//			r1.setRecipeNo(2);
//			r1.setReportingReason("你的大麻來源不明");
//			r1.setReportingStatus((byte)1);
//			
//			session.save(r1);
			
			//更新資料
//			r1 = session.get(RecipeReportVO.class, 1);
//			r1.setReportingReason("你的大麻來源不明");
			
			//刪除資料
//			r1.setRecipeReportNo(1);
//			session.delete(r1);	
	
			//查詢全部
//			Query<RecipeReportVO> query3 = session.createQuery(
//					" from RecipeReportVO", RecipeReportVO.class);
//			List<RecipeReportVO> list2 = query3.list();
//			System.out.println(list2);
			
			
			//查詢資料(單筆)
			RecipeReportVO recipeReportVO = session.createQuery("from RecipeReportVO where recipeReportNo = 2", RecipeReportVO.class).uniqueResult();
			System.out.println(recipeReportVO);
//			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}

	}
}
