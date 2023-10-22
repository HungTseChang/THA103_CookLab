 package com.cooklab.support_form_record.model;

import java.util.List;

public interface SupportFormRecordDAO {
    public Integer insert(SupportFormRecordVO supportFormRecord);
    public Integer update(SupportFormRecordVO supportFormRecord);
    public Integer delete(Integer recordNo);
    public SupportFormRecordVO findByPrimaryKey(Integer recordNo);
    public List<SupportFormRecordVO> getAll();
}
