package com.cooklab.article_picture.model;

import java.util.List;
import  com.cooklab.article_report.model.*;
import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;

public class ArticlePictureTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			// 多筆資料查詢可用 hibernate 的 list() 或 JPA 的 getResultList()
//			List<Emp> list1 = session.createQuery("from Emp", Emp.class).list();
//			
			List<ArticleReportVO> list2 = session.createQuery("from ArticleReportVO").list();
//			
//			List<Emp> list3 = session.createQuery("from Emp order by empno", Emp.class).list();
//			
//			List<Emp> list4 = session.createQuery("from Emp order by empno desc", Emp.class).list();
//			
//			List<Emp> list5 = session.createQuery("from Emp as e order by e.empno", Emp.class).list();
			
//			List<ArticlePictureVO> list6 = session.createQuery("from com.cooklab.article_picture.model.ArticlePictureVO", ArticlePictureVO.class).list();
			System.out.println(list2);
			// '%K%' 單引號一定要加，否則會得到語法錯誤的例外
//			List<Emp> list7 = session.createQuery("from Emp where ename like '%K%'", Emp.class).list();
//			System.out.println(list7);
			
			// 單筆資料查詢可用 hibernate 的 uniqueResult() 或是 JPA 的 getSingleResult()
//			Emp emp = session.createQuery("from Emp where empno = 7001", Emp.class).uniqueResult();
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
	

	}

}
