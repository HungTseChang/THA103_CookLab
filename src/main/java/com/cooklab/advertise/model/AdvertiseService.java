package com.cooklab.advertise.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.cooklab.advertise.model.*;

import com.cooklab.util.HibernateUtil;

public class AdvertiseService {
	
	private AdvertiseHBDAO dao;
//	private AdvertiseJDBCDAOIm dao;
	public AdvertiseService() {
		dao = new AdvertiseHBDAO();
//		dao = new AdvertiseJDBCDAOIm();	
		}
	
	public AdvertiseVO addAdvertise(Integer advertiseNo, String advertiseName, Date advertiseShelfTime,
			Date avertiseOffsaleTime, String advertiseImg, String advertiseUrl, Timestamp createdTimestamp) {

		AdvertiseVO AdvertiseVO = new AdvertiseVO();

		AdvertiseVO.setAdvertiseNo(advertiseNo);
		AdvertiseVO.setAdvertiseName(advertiseName);
		AdvertiseVO.setAdvertiseShelfTime(advertiseShelfTime);
		AdvertiseVO.setAdvertiseOffsaleTime(avertiseOffsaleTime);
		AdvertiseVO.setAdvertiseImg(advertiseImg);
		AdvertiseVO.setAdvertiseUrl(advertiseUrl);
		AdvertiseVO.setCreatedTimestamp(createdTimestamp);
		dao.insert(AdvertiseVO);

		return AdvertiseVO;
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
		dao.update(AdvertiseVO);

		return PromoCodeVO;
	}

	public void deletePromocode(Integer advertiseno) {
		dao.delete(advertiseno);
	}

	public PromoCodeVO getOnePromoCode(Integer advertiseno) {
		return dao.findByPrimaryKey(advertiseno);
	}

	public List<AdvertiseVO> getAll() {
		return dao.getAll();
	}

	public void delete(Integer advertiseno) {
		// TODO Auto-generated method stub
		
	}


		
	}

	
		
	
