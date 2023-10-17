package com.cooklab.support_form.model;

import java.util.List;

public interface SupportFormServie {
	public SupportFormVO addSupportForm(String realName, Integer supportFormCategoryId, String replyEmail,
			String formTitle, String formContext, String formSource, String formSubmitter);

	public SupportFormVO updateSupportForm(Integer formNo, String realName, Integer supportFormCategoryId,
			String replyEmail, String formTitle, String formContext, Byte formStatus, String formSource,
			String formSubmitter, Integer formResponder);

	public void deleteSupportForm(Integer formNo);

	public SupportFormVO getOneSupportForm(Integer formNo);

	public List<SupportFormVO> getAll();
}
