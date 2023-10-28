package com.cooklab.question.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class QuestionHDAOIm implements QuestionDAO {

	// 宣告SessionFactory以便開啟Session執行交易
	private SessionFactory factory;

	public QuestionHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(QuestionVO questionVO) {
		//回傳給Service，1代表成功、-1代表失敗
		try {
			getSession().save(questionVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Integer update(QuestionVO questionVO) {
		try {
			getSession().update(questionVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public Integer delete(Integer questionNo) {
		QuestionVO questionVO = getSession().get(QuestionVO.class, questionNo);
		if (questionVO != null) {
			getSession().delete(questionVO);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}

	}

	@Override
	public QuestionVO findByPrimaryKey(Integer questionNo) {
		return getSession().get(QuestionVO.class, questionNo);
	}

	//為了排除1+N問題，此處使用left join語法
	@Override
	public List<QuestionVO> getAll() {
		return getSession().createQuery("select distinct q from QuestionVO q left join fetch q.questionGroup ", QuestionVO.class).list();
	}

}
