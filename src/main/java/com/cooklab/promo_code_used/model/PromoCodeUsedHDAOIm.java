package com.cooklab.promo_code_used.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PromoCodeUsedHDAOIm implements PromoCodeUsedDAO {

	private SessionFactory factory;

	public PromoCodeUsedHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	
	
	@Override
	public void insert(PromoCodeUsedVO promoCodeUsedVO) {
		Session session = getSession();
		
		session.save(promoCodeUsedVO);
		System.out.println("新增成功");
		
	}

	@Override
	public void update(PromoCodeUsedVO promoCodeUsedVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer promoCodeUsedNo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PromoCodeUsedVO findByPrimaryKey(Integer promoCodeUsedNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PromoCodeUsedVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
