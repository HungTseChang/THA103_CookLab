package com.cooklab.support_form_record.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SupportFormRecordHDAOIm implements SupportFormRecordDAO {

	// 宣告SessionFactory以便開啟Session執行交易
	private SessionFactory factory;

	public SupportFormRecordHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(SupportFormRecordVO SupportFormRecordVO) {
		// 回傳給Service，1代表成功、-1代表失敗
		try {
			getSession().save(SupportFormRecordVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Integer update(SupportFormRecordVO SupportFormRecordVO) {
		try {
			getSession().update(SupportFormRecordVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public Integer delete(Integer recordNo) {
		SupportFormRecordVO SupportFormRecordVO = getSession().get(SupportFormRecordVO.class, recordNo);
		if (SupportFormRecordVO != null) {
			getSession().delete(SupportFormRecordVO);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}

	}

	@Override
	public SupportFormRecordVO findByPrimaryKey(Integer recordNo) {
		return getSession().get(SupportFormRecordVO.class, recordNo);
	}

	// 為了排除1+N問題，此處使用left join語法
	@Override
	public List<SupportFormRecordVO> getAll() {
		return getSession().createQuery(
				"select distinct s from SupportFormRecordVO s left join fetch s.admins left join fetch s.supportForms",
				SupportFormRecordVO.class).list();

	}

}
