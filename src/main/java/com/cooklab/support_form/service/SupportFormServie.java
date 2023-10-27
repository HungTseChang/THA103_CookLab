package com.cooklab.support_form.service;

import java.util.List;

import com.cooklab.support_form.model.SupportFormVO;

public interface SupportFormServie {
	public SupportFormVO addSupportForm(String realName, Integer supportFormCategoryId, String replyEmail,
			String formTitle, String formContext, Byte formSource,Byte formStatus, String formSubmitter);

	public SupportFormVO updateSupportForm(Integer formNo, String realName, Integer supportFormCategoryId,
			String replyEmail, String formTitle, String formContext, Byte formStatus, Byte formSource,
			String formSubmitter, Integer formResponder);

	public void deleteSupportForm(Integer formNo);

	public SupportFormVO getOneSupportForm(Integer formNo);

	public List<SupportFormVO> getAll();
}
