package com.cooklab.admins.model;

import java.sql.Timestamp;
import java.util.List;

public class AdminsService {

	private AdminsHBDAO dao;
	
	public AdminsService() {
		dao = new AdminsHBDAO();
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

	

}
