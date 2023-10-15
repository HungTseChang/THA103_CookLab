package com.cooklab.support_form.model;

import java.util.List;

public class SupportFormService {

	private SupportFormDAO dao;

	public SupportFormService() {
		dao = new SupportFormJDBCDAOIm();
	}

	public SupportFormVO addSupportForm(String realName, Integer supportFormCategoryId, String replyEmail,
			String formTitle, String formContext,String formSource,String formSubmitter) {

		SupportFormVO supportFormVO = new SupportFormVO();

		supportFormVO.setRealName(realName);
		supportFormVO.setSupportFormCategoryId(supportFormCategoryId);
		supportFormVO.setReplyEmail(replyEmail);
		supportFormVO.setFormTitle(formTitle);
		supportFormVO.setFormContext(formContext);
		supportFormVO.setFormSource(formSource);
		supportFormVO.setFormSubmitter(formSubmitter);
		dao.insert(supportFormVO);

		return supportFormVO;
	}

	public SupportFormVO updateSupportForm(Integer formNo, String realName, Integer supportFormCategoryId,
			String replyEmail, String formTitle, String formContext, Byte formStatus,String formSource,String formSubmitter,Integer formResponder) {

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
}
