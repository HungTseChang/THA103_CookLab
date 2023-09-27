package com.cooklab.question.model;

import java.util.List;

public interface QuestionDAO {
    public void insert(QuestionVO question);
    public void update(QuestionVO question);
    public void delete(Integer QuestionNo);
    public QuestionVO findByPrimaryKey(Integer questionNo);
    public List<QuestionVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<PurchaseOrderVO> getAll(Map<String, String[]> map); 
}
