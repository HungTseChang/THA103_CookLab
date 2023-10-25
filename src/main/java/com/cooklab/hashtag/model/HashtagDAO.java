package com.cooklab.hashtag.model;

import java.util.*;

public interface HashtagDAO {
    public int insert(HashtagVO hashtagVO);
    public boolean update(HashtagVO hashtagVO);
    public boolean delete(Integer hashtagNO);
    public HashtagVO findByPrimaryKey(Integer hashtagNO);
    public List<HashtagVO> getAll();
    public List<HashtagVO> getOfficalHashtag();
    public List<HashtagVO> getPopularHashtag(int n);
    public HashtagVO findByHashtagName(String hashtagName);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
