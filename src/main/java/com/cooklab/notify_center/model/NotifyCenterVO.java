package com.cooklab.notify_center.model;

import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "notify_center")

public class NotifyCenterVO  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name="notify_no")
	private Integer notifyNo;
	
	@Column (name="member_id")
	private Integer memberId;
	
	@Column (name="notify_type")
	private Integer notifyType;
	
	@Column (name="notify_read")
	private Byte notifyRead;
	
	@Column (name="notify_content")
	private String notifyContent;
	
	@Column (name="created_timestamp" ,insertable = false)
	private Timestamp createdTimestamp;
	
	
	public NotifyCenterVO() {}
	
	public NotifyCenterVO( Integer memberId, Integer notifyType, Byte notifyRead,
			String notifyContent) {
		super();
		
		this.memberId = memberId;
		this.notifyType = notifyType;
		this.notifyRead = notifyRead;
		this.notifyContent = notifyContent;

	}
	
	public Integer getNotifyNo() {
		return notifyNo;
	}
	public void setNotifyNo(Integer notifyNo) {
		this.notifyNo = notifyNo;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getNotifyType() {
		return notifyType;
	}
	public void setNotifyType(Integer notifyType) {
		this.notifyType = notifyType;
	}
	public Byte getNotifyRead() {
		return notifyRead;
	}
	public void setNotifyRead(Byte notifyRead) {
		this.notifyRead = notifyRead;
	}
	public String getNotifyContent() {
		return notifyContent;
	}
	public void setNotifyContent(String notifyContent) {
		this.notifyContent = notifyContent;
	}
	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	@Override
	public String toString() {
		return "NotifyCenterVO [notifyNo=" + notifyNo + ", memberId=" + memberId + ", notifyType=" + notifyType
				+ ", notifyRead=" + notifyRead + ", notifyContent=" + notifyContent + ", createdTimestamp="
				+ createdTimestamp + "]";
	}
	
	
	
	
	
	

}
