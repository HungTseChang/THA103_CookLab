package com.cooklab.question.service;

import java.util.List;

import com.cooklab.question.model.QuestionVO;

public interface QuestionServie {
	public QuestionVO addQuestion(Integer questionGroupNo, String questionTitle, String questionContent);

	public QuestionVO updateQuestion(Integer questionNo, Integer questionGroupNo, String questionTitle,
			String questionContent);

	public Integer deleteQuestion(Integer questionNo);

	public QuestionVO getOneQuestion(Integer questionNo);

	public List<QuestionVO> getAll();
}
