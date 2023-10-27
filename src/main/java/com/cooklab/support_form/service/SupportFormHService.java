package com.cooklab.support_form.service;

import java.util.List;

import com.cooklab.admins.model.AdminsHBDAO;
import com.cooklab.admins.model.AdminsVO;
import com.cooklab.support_form.model.SupportFormHDAOIm;
import com.cooklab.support_form.model.SupportFormVO;
import com.cooklab.support_form_record.model.SupportFormRecordVO;
import com.cooklab.util.HibernateUtil;

public class SupportFormHService implements SupportFormServie {

	private SupportFormHDAOIm dao;

	// 建構子呼叫工廠
	public SupportFormHService() {
		dao = new SupportFormHDAOIm(HibernateUtil.getSessionFactory());
	}

	public SupportFormVO addSupportForm(String realName, Integer supportFormCategoryId, String replyEmail,
			String formTitle, String formContext, Byte formSource, Byte formStatus, String formSubmitter) {

		SupportFormVO supportFormVO = new SupportFormVO();

		supportFormVO.setRealName(realName);
		supportFormVO.setSupportFormCategoryId(supportFormCategoryId);
		supportFormVO.setReplyEmail(replyEmail);
		supportFormVO.setFormTitle(formTitle);
		supportFormVO.setFormContext(formContext);
		supportFormVO.setFormSource(formSource);
		supportFormVO.setFormStatus(formStatus);
		supportFormVO.setFormSubmitter(formSubmitter);
		dao.insert(supportFormVO);

		return supportFormVO;
	}

	public SupportFormVO updateSupportForm(Integer formNo, String realName, Integer supportFormCategoryId,
			String replyEmail, String formTitle, String formContext, Byte formStatus, Byte formSource,
			String formSubmitter, Integer formResponder) {

		SupportFormVO supportFormVO = new SupportFormVO();

		supportFormVO.setFormNo(formNo);
		supportFormVO.setRealName(realName);
		supportFormVO.setSupportFormCategoryId(supportFormCategoryId);
		supportFormVO.setReplyEmail(replyEmail);
		supportFormVO.setFormTitle(formTitle);
		supportFormVO.setFormContext(formContext);
		supportFormVO.setFormStatus(formStatus);
		supportFormVO.setFormSource(formSource);
		supportFormVO.setFormSubmitter(formSubmitter);
		supportFormVO.setFormResponder(formResponder);
		dao.update(supportFormVO);

		return supportFormVO;
	}

	public void deleteSupportForm(Integer formNo) {
		dao.delete(formNo);
	}

	public SupportFormVO getOneSupportForm(Integer supportFormno) {
		return dao.findByPrimaryKey(supportFormno);
	}

	public List<SupportFormVO> getAll() {
		return dao.getAll();
	}

	public List<SupportFormRecordVO> getRecord(Integer formNo) {
		return dao.getRecordByFormNo(formNo);
	}

	public SupportFormVO changeInfo(Integer formNo, Integer adminNo, Byte formStatus) {
		SupportFormVO supportForm = dao.findByPrimaryKey(formNo);

		AdminsHBDAO admindao = new AdminsHBDAO(HibernateUtil.getSessionFactory());
		AdminsVO admin = admindao.findByPrimaryKey(adminNo);
		supportForm.setFormNo(formNo);
		supportForm.setFormStatus(formStatus);
		supportForm.setAdmins(admin);
		dao.update(supportForm);
		return supportForm;
	}

	public SupportFormVO dashboardupdate(Integer formNo, String realName, Integer supportFormCategoryId,
			String replyEmail) {
		SupportFormVO supportForm = dao.findByPrimaryKey(formNo);

		supportForm.setFormNo(formNo);
		supportForm.setRealName(realName);
		supportForm.setSupportFormCategoryId(supportFormCategoryId);
		supportForm.setReplyEmail(replyEmail);
		dao.update(supportForm);
		return supportForm;
	}

}
