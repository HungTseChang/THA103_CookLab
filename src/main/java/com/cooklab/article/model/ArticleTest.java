package com.cooklab.article.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.article_report.model.ArticleReportVO;
import com.cooklab.util.HibernateUtil;

public class ArticleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			/////////新增
//			ArticleVO ArticleVO = new ArticleVO("無聊版","今天大家吃甚麼?",1,(byte) 1,"吃葡萄不吐葡萄皮",1,1);
//
//           	session.save(ArticleVO);
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
for(int i=0; i<list1.size();i++) {
	System.out.println(
		"ArticleNo :"+list1.get(i).getArticleNo()+"\n  "
	+ "ArticleCategory :"+list1.get(i).getArticleCategory()+"\n"
	+"ArticleTitle :"+ list1.get(i).getArticleTitle()+"\n"
	+"MemberId :"+list1.get(i).getMemberId()+"\n"
	+"CreatedTimestamp :"+list1.get(i).getCreatedTimestamp()+"\n"
	+"ArticleStatus :"+list1.get(i).getArticleStatus()
	+"\n"
	+"ArticleContent :"+list1.get(i).getArticleContent()+"\n"
	+"ArticleCount :"+list1.get(i).getArticleCount()+"\n"
	+"ViewCount :"+list1.get(i).getViewCount()+"\n"
	+"LastEditTimestamp :"+list1.get(i).getLastEditTimestamp()+"\n"
	+"============================================="
			); 
}

			
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
