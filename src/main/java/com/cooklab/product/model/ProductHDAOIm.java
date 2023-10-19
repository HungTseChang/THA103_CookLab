package com.cooklab.product.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.purchase_order.model.PurchaseOrderVO;
import com.cooklab.util.HibernateUtil;


public class ProductHDAOIm implements ProductDAO {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public ProductHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(ProductVO productVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(productVO);
			System.out.println("成功");
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
	}

	@Override
	public boolean update(ProductVO productVO) {
		try {
			getSession().update(productVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Integer productNo) {
		ProductVO vo = getSession().get(ProductVO.class, productNo);
		if (vo != null) {
			getSession().delete(vo);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ProductVO findByPrimaryKey(Integer productNo) {
		return getSession().get(ProductVO.class, productNo);
	}

	@Override
	public List<ProductVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<ProductVO> list = session.createQuery("from ProductVO ", ProductVO.class).list();
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
