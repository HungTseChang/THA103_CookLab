package com.cooklab.permission.model;

import java.util.List;

public interface PermissionDAO {
	public void insert(PermissionVO Permission);

	public void update(PermissionVO Permission);

	public void delete(Integer PermissionNo);

	public PermissionVO findByPrimaryKey(Integer PermissionNo);

	public List<PermissionVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<PurchaseOrderVO> getAll(Map<String, String[]> map); 
}