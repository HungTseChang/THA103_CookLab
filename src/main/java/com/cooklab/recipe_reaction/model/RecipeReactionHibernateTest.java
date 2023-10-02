package com.cooklab.recipe_reaction.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.Util.HibernateUtil;
import com.cooklab.article_collection.model.ArticleCollectionVO;
import com.cooklab.recipe_reaction.model.RecipeReactionVO.CompositeDetail;

public class RecipeReactionHibernateTest {
	public static void main(String[] args)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		// 新增 =============================================================
//		try {
//			session.beginTransaction();
//
//			RecipeReactionVO rrt = new RecipeReactionVO();
//			rrt.setRecipeNo(4);
//			rrt.setMemberId(3);
//			rrt.setRecipeReactionStatus((byte) 7);
//			session.save(rrt);
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
//			RecipeReactionVO rr = new RecipeReactionVO();
//			rr.setRecipeNo(4);
//			rr.setMemberId(3);
//			rr.setRecipeReactionStatus((byte)3);
//
//			session.saveOrUpdate(rr);
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
//			 // 创建联合主键对象
//			CompositeDetail id = new CompositeDetail(4,3);
//		    
//			RecipeReactionVO ac = session.get(RecipeReactionVO.class, id);
//			if (ac != null)
//				session.delete(ac);
//
//			session.getTransaction().commit();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
//		 查詢一筆 =======================================================
//		try {
//			session.beginTransaction();
//
//			CompositeDetail id = new CompositeDetail(3,3);
//			RecipeReactionVO rr = session.get(RecipeReactionVO.class, id);
//			System.out.println(rr);
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
//			List<RecipeReactionVO> list = session.createQuery("from RecipeReactionVO", RecipeReactionVO.class).list();
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
