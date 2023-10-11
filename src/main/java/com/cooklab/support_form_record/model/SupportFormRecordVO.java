package com.cooklab.support_form_record.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "support_form_record")
public class SupportFormRecordVO implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "record_no", updatable = false, insertable = false)
	private Integer recordNo;
	
	@Column(name = "form_no")
	private Integer formNo;
	
	@Column(name = "record_context",columnDefinition = "longtext")
	private String recordContext;
	
	@Column(name = "admin_no")
	private Integer adminNo;
	
	@Column(name = "created_timestamp", updatable = false, insertable = false)
	private Timestamp createdTimestamp;

	public SupportFormRecordVO() {
	}

	public Integer getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}

	public Integer getFormNo() {
		return formNo;
	}

	public void setFormNo(Integer formNo) {
		this.formNo = formNo;
	}

	public String getRecordContext() {
		return recordContext;
	}

	public void setRecordContext(String recordContext) {
		this.recordContext = recordContext;
	}

	public Integer getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "SupportFormRecord [recordNo=" + recordNo + ", formNo=" + formNo + ", recordContext=" + recordContext
				+ ", adminNo=" + adminNo + ", createdTimestamp=" + createdTimestamp + "]";
	}

}
