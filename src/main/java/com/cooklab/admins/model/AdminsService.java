package com.cooklab.admins.model;

import java.sql.Timestamp;
import java.util.List;

public class AdminsService {

//	private AdminsHBDAO dao;
	private AdminsJDBCDAOIm dao;
	public AdminsService() {
//		dao = new AdminsHBDAO();
		dao = new AdminsJDBCDAOIm();	
		}

	public AdminsVO add(String adminNickname, Integer permissionNo, String adminAccount,
			String adminPassword) {

		AdminsVO AdminsVO = new AdminsVO();

		
		AdminsVO.setAdminNickname(adminNickname);
		AdminsVO.setPermissionNo(permissionNo);
		AdminsVO.setAdminAccount(adminAccount);
		AdminsVO.setAdminPassword(adminPassword);
   	
		dao.insert(AdminsVO);

		

		return AdminsVO;
	}

	public AdminsVO update(String adminNickname, Integer permissionNo, String adminAccount,
			String adminPassword, Integer adminNo) {

		AdminsVO AdminsVO = new AdminsVO();
	
		AdminsVO.setAdminNickname(adminNickname);
		AdminsVO.setPermissionNo(permissionNo);
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
