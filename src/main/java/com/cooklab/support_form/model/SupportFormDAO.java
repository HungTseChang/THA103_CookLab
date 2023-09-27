package com.cooklab.support_form.model;

import java.util.List;


public interface SupportFormDAO {
	public void insert(SupportFormVO supportFormVO);
    public void update(SupportFormVO supportFormVO);
    public void delete(Integer formNo);
    public SupportFormVO findByPrimaryKey(Integer formNo);
    public List<SupportFormVO> getAll();
}
