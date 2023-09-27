 package com.cooklab.product.model;

import java.util.List;

public interface ProductDAO {
    public void insert(ProductVO product);
    public void update(ProductVO product);
    public void delete(Integer productNo);
    public ProductVO findByPrimaryKey(Integer productNo);
    public List<ProductVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<PurchaseOrderVO> getAll(Map<String, String[]> map); 
}
