package com.cooklab.support_form_record.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cooklab.admins.model.AdminsVO;
import com.cooklab.support_form.model.SupportFormVO;

@Entity
@Table(name = "support_form_record")
public class SupportFormRecordVO implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "record_no", updatable = false, insertable = false)
	private Integer recordNo;

	@Column(name = "record_context", columnDefinition = "longtext")
	private String recordContext;

	@Column(name = "created_timestamp", updatable = false, insertable = false)
	private Timestamp createdTimestamp;

	@ManyToOne
	@JoinColumn(name = "admin_no", referencedColumnName = "admin_no")
	private AdminsVO admins;

	@ManyToOne
	@JoinColumn(name = "form_no", referencedColumnName = "form_no")
	private SupportFormVO supportForms;

	@Transient
	private Integer formNo;

	@Transient
	private Integer adminNo;

	public SupportFormRecordVO() {
	}

	public Integer getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(Integer recordNo) {
		this.recordNo = recordNo;
	}

	public String getRecordContext() {
		return recordContext;
	}

	public void setRecordContext(String recordContext) {
		this.recordContext = recordContext;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public AdminsVO getAdmins() {
		return admins;
	}

	public void setAdmins(AdminsVO admins) {
		this.admins = admins;
	}

	public SupportFormVO getSupportForms() {
		return supportForms;
	}

	public void setSupportForms(SupportFormVO supportForms) {
		this.supportForms = supportForms;
	}

	public Integer getFormNo() {
		return formNo;
	}

	public void setFormNo(Integer formNo) {
		this.formNo = formNo;
	}

	public Integer getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}

	@Override
	public String toString() {
		return "SupportFormRecord [recordNo=" + recordNo + ", recordContext=" + recordContext + ", createdTimestamp="
				+ createdTimestamp + "]";
	}

}
