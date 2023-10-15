package com.cooklab.article_category.model;

import java.util.List;
import com.cooklab.article_category.model.*;

public class ArticleCategoryService {
	private ArticleCategoryDAO dao;
	
	public ArticleCategoryService () {
		dao = new ArticleCategoryJDBCDAOIm();
	}
	
	public ArticleCategoryVO add(String articleCategory) {
		
		ArticleCategoryVO artVO =new ArticleCategoryVO();
		
		artVO.setArticleCategory(articleCategory);
		
		dao.insert(artVO);
		return artVO;
	}
	
	public ArticleCategoryVO upate (Byte categoryStatus) {
		ArticleCategoryVO artVO =new ArticleCategoryVO();
		
		artVO.setCategoryStatus(categoryStatus);;
		
		dao.update(artVO);
		return artVO;
		
	}
	
	public ArticleCategoryVO getOne(Integer articleCategoryNo) {
		return dao.findByPrimaryKey(articleCategoryNo);
	}
	
	public  List<ArticleCategoryVO>getAll(){
		return dao.getAll();
	}
//	=========================================
	
//	public ArticleCategoryVO add(String articleCategory) {
//		
//		ArticleCategoryVO artVO =new ArticleCategoryVO();
//		
//		artVO.setArticleCategory(articleCategory);
//		
//		dao.insert(artVO);
//		return artVO;
//	}
//	
//	public ArticleCategoryVO upate (Byte categoryStatus) {
//		ArticleCategoryVO artVO =new ArticleCategoryVO();
//		
//		artVO.setCategoryStatus(categoryStatus);;
//		
//		dao.update(artVO);
//		return artVO;
//		
//	}
//	
//	public ArticleCategoryVO getOne(Integer articleCategoryNo) {
//		return dao.findByPrimaryKey(articleCategoryNo);
//	}
//	
//	public  List<ArticleCategoryVO>getAll(){
//		return dao.getAll();
//	}
//	
//	public static void main (String[] args) {
//		
//	    ArticleCategoryService service = new ArticleCategoryService();
//
//	    // 调用 getAll 方法获取文章类别列表
//	    List<ArticleCategoryVO> categoryList = service.getAll();
//
//	    // 遍历并打印文章类别信息
//	    for (ArticleCategoryVO category : categoryList) {
//	        System.out.println("文章號碼 " + category.getArticleCategoryNo());
//	        System.out.println("類別名稱: " + category.getArticleCategory());
//	        System.out.println("文章狀態: " + category.getCategoryStatus());
//	        System.out.println("时间: " + category.getCreatedTimestamp());
//	        System.out.println();
//	    } 上面是用Hibernate 的service 但是getAll無法正常使用，待修正
	
	public static void main (String[] args) {
//		
	    ArticleCategoryService service = new ArticleCategoryService();

	    // 调用 getAll 方法获取文章类别列表
	    List<ArticleCategoryVO> categoryList = service.getAll();

	    // 遍历并打印文章类别信息
	    for (ArticleCategoryVO category : categoryList) {
	        System.out.println("文章號碼 " + category.getArticleCategoryNo());
	        System.out.println("類別名稱: " + category.getArticleCategory());
	        System.out.println("文章狀態: " + category.getCategoryStatus());
	        System.out.println("时间: " + category.getCreatedTimestamp());
	        System.out.println();
	
	
	    }
	}
	
}
