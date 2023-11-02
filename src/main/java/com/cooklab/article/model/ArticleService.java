package com.cooklab.article.model;

import java.sql.Timestamp;
import java.util.List;

import com.cooklab.article.model.ArticleVO;
import com.cooklab.util.HibernateUtil;

public class ArticleService {
	private ArticleDAO dao;

	public ArticleService() {

		dao = new ArticleHBDAO(HibernateUtil.getSessionFactory());
	}

//	public ArticleVO addArt(Integer articleCategory, String articleTitle, Integer memberId,
//			Byte articleStatus,String articleContent,Integer articleCount,Integer viewCount) {
//
//		ArticleVO artVO = new ArticleVO();
//
//		
//		artVO.setArticleCategory(articleCategory);
//		artVO.setArticleTitle(articleTitle);
//		artVO.setMemberId(memberId);
//		artVO.setArticleStatus(articleStatus);
//		artVO.setArticleContent(articleContent);
//		artVO.setArticleCount(articleCount);
//		artVO.setViewCount(viewCount);
//		
//		dao.insert(artVO);
//
//		return artVO;
//	}
//
//	public ArticleVO updateArt(Integer articleCategory, String articleTitle, Integer memberId,
//			Byte articleStatus,String articleContent,Integer articleCount,Integer viewCount
//			,Integer articleNo) {
//
//		ArticleVO artVO = new ArticleVO();
//
////		artVO.setArticleCategory(articleCategory);
//		artVO.setArticleTitle(articleTitle);
//		artVO.setMemberId(memberId);
//		artVO.setArticleStatus(articleStatus);
//		artVO.setArticleContent(articleContent);
//		artVO.setArticleCount(articleCount);
//		artVO.setViewCount(viewCount);
//		artVO.setArticleNo(articleNo);
//		
//		dao.update(artVO);
//
//		return artVO;
//	}

	public void addArt(ArticleVO articleVO) {
		dao.insert(articleVO);
	}

	public void updateArt(ArticleVO articleVO) {
		dao.update(articleVO);
	}
	// 因為介面的update的class為ArticleVO，會預設更新全部欄位，
	// 下面方法是為了更新單一欄位而設定

	public void updateArticleStatus(Integer articleNo, Byte articleStatus) {
		ArticleVO existingArticle = dao.findByPrimaryKey(articleNo);

		existingArticle.setArticleStatus(articleStatus);
		System.out.println(existingArticle);
		dao.update(existingArticle);

	}

	public void updateViewCount(Integer articleNo, Integer viewCount) {
		ArticleVO existingArticle = dao.findByPrimaryKey(articleNo);
		existingArticle.setArticleNo(articleNo);
		existingArticle.setViewCount(viewCount);
	
		dao.update(existingArticle);
	}

	public void deleteArt(Integer articleNo) {
		dao.delete(articleNo);
	}

	public ArticleVO getOneArt(Integer articleNo) {
		return dao.findByPrimaryKey(articleNo);
	}
	
	public List<ArticleVO> getStatus(Byte articleStatus) {
		return dao.findByStatus(articleStatus);
	}
	
	public ArticleVO getOneByStatus(Byte articleStatus) {
		return dao.findOneByStatus(articleStatus);
	}
	public List<ArticleVO> getAll() {
		return dao.getAll();
	}

}
