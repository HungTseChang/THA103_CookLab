package com.cooklab.recipe_comments.model;


import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import com.cooklab.Util.HibernateUtil;

public class RecipeCommentsHibernateTest {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		//新增 =============================================================
		
		try {
			session.beginTransaction();
			
			RecipeCommentsVO rcr = new RecipeCommentsVO();
			rcr.setRecipeNo(3);
			rcr.setMemberId(2);
			rcr.setCommentContent("食譜留言區測試99");
			rcr.setReportComments(3);
			
			Date currentDate = new Date();
	        Timestamp timestamp = new Timestamp(currentDate.getTime());
			rcr.setLastEditTimestamp(timestamp);
			
			session.save(rcr);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}
		
		
	}
}
