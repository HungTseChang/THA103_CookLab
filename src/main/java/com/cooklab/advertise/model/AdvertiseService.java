package com.cooklab.advertise.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.cooklab.advertise.model.*;
import com.cooklab.article.model.ArticleVO;
import com.cooklab.util.HibernateUtil;

public class AdvertiseService {

	private AdvertiseHBDAO dao;

//	private AdvertiseJDBCDAOIm dao;
	public AdvertiseService() {
		dao = new AdvertiseHBDAO(HibernateUtil.getSessionFactory());
//		dao = new AdvertiseJDBCDAOIm();	
	}

//	public AdvertiseVO addAdvertise(Integer advertiseNo, String advertiseName, Timestamp advertiseShelfTime,
//			Timestamp avertiseOffsaleTime, String advertiseImg, String advertiseUrl, Timestamp createdTimestamp) {
//
//		AdvertiseVO AdvertiseVO = new AdvertiseVO();
//
//		AdvertiseVO.setAdvertiseNo(advertiseNo);
//		AdvertiseVO.setAdvertiseName(advertiseName);
//		AdvertiseVO.setAdvertiseShelfTime(advertiseShelfTime);
//		AdvertiseVO.setAdvertiseOffsaleTime(avertiseOffsaleTime);
//		AdvertiseVO.setAdvertiseImg(advertiseImg);
//		AdvertiseVO.setAdvertiseUrl(advertiseUrl);
//		AdvertiseVO.setCreatedTimestamp(createdTimestamp);
//		dao.insert(AdvertiseVO);
//
//		return AdvertiseVO;
//	}
//
//	public AdvertiseVO updatePromoCode(Integer advertiseNo, String advertiseName, Timestamp advertiseShelfTime,
//			Timestamp avertiseOffsaleTime, String advertiseImg, String advertiseUrl, Timestamp createdTimestamp) {
//
//		AdvertiseVO AdvertiseVO = new AdvertiseVO();
//
//		AdvertiseVO.setAdvertiseNo(advertiseNo);
//		AdvertiseVO.setAdvertiseName(advertiseName);
//		AdvertiseVO.setAdvertiseShelfTime(advertiseShelfTime);
//		AdvertiseVO.setAdvertiseOffsaleTime(avertiseOffsaleTime);
//		AdvertiseVO.setAdvertiseImg(advertiseImg);
//		AdvertiseVO.setAdvertiseUrl(advertiseUrl);
//		AdvertiseVO.setCreatedTimestamp(createdTimestamp);
//		dao.insert(AdvertiseVO);
//
//		return AdvertiseVO;
//	}

	public void addAd(AdvertiseVO advertiseVO) {
		dao.insert(advertiseVO);
	}

	public void updateAd(AdvertiseVO advertiseVO) {
		dao.update(advertiseVO);
	}

	public void deleteAd(AdvertiseVO advertiseVO) {
		dao.delete(advertiseVO);
	}

	public AdvertiseVO getOneAd(Integer advertiseNO) {
		return dao.findByPrimaryKey(advertiseNO);
	}

	public List<AdvertiseVO> getAll() {
		return dao.getAll();
	}

	public List<AdvertiseVO> upAd() {
		return dao.upAd();
	}
}