package com.cooklab.support_form.model;

import java.util.List;


public interface SupportFormDAO {
	public Integer insert(SupportFormVO supportFormVO);
    public Integer update(SupportFormVO supportFormVO);
    public Integer delete(Integer formNo);
    public SupportFormVO findByPrimaryKey(Integer formNo);
    public List<SupportFormVO> getAll();
}
