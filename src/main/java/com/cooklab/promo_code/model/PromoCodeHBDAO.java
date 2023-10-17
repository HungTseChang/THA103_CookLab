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
	public void insert(PromoCodeVO PromoCodeVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

           	session.save(PromoCodeVO);
			session.getTransaction().commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
	

	}

//============================insert完結=======================================
	@Override
	public void update(PromoCodeVO PromoCodeVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		PromoCodeVO   PromoCodeVO1 = null;
		try {
			session.beginTransaction();

			PromoCodeVO1  = session.get(PromoCodeVO.class,PromoCodeVO.getPromoCodeNo());
       	if(PromoCodeVO1 != null) {
       		PromoCodeVO1.setPromoCodeSerialNumber(PromoCodeVO.getPromoCodeSerialNumber());
       		PromoCodeVO1.setStartTime(PromoCodeVO.getStartTime());
       		PromoCodeVO1.setEndTime(PromoCodeVO.getEndTime());
       		PromoCodeVO1.setPercentageDiscountAmount(PromoCodeVO.getPercentageDiscountAmount());
       		PromoCodeVO1.setFixedDiscountAmount(PromoCodeVO.getFixedDiscountAmount());
       		PromoCodeVO1.setUsagesAllowed(PromoCodeVO.getUsagesAllowed());
       		PromoCodeVO1.setMinimumConsumption(PromoCodeVO.getMinimumConsumption());
       		
       	}
			session.getTransaction().commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}


	}
	
//=================================update結束========================================
@Override
	public void delete(Integer promoCodeNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PromoCodeVO promoCodeVO2 = session.get(PromoCodeVO.class, promoCodeNo);
      	if(promoCodeVO2 != null) {
       		session.delete(promoCodeVO2);
       	}
		session.getTransaction().commit();
		session.close();
		
	} catch (Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	} finally {
		HibernateUtil.shutdown();
	}
	}

//===============================delete結束======================================
	@Override
	public PromoCodeVO findByPrimaryKey(Integer promoCodeNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		PromoCodeVO PromoCodeVO3 =null;
		try {
			session.beginTransaction();
			 PromoCodeVO3 =session.get(PromoCodeVO.class, promoCodeNo);
//			System.out.println(
//					"promoCodeNo :"+PromoCodeVO3.getpromoCodeNo()+"\n  "
//				+ "PromoCodeSerialNumber :"+PromoCodeVO3.getPromoCodeSerialNumber()+"\n"
//				+"StartTime :"+ PromoCodeVO3.getStartTime()+"\n"
//				+"EndTime :"+PromoCodeVO3.getEndTime()+"\n"
//				+"PercentageDiscountAmount :"+PromoCodeVO3.getPercentageDiscountAmount()+"\n"
//				+"FixedDiscountAmount :"+PromoCodeVO3.getFixedDiscountAmount()+"\n"
//				+"UsagesAllowed :"+PromoCodeVO3.getUsagesAllowed()+"\n"
//				+"MinimumConsumption :"+PromoCodeVO3.getMinimumConsumption()+"\n"
//				+"tCreatedTimestamp :"+PromoCodeVO3.getCreatedTimestamp()
//				+"\n"+"============================================="
//						); 
//    
		session.getTransaction().commit();
		session.close();
	} catch (Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	} finally {
		HibernateUtil.shutdown();
	}
		return PromoCodeVO3;
	}

