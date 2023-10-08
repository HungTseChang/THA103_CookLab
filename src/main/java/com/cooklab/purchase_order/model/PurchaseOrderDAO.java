package com.cooklab.purchase_order.model;

import java.util.List;

public interface PurchaseOrderDAO {
    public void insert(PurchaseOrderVO purchaseOrder);
    public void update(PurchaseOrderVO purchaseOrder);
    public void delete(Integer purchaseOrderNo);
    public PurchaseOrderVO findByPrimaryKey(Integer purchaseOrderNo);
    public List<PurchaseOrderVO> getAll();
}
