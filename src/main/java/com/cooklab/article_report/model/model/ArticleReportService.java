package com.cooklab.article_report.model.model;

import java.sql.Timestamp;
import java.util.List;

public class ArticleReportService {

	private ArticleReportDAO dao;
	
	public ArticleReportService() {
		dao = new ArticleReportJDBCDAOIm();
	}

	public ArticleReportVO add(Integer articleNo,Integer reporterId, String reportingReason, Byte reportingStatus,
			String reportingAnswer,Timestamp createdTimestamp) {

		ArticleReportVO ArticleReportVO = new ArticleReportVO();

		
		ArticleReportVO.setArticleNo(articleNo);
		ArticleReportVO.setReporterId(reporterId);
		ArticleReportVO.setReportingReason(reportingReason);
		ArticleReportVO.setReportingStatus(reportingStatus);
		ArticleReportVO.setReportingAnswer(reportingAnswer);
		ArticleReportVO.setCreatedTimestamp(createdTimestamp);

		dao.insert(ArticleReportVO);

		

		return ArticleReportVO;
	}

	public ArticleReportVO update(Integer articleNo,Integer reporterId,String reportingReason,
			Byte ArticleReportStatus,String reportingAnswer,Timestamp createdTimestamp,Integer ArticleReportNo) {

		ArticleReportVO ArticleReportVO = new ArticleReportVO();
	
		ArticleReportVO.setArticleNo(articleNo);
		ArticleReportVO.setReporterId(reporterId);
		ArticleReportVO.setReportingReason(reportingReason);
		ArticleReportVO.setReportingStatus(ArticleReportStatus);
		ArticleReportVO.setReportingAnswer(reportingAnswer);
		ArticleReportVO.setCreatedTimestamp(createdTimestamp);

		ArticleReportVO.setArticleReportNo(ArticleReportNo);
		
		dao.update(ArticleReportVO);

		return ArticleReportVO;
	}

	public void delete(Integer ArticleReportNo) {
		dao.delete(ArticleReportNo);
	}

	public ArticleReportVO getOne(Integer ArticleReportNo) {
		return dao.findByPrimaryKey(ArticleReportNo);
	}

	public List<ArticleReportVO> getAll() {
		return dao.getAll();
	}

	

}