//===========================findByPrimaryKey結束==============================================
	@Override
	public List<PromoCodeVO> getAll() {
		List<PromoCodeVO> list1 = new ArrayList<PromoCodeVO>();

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			list1 = session.createQuery("from PromoCodeVO",PromoCodeVO.class).list();
//			for(int i=0; i<list1.size();i++) {
//				System.out.println(
//			"promoCodeNo :"+PromoCodeVO3.getpromoCodeNo()+"\n  "
//		+ "PromoCodeSerialNumber :"+PromoCodeVO3.getPromoCodeSerialNumber()+"\n"
//		+"StartTime :"+ PromoCodeVO3.getStartTime()+"\n"
//		+"EndTime :"+PromoCodeVO3.getEndTime()+"\n"
//		+"PercentageDiscountAmount :"+PromoCodeVO3.getPercentageDiscountAmount()+"\n"
//		+"FixedDiscountAmount :"+PromoCodeVO3.getFixedDiscountAmount()+"\n"
//		+"UsagesAllowed :"+PromoCodeVO3.getUsagesAllowed()+"\n"
//		+"MinimumConsumption :"+PromoCodeVO3.getMinimumConsumption()+"\n"
//		+"tCreatedTimestamp :"+PromoCodeVO3.getCreatedTimestamp()
//		+"\n"+"============================================="
//				); 
//
			
			
			
		session.getTransaction().commit();
		session.close();

	} catch (Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	} finally {
		HibernateUtil.shutdown();
	}
		return list1;

	}
	
	
	
//	=============================getAll結束==================================================
	
	
	public static void main(String[] args) {

		PromoCodeHBDAO dao = new PromoCodeHBDAO();
		// 新增
		// 新增
//		PromoCodeVO PromoCodeVO1 = new PromoCodeVO();
//		PromoCodeVO1.setPromoCodeSerialNumber(1);
//		PromoCodeVO1.setStartTime("2020-11-11");
//		PromoCodeVO1.setEndTime("2020-12-12");
//		PromoCodeVO1.setPercentageDiscountAmount(50);
//		PromoCodeVO1.setFixedDiscountAmount(50);
//		PromoCodeVO1.setUsagesAllowed(1000);
//		PromoCodeVO1.setMinimumConsumption(20);
//		dao.insert(PromoCodeVO1);

////			// 修改
//		PromoCodeVO PromoCodeVO2 = new PromoCodeVO();
//		PromoCodeVO2.setPromoCodeSerialNumber(2);
//		PromoCodeVO2.setStartTime("2022-10-10");
//		PromoCodeVO2.setEndTime("2022-11-11");
//		PromoCodeVO2.setPercentageDiscountAmount(30);
//		PromoCodeVO2.setFixedDiscountAmount(30);
//		PromoCodeVO2.setUsagesAllowed(500);
//		PromoCodeVO2.setMinimumConsumption(100);
//		dao.insert(PromoCodeVO2);
//
//////			// 刪除
//			dao.delete(3);
//
//
//////
//////			// 查詢
			PromoCodeVO PromoCodeVO3 = dao.findByPrimaryKey(2);
			
//			System.out.print(PromoCodeVO3.getPromoCodeNo() + ",");
//			System.out.print(PromoCodeVO3.getPromoCodeSerialNumber() + ",");
//			System.out.print(PromoCodeVO3.getStartTime() + ",");
//			System.out.print(PromoCodeVO3.getEndTime() + ",");
//			System.out.print(PromoCodeVO3.getPercentageDiscountAmount() + ",");
//			System.out.print(PromoCodeVO3.getFixedDiscountAmount() + ",");
//			System.out.print(PromoCodeVO3.getUsagesAllowed()+ ",");
//			System.out.print(PromoCodeVO3.getMinimumConsumption()+",");
//			System.out.println(PromoCodeVO3.getCreatedTimestamp() + ",");
//
//			System.out.println("---------------------");
//////
//////			// 查詢
//		List<PromoCodeVO> list = dao.getAll();
//		for (PromoCodeVO PromoCodeVO : list) {
//			System.out.print(PromoCodeVO3.getPromoCodeNo() + ",");
//			System.out.print(PromoCodeVO3.getPromoCodeSerialNumber() + ",");
//			System.out.print(PromoCodeVO3.getStartTime() + ",");
//			System.out.print(PromoCodeVO3.getEndTime() + ",");
//			System.out.print(PromoCodeVO3.getPercentageDiscountAmount() + ",");
//			System.out.print(PromoCodeVO3.getFixedDiscountAmount() + ",");
//			System.out.print(PromoCodeVO3.getUsagesAllowed()+ ",");
//			System.out.print(PromoCodeVO3.getMinimumConsumption()+",");
//			System.out.println(PromoCodeVO3.getCreatedTimestamp() + ",");
//
//			System.out.println("---------------------");
//		}
//	
	
	
	
	
	
}}
