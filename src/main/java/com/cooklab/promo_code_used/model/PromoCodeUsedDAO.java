package com.cooklab.promo_code_used.model;

import java.util.List;



public interface PromoCodeUsedDAO {
    public void insert(PromoCodeUsedVO promoCodeUsedVO);
    public void update(PromoCodeUsedVO promoCodeUsedVO);
    public void delete(Integer promoCodeUsedNo);
    public PromoCodeUsedVO findByPrimaryKey(Integer promoCodeUsedNo);
    public List<PromoCodeUsedVO> getAll();
}
