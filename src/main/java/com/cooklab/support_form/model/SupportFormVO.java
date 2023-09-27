package com.cooklab.support_form.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SupportFormVO implements Serializable{
	
	private Integer formNo;
	private String realName ;
	private Integer supportFormCategoryId;
	private String replyEmail;
	private String formTitle;
	private String formContext;
	private Timestamp createdTimestamp;
	
	
	
	public Integer getFormNo() {
		return formNo;
	}
	public void setFormNo(Integer formNo) {
		this.formNo = formNo;
	}
	
	
	
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Integer getSupportFormCategoryId() {
		return supportFormCategoryId;
	}
	public void setSupportFormCategoryId(Integer supportFormCategoryId) {
		this.supportFormCategoryId = supportFormCategoryId;
	}
	public String getReplyEmail() {
		return replyEmail;
	}
	public void setReplyEmail(String replyEmail) {
		this.replyEmail = replyEmail;
	}
	public String getFormTitle() {
		return formTitle;
	}
	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}
	public String getFormContext() {
		return formContext;
	}
	public void setFormContext(String formContext) {
		this.formContext = formContext;
	}
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	@Override
	public String toString() {
		return "SupportFormVO [realName=" + realName + ", supportFormCategoryId=" + supportFormCategoryId
				+ ", replyEmail=" + replyEmail + ", formTitle=" + formTitle + ", formContext=" + formContext
				+ ", createdTimestamp=" + createdTimestamp + "]";
	}
	
	
	
	
}
