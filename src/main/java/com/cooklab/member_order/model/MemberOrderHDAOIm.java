package com.cooklab.member_order.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.cooklab.util.HibernateUtil;

public class MemberOrderHDAOIm implements MemberOrderDAO {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public MemberOrderHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(MemberOrderVO memberOrderVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(memberOrderVO);
			System.out.println("新增成功");
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		
	}

	@Override
	public void update(MemberOrderVO memberOrderVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer orderNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemberOrderVO findByPrimaryKey(Integer orderNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberOrderVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
