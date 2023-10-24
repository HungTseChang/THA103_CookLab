package com.cooklab.support_form.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.support_form_record.model.SupportFormRecordVO;

public class SupportFormHDAOIm implements SupportFormDAO {

	// 宣告SessionFactory以便開啟Session執行交易
	private SessionFactory factory;

	public SupportFormHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(SupportFormVO supportFormVO) {
		//回傳給Service，1代表成功、-1代表失敗
		try {
			getSession().save(supportFormVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Integer update(SupportFormVO supportFormVO) {
		try {
			getSession().update(supportFormVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public Integer delete(Integer formNo) {
		SupportFormVO supportFormVO = getSession().get(SupportFormVO.class, formNo);
		if (supportFormVO != null) {
			getSession().delete(supportFormVO);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}

	}

	@Override
	public SupportFormVO findByPrimaryKey(Integer formNo) {
		return getSession().get(SupportFormVO.class, formNo);
	}

	//為了排除1+N問題，此處使用left join語法
	@Override
	public List<SupportFormVO> getAll() {
		return getSession().createQuery("select distinct s from SupportFormVO s left join fetch s.admins ", SupportFormVO.class).list();
	}
	
	// 實作從單一表單編號查詢所有關聯的表單記錄方法
	public List<SupportFormRecordVO> getRecordByFormNo(Integer formNo) {
		SupportFormVO sfvo = getSession().get(SupportFormVO.class, formNo);
		List<SupportFormRecordVO> sfrlist = new ArrayList<SupportFormRecordVO>();
		for(SupportFormRecordVO sfrvo: sfvo.getSupportFormRecords()) {
			sfrlist.add(sfrvo);
		}
		return sfrlist;
	}

}
