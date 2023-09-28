package com.cooklab.recipe_reaction.model;

import org.hibernate.Session;

import com.cooklab.Util.HibernateUtil;
import com.cooklab.article_collection.model.ArticleCollectionVO;

public class RecipeReactionHibernateTest {
	public static void main(String[] args)
	{

//		RecipeReactionVO rrV01 = new RecipeReactionVO();
//		rrV01.setRecipeNo(4);
//		rrV01.setMemberId(2);
//		rrV01.setRecipeReactionStatus((byte) 7);
//		dao.insert(rrV01);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		// 新增 =============================================================

		try {
			session.beginTransaction();

			RecipeReactionVO rrt = new RecipeReactionVO();
			rrt.setRecipeNo(4);
			rrt.setMemberId(3);
			rrt.setRecipeReactionStatus((byte) 7);
			session.save(rrt);

			session.getTransaction().commit();

		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}
	}
}
