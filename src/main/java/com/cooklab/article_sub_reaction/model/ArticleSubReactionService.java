package com.cooklab.article_sub_reaction.model;

import com.cooklab.util.HibernateUtil;

public class ArticleSubReactionService {

	private ArticleSubReactionDAO dao;

	public ArticleSubReactionService() {
		dao = new ArticleSubReactionHDAO(HibernateUtil.getSessionFactory());
	}

	public void create(Integer memberId, Integer articleSubNo, Byte status) {
		ArticleSubReactionVO exist = dao.findByTwoCol(memberId, articleSubNo);
		if (exist == null) {
			ArticleSubReactionVO newone = new ArticleSubReactionVO();
			newone.setArticleSubNo(articleSubNo);
			newone.setMemberId(memberId);
			newone.setStatus(status);

			dao.saveOrUpdate(newone);
		} else {
			exist.setStatus(status);

			dao.saveOrUpdate(exist);
		}
	}

	public ArticleSubReactionVO findTwo(Integer memberId, Integer articleSubNo) {
		System.out.println("啟動回文的findTwo方法");
		ArticleSubReactionVO vo = dao.findByTwoCol(memberId, articleSubNo);
		return vo;
	}

	public Long allCount(Integer articleSubNo, Byte status) {
		System.out.println("啟動回文的allCount方法");
		return dao.allCount(articleSubNo, status);
	}

	public ArticleSubReactionVO getOne(Integer articleSubReactionNo) {
		return dao.findByPrimaryKey(articleSubReactionNo);
	}

}
