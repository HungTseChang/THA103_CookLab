package com.cooklab.advertise.model;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;


import com.cooklab.promo_code.model.*;
import com.cooklab.util.HibernateUtil;

public class AdvertiseHBDAO implements AdvertiseDAO {
	@Override
	public void insert(AdvertiseVO AdvertiseVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

           	session.save(AdvertiseVO);
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
	public void update(AdvertiseVO AdvertiseVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		AdvertiseVO  AdvertiseVO1 = null;
		try {
			session.beginTransaction();

			AdvertiseVO1  = session.get(AdvertiseVO.class,AdvertiseVO.getAdvertiseNo());
       	if(AdvertiseVO1 != null) {
       		AdvertiseVO1.setAdvertiseName(AdvertiseVO.getAdvertiseName());
       		AdvertiseVO1.setAdvertiseShelfTime(AdvertiseVO.getAdvertiseShelfTime());
       		AdvertiseVO1.setAdvertiseOffsaleTime(AdvertiseVO.getAdvertiseOffsaleTime());
       		AdvertiseVO1.setAdvertiseImg(AdvertiseVO.getAdvertiseImg());
       		AdvertiseVO1.setAdvertiseUrl(AdvertiseVO.getAdvertiseUrl());
       	
       		
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
	public void delete(Integer advertiseNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			AdvertiseVO advertiseVO2 = session.get(AdvertiseVO.class, advertiseNo);
      	if(advertiseVO2 != null) {
       		session.delete(advertiseVO2);
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
	public AdvertiseVO findByPrimaryKey(Integer advertiseNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		AdvertiseVO AdvertiseVO3 =null;
		try {
			session.beginTransaction();
			 AdvertiseVO3 =session.get(AdvertiseVO.class, advertiseNo);
//			System.out.println(
//					"advertiseNo :"+AdvertiseVO3.getadvertiseNo()+"\n  "
//				+ "AdvertiseName :"+AdvertiseVO3.getAdvertiseName()+"\n"
//				+"AdvertiseShelfTime :"+ AdvertiseVO3.getAdvertiseShelfTime()+"\n"
//				+"AdvertiseOffsaleTime :"+AdvertiseVO3.getAdvertiseOffsaleTime()+"\n"
//				+"AdvertiseImg :"+AdvertiseVO3.getAdvertiseImg()+"\n"
//				+"AdvertiseUrl :"+AdvertiseVO3.getAdvertiseUrl()+"\n"
//				+"CreatedTimestamp :"+AdvertiseVO3.getCreatedTimestamp()
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
		return AdvertiseVO3;
	}

//===========================findByPrimaryKey結束==============================================
	@Override
	public List<AdvertiseVO> getAll() {
		List<AdvertiseVO> list1 = new ArrayList<AdvertiseVO>();

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			list1 = session.createQuery("from AdvertiseVO",AdvertiseVO.class).list();
//			for(int i=0; i<list1.size();i++) {
//				System.out.println(
//			//					"advertiseNo :"+AdvertiseVO3.getadvertiseNo()+"\n  "
//			+ "AdvertiseName :"+AdvertiseVO3.getAdvertiseName()+"\n"
//			+"AdvertiseShelfTime :"+ AdvertiseVO3.getAdvertiseShelfTime()+"\n"
//			+"AdvertiseOffsaleTime :"+AdvertiseVO3.getAdvertiseOffsaleTime()+"\n"
//			+"AdvertiseImg :"+AdvertiseVO3.getAdvertiseImg()+"\n"
//			+"AdvertiseUrl :"+AdvertiseVO3.getAdvertiseUrl()+"\n"
//			+"CreatedTimestamp :"+AdvertiseVO3.getCreatedTimestamp()
//			+"\n"+"============================================="
//					); 
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

		AdvertiseHBDAO dao = new AdvertiseHBDAO();
		// 新增
		// 新增
//		AdvertiseVO AdvertiseVO1 = new AdvertiseVO();
//		AdvertiseVO1.setAdvertiseName(1);
//		AdvertiseVO1.setAdvertiseShelfTime("2020-11-11");
//		AdvertiseVO1.setAdvertiseOffsaleTime("2020-12-12");
//		AdvertiseVO1.setAdvertiseImg(50);
//		AdvertiseVO1.setAdvertiseUrl(50);
//		dao.insert(AdvertiseVO1);

////			// 修改
//		AdvertiseVO AdvertiseVO2 = new AdvertiseVO();
//		AdvertiseVO2.setAdvertiseName(2);
//		AdvertiseVO2.setAdvertiseShelfTime("2022-10-10");
//		AdvertiseVO2.setAdvertiseOffsaleTime("2022-11-11");
//		AdvertiseVO2.setAdvertiseImg(30);
//		AdvertiseVO2.setAdvertiseUrl(30);
//		dao.insert(AdvertiseVO2);
//
//////			// 刪除
//			dao.delete(3);
//
//
//////
//////			// 查詢
			AdvertiseVO AdvertiseVO3 = dao.findByPrimaryKey(2);
			
//			System.out.print(AdvertiseVO3.getAdvertiseNo() + ",");
//			System.out.print(AdvertiseVO3.getAdvertiseName() + ",");
//			System.out.print(AdvertiseVO3.getAdvertiseShelfTime() + ",");
//			System.out.print(AdvertiseVO3.getAdvertiseOffsaleTime() + ",");
//			System.out.print(AdvertiseVO3.getAdvertiseImg() + ",");
//			System.out.print(AdvertiseVO3.getAdvertiseUrl() + ",");
//			System.out.println(AdvertiseVO3.getCreatedTimestamp() + ",");
//
//			System.out.println("---------------------");
//////
//////			// 查詢
//		List<AdvertiseVO> list = dao.getAll();
//		for (AdvertiseVO AdvertiseVO : list) {
//			System.out.print(AdvertiseVO3.getAdvertiseNo() + ",");
//			System.out.print(AdvertiseVO3.getAdvertiseName() + ",");
//			System.out.print(AdvertiseVO3.getAdvertiseShelfTime() + ",");
//			System.out.print(AdvertiseVO3.getAdvertiseOffsaleTime() + ",");
//			System.out.print(AdvertiseVO3.getAdvertiseImg() + ",");
//			System.out.print(AdvertiseVO3.getAdvertiseUrl() + ",");
//			System.out.println(AdvertiseVO3.getCreatedTimestamp() + ",");
//			System.out.println("---------------------");
//		}
//	
	
	
	
	
	
}}
