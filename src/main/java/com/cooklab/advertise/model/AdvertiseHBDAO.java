package com.cooklab.advertise.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.cooklab.util.HibernateUtil;

public class AdvertiseHBDAO implements AdvertiseDAO {

	private SessionFactory factory;

	public AdvertiseHBDAO(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(AdvertiseVO advertise) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Session session = getSession();
		try {
			session.beginTransaction();
			session.save(advertise);

			session.getTransaction().commit();
			session.close();

		} catch (

		Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public void update(AdvertiseVO advertise) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Session session = getSession();
		try {
			session.beginTransaction();
			session.update(advertise);
			System.out.println("嘗試更新資料庫");
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public void delete(AdvertiseVO advertise) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Session session = getSession();
		try {
			session.beginTransaction();
			session.delete(advertise);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public AdvertiseVO findByPrimaryKey(Integer advertiseNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Session session = getSession();
		try {
			session.beginTransaction();
			AdvertiseVO advertiseVo = session
					.createQuery("from AdvertiseVO where advertise_no =" + advertiseNo, AdvertiseVO.class)
					.uniqueResult();

			session.getTransaction().commit();
			return advertiseVo;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return null;
	}

	@Override
	public List<AdvertiseVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Session session = getSession();
		try {
			session.beginTransaction();
			List<AdvertiseVO> list = session.createQuery("from AdvertiseVO", AdvertiseVO.class).list();

			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return null;
	}

	@Override
	public List<AdvertiseVO> upAd() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Session session = getSession();
		try {
			session.beginTransaction();
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());

			String hql = "FROM AdvertiseVO ad " 
						+ "ORDER BY ad.AdvertiseShelfTime  DESC";

			Query query = session.createQuery(hql);
			query.setParameter("currentTime", currentTime);

			List<AdvertiseVO> listAd = query.list();
			session.getTransaction().commit();
			return listAd;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return null;
	}

}
