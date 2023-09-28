package com.cooklab.article_collection.model;

import org.hibernate.Session;

import com.cooklab.Util.HibernateUtil;
import com.cooklab.recipe_hashtag.model.RecipeHashtagVO;

public class AricleCollectionHibernateTest {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		// 新增 =============================================================

		try {
			session.beginTransaction();

			ArticleCollectionVO ac = new ArticleCollectionVO();
			ac.setArticleNo(5);
			ac.setMemberId(4);
			session.save(ac);

			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}
	}
}
