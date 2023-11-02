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
			System.out.println("新增成功");
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}

	}

	@Override
	public void update(AdvertiseVO advertise) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Session session = getSession();
		try {
			session.beginTransaction();
			session.update(advertise);
			session.getTransaction().commit();

		} catch (

		Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
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
		} catch (

				Exception e) {
					e.printStackTrace();
					session.getTransaction().rollback();
				} finally {
					HibernateUtil.shutdown();
				}
	}

	@Override
	public AdvertiseVO findByPrimaryKey(Integer advertiseNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Session session = getSession();
		try {
			session.beginTransaction();
			AdvertiseVO advertiseVo = session
				.createQuery("from AdvertiseVO where advertise_no =" + advertiseNo, AdvertiseVO.class).uniqueResult();
			session.getTransaction().commit();
		System.out.println("搜一筆");
		return advertiseVo;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
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
			System.out.println("搜尋");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<AdvertiseVO> upAd() {
//<<<<<<< HEAD
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//			Session session = getSession();
			try {
				session.beginTransaction();
				Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	
				String hql = "FROM AdvertiseVO ad " 
						+ "WHERE :currentTime >= ad.AdvertiseShelfTime "
						+ "AND :currentTime <= ad.AdvertiseOffsaleTime " 
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
