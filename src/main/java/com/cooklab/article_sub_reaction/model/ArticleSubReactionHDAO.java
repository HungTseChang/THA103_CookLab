package com.cooklab.article_sub_reaction.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.article_reaction.model.ArticleReactionVO;
import com.cooklab.util.HibernateUtil;

public class ArticleSubReactionHDAO implements ArticleSubReactionDAO  {
	
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public ArticleSubReactionHDAO(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(ArticleSubReactionVO articleSubReactionVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.save(articleSubReactionVO);
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public void update(ArticleSubReactionVO articleSubReactionVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {		
				session.update(articleSubReactionVO);
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public void delete(Integer articleSubReactionNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArticleSubReactionVO findByPrimaryKey(Integer articleSubReactionNo) {
		
		return getSession().get(ArticleSubReactionVO.class, articleSubReactionNo);
	}

	@Override
	public List<ArticleSubReactionVO> getAll() {
		return getSession().createQuery("from ArticleSubReactionVO",ArticleSubReactionVO.class).list();
	}

	@Override //用來尋找會員是否已對該篇按過讚
	public ArticleSubReactionVO findByTwoCol(Integer memberId, Integer articleSubNo) {
	    return getSession().createQuery("from ArticleSubReactionVO where memberId = :memberId and articleSubNo = :articleSubNo", ArticleSubReactionVO.class)
	            .setParameter("memberId", memberId)
	            .setParameter("articleSubNo", articleSubNo)
	            .uniqueResult();
	}

	@Override//用來計算文章的按讚數
	public Long allCount(Integer articleSubNo, Byte statuts) {
		return (Long) getSession().createQuery("select count (*) from ArticleSubReactionVO where articleSubNo = :articleSubNo and statuts = :statuts")
				.setParameter("articleSubNo",articleSubNo)
				.setParameter("statuts",statuts)
				.uniqueResult();
	}

	@Override
	public void saveOrUpdate(ArticleSubReactionVO articleSubReactionVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.saveOrUpdate(articleSubReactionVO);
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	

	

}
