package com.cooklab.support_form_record.model;

import java.util.List;

import com.cooklab.admins.model.AdminsService;
import com.cooklab.admins.model.AdminsVO;
import com.cooklab.support_form.model.SupportFormHService;
import com.cooklab.support_form.model.SupportFormVO;
import com.cooklab.util.HibernateUtil;

public class SupportFormRecordHService implements SupportFormRecordServie{
	// 介面多型
	private SupportFormRecordDAO dao;

	// 建構子呼叫工廠
	public SupportFormRecordHService() {
		dao = new SupportFormRecordHDAOIm(HibernateUtil.getSessionFactory());
	}

	public SupportFormRecordVO addSupportFormRecord(String recordContext,Integer adminNo,Integer formNo) {

		SupportFormRecordVO SupportFormRecordVO = new SupportFormRecordVO();
		
		SupportFormHService sfSvc = new SupportFormHService();
		AdminsService adSvc = new AdminsService();
		
		SupportFormVO supportForm = sfSvc.getOneSupportForm(formNo);
		AdminsVO admin = adSvc.getOne(adminNo);

		SupportFormRecordVO.setRecordContext(recordContext);
		SupportFormRecordVO.setAdmins(admin);
		SupportFormRecordVO.setSupportForms(supportForm);

		dao.insert(SupportFormRecordVO);

		return SupportFormRecordVO;
	}

	public SupportFormRecordVO updateSupportFormRecord(Integer recordNo, String recordContext,Integer adminNo,Integer formNo) {

		SupportFormRecordVO SupportFormRecordVO = new SupportFormRecordVO();
		
		SupportFormHService sfSvc = new SupportFormHService();
		AdminsService adSvc = new AdminsService();
		
		SupportFormVO supportForm = sfSvc.getOneSupportForm(formNo);
		AdminsVO admin = adSvc.getOne(adminNo);

		SupportFormRecordVO.setRecordContext(recordContext);
		SupportFormRecordVO.setAdmins(admin);
		SupportFormRecordVO.setSupportForms(supportForm);
		SupportFormRecordVO.setRecordNo(recordNo);;

		dao.update(SupportFormRecordVO);

		return SupportFormRecordVO;
	}

	public void deleteSupportFormRecord(Integer formNo) {
		dao.delete(formNo);
	}

	public SupportFormRecordVO getOneSupportFormRecord(Integer supportFormno) {
		return dao.findByPrimaryKey(supportFormno);
	}

	public List<SupportFormRecordVO> getAll() {
		return dao.getAll();
	}
}
