package com.cooklab.support_form.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.util.HibernateUtil;

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
	
	//單獨測試HQL，實際上線不會使用此方法
	public void add(SupportFormVO supportFormVO) {
		//回傳給Service，1代表成功、-1代表失敗
		try {
			getSession().beginTransaction();
			getSession().save(supportFormVO);
			getSession().getTransaction().commit();
		} catch (Exception e) {
			getSession().getTransaction().rollback();
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

	@Override
	public List<SupportFormVO> getAll() {
		return getSession().createQuery("select distinct s from SupportFormVO s left join fetch s.admins ", SupportFormVO.class).list();
	}

}
