package com.cooklab.question_group.service;

import java.util.List;

import com.cooklab.question_group.model.QuestionGroupVO;

public interface QuestionGroupServie {
	public QuestionGroupVO addQuestionGroup(String questionName);

	public QuestionGroupVO updateQuestionGroup(Integer questionGroupNo, String questionName);

	public void deleteQuestionGroup(Integer questionGroupNo);

	public QuestionGroupVO getOneQuestionGroup(Integer questionGroupNo);

	public List<QuestionGroupVO> getAll();
}
