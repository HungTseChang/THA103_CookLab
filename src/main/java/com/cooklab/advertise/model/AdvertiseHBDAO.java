package com.cooklab.advertise.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.cooklab.util.HibernateUtil;

public class AdvertiseHBDAO implements AdvertiseDAO {

	@Override
	public void insert(AdvertiseVO advertise) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
				session.beginTransaction();
				session.save(advertise);
				
				session.getTransaction().commit();
				session.close();
				
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public void update(AdvertiseVO advertise) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
				session.beginTransaction();
				session.update(advertise);
				System.out.println("嘗試更新資料庫");
				session.getTransaction().commit();
				session.close();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public void delete(AdvertiseVO advertise) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
				session.beginTransaction();
				session.delete(advertise);
				session.getTransaction().commit();
				session.close();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public AdvertiseVO findByPrimaryKey(Integer advertiseNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
				session.beginTransaction();
				AdvertiseVO advertiseVo = session.createQuery("from AdvertiseVO where advertise_no =" +
				advertiseNo,AdvertiseVO.class).uniqueResult();
				
				session.getTransaction().commit();
				return advertiseVo;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
	}

	@Override
	public List<AdvertiseVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
				session.beginTransaction();
				List<AdvertiseVO> list =session.createQuery("from AdvertiseVO", AdvertiseVO.class)
						.list();
				
				
				session.getTransaction().commit();
				return list;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return null;
	}

}
