package com.cooklab.question.model;

import java.util.List;

public interface QuestionDAO {
    public Integer insert(QuestionVO question);
    public Integer update(QuestionVO question);
    public Integer delete(Integer QuestionNo);
    public QuestionVO findByPrimaryKey(Integer questionNo);
    public List<QuestionVO> getAll();
}
