package com.cooklab.order_detail.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;

public class OrderDetailHibernateTest {

	public OrderDetailHibernateTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//新增資料
//			OrderDetailVO orderDetailVO1 = new OrderDetailVO();
//			orderDetailVO1.setOrderNo(1);
//			orderDetailVO1.setProductNo(1);
//			orderDetailVO1.setOrderQty(500);
//			System.out.println(orderDetailVO1);
//			session.saveOrUpdate(orderDetailVO1);
			
			//更新資料
//			OrderDetailVO orderDetailVO1 = new OrderDetailVO();
//			orderDetailVO1.setOrderDetailNo(1);
//			orderDetailVO1.setOrderNo(1);
//			orderDetailVO1.setProductNo(1);
//			orderDetailVO1.setOrderQty(1000);
//			System.out.println(orderDetailVO1);
//			session.saveOrUpdate(orderDetailVO1);
			
			//刪除資料
//			OrderDetailVO orderDetail3 = session.get(OrderDetailVO.class,3);
//			if(orderDetail3 !=null) {
//				session.delete(orderDetail3);
//			}
			
			//查詢資料(多筆)
			List<OrderDetailVO> list = session.createQuery("from OrderDetailVO ",OrderDetailVO.class).list();
			System.out.println(list);
			
			//查詢資料(單筆)
			OrderDetailVO orderDetailVO = session.createQuery("from OrderDetailVO where orderDetailNo = 1", OrderDetailVO.class).uniqueResult();
			System.out.println(orderDetailVO);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			HibernateUtil.shutdown();
		}
	}

}
