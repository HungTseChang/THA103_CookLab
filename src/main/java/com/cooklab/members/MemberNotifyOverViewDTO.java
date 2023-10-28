package com.cooklab.members;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.cooklab.members.model.MembersVO;
import com.cooklab.notify_center.model.NotifyCenterVO;
import com.cooklab.notify_center.model.*;
public class MemberNotifyOverViewDTO {
	private Integer merberId;
	private String notifyType;
	private byte notifyRead;
	private Timestamp notifyTime;
	private String notifyContent;
	private Integer notifyNo;
	private Timestamp createdTime;
	
	public MemberNotifyOverViewDTO() {}
	
	public MemberNotifyOverViewDTO(NotifyCenterVO NotifyVO) {
		this.merberId = NotifyVO.getMembers().getMemberId();
		this.notifyType = NotifyVO.getNCTypeName();
		this.notifyRead = NotifyVO.getNotifyRead();
		this.notifyTime = NotifyVO.getNotifyTime();
		this.notifyContent = NotifyVO.getNotifyContent();
		this.notifyNo = NotifyVO.getNotifyNo();
		this.createdTime = NotifyVO.getCreatedTimestamp();

	}
//	public class NotifyCenterDTO{
//		
//		
//		public NotifyCenterDTO(NotifyCenterVO nVO) {
//			this.notifyType = nVO.getNotifyType();
//			this.notifyRead = nVO.getNotifyRead();
//			this.notifyTime = nVO.getNotifyTime();
//			this.notifyContent = nVO.getNotifyContent();
//		}
//		
//	}
}
