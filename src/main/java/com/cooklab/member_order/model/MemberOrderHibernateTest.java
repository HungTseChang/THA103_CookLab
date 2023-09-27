package com.cooklab.member_order.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;

public class MemberOrderHibernateTest {

	public MemberOrderHibernateTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//新增資料
//			MemberOrderVO memberorder1 = new MemberOrderVO();
//			memberorder1.setMemberId(1);
//			memberorder1.setOrderStatus((byte) 1);
//			memberorder1.setTotalOrderAmount(1000);
//			memberorder1.setCheckoutAmount(2000);
//			memberorder1.setPromoCodeNo(1);
//			memberorder1.setShippingAddress("Taipei");
//			System.out.println(memberorder1);
//			session.saveOrUpdate(memberorder1);
			
			//更新資料
//			MemberOrderVO memberorder2 = new MemberOrderVO();
//			memberorder2.setOrderNo(1);
//			memberorder2.setMemberId(1);
//			memberorder2.setOrderStatus((byte) 1);
//			memberorder2.setTotalOrderAmount(1000);
//			memberorder2.setCheckoutAmount(2000);
//			memberorder2.setPromoCodeNo(1);
//			memberorder2.setShippingAddress("新北");
//			System.out.println(memberorder2);
//			session.saveOrUpdate(memberorder2);
			
			//刪除資料
//			MemberOrderVO memberorder3 = session.get(MemberOrderVO.class,3);
//			if(memberorder3 !=null) {
//				session.delete(memberorder3);
//			}
			
			//查詢資料(多筆)
			List<MemberOrderVO> list = session.createQuery("from MemberOrderVO ",MemberOrderVO.class).list();
			System.out.println(list);
			
			//查詢資料(單筆)
			MemberOrderVO memberOrderVO = session.createQuery("from MemberOrderVO where orderNo = 1", MemberOrderVO.class).uniqueResult();
			System.out.println(memberOrderVO);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			HibernateUtil.shutdown();
		}
	}

}
