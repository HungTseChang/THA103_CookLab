package com.cooklab.permission.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
@Entity
@Table(name="permission") 
public class PermissionVO implements java.io.Serializable {
	@Expose
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "permission_no" , insertable = false, updatable = false)  
	private Integer permissionNo;
	@Expose
	@Column(name = "permission_title")  
	private String permissionTitle;
	@Expose
	@Column(name = "admin_management")  
	private Byte adminManagement;
	@Expose
	@Column(name = "service_management")  
	private Byte serviceManagement;
	@Expose
	@Column(name = "membership_management")  
	private Byte membershipManagement;
	@Expose
	@Column(name = "advertising_management")  
	private Byte advertisingManagement;
	@Expose
	@Column(name = "reporting_management")  
	private Byte reportingManagement;
	@Column(name = "article_management")  
	@Expose
	private Byte articleManagement;
	@Column(name = "recipe_management")  
	@Expose
	private Byte recipeManagement;
	@Column(name = "mall_management")  
	@Expose
	private Byte mallManagement;
	@Expose
	@Column(name = "created_timestamp" , insertable = false, updatable = false)  
	private Timestamp createdTimestamp;
	
	public Integer getPermissionNo() {
		return permissionNo;
	}
	public void setPermissionNo(Integer permissionNo) {
		this.permissionNo = permissionNo;
	}
	public String getPermissionTitle() {
		return permissionTitle;
	}
	public void setPermissionTitle(String permissionTitle) {
		this.permissionTitle = permissionTitle;
	}
	public Byte getAdminManagement() {
		return adminManagement;
	}
	public void setAdminManagement(Byte adminManagement) {
		this.adminManagement = adminManagement;
	}
	public Byte getServiceManagement() {
		return serviceManagement;
	}
	public void setServiceManagement(Byte serviceManagement) {
		this.serviceManagement = serviceManagement;
	}
	public Byte getMembershipManagement() {
		return membershipManagement;
	}
	public void setMembershipManagement(Byte membershipManagement) {
		this.membershipManagement = membershipManagement;
	}
	public Byte getAdvertisingManagement() {
		return advertisingManagement;
	}
	public void setAdvertisingManagement(Byte advertisingManagement) {
		this.advertisingManagement = advertisingManagement;
	}
	public Byte getReportingManagement() {
		return reportingManagement;
	}
	public void setReportingManagement(Byte reportingManagement) {
		this.reportingManagement = reportingManagement;
	}
	public Byte getArticleManagement() {
		return articleManagement;
	}
	public void setArticleManagement(Byte articleManagement) {
		this.articleManagement = articleManagement;
	}
	public Byte getRecipeManagement() {
		return recipeManagement;
	}
	public void setRecipeManagement(Byte recipeManagement) {
		this.recipeManagement = recipeManagement;
	}
	public Byte getMallManagement() {
		return mallManagement;
	}
	public void setMallManagement(Byte mallManagement) {
		this.mallManagement = mallManagement;
	}
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	
	
	
	



}