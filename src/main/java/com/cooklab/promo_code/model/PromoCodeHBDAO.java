package com.cooklab.promo_code.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.cooklab.promo_code.model.*;
import com.cooklab.util.HibernateUtil;

public class PromoCodeHBDAO implements PromoCodeDAO {
	@Override
	public void insert(PromoCodeVO promoCode) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(promoCode);

			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public void update(PromoCodeVO promoCode) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(promoCode);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public void delete(PromoCodeVO promoCode) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(promoCode);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public PromoCodeVO findByPrimaryKey(Integer promoCodeNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

				session.beginTransaction();
				PromoCodeVO promoCodeVo = session.createQuery("from PromoCodeVO where promoCodeNo=" +
				promoCodeNo,PromoCodeVO.class).uniqueResult();
				
				session.getTransaction().commit();
				return promoCodeVo;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return null;
	}

	@Override
	public List<PromoCodeVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<PromoCodeVO> list = session.createQuery("from PromoCodeVO", PromoCodeVO.class).list();

			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		return null;
	}

	@Override
	public PromoCodeVO findByPromoCodeSerialNumber(String promoCodeSerialNumber) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String hql = "FROM PromoCodeVO p WHERE p.promoCodeSerialNumber = :serialNumber";
			Query<PromoCodeVO> query = session.createQuery(hql, PromoCodeVO.class);
			query.setParameter("serialNumber", promoCodeSerialNumber);
			PromoCodeVO promoCode = query.uniqueResult();

			session.getTransaction().commit();
			return promoCode;

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

}
