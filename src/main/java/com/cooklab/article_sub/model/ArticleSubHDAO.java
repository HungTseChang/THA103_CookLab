package com.cooklab.article_sub.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;

public class ArticleSubHDAO  implements ArticleSubDAO{

	@Override
	public void insert(ArticleSubVO articleSubVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(articleSubVO);
			
			session.getTransaction().commit();
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void update(ArticleSubVO articleSubVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(articleSubVO);
			
			session.getTransaction().commit();
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		
	}

	@Override
	public void delete(Integer articleSubNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(articleSubNo);
			
			session.getTransaction().commit();
			session.close();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public ArticleSubVO findByPrimaryKey(Integer articleSubNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ArticleSubVO artVO = session.createQuery("from ArticleSubVO where articleSubNo= "+ articleSubNo,
					ArticleSubVO.class).uniqueResult();
			session.getTransaction().commit();
			return artVO;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<ArticleSubVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			List<ArticleSubVO> list = session.createQuery("from ArticleSubVO ",ArticleSubVO.class).
					list();
			
			session.getTransaction().commit();
			return list;
			
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	
	
	
}
