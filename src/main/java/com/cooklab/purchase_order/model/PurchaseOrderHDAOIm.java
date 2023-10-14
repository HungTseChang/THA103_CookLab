package com.cooklab.purchase_order.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.cooklab.util.HibernateUtil;
import com.cooklab.purchase_order.model.*;

public class PurchaseOrderHDAOIm implements PurchaseOrderDAO {

	@Override
	public void insert(PurchaseOrderVO purchaseOrderVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(purchaseOrderVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
	}

	@Override
	public void update(PurchaseOrderVO purchaseOrderVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(purchaseOrderVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}

	}

	@Override
	public void delete(Integer purchaseOrderNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PurchaseOrderVO vo = session.get(PurchaseOrderVO.class, purchaseOrderNO);
			if(vo !=null) {
				session.delete(vo);
			}
			session.getTransaction().commit();
			System.out.println("搜尋");
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}

	}

	@Override
	public PurchaseOrderVO findByPrimaryKey(Integer purchaseOrderNO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PurchaseOrderVO purchaseOrderVO = session.createQuery("from PurchaseOrderVO where  purchaseOrderNO =" + purchaseOrderNO, PurchaseOrderVO.class)
					.uniqueResult();

			session.getTransaction().commit();
			System.out.println("搜一筆");
			return purchaseOrderVO;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<PurchaseOrderVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<PurchaseOrderVO> list = session.createQuery("from PurchaseOrderVO ", PurchaseOrderVO.class).list();
			session.getTransaction().commit();
			System.out.println("搜尋");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return null;
	}

}
