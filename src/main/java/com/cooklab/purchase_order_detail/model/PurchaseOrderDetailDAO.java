package com.cooklab.purchase_order_detail.model;

import java.util.List;

public interface PurchaseOrderDetailDAO {
    public void insert(PurchaseOrderDetailVO purchaseOrderDetail);
    public void update(PurchaseOrderDetailVO purchaseOrderDetail);
    public void delete(Integer purchaseOrderDetailNo);
    public PurchaseOrderDetailVO findByPrimaryKey(Integer purchaseOrderDetailNo);
    public List<PurchaseOrderDetailVO> getAll();
}
