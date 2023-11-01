package com.cooklab.promo_code.model;

import java.util.List;

import com.cooklab.promo_code.model.*;



public interface PromoCodeDAO {
    public void insert(PromoCodeVO promoCodeVO);
    public void update(PromoCodeVO promoCodeVO);
    public void delete(PromoCodeVO promoCode);
    public PromoCodeVO findByPrimaryKey(Integer promoCodeNo);
    public List<PromoCodeVO> getAll();
    public PromoCodeVO findByPromoCodeSerialNumber(String promoCodeSerialNumber);
    
}
