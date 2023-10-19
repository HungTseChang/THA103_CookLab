package com.cooklab.support_form.model;

import java.util.List;

import com.cooklab.util.HibernateUtil;

public class SupportFormHService implements SupportFormServie{
	// 介面多型
	private SupportFormDAO dao;

	// 建構子呼叫工廠
	public SupportFormHService() {
		dao = new SupportFormHDAOIm(HibernateUtil.getSessionFactory());
	}

	public SupportFormVO addSupportForm(String realName, Integer supportFormCategoryId, String replyEmail,
			String formTitle, String formContext,String formSource,Byte formStatus,String formSubmitter) {

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
