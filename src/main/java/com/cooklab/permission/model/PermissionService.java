package com.cooklab.permission.model;

import java.sql.Timestamp;
import java.util.List;

import com.cooklab.util.HibernateUtil;

public class PermissionService {

	private PermissionDAO dao;
	
	public PermissionService() {
		dao = new PermissionHBDAO(HibernateUtil.getSessionFactory());
	}

	
	public PermissionVO add(String permissionTitle, Byte adminManagement, Byte serviceManagement,
			Byte membershipManagement, Byte advertisingManagement, Byte reportingManagement, Byte articleManagement,
			Byte recipeManagement,Byte mallManagement, Timestamp createTimestamp) {

		PermissionVO PermissionVO = new PermissionVO();

		
		PermissionVO.setPermissionTitle(permissionTitle);
		PermissionVO.setAdminManagement(adminManagement);
		PermissionVO.setServiceManagement(serviceManagement);
		PermissionVO.setMembershipManagement(membershipManagement);
		PermissionVO.setAdvertisingManagement(advertisingManagement);
		PermissionVO.setReportingManagement(reportingManagement);
		PermissionVO.setArticleManagement(articleManagement);
		PermissionVO.setRecipeManagement(recipeManagement);		
		PermissionVO.setMallManagement(mallManagement);		
		PermissionVO.setCreatedTimestamp(createTimestamp);
		dao.insert(PermissionVO);

		

		return PermissionVO;
	}

	public PermissionVO update(String permissionTitle, Byte adminManagement, Byte serviceManagement,
			Byte membershipManagement, Byte advertisingManagement, Byte reportingManagement, Byte articleManagement,
			Byte recipeManagement,Byte mallManagement, Integer permissionnNO)  {

		PermissionVO PermissionVO = new PermissionVO();
		PermissionVO.setPermissionTitle(permissionTitle);
		PermissionVO.setAdminManagement(adminManagement);
		PermissionVO.setServiceManagement(serviceManagement);
		PermissionVO.setMembershipManagement(membershipManagement);
		PermissionVO.setAdvertisingManagement(advertisingManagement);
		PermissionVO.setReportingManagement(reportingManagement);
		PermissionVO.setArticleManagement(articleManagement);
		PermissionVO.setRecipeManagement(recipeManagement);
		PermissionVO.setMallManagement(mallManagement);		

	

		PermissionVO.setPermissionNo(permissionnNO);
		
		dao.update(PermissionVO);

		return PermissionVO;
	}

	public void delete(Integer permissopnNO) {
		dao.delete(permissopnNO);
	}

	public PermissionVO getOne(Integer permissopnNO) {
		return dao.findByPrimaryKey(permissopnNO);
	}

	public List<PermissionVO> getAll() {
		return dao.getAll();
	}

	

}
