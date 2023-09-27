package com.cooklab.promo_code_used.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;

public class PromoCodeUsedHibernateTest {

	public PromoCodeUsedHibernateTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//新增資料
//			PromoCodeUsedVO promoCodeUsedVO1 = new PromoCodeUsedVO();
//			promoCodeUsedVO1.setMemberId(1);
//			promoCodeUsedVO1.setPromoCodeNo(2);
//			System.out.println(promoCodeUsedVO1);
//			session.saveOrUpdate(promoCodeUsedVO1);
			
			//更新資料
//			PromoCodeUsedVO promoCodeUsedVO1 = new PromoCodeUsedVO();
//			promoCodeUsedVO1.setPromoCodeUsedNo(1);
//			promoCodeUsedVO1.setMemberId(1);
//			promoCodeUsedVO1.setPromoCodeNo(2);
//			System.out.println(promoCodeUsedVO1);
//			session.saveOrUpdate(promoCodeUsedVO1);
			
			//刪除資料
//			MemberOrderVO memberorder3 = session.get(MemberOrderVO.class,3);
//			if(memberorder3 !=null) {
//				session.delete(memberorder3);
//			}
			
			//查詢資料(多筆)
			List<PromoCodeUsedVO> list = session.createQuery("from PromoCodeUsedVO ",PromoCodeUsedVO.class).list();
			System.out.println(list);
			
			//查詢資料(單筆)
			PromoCodeUsedVO promoCodeUsedVO = session.createQuery("from PromoCodeUsedVO where promoCodeUsedNo = 1", PromoCodeUsedVO.class).uniqueResult();
			System.out.println(promoCodeUsedVO);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			HibernateUtil.shutdown();
		}
	}

}
