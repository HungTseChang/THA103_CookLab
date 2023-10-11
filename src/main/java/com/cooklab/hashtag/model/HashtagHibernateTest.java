package com.cooklab.hashtag.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;
import com.cooklab.recipe_comments_report.model.RecipeCommentsReportVO;

public class HashtagHibernateTest {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		// 新增 =============================================================
//		try {
//			session.beginTransaction();
//			
//			HashtagVO ht = new HashtagVO();
//			ht.setHashtangName("標籤6");
//			ht.setSearchCount(5);
//			ht.setUseCount(6);
//			
//			session.save(ht);
//			
//			session.getTransaction().commit();
//			
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		} finally {
//			HibernateUtil.shutdown();
//		}
//		 修改 =======================================================
//		try {
//			session.beginTransaction();
//
//			HashtagVO ht = new HashtagVO();
//			ht.setHashtangName("標籤修改TEST1");
//			ht.setSearchCount(8);
//			ht.setUseCount(8);
//			ht.setHashtagNO(6);
//
//			session.saveOrUpdate(ht);
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
//			HashtagVO ht = session.get(HashtagVO.class, 6);
//			if (ht != null)
//				session.delete(ht);
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
//			HashtagVO ht = session.get(HashtagVO.class, 5);
//			System.out.println(ht);
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
//			List<HashtagVO> list = session.createQuery("from HashtagVO", HashtagVO.class).list();
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
