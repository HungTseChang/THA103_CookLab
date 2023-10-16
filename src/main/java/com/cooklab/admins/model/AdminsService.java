package com.cooklab.admins.model;

import java.sql.Timestamp;
import java.util.List;
import com.cooklab.permission.model.*;
import com.cooklab.util.HibernateUtil;

public class AdminsService {

	private AdminsHBDAO dao;
//	private AdminsJDBCDAOIm dao;
	public AdminsService() {
		dao = new AdminsHBDAO(HibernateUtil.getSessionFactory());
//		dao = new AdminsJDBCDAOIm();	
		}

	public AdminsVO add(String adminNickname, Integer permissionNo, String adminAccount,
			String adminPassword, Timestamp createTimestamp) {

		AdminsVO AdminsVO = new AdminsVO();
		PermissionService PermissionService = new PermissionService();
		PermissionVO a = PermissionService.getOne(permissionNo);

		
		AdminsVO.setAdminNickname(adminNickname);
		AdminsVO.setPermissionVO(a);
		AdminsVO.setAdminAccount(adminAccount);
		AdminsVO.setAdminPassword(adminPassword);
		AdminsVO.setCreatedTimestamp(createTimestamp);
		dao.insert(AdminsVO);

		

		return AdminsVO;
	}

	public AdminsVO update(String adminNickname, Integer permissionNo, String adminAccount,
			String adminPassword, Integer adminNo) {

		AdminsVO AdminsVO = new AdminsVO();
		PermissionService PermissionService = new PermissionService();
		PermissionVO a = PermissionService.getOne(permissionNo);
		AdminsVO.setAdminNickname(adminNickname);
		AdminsVO.setPermissionVO(a);
		AdminsVO.setAdminAccount(adminAccount);
		AdminsVO.setAdminPassword(adminPassword);
		AdminsVO.setAdminNo(adminNo);
		
		dao.update(AdminsVO);

		return AdminsVO;
	}

	public void delete(Integer adminNo) {
		dao.delete(adminNo);
	}

	public AdminsVO getOne(Integer adminNo) {
		return dao.findByPrimaryKey(adminNo);
	}

	public List<AdminsVO> getAll() {
		return dao.getAll();
	}

//	public  static void main(String[] args) {
////		AdminsVO AdminsVO = new AdminsVO();
////		AdminsVO.setAdminNickname(String.valueOf("王曉明"));
////		AdminsVO.setPermissionNo(Integer.valueOf(2));
////		AdminsVO.setAdminAccount(String.valueOf("ABCD"));
////		AdminsVO.setAdminPassword(String.valueOf("DDDD"));
//		AdminsService AdminsService = new AdminsService();
//		AdminsService.update("11",1,"22","3",1);
//		
//	}

}
