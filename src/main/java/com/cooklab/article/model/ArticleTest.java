package com.cooklab.article.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.article_category.model.ArticleCategoryVO;
import com.cooklab.util.HibernateUtil;

public class ArticleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
		///////// 新增
//			ArticleVO ArticleVO = new ArticleVO(1,"今天大家吃甚麼?",1,(byte) 1,"吃葡萄不吐葡萄皮",1,1);
//
//          session.save(ArticleVO);
		//// =========================== 修改====================================

//           	ArticleVO ArticleVO1  = session.get(ArticleVO.class, 1);
//           	System.out.println(ArticleVO1);
//           	ArticleVO1.setArticleCategoryNo(1);
//           	session.update(ArticleVO1);
//		ArticleHBDAO bbb = new ArticleHBDAO();
//		ArticleVO ArticleVO1 = bbb.findByPrimaryKey(1);
////			System.out.println(ArticleVO1);
//			ArticleVO1.setArticleCategoryNo(1);
//		bbb.update(ArticleVO1);
		ArticleHBDAO dao=new ArticleHBDAO();
		ArticleVO existingArticle = dao.findByPrimaryKey(1);

		existingArticle.setArticleStatus(Byte.valueOf("1"));
		System.out.println(existingArticle);
		dao.update(existingArticle);
//	========================刪除===========================

//			ArticleVO ArticleVO2 = session.get(ArticleVO.class, 7);
//          	if(ArticleVO2 != null) {
//           		session.delete(ArticleVO2);
//           	}
//           	

//           	=============================================		
		//// 查詢
//    		ArticleVO a2 = session.createQuery("from ArticleVO where articleCategoryNo = 3", ArticleVO.class).uniqueResult();
//    		System.out.println(a2);			

//          	
//			session.getTransaction().commit();
//			session.close();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}

		Integer viewCount = 1 + Integer.valueOf(("6"));
		System.out.println(viewCount);
	}

}
