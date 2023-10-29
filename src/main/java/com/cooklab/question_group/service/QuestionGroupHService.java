package com.cooklab.question_group.service;

import java.util.List;

import com.cooklab.question_group.model.QuestionGroupHDAOIm;
import com.cooklab.question_group.model.QuestionGroupVO;
import com.cooklab.util.HibernateUtil;

public class QuestionGroupHService implements QuestionGroupServie {

	private QuestionGroupHDAOIm dao;

	// 建構子呼叫工廠
	public QuestionGroupHService() {
		dao = new QuestionGroupHDAOIm(HibernateUtil.getSessionFactory());
	}

	public QuestionGroupVO addQuestionGroup(String questionName) {

		QuestionGroupVO questionGroupVO = new QuestionGroupVO();

		questionGroupVO.setQuestionName(questionName);

		dao.insert(questionGroupVO);

		return questionGroupVO;
	}

	public QuestionGroupVO updateQuestionGroup(Integer questionGroupNo, String questionName) {

		QuestionGroupVO questionGroupVO = new QuestionGroupVO();

		questionGroupVO.setQuestionGroupNo(questionGroupNo);
		questionGroupVO.setQuestionName(questionName);
		dao.update(questionGroupVO);

		return questionGroupVO;
	}

	public void deleteQuestionGroup(Integer questionGroupNo) {
		dao.delete(questionGroupNo);
	}

	public QuestionGroupVO getOneQuestionGroup(Integer questionGroupNo) {
		return dao.findByPrimaryKey(questionGroupNo);
	}

	public List<QuestionGroupVO> getAll() {
		return dao.getAll();
	}

}
