package com.cooklab.article_reaction.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.article_report.model.model.ArticleReportVO;
import com.cooklab.util.HibernateUtil;

public class ArticleReactionVOTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			/////////新增
//			ArticleReactionVO ArticleReactionVO = new ArticleReactionVO(1,1, (byte)1);
//
//           	session.save(ArticleReactionVO);
 ////=========================== 修改====================================
			 
//           	ArticleReactionVO ArticleReactionVO1  = session.get(ArticleReactionVO.class, 1);
//           	if(ArticleReactionVO1 != null) {
//           		ArticleReactionVO1.setMemberId(5);
//           	}
			
//	========================刪除===========================
           	
			ArticleReactionVO ArticleReactionVO2 = session.get(ArticleReactionVO.class, 6);
          	if(ArticleReactionVO2 != null) {
           		session.delete(ArticleReactionVO2);
           	}
//           	
           	
//           	=============================================		
			////查詢
	List<ArticleReactionVO> list1 = session.createQuery("from ArticleReactionVO").list();
for(int i=0; i<list1.size();i++) {
	System.out.println(
		"ArticleReactionNo :"+list1.get(i).getArticleReactionNo()+"\n  "
	+ "MemberId :"+list1.get(i).getMemberId()+"\n"
	+"ArticleNo :"+ list1.get(i).getArticleNo()+"\n"
	+"Statuts :"+list1.get(i).getStatuts()+"\n"
	+"CreatedTimestamp :"+list1.get(i).getCreatedTimestamp()+"\n"
//	+"tCreatedTimestamp :"+list1.get(i).getCreatedTimestamp()
	+"\n"+"============================================="
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