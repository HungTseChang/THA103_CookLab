package com.cooklab.notify_center.service;

import java.sql.Timestamp;
import java.util.List;

import com.cooklab.admins.model.AdminsVO;
import com.cooklab.notify_center.model.NotifyCenterVO;
import com.cooklab.support_form.model.SupportFormVO;

public interface NotifyCenterServie {
	public NotifyCenterVO addNotifyCenter(Byte notifyType, String notifyContent, Timestamp notifyTime, Integer memberId);

	public NotifyCenterVO updateNotifyCenter(Integer notifyNo, Byte notifyType, Byte notifyRead, String notifyContent, Timestamp notifyTime, Integer memberId);

	public void deleteNotifyCenter(Integer notifyNo);

	public NotifyCenterVO getOneNotifyCenter(Integer notifyNo);

	public List<NotifyCenterVO> getAll();
}
