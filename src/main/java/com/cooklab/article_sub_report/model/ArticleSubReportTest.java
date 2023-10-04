package com.cooklab.article_sub_report.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.cooklab.notify_center.model.NotifyCenterVO;
import com.cooklab.util.HibernateUtil;


public class ArticleSubReportTest {
	public static void main(String[] args) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {			
			//新增資料  article_sub_no,reporter_id,reporting_reason,reporting_status
			session.beginTransaction();
//			
			ArticleSubReportVO a1 =new ArticleSubReportVO();
//			a1.setArticleSubNo(1);
//			a1.setReporterId(1);
//			a1.setReportingReason("開心想檢舉");
//			a1.setReportingStatus((byte) 1);
//			a1.setReportingAnswer("不給你過就是不給你過");
//			
//			session.save(a1);
			
			//更新資料
			a1 = session.get(ArticleSubReportVO.class, 3);
			a1.setReportingAnswer("Would you kidding me ?");
			
			//刪除資料
//			a1.setArticleSubReportNo(2);
//			session.delete(a1);	
	
			//查詢全部
//		Query<ArticleSubReportVO> query3 = session.createQuery(
//				" from ArticleSubReportVO", ArticleSubReportVO.class);
//		List<ArticleSubReportVO> list2 = query3.list();
//		System.out.println(list2);
			
			
			//查詢資料(單筆)
//			a1 = session.createQuery("from ArticleSubReportVO where articleSubReportNo = 3", ArticleSubReportVO.class).uniqueResult();
//			System.out.println(a1);
		
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}

	}
}
