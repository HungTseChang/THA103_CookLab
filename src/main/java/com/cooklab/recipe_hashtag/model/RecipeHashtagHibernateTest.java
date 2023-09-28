package com.cooklab.recipe_hashtag.model;

import org.hibernate.Session;

import com.cooklab.Util.HibernateUtil;
import com.cooklab.hashtag.model.HashtagVO;

public class RecipeHashtagHibernateTest {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//新增 =============================================================
		
		try {
			session.beginTransaction();
			
			RecipeHashtagVO hht = new RecipeHashtagVO();
			hht.setHashTagNo(4);
			hht.setRecipeNO(4);
			
			session.save(hht);
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}
	}
}
