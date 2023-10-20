package com.cooklab.promo_code.model;

import java.sql.Timestamp;
import java.util.List;

import com.cooklab.advertise.model.AdvertiseVO;

public class PromoCodeService {
	private PromoCodeHBDAO dao;
//	private PromoCodeDAO dao;

	public PromoCodeService() {
//		dao = new PromoCodeJDBCDAOIm();
		dao = new PromoCodeHBDAO();
	}

//	public PromoCodeVO addPromoCode(Integer promoCodeNo, String promoCodeSerialNumber, Timestamp startTime,
//			Timestamp endTime, Integer percentageDiscountAmount, Integer fixedDiscountAmount, Integer usagesAllowed,
//			Integer minimumConsumption, Timestamp createdTimestamp) {
//
//		PromoCodeVO PromoCodeVO = new PromoCodeVO();
//
//		PromoCodeVO.setPromoCodeNo(promoCodeNo);
//		PromoCodeVO.setPromoCodeSerialNumber(promoCodeSerialNumber);
//		PromoCodeVO.setStartTime(startTime);
//		PromoCodeVO.setEndTime(endTime);
//		PromoCodeVO.setPercentageDiscountAmount(percentageDiscountAmount);
//		PromoCodeVO.setFixedDiscountAmount(fixedDiscountAmount);
//		PromoCodeVO.setMinimumConsumption(minimumConsumption);
//		PromoCodeVO.setCreatedTimestamp(createdTimestamp);
//		dao.insert(PromoCodeVO);
//
//		return PromoCodeVO;
//	}
//
//	public PromoCodeVO updatePromoCode(Integer promoCodeNo, String promoCodeSerialNumber, Timestamp startTime,
//			Timestamp endTime, Integer percentageDiscountAmount, Integer fixedDiscountAmount, Integer usagesAllowed,
//			Integer minimumConsumption, Timestamp createdTimestamp) {
//
//		PromoCodeVO PromoCodeVO = new PromoCodeVO();
//
//		PromoCodeVO.setPromoCodeNo(promoCodeNo);
//		PromoCodeVO.setPromoCodeSerialNumber(promoCodeSerialNumber);
//		PromoCodeVO.setStartTime(startTime);
//		PromoCodeVO.setEndTime(endTime);
//		PromoCodeVO.setPercentageDiscountAmount(percentageDiscountAmount);
//		PromoCodeVO.setFixedDiscountAmount(fixedDiscountAmount);
//		PromoCodeVO.setMinimumConsumption(minimumConsumption);
//		PromoCodeVO.setCreatedTimestamp(createdTimestamp);
//		dao.update(PromoCodeVO);
//
//		return PromoCodeVO;
//	}

	public void addPc (PromoCodeVO promoCodeVO) {
		dao.insert(promoCodeVO);
	}
	
	public void  updatePc(PromoCodeVO promoCodeVO) {
		dao.update(promoCodeVO);
	}
	
	
	public void deletePc(PromoCodeVO promoCodeVO) {
		dao.delete(promoCodeVO);
	}

	public PromoCodeVO getOnePc(Integer promoCodeNO) {
		return dao.findByPrimaryKey(promoCodeNO);
	}

	public List<PromoCodeVO> getAll() {
		return dao.getAll();
	}


		
	}

	
		
	

