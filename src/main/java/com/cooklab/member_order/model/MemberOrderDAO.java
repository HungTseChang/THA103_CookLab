package com.cooklab.member_order.model;

import java.util.List;


public interface MemberOrderDAO {

    public int insert(MemberOrderVO memberOrderVO);
    public Integer update(MemberOrderVO memberOrderVO);
    public void delete(Integer orderNo);
    public MemberOrderVO findByPrimaryKey(Integer orderNo);
    public List<MemberOrderVO> getAll();
}
