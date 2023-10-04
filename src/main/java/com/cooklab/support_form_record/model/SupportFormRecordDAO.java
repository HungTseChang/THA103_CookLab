 package com.cooklab.support_form_record.model;

import java.util.List;

public interface SupportFormRecordDAO {
    public void insert(SupportFormRecordVO supportFormRecord);
    public void update(SupportFormRecordVO supportFormRecord);
    public void delete(Integer recordNo);
    public SupportFormRecordVO findByPrimaryKey(Integer recordNo);
    public List<SupportFormRecordVO> getAll();
}
