package com.cooklab.question_group.model;

import java.util.List;

import com.cooklab.question_group.model.QuestionGroupVO;

public interface QuestionGroupDAO {
	 public void insert(QuestionGroupVO questionGroup);
	    public void update(QuestionGroupVO questionGroup);
	    public void delete(Integer QuestionGroupNo);
	    public QuestionGroupVO findByPrimaryKey(Integer questionGroupNo);
	    public List<QuestionGroupVO> getAll();
	    //萬用複合查詢(傳入參數型態Map)(回傳 List)
	//  public List<PurchaseOrderVO> getAll(Map<String, String[]> map); 
	}
