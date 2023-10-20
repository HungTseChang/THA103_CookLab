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
}
