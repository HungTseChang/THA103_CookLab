package com.cooklab.support_form.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cooklab.admins.model.AdminsVO;
import com.cooklab.support_form_record.model.SupportFormRecordVO;

@Entity
@Table(name = "support_form")
public class SupportFormVO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "form_no", updatable = false, insertable = false)
	private Integer formNo;

	@Column(name = "real_name")
	private String realName;

	@Column(name = "support_form_category_id")
	private Integer supportFormCategoryId;

	@Column(name = "reply_email")
	private String replyEmail;

	@Column(name = "form_title")
	private String formTitle;

	@Column(name = "form_context", columnDefinition = "longtext")
	private String formContext;

	@Column(name = "form_status")
	private Byte formStatus;

	@Column(name = "form_source")
	private String formSource;

	@Column(name = "form_submitter")
	private String formSubmitter;

	@Column(name = "created_timestamp", updatable = false, insertable = false)
	private Timestamp createdTimestamp;

	@OneToMany(mappedBy = "supportForms", cascade = CascadeType.ALL)
	@OrderBy("form_no")
	private Set<SupportFormRecordVO> supportFormRecords;

	@ManyToOne
	@JoinColumn(name = "form_responder", referencedColumnName = "admin_no")
	private AdminsVO admins;

	@Transient
	private Integer formResponder;

	public SupportFormVO() {
		super();
	}

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

	public Byte getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(Byte formStatus) {
		this.formStatus = formStatus;
	}

	public String getFormSource() {
		return formSource;
	}

	public void setFormSource(String formSource) {
		this.formSource = formSource;
	}

	public String getFormSubmitter() {
		return formSubmitter;
	}

	public void setFormSubmitter(String formSubmitter) {
		this.formSubmitter = formSubmitter;
	}

	public AdminsVO getAdmins() {
		return admins;
	}

	public void setAdmins(AdminsVO admins) {
		this.admins = admins;
	}

	public Integer getFormResponder() {
		return formResponder;
	}

	public void setFormResponder(Integer formResponder) {
		this.formResponder = formResponder;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Set<SupportFormRecordVO> getSupportFormRecords() {
		return supportFormRecords;
	}

	public void setSupportFormRecords(Set<SupportFormRecordVO> supportFormRecords) {
		this.supportFormRecords = supportFormRecords;
	}

	@Override
	public String toString() {
		return "SupportFormVO [formNo=" + formNo + ", realName=" + realName + ", supportFormCategoryId="
				+ supportFormCategoryId + ", replyEmail=" + replyEmail + ", formTitle=" + formTitle + ", formContext="
				+ formContext + ", formStatus=" + formStatus + ", formSource=" + formSource + ", formSubmitter="
				+ formSubmitter + ", createdTimestamp=" + createdTimestamp + "]";
	}

}
