package com.cooklab.article_collection.model;

import org.hibernate.Session;
<<<<<<< HEAD

import com.cooklab.util.HibernateUtil;
=======
import com.cooklab.Util.HibernateUtil;
>>>>>>> refs/heads/master
import com.cooklab.recipe_hashtag.model.RecipeHashtagVO;
import java.util.List;

public class AricleCollectionHibernateTest {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		// 新增 =============================================================
//		try {
//			session.beginTransaction();
//
//			ArticleCollectionVO ac = new ArticleCollectionVO();
//			ac.setArticleNo(5);
//			ac.setMemberId(4);
//			session.save(ac);
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
//			ArticleCollectionVO ac = new ArticleCollectionVO();
//			ac.setArticleNo(1);
//			ac.setMemberId(1);
//			ac.setArticleCollectionNo(6);
//
//			session.saveOrUpdate(ac);
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
//			ArticleCollectionVO ac = session.get(ArticleCollectionVO.class, 2);
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
		// 查詢一筆 =======================================================
//		try {
//			session.beginTransaction();
//			
//			ArticleCollectionVO ac = session.get(ArticleCollectionVO.class, 3);
//			System.out.println(ac);
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
//			List<ArticleCollectionVO> list = session.createQuery("from ArticleCollectionVO", ArticleCollectionVO.class).list();
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
