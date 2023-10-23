package com.cooklab.notify_center.model;

import java.sql.Timestamp;
import java.util.List;

import com.cooklab.admins.model.AdminsService;
import com.cooklab.admins.model.AdminsVO;
import com.cooklab.members.model.MembersService;
import com.cooklab.members.model.MembersVO;
import com.cooklab.support_form.model.SupportFormHService;
import com.cooklab.support_form.model.SupportFormVO;
import com.cooklab.util.HibernateUtil;

public class NotifyCenterHService implements NotifyCenterServie {
	// 介面多型
	private NotifyCenterHDAOIm dao;

	// 建構子呼叫工廠
	public NotifyCenterHService() {
		dao = new NotifyCenterHDAOIm(HibernateUtil.getSessionFactory());
	}

	public NotifyCenterVO addNotifyCenter(Byte notifyType, String notifyContent, Timestamp notifyTime,
			Integer memberId) {

		NotifyCenterVO notifyCenterVO = new NotifyCenterVO();

		MembersService mSvc = new MembersService();

		MembersVO member = mSvc.getOneMember(memberId);

		notifyCenterVO.setNotifyType(notifyType);
		notifyCenterVO.setNotifyContent(notifyContent);
		notifyCenterVO.setNotifyTime(notifyTime);
		notifyCenterVO.setMembers(member);

		dao.insert(notifyCenterVO);

		return notifyCenterVO;
	}

	public NotifyCenterVO updateNotifyCenter(Integer notifyNo, Byte notifyType, Byte notifyRead, String notifyContent,
			Timestamp notifyTime, Integer memberId) {

		NotifyCenterVO notifyCenterVO = new NotifyCenterVO();

		MembersService mSvc = new MembersService();

		MembersVO member = mSvc.getOneMember(memberId);

		notifyCenterVO.setNotifyType(notifyType);
		notifyCenterVO.setNotifyContent(notifyContent);
		notifyCenterVO.setNotifyTime(notifyTime);
		notifyCenterVO.setMembers(member);
		notifyCenterVO.setNotifyNo(notifyNo);
		

		dao.update(notifyCenterVO);

		return notifyCenterVO;
	}

	public void deleteNotifyCenter(Integer formNo) {
		dao.delete(formNo);
	}

	public NotifyCenterVO getOneNotifyCenter(Integer supportFormno) {
		return dao.findByPrimaryKey(supportFormno);
	}

	public List<NotifyCenterVO> getAll() {
		return dao.getAll();
	}
}
