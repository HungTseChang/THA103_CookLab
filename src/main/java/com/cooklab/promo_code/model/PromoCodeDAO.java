package com.cooklab.promo_code.model;

import java.util.List;



public interface PromoCodeDAO {
    public void insert(PromoCodeVO promoCodeVO);
    public void update(PromoCodeVO promoCodeVO);
    public void delete(Integer promoCodeNo);
    public PromoCodeVO findByPrimaryKey(Integer promoCodeNo);
    public List<PromoCodeVO> getAll();
}
