package com.cooklab.article_reaction.model;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.criteria.Expression;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.cooklab.article_reaction.model.*;
import com.cooklab.product.model.ProductVO;
import com.cooklab.util.HibernateUtil;

public class ArticleReactionHDAO implements ArticleReactionDAO {
	
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public ArticleReactionHDAO(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
		
	@Override
	public void insert(ArticleReactionVO ArticleReactionVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
				session.save(ArticleReactionVO);
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public void update(ArticleReactionVO ArticleReactionVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
//			
				session.saveOrUpdate(ArticleReactionVO);
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public ArticleReactionVO findByPrimaryKey(Integer articleReactionNo) {

		return getSession().get(ArticleReactionVO.class, articleReactionNo);
	}
	
	
//	@Override
//	public ArticleReactionVO findByTwoCol(Integer memberId, Integer articleNo) {
//		return getSession().createQuery("from ArticleReactionVO where memberId = '" + memberId + "'" +
//	"articleNo= '"+ articleNo + "'"
//	, ArticleReactionVO.class)
//				.uniqueResult();
//		
//	}
	
	@Override
	public ArticleReactionVO findByTwoCol(Integer memberId, Integer articleNo) {
	    return getSession().createQuery("from ArticleReactionVO where memberId = :memberId and articleNo = :articleNo", ArticleReactionVO.class)
	            .setParameter("memberId", memberId)
	            .setParameter("articleNo", articleNo)
	            .uniqueResult();
	}


	@Override
	public List<ArticleReactionVO> getAll() {

		return getSession().createQuery("from ArticleReactionVO", ArticleReactionVO.class).list();
	
	}

	@Override
	public void delete(Integer articleReactionNo) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
				
	}

}
