package com.cooklab.notify_center.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class NotifyCenterHDAOIm implements NotifyCenterDAO {

	// 宣告SessionFactory以便開啟Session執行交易
	private SessionFactory factory;

	public NotifyCenterHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(NotifyCenterVO notifyCenterVO) {
		// 回傳給Service，1代表成功、-1代表失敗
		try {
			getSession().save(notifyCenterVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Integer update(NotifyCenterVO notifyCenterVO) {
		try {
			getSession().update(notifyCenterVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public Integer delete(Integer notifyNo) {
		NotifyCenterVO notifyCenterVO = getSession().get(NotifyCenterVO.class, notifyNo);
		if (notifyCenterVO != null) {
			getSession().delete(notifyCenterVO);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}

	}

	@Override
	public NotifyCenterVO findByPrimaryKey(Integer notifyNo) {
		return getSession().get(NotifyCenterVO.class, notifyNo);
	}

	// 為了排除1+N問題，此處使用left join語法
	@Override
	public List<NotifyCenterVO> getAll() {
		return getSession().createQuery(
				"select distinct n from NotifyCenterVO n left join fetch n.members order by n.createdTimestamp desc",
				NotifyCenterVO.class).list();

	}

}
