package com.cooklab.article_sub.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.cooklab.article_sub.model.ArticleSubVO;
import com.cooklab.util.HibernateUtil;

public class ArticleSubTest {
	public static void main(String[] args) throws Exception {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		ArticleSubJDBCDAO dao = new ArticleSubJDBCDAO();

//		// 新增
//		Emp emp1 = new Emp();
//		emp1.setEname("David");
//		emp1.setJob("MANAGER");
//		emp1.setHiredate(new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-07"));
//		emp1.setSal(new BigDecimal(50000));
//		emp1.setComm(new BigDecimal(500));
//		emp1.setEmpdeptno(10);
//		dao.add(emp1);
		
		ArticleSubVO a1 = new ArticleSubVO();
//		a1.setArticleNo(1);
//		a1.setMemberId(1);
//		a1.setArticleSubCount(1);
//		a1.setArticleSubStatus((byte) 1);
//		a1.setArticleSubContent("不知道打什麼");
//		dao.insert(a1);
//		
		
		try {			
			//查詢全部
			session.beginTransaction();
			Query<ArticleSubVO> query3 = session.createQuery(
					" from ArticleSubVO", ArticleSubVO.class);
			List<ArticleSubVO> list2 = query3.list();
			System.out.println(list2);
			
			
			//查詢資料(單筆)
//			ArticleSubVO articleSubVO = session.createQuery("from ArticleSubVO where articleSubNo = 3", ArticleSubVO.class).uniqueResult();
//			System.out.println(articleSubVO);
//			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}

	}
}
