package com.cooklab.purchase_order.model;

import java.util.List;

public interface PurchaseOrderDAO {
    public void insert(PurchaseOrderVO purchaseOrder);
    public void update(PurchaseOrderVO purchaseOrder);
    public void delete(Integer purchaseOrderNo);
    public PurchaseOrderVO findByPrimaryKey(Integer purchaseOrderNo);
    public List<PurchaseOrderVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<PurchaseOrderVO> getAll(Map<String, String[]> map); 
}
