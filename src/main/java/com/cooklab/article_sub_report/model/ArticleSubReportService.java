package com.cooklab.article_sub_report.model;

import java.sql.Timestamp;
import java.util.List;

import com.cooklab.util.HibernateUtil;

public class ArticleSubReportService {

	private ArticleSubReportHBDAO dao;
	
	public ArticleSubReportService() {
		dao = new ArticleSubReportHBDAO(HibernateUtil.getSessionFactory());
	}

	public ArticleSubReportVO add(Integer articleSubNo,Integer reporterId, String reportingReason, Byte reportingStatus) {

		ArticleSubReportVO ArticleSubReportVO = new ArticleSubReportVO();

		
		ArticleSubReportVO.setArticleSubNo(articleSubNo);
		ArticleSubReportVO.setReporterId(reporterId);
		ArticleSubReportVO.setReportingReason(reportingReason);
		ArticleSubReportVO.setReportingStatus(reportingStatus);


		dao.insert(ArticleSubReportVO);

		

		return ArticleSubReportVO;
	}

	public ArticleSubReportVO update(Integer articleSubNo,Integer reporterId,String reportingReason,
			Byte ArticleSubReportStatus,String reportingAnswer,Timestamp createdTimestamp,Integer ArticleSubReportNo) {

		ArticleSubReportVO ArticleSubReportVO = new ArticleSubReportVO();
	
		ArticleSubReportVO.setArticleSubNo(articleSubNo);
		ArticleSubReportVO.setReporterId(reporterId);
		ArticleSubReportVO.setReportingReason(reportingReason);
		ArticleSubReportVO.setReportingStatus(ArticleSubReportStatus);
		ArticleSubReportVO.setReportingAnswer(reportingAnswer);
		ArticleSubReportVO.setCreatedTimestamp(createdTimestamp);

		ArticleSubReportVO.setArticleSubReportNo(ArticleSubReportNo);
		
		dao.update(ArticleSubReportVO);

		return ArticleSubReportVO;
	}

	public ArticleSubReportVO update(ArticleSubReportVO ArticleSubReportVO) {
		
		dao.update(ArticleSubReportVO);

		return ArticleSubReportVO;
	}
	
	
	
	public void delete(Integer ArticleSubReportNo) {
		dao.delete(ArticleSubReportNo);
	}

	public ArticleSubReportVO getOne(Integer ArticleSubReportNo) {
		return dao.findByPrimaryKey(ArticleSubReportNo);
	}

	public List<ArticleSubReportVO> getAll() {
		return dao.getAll();
	}

	

}
