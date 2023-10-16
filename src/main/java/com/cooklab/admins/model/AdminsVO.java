package com.cooklab.admins.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cooklab.permission.model.PermissionVO;
import com.google.gson.annotations.Expose;
@Entity
@Table(name="admins") 
public class AdminsVO implements java.io.Serializable {
	@Expose
	@Id 
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name = "admin_no" , insertable = false, updatable = false)  
	private Integer adminNo;
	@Expose
	@Column(name = "admin_nickname")  
	private String adminNickname;
	@Transient
	private Integer permissionNo;
	@Expose
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "permission_no", referencedColumnName = "permission_no")
	private PermissionVO permissionVO;
	@Expose
	@Column(name = "admin_account")  
	private String adminAccount;
	@Expose
	@Column(name = "admin_password")  
	private String adminPassword;
	@Expose
	@Column(name = "created_timestamp" , insertable = false, updatable = false)  
	private Timestamp createdTimestamp;

	


	public PermissionVO getPermissionVO() {
		return permissionVO;
	}
	public void setPermissionVO(PermissionVO permissionVO) {
		this.permissionVO = permissionVO;
	}
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
