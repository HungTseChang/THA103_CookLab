package com.cooklab.notify_center.model;

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

import com.cooklab.members.model.MembersVO;

@Entity
@Table(name = "notify_center")

public class NotifyCenterVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notify_no")
	private Integer notifyNo;

	@Column(name = "notify_type")
	private Byte notifyType;

	@Column(name = "notify_read", insertable = false)
	private Byte notifyRead;

	@Column(name = "notify_content")
	private String notifyContent;
	
	@Column(name = "notifytime")
	private Timestamp notifyTime;

	@Column(name = "created_timestamp", insertable = false, updatable = false)
	private Timestamp createdTimestamp;

	@ManyToOne
	@JoinColumn(name = "member_id", referencedColumnName = "member_id")
	private MembersVO members;

	@Transient
	private Integer memberId;

	public NotifyCenterVO() {
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

	public Byte getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(Byte notifyType) {
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

	public MembersVO getMembers() {
		return members;
	}

	public void setMembers(MembersVO members) {
		this.members = members;
	}

	public Timestamp getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(Timestamp notifyTime) {
		this.notifyTime = notifyTime;
	}

	@Override
	public String toString() {
		return "NotifyCenterVO [notifyNo=" + notifyNo + ", notifyType=" + notifyType + ", notifyRead=" + notifyRead
				+ ", notifyContent=" + notifyContent + ", notifyTime=" + notifyTime + ", createdTimestamp="
				+ createdTimestamp +  ", memberId=" + memberId + "]";
	}


	public String getNCTypeName() {
		NCType[] type = NCType.values();
	    for (NCType atype: type) {
	        if (atype.getValue() == this.notifyType) {
	            return atype.getDesc();
	        }
	    }
	    return "無此類別";
	}
	
	public String getNCReadName() {
		NCRead[] read = NCRead.values();
	    for (NCRead aread: read) {
	        if (aread.getValue() == this.notifyRead) {
	            return aread.getDesc();
	        }
	    }
	    return "未知的讀取狀態";
	}

}
