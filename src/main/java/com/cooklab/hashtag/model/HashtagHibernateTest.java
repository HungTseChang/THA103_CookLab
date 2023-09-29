package com.cooklab.hashtag.model;

import org.hibernate.Session;

import com.cooklab.Util.HibernateUtil;
import com.cooklab.recipe_comments_report.model.RecipeCommentsReportVO;

public class HashtagHibernateTest {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//新增 =============================================================
		
		try {
			session.beginTransaction();
			
			HashtagVO ht = new HashtagVO();
			ht.setHashtangName("標籤6");
			ht.setSearchCount(5);
			ht.setUseCount(6);
			
			session.save(ht);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}
	}
}
