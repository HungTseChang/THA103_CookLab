package com.cooklab.support_form.service;

import java.util.List;

import com.cooklab.support_form.model.SupportFormDAO;
import com.cooklab.support_form.model.SupportFormJDBCDAOIm;
import com.cooklab.support_form.model.SupportFormVO;

public class SupportFormJDBCService implements SupportFormServie {

	private SupportFormDAO dao;

	public SupportFormJDBCService() {
		dao = new SupportFormJDBCDAOIm();
	}

	public SupportFormVO addSupportForm(String realName, Integer supportFormCategoryId, String replyEmail,
			String formTitle, String formContext,Byte formSource,Byte formStatus,String formSubmitter) {

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
			String replyEmail, String formTitle, String formContext, Byte formStatus,Byte formSource,String formSubmitter,Integer formResponder) {

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

	public SupportFormVO getOneSupportForm(Integer formNo) {
		return dao.findByPrimaryKey(formNo);
	}

	public List<SupportFormVO> getAll() {
		return dao.getAll();
	}
}
