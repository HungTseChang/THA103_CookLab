package com.cooklab.article_reaction.model;

import com.cooklab.util.HibernateUtil;

public class ArticleReactionService {
	
	
	private ArticleReactionDAO dao;
	
	public ArticleReactionService() {
		dao =new ArticleReactionHDAO(HibernateUtil.getSessionFactory());
	}
	
	public void update(ArticleReactionVO ArticleReactionVO) {
		dao.update(ArticleReactionVO);
	}
	//下面是一次用兩個欄位收尋
	public void findTwo (Integer memberId , Integer articleNo) {
		dao.findByTwoCol(memberId, articleNo);
	}
	
}
