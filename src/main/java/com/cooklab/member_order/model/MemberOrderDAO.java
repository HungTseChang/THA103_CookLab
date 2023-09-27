package com.cooklab.member_order.model;

import java.util.List;


public interface MemberOrderDAO {

    public void insert(MemberOrderVO memberOrderVO);
    public void update(MemberOrderVO memberOrderVO);
    public void delete(Integer orderNo);
    public MemberOrderVO findByPrimaryKey(Integer orderNo);
    public List<MemberOrderVO> getAll();
}
