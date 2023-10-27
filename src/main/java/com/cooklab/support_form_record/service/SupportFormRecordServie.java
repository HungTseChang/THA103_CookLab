package com.cooklab.support_form_record.service;

import java.sql.Timestamp;
import java.util.List;

import com.cooklab.admins.model.AdminsVO;
import com.cooklab.support_form.model.SupportFormVO;
import com.cooklab.support_form_record.model.SupportFormRecordVO;

public interface SupportFormRecordServie {
	public SupportFormRecordVO addSupportFormRecord(String recordContext,Integer adminNo,Integer formNo);

	public SupportFormRecordVO updateSupportFormRecord(Integer recordNo, String recordContext,Integer adminNo,Integer formNo);

	public void deleteSupportFormRecord(Integer recordNo);

	public SupportFormRecordVO getOneSupportFormRecord(Integer recordNo);

	public List<SupportFormRecordVO> getAll();
}
