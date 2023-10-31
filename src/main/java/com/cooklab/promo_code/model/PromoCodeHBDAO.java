package com.cooklab.promo_code.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.util.HibernateUtil;

public class PromoCodeHBDAO implements PromoCodeDAO {

	private SessionFactory factory;

	public PromoCodeHBDAO(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(PromoCodeVO promoCode) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
//		try {
//			session.beginTransaction();
		session.save(promoCode);

//			session.getTransaction().commit();
//			session.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}

	}

	@Override
	public void update(PromoCodeVO promoCode) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
//		try {
//			session.beginTransaction();
		session.update(promoCode);
//			System.out.println("開始更新");
//			session.getTransaction().commit();
//			session.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}

	}

	@Override
	public void delete(PromoCodeVO promoCode) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
//		try {
//			session.beginTransaction();
		session.delete(promoCode);
//			session.getTransaction().commit();
//			session.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}

	}

	@Override
	public PromoCodeVO findByPrimaryKey(Integer promoCodeNo) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
//		try {
//
//			session.beginTransaction();
		PromoCodeVO promoCodeVo = session
				.createQuery("from PromoCodeVO where promo_code_no=" + promoCodeNo, PromoCodeVO.class).uniqueResult();

//			session.getTransaction().commit();
		return promoCodeVo;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//
//		return null;
	}

	@Override
	public List<PromoCodeVO> getAll() {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
//		try {
//			session.beginTransaction();
		List<PromoCodeVO> list = session.createQuery("from PromoCodeVO", PromoCodeVO.class).list();

//			session.getTransaction().commit();
		return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}

//		return null;
	}

	@Override
	public PromoCodeVO findByPromoCodeSerialNumber(String promoCodeSerialNumber) {
		Session session = getSession();

		String hql = "FROM PromoCodeVO pc WHERE pc.promoCodeSerialNumber = :serialNumber";
		Query query = session.createQuery(hql);
		query.setParameter("serialNumber", promoCodeSerialNumber);

		PromoCodeVO promoCode = (PromoCodeVO) query.uniqueResult(); // 使用uniqueResult获取单一结果

		session.close(); // 记得关闭Session

		return promoCode;
	}
}
