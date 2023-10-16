package com.cooklab.article.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.article_category.model.ArticleCategoryVO;
import com.cooklab.util.HibernateUtil;

public class ArticleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			/////////新增
//			ArticleVO ArticleVO = new ArticleVO(1,"今天大家吃甚麼?",1,(byte) 1,"吃葡萄不吐葡萄皮",1,1);
//
//          session.save(ArticleVO);
 ////=========================== 修改====================================
			 
//           	ArticleVO ArticleVO1  = session.get(ArticleVO.class, 1);
//           	if(ArticleVO1 != null) {
//           		ArticleVO1.setArticleCategory("有聊版");
//           	}
			
//	========================刪除===========================
           	
			ArticleVO ArticleVO2 = session.get(ArticleVO.class, 7);
          	if(ArticleVO2 != null) {
           		session.delete(ArticleVO2);
           	}
//           	
           	
//           	=============================================		
			////查詢
	List<ArticleVO> list1 = session.createQuery("from ArticleVO").list();
			
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
