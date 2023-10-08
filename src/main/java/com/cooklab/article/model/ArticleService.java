package com.cooklab.article.model;

import java.sql.Timestamp;
import java.util.List;


import com.cooklab.article.model.ArticleVO;




public class ArticleService {
	private ArticleDAO dao;
	public ArticleService() {
		dao = new ArticleJDBCDAOIm();
	}

	public ArticleVO addArt(Integer articleCategory, String articleTitle, Integer memberId,
			Byte articleStatus,String articleContent,Integer articleCount,Integer viewCount) {

		ArticleVO artVO = new ArticleVO();

		
		artVO.setArticleCategory(articleCategory);
		artVO.setArticleTitle(articleTitle);
		artVO.setMemberId(memberId);
		artVO.setArticleStatus(articleStatus);
		artVO.setArticleContent(articleContent);
		artVO.setArticleCount(articleCount);
		artVO.setViewCount(viewCount);
		
		dao.insert(artVO);

		return artVO;
	}

	public ArticleVO updateArt(Integer articleCategory, String articleTitle, Integer memberId,
			Byte articleStatus,String articleContent,Integer articleCount,Integer viewCount
			,Integer articleNo) {

		ArticleVO artVO = new ArticleVO();

		artVO.setArticleCategory(articleCategory);
		artVO.setArticleTitle(articleTitle);
		artVO.setMemberId(memberId);
		artVO.setArticleStatus(articleStatus);
		artVO.setArticleContent(articleContent);
		artVO.setArticleCount(articleCount);
		artVO.setViewCount(viewCount);
		artVO.setArticleNo(articleNo);
		
		dao.update(artVO);

		return artVO;
	}

	public void deleteArt(Integer articleNo) {
		dao.delete(articleNo);
	}

	public ArticleVO getOneArt(Integer articleNo) {
		return dao.findByPrimaryKey(articleNo);
	}

	public List<ArticleVO> getAll() {
		return dao.getAll();
	}

	
}
