package com.cooklab.order_detail.model;

import java.util.List;



public interface OrderDetailDAO {

    public void insert(OrderDetailVO orderDetailVO);
    public void update(OrderDetailVO orderDetailVO);
    public void delete(Integer orderDetailNo);
    public OrderDetailVO findByPrimaryKey(Integer orderDetailNo);
    public List<OrderDetailVO> getAll();
}
