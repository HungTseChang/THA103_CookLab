package com.cooklab.admins.model;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.cooklab.article.model.ArticleHBDAO;
import com.cooklab.article.model.ArticleVO;
import com.cooklab.util.HibernateUtil;

public class AdminsHBDAO implements AdminsDAO {
	@Override
	public void insert(AdminsVO AdminsVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

           	session.save(AdminsVO);
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
	public void update(AdminsVO AdminsVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			AdminsVO AdminsVO1  = session.get(AdminsVO.class,AdminsVO.getAdminNo());
       	if(AdminsVO != null) {
       		AdminsVO1.setAdminNickname(AdminsVO.getAdminNickname());
       		AdminsVO1.setPermissionNo(AdminsVO.getPermissionNo());
       		AdminsVO1.setAdminAccount(AdminsVO.getAdminAccount());
       		AdminsVO1.setAdminPassword(AdminsVO.getAdminPassword());
       		
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
	public void delete(Integer adminNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			AdminsVO AdminsVO = session.get(AdminsVO.class, adminNo);
      	if(AdminsVO != null) {
       		session.delete(AdminsVO);
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
	public AdminsVO findByPrimaryKey(Integer adminNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		AdminsVO AdminsVO =null;
		try {
			session.beginTransaction();
			AdminsVO =session.get(AdminsVO.class, adminNo);
			System.out.println(
					"AdminNo :"+AdminsVO.getAdminNo()+"\n  "
				+"AdminNickname :"+AdminsVO.getAdminNickname()+"\n  "
				+ "PermissionNo :"+AdminsVO.getPermissionNo()+"\n"
				+"AdminAccount :"+ AdminsVO.getAdminAccount()+"\n"
				+"AdminPassword :"+AdminsVO.getAdminPassword()+"\n"
				+"tCreatedTimestamp :"+AdminsVO.getCreatedTimestamp()
				+"\n"+"============================================="
						); 
    
		session.getTransaction().commit();
		session.close();
	} catch (Exception e) {
		e.printStackTrace();
		session.getTransaction().rollback();
	} finally {
		HibernateUtil.shutdown();
	}
		return AdminsVO;
	}

//===========================findByPrimaryKey結束==============================================
	@Override
	public List<AdminsVO> getAll() {
		List<AdminsVO> list1 = new ArrayList<AdminsVO>();

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			list1 = session.createQuery("from AdminsVO",AdminsVO.class).list();
			for(int i=0; i<list1.size();i++) {
				System.out.println(
					"AdminNo :"+list1.get(i).getAdminNo()+"\n  "
				+ "AdminNickname :"+list1.get(i).getAdminNickname()+"\n"
				+"PermissionNo :"+ list1.get(i).getPermissionNo()+"\n"
				+"AdminAccount :"+list1.get(i).getAdminAccount()+"\n"
				+"AdminPassword :"+list1.get(i).getAdminPassword()+"\n"
				+"tCreatedTimestamp :"+list1.get(i).getCreatedTimestamp()
				+"\n"+"============================================="
						); 
			}
			
			
			
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

		AdminsHBDAO dao = new AdminsHBDAO();
		// 新增
//		AdminsVO AdVO1 = new AdminsVO();
//		AdVO1.setAdminNickname(String.valueOf("王曉明"));
//		AdVO1.setPermissionNo(Integer.valueOf(2));
//		AdVO1.setAdminAccount(String.valueOf("ABCD"));
//		AdVO1.setAdminPassword(String.valueOf("DDDD"));
//		dao.insert(AdVO1);

//		// 修改
//	AdminsVO AdminsVO2 = new AdminsVO();
//	AdminsVO2.setArticleReportNo(Integer.valueOf(6));
//	AdminsVO2.setArticleNo(Integer.valueOf(1));
//	AdminsVO2.setReporterId(Integer.valueOf(1));
//	AdminsVO2.setReportingReason("排版真的好累ZSSSZ");
//	AdminsVO2.setReportingStatus(Byte.valueOf((byte) 0));
//	AdminsVO2.setReportingAnswer("依然不准休息");
//	dao.update(AdminsVO2);

////		// 刪除
//		dao.delete(2);
////
////		// 查詢
		AdminsVO AdminsVO3 = dao.findByPrimaryKey(1);
		System.out.print(AdminsVO3.getAdminNo() + ",");
		System.out.print(AdminsVO3.getAdminNickname() + ",");
		System.out.print(AdminsVO3.getPermissionNo() + ",");
		System.out.print(AdminsVO3.getAdminAccount() + ",");
		System.out.print(AdminsVO3.getAdminPassword() + ",");
		System.out.print(AdminsVO3.getCreatedTimestamp() + ",");
		System.out.println("---------------------");
//
////		// 查詢
//	List<AdminsVO> list = dao.getAll();
//	for (AdminsVO aAdminsVO : list) {
//		System.out.print(aAdminsVO.getArticleReportNo() + ",");
//		System.out.print(aAdminsVO.getArticleNo() + ",");
//		System.out.print(aAdminsVO.getReporterId() + ",");
//		System.out.print(aAdminsVO.getReportingReason() + ",");
//		System.out.print(aAdminsVO.getReportingStatus() + ",");
//		System.out.print(aAdminsVO.getReportingAnswer() + ",");
//		System.out.println(aAdminsVO.getCreatedTimestamp() + ",");
//		System.out.println("---------------------");
//	}
	
//		AdminsVO AdminsVO7 = dao.findByPrimaryKey(6);
//		AdminsVO7.setArticleNo(5);
//		dao.update(AdminsVO7);
//		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//		System.out.println(AdminsVO7.getArticleNo());
	
	
	
}}
