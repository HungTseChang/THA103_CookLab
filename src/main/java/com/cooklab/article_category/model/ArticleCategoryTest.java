package com.cooklab.article_category.model;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import org.hibernate.Session;


import com.cooklab.util.HibernateUtil;

public class ArticleCategoryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			/////////新增
		ArticleCategoryVO a1 = new ArticleCategoryVO();
		
//		a1.setArticleCategory("敗家玩物");
//		
//        session.save(a1);
//   =========================== 修改====================================
//		a1.setArticleCategory("改動過囉");
//		a1.setArticleCategoryNo(2);
//		
//		session.update(a1);
		
//	========================刪除===========================
//		a1.setArticleCategoryNo(7);
//		
//		session.delete(a1);
//           	
           	
//   ========================查詢============================		
//	==============全部查詢===========================	
	List<ArticleCategoryVO> list1 = session.createQuery("from ArticleCategoryVO").list();
	System.out.println(list1);
		
// =============單筆查詢========================
		
//		ArticleCategoryVO a2 = session.createQuery("from ArticleCategoryVO where articleCategoryNo = 3", ArticleCategoryVO.class).uniqueResult();
//		System.out.println(a2);
		
		session.getTransaction().commit();
		session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
	

	}

}
