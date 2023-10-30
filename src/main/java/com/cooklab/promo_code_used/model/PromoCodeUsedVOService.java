package com.cooklab.promo_code_used.model;

import com.cooklab.util.HibernateUtil;

public class PromoCodeUsedVOService {
	
	private PromoCodeUsedDAO dao;
	
	public PromoCodeUsedVOService() {
		dao = new  PromoCodeUsedHDAOIm(HibernateUtil.getSessionFactory());
	}
	
	public void insert(PromoCodeUsedVO promoCodeUsedVO) {
		dao.insert(promoCodeUsedVO);
	}
}
