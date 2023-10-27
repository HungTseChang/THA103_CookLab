package com.cooklab.promo_code.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;

public class PromoCodeHibernateTest {

	public PromoCodeHibernateTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//新增資料
//			PromoCodeVO promoCode1 = new PromoCodeVO();
//			promoCode1.setPromoCodeSerialNumber("AZ213");
//			promoCode1.setStartTime(java.sql.Timestamp.valueOf("2023-09-09 12:34:56"));
//			promoCode1.setEndTime(java.sql.Timestamp.valueOf("2027-09-09 12:34:56"));
//			promoCode1.setPercentageDiscountAmount(0);
//			promoCode1.setFixedDiscountAmount(1000);
//			promoCode1.setUsagesAllowed(2000);
//			promoCode1.setMinimumConsumption(200);
//			System.out.println(promoCode1);
//			session.saveOrUpdate(promoCode1);
			
			//更新資料
//			PromoCodeVO promoCodeVO2 = session.get(PromoCodeVO.class, 1);
//			if(promoCodeVO2 != null) {
//				promoCodeVO2.setMinimumConsumption(9999);
//			}
//			System.out.println(promoCodeVO2);
			
			//刪除資料
			PromoCodeVO promoCodeVO3 = session.get(PromoCodeVO.class,3);
			if(promoCodeVO3 !=null) {
				session.delete(promoCodeVO3);
			}
			
			//查詢資料(多筆)
			List<PromoCodeVO> list = session.createQuery("from PromoCodeVO ",PromoCodeVO.class).list();
			System.out.println(list);
			
			//查詢資料(單筆)
//			PromoCodeVO memberOrderVO = session.createQuery("from PromoCodeVO where promoCodeNo = 1", PromoCodeVO.class).uniqueResult();
//			System.out.println(memberOrderVO);
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			HibernateUtil.shutdown();
		}
	}

}
