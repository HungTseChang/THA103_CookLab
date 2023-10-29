package com.cooklab.question_group.model;

import java.util.List;

public interface QuestionGroupDAO {
	 public Integer insert(QuestionGroupVO questionGroup);
	    public Integer update(QuestionGroupVO questionGroup);
	    public Integer delete(Integer questionGroupNo);
	    public QuestionGroupVO findByPrimaryKey(Integer questionGroupNo);
	    public List<QuestionGroupVO> getAll();
	}
