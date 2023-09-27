package com.cooklab.admins.model;

import java.util.List;

public interface AdminsDAO {
    public void insert(AdminsVO Admins);
    public void update(AdminsVO Admins);
    public void delete(Integer AdminsNo);
    public AdminsVO findByPrimaryKey(Integer adminsNo);
    public List<AdminsVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<PurchaseOrderVO> getAll(Map<String, String[]> map); 
}
