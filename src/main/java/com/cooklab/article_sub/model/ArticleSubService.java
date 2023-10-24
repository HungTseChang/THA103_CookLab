package com.cooklab.article_sub.model;

import java.util.List;
import com.cooklab.article_sub.model.*;
public class ArticleSubService {
	
	private ArticleSubDAO dao;
	
	public ArticleSubService() {
		dao = new ArticleSubHDAO();
	}
	
	public void add (ArticleSubVO  articleSubVO) {
		dao.insert(articleSubVO);
	}
	
	public void update (ArticleSubVO  articleSubVO) {
		dao.update(articleSubVO);
	}
	
	public void delete (Integer articleSubNo) {
		dao.delete(articleSubNo);
	}
	
	public ArticleSubVO getOneSubArt (Integer articleSubNo) {
		return dao.findByPrimaryKey(articleSubNo);
	}
	
	public List<ArticleSubVO> getAll(){
		return dao.getAll();
	}
	
	
	
	 public static void main(String[] args) {
		    ArticleSubService articleSubService = new ArticleSubService();

		    // 指定要查询的 articleSubNo
		    Integer articleSubNo = 10; // 替换成你想要查询的主键值

		    // 调用 getOneSubArt 方法
		    ArticleSubVO result = articleSubService.getOneSubArt(articleSubNo);

		    // 验证结果是否符合预期
		    if (result != null) {
		        System.out.println("ArticleSubNo: " + result.getArticleSubNo());
		        System.out.println("ArticleSubContent: " + result.getArticleSubContent());
		        // 输出其他属性
		    } else {
		        System.out.println("ArticleSubVO not found.");
		    }
	       
	 }
}
