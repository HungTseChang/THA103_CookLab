package com.cooklab.memeber_collection.model;

import java.util.List;


public interface MemberCollectionDAO {
    public void insert(MemberCollectionVO memberCollectionVO);
    public void update(MemberCollectionVO memberCollectionVO);
    public void delete(Integer memberCollectionNo);
    public MemberCollectionVO findByPrimaryKey(Integer memberCollectionNo);
    public List<MemberCollectionVO> getAll();
}
