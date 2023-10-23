package com.cooklab.notify_center.model;

import java.util.*;


public interface NotifyCenterDAO{
	public Integer insert(NotifyCenterVO notifyCenterVO);
    public Integer update(NotifyCenterVO notifyCenterVO);
    public Integer delete(Integer notifyNo);
    public NotifyCenterVO findByPrimaryKey(Integer notifyNo);
    public List<NotifyCenterVO> getAll();
}
