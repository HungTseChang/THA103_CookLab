package com.cooklab.permission.model;

import java.sql.Date;

public class PermissionVO implements java.io.Serializable {
	private Integer permissionNo;
	private String permissionTitle;
	private Integer superAdmin;
	private Integer cancelAllPermission;
	private Integer membershipManagement;
	private Integer advertisingManagement;
	private Integer reportingManagement;
	private Integer articleManagement;
	private Integer recipeManagement;
	private Date createdTimestamp;

	public Integer getPermissionNo() {
		return permissionNo;
	}

	public void setPermissionNo(Integer permissionNo) {
		this.permissionNo = permissionNo;
	}

	

	public Integer getSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(Integer superAdmin) {
		this.superAdmin = superAdmin;
	}

	public Integer getCancelAllPermission() {
		return cancelAllPermission;
	}

	public void setCancelAllPermission(Integer cancelAllPermission) {
		this.cancelAllPermission = cancelAllPermission;
	}

	public Integer getMembershipManagement() {
		return membershipManagement;
	}

	public void setMembershipManagement(Integer membershipManagement) {
		this.membershipManagement = membershipManagement;
	}

	public Integer getAdvertisingManagement() {
		return advertisingManagement;
	}

	public void setAdvertisingManagement(Integer advertisingManagement) {
		this.advertisingManagement = advertisingManagement;
	}

	public Integer getReportingManagement() {
		return reportingManagement;
	}

	public void setReportingManagement(Integer reportingManagement) {
		this.reportingManagement = reportingManagement;
	}

	public Integer getArticleManagement() {
		return articleManagement;
	}

	public void setArticleManagement(Integer articleManagement) {
		this.articleManagement = articleManagement;
	}

	public Integer getRecipeManagement() {
		return recipeManagement;
	}

	public void setRecipeManagement(Integer recipeManagement) {
		this.recipeManagement = recipeManagement;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getPermissionTitle() {
		return permissionTitle;
	}

	public void setPermissionTitle(String permissionTitle) {
		this.permissionTitle = permissionTitle;
	}

}