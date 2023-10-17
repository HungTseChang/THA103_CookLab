package com.cooklab.article_report.model.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;

public class ArticleReportTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			/////////新增
			ArticleReportVO ArticleReportVO = new ArticleReportVO(3,1,"心情不好",(byte) 1);

           	session.save(ArticleReportVO);
 ////=========================== 修改====================================
			 
//           	ArticleReportVO ArticleReportVO1  = session.get(ArticleReportVO.class, 1);
//           	if(ArticleReportVO1 != null) {
//           		ArticleReportVO1.setReportingReason("心情很好");
//           	}
			
//	========================刪除===========================
           	
        	ArticleReportVO ArticleReportVO2 = session.get(ArticleReportVO.class, 3);
          	if(ArticleReportVO2 != null) {
           		session.delete(ArticleReportVO2);
           	}
//           	
           	
//           	=============================================		
			////查詢
	List<ArticleReportVO> list1 = session.createQuery("from ArticleReportVO").list();
for(int i=0; i<list1.size();i++) {
	System.out.println(
		"ArticleReportNo :"+list1.get(i).getArticleReportNo()+"\n  "
	+ "ArticleNo :"+list1.get(i).getArticleNo()+"\n"
	+"ReporterId :"+ list1.get(i).getReporterId()+"\n"
	+"ReportingReason :"+list1.get(i).getReportingReason()+"\n"
	+"ReportingStatus :"+list1.get(i).getReportingStatus()+"\n"
	+"tCreatedTimestamp :"+list1.get(i).getCreatedTimestamp()
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
