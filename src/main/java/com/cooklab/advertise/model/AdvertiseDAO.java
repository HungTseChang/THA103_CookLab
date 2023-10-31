package com.cooklab.advertise.model;

import java.util.List;



public interface AdvertiseDAO {
	public void insert(AdvertiseVO advertise);
    public void update(AdvertiseVO advertise);
    
    
    public List<AdvertiseVO> upAd();
    
    public void delete(AdvertiseVO advertise);
    public AdvertiseVO findByPrimaryKey(Integer advertiseNo);
    public List<AdvertiseVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<PurchaseOrderVO> getAll(Map<String, String[]> map); 
	
}
