package com.cooklab.notify_center.model;

import java.util.*;


public interface NotifyCenterDAO_interface {
	public void insert(NotifyCenterVO notifyCenterVO);
    public void update(NotifyCenterVO notifyCenterVO);
    public void delete(Integer notifyNo);
    public NotifyCenterVO findByPrimaryKey(Integer notifyNo);
    public List<NotifyCenterVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<NotifyCenterVO> getAll(Map<String, String[]> map); 

}
