package com.cooklab.hashtag.model;

import java.util.*;

public interface HashtagDAO {
    public void insert(HashtagVO hashtagVO);
    public void update(HashtagVO hashtagVO);
    public void delete(Integer hashtagNO);
    public HashtagVO findByPrimaryKey(Integer hashtagNO);
    public List<HashtagVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
