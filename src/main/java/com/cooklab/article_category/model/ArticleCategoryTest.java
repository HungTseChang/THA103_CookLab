package com.cooklab.article_category.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.article_category.model.ArticleCategoryVO;
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
		a1.setArticleCategory("改動過囉");
		a1.setArticleCategoryNo(2);
		
		session.update(a1);
		
//	========================刪除===========================
           	

//           	
           	
//   ========================查詢============================		

//	List<ArticleVO> list1 = session.createQuery("from ArticleVO").list();

		

			
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
