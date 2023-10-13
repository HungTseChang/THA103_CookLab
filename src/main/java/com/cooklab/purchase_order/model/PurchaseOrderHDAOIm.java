package com.cooklab.purchase_order.model;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.purchase_order_detail.model.*;
import com.cooklab.util.HibernateUtil;

public class PurchaseOrderHDAOIm implements PurchaseOrderDAO{
	
	private SessionFactory factory;

	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	public PurchaseOrderHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}
	
	public PurchaseOrderHDAOIm() {
		// TODO Auto-generated constructor stub
	}


	
	
	
	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {

			session.beginTransaction();


			PurchaseOrderVO purchaseOrder = session.get(PurchaseOrderVO.class, 5);
			for (PurchaseOrderDetailVO e : purchaseOrder.getPurchaseOrderDetail()) {
				System.out.println(e.getProductName());
			}
			
			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
	}





	@Override
	public void insert(PurchaseOrderVO purchaseOrder) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void update(PurchaseOrderVO purchaseOrder) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void delete(Integer purchaseOrderNo) {
		PurchaseOrderVO purchaseOrder = getSession().get(PurchaseOrderVO.class, purchaseOrderNo);
		if (purchaseOrder != null) {
			getSession().delete(purchaseOrder);
		}
		
	}





	@Override
	public PurchaseOrderVO findByPrimaryKey(Integer purchaseOrderNo) {
		// TODO Auto-generated method stub
		return null;
	}





	@Override
	public List<PurchaseOrderVO> getAll() {
		return getSession().createQuery("from PurchaseOrderVO", PurchaseOrderVO.class).list();
	}



}
