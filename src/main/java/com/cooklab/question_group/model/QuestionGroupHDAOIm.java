package com.cooklab.question_group.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class QuestionGroupHDAOIm implements QuestionGroupDAO {

	// 宣告SessionFactory以便開啟Session執行交易
	private SessionFactory factory;

	public QuestionGroupHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(QuestionGroupVO questionGroupVO) {
		// 回傳給Service，1代表成功、-1代表失敗
		try {
			getSession().save(questionGroupVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Integer update(QuestionGroupVO questionGroupVO) {
		try {
			getSession().update(questionGroupVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public Integer delete(Integer questionGroupNo) {
		QuestionGroupVO questionGroupVO = getSession().get(QuestionGroupVO.class, questionGroupNo);
		if (questionGroupVO != null) {
			getSession().delete(questionGroupVO);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}

	}

	@Override
	public QuestionGroupVO findByPrimaryKey(Integer questionGroupNo) {
		return getSession().get(QuestionGroupVO.class, questionGroupNo);
	}

	@Override
	public List<QuestionGroupVO> getAll() {
		return getSession().createQuery("from QuestionGroupVO", QuestionGroupVO.class).list();
	}

}
