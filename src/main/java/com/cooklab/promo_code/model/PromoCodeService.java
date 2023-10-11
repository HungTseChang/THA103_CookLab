package com.cooklab.promo_code.model;

import java.sql.Timestamp;
import java.util.List;

public class PromoCodeService {

	private PromoCodeDAO dao;

	public PromoCodeService() {
		dao = new PromoCodeJDBCDAOIm();
	}

	public PromoCodeVO addPromoCode(Integer promoCodeNo, String promoCodeSerialNumber, Timestamp startTime,
			Timestamp endTime, Integer percentageDiscountAmount, Integer fixedDiscountAmount, Integer usagesAllowed,
			Integer minimumConsumption, Timestamp createdTimestamp) {

		PromoCodeVO PromoCodeVO = new PromoCodeVO();

		PromoCodeVO.setPromoCodeNo(promoCodeNo);
		PromoCodeVO.setPromoCodeSerialNumber(promoCodeSerialNumber);
		PromoCodeVO.setStartTime(startTime);
		PromoCodeVO.setEndTime(endTime);
		PromoCodeVO.setPercentageDiscountAmount(percentageDiscountAmount);
		PromoCodeVO.setFixedDiscountAmount(fixedDiscountAmount);
		PromoCodeVO.setMinimumConsumption(minimumConsumption);
		PromoCodeVO.setCreatedTimestamp(createdTimestamp);
		dao.insert(PromoCodeVO);

		return PromoCodeVO;
	}

	public PromoCodeVO updatePromoCode(Integer promoCodeNo, String promoCodeSerialNumber, Timestamp startTime,
			Timestamp endTime, Integer percentageDiscountAmount, Integer fixedDiscountAmount, Integer usagesAllowed,
			Integer minimumConsumption, Timestamp createdTimestamp) {

		PromoCodeVO PromoCodeVO = new PromoCodeVO();

		PromoCodeVO.setPromoCodeNo(promoCodeNo);
		PromoCodeVO.setPromoCodeSerialNumber(promoCodeSerialNumber);
		PromoCodeVO.setStartTime(startTime);
		PromoCodeVO.setEndTime(endTime);
		PromoCodeVO.setPercentageDiscountAmount(percentageDiscountAmount);
		PromoCodeVO.setFixedDiscountAmount(fixedDiscountAmount);
		PromoCodeVO.setMinimumConsumption(minimumConsumption);
		PromoCodeVO.setCreatedTimestamp(createdTimestamp);
		dao.update(PromoCodeVO);

		return PromoCodeVO;
	}

	public void deletePromocode(Integer promocodeno) {
		dao.delete(promocodeno);
	}

	public PromoCodeVO getOnePromoCode(Integer promocodeno) {
		return dao.findByPrimaryKey(promocodeno);
	}

	public List<PromoCodeVO> getAll() {
		return dao.getAll();
	}
}
