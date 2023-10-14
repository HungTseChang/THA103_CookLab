package com.cooklab.permission.model;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.util.HibernateUtil;
import com.cooklab.article.model.ArticleHBDAO;
import com.cooklab.article.model.ArticleVO;


public class PermissionHBDAO implements PermissionDAO {
private SessionFactory factory;
public PermissionHBDAO(SessionFactory factory) {
	this.factory = factory;
}

// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
// 以避免請求執行緒共用了同個 Session
private Session getSession() {
	return factory.getCurrentSession();
}

@Override
public void insert(PermissionVO PermissionVO) {
	// 回傳給 service 剛新增成功的自增主鍵值
	 getSession().save(PermissionVO);
}

@Override
public void update(PermissionVO PermissionVO) {
	try {
		getSession().update(PermissionVO);
	} catch (Exception e) {
	}
}

@Override
public void delete(Integer permissionNO) {
	PermissionVO PermissionVO = getSession().get(PermissionVO.class, permissionNO);
	if (PermissionVO != null) {
		getSession().delete(PermissionVO);
		// 回傳給 service，1代表刪除成功
	} else {
		// 回傳給 service，-1代表刪除失敗
	}
}

@Override
public PermissionVO findByPrimaryKey(Integer permissionNO) {
	return getSession().get(PermissionVO.class, permissionNO);
}

@Override
public List<PermissionVO> getAll() {
	System.out.println("ABCABCABCABCABCABCABCABCABC");
	return getSession().createQuery("from PermissionVO", PermissionVO.class).list();
}
	

	
//	=============================getAll結束==================================================
	
	
	public static void main(String[] args) {

//		AdminsHBDAO dao = new AdminsHBDAO();
//		// 新增
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
//		AdminsVO AdminsVO3 = dao.findByPrimaryKey(1);
//		System.out.print(AdminsVO3.getAdminNo() + ",");
//		System.out.print(AdminsVO3.getAdminNickname() + ",");
//		System.out.print(AdminsVO3.getPermissionNo() + ",");
//		System.out.print(AdminsVO3.getAdminAccount() + ",");
//		System.out.print(AdminsVO3.getAdminPassword() + ",");
//		System.out.print(AdminsVO3.getCreatedTimestamp() + ",");
//		System.out.println("---------------------");
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
