package com.cooklab.recipe_hashtag.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.Util.HibernateUtil;
import com.cooklab.hashtag.model.HashtagVO;

public class RecipeHashtagHibernateTest {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		//新增 =============================================================
//		
//		try {
//			session.beginTransaction();
//			
//			RecipeHashtagVO hht = new RecipeHashtagVO();
//			hht.setHashTagNo(4);
//			hht.setRecipeNO(4);
//			
//			session.save(hht);
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
//			RecipeHashtagVO rh = new RecipeHashtagVO();
//			rh.setHashTagNo(4);
//			rh.setRecipeNO(3);
//			rh.setRepiceHashTagNo(7);
//
//			session.saveOrUpdate(rh);
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
//			RecipeHashtagVO rh = session.get(RecipeHashtagVO.class, 4);
//			if (rh != null)
//				session.delete(rh);
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
//			RecipeHashtagVO rh = session.get(RecipeHashtagVO.class, 9);
//			System.out.println(rh);
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
//			List<RecipeHashtagVO> list = session.createQuery("from RecipeHashtagVO", RecipeHashtagVO.class).list();
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
