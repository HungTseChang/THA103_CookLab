package com.cooklab.admins.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="admins") 
public class AdminsVO implements java.io.Serializable {
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "admin_no" , insertable = false, updatable = false)  
	private Integer adminNo;
	@Column(name = "admin_nickname")  
	private String adminNickname;
	@Column(name = "permission_no")  
	private Integer permissionNo;
	@Column(name = "admin_account")  
	private String adminAccount;
	@Column(name = "admin_password")  
	private String adminPassword;
	@Column(name = "created_timestamp" , insertable = false, updatable = false)  
	private Timestamp createdTimestamp;
	
	
	public Integer getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}
	public String getAdminNickname() {
		return adminNickname;
	}
	public void setAdminNickname(String adminNickname) {
		this.adminNickname = adminNickname;
	}

	public Integer getPermissionNo() {
		return permissionNo;
	}
	public void setPermissionNo(Integer permissionNo) {
		this.permissionNo = permissionNo;
	}
	public String getAdminAccount() {
		return adminAccount;
	}
	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public Date getAdminRegisterTime() {
		// TODO Auto-generated method stub
		return null;
	}
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
}
