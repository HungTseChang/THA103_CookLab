package com.cooklab.advertise.model;

import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;

import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;
import com.cooklab.advertise.model.AdvertiseVO;


public class adtest {
	public static void main(String []args) throws Exception{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			AdvertiseVO aaa= new AdvertiseVO();
			aaa.setAdvertiseName("廣告");
			aaa.setAdvertiseShelfTime(java.sql.Date.valueOf("1999-11-09 12:12:00"));
			aaa.setAdvertiseOffsaleTime(java.sql.Date.valueOf("2000-11-09 12:12:00"));
			aaa.setAdvertiseImg(null);
			aaa.setAdvertiseUrl("http://.www.com.tw");
			
			session.saveOrUpdate(aaa);
			
			session.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtil.shutdown();
		}
		
		
//		
//		AdvertiseVO advertise1 = new AdvertiseVO();
//		advertise1.setAdvertiseName("offukingsell");
//		advertise1.setAdvertiseShelfTime(java.sql.Date.valueOf("1994,11,09"));
//		advertise1.setAdvertiseOffsaleTime(java.sql.Date.valueOf("2000,00,00"));
//		advertise1.setAdvertiseImg("http://wwwwwww.ccccc.tw");
//		advertise1.setAdvertiseUrl("http://wwwwwww.ccccc.tw");
//		
//		
//		
//		List<AdvertiseVO> list = dao.getAll();
//		for (AdvertiseVO advertise : list) {
//			System.out.print(advertise.getAdvertiseNo() + ",");
//			
//		}

	}}
