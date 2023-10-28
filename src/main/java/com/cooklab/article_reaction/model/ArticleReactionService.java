package com.cooklab.article_reaction.model;

import com.cooklab.util.HibernateUtil;

public class ArticleReactionService {
	
	
	private ArticleReactionDAO dao;
	
	public ArticleReactionService() {
		dao =new ArticleReactionHDAO(HibernateUtil.getSessionFactory());
	}
	

	
	public void update(ArticleReactionVO ArticleReactionVO) {
		dao.saveOrUpdate(ArticleReactionVO);
	}
	//下方用來新增以及修改SQL
	public void create(Integer memberId ,Integer articleNo ,Byte status ) {
		ArticleReactionVO exist =dao.findByTwoCol(memberId,articleNo);
		
		if(exist == null) {
			ArticleReactionVO newone = new ArticleReactionVO();
			newone.setArticleNo(articleNo);
			newone.setMemberId(memberId);
			newone.setStatuts(status);
			
			dao.saveOrUpdate(newone);
		}else {
			exist.setStatuts(status);
			
			dao.saveOrUpdate(exist);
		}
	}
	
	//下面是一次用兩個欄位收尋
	public ArticleReactionVO findTwo (Integer memberId , Integer articleNo) {
		return dao.findByTwoCol(memberId, articleNo);
	}
	
	public Long allCount (Integer articleNo ,Byte statuts) {
		System.out.println("啟動方法");
		return dao.allCount(articleNo, statuts);
		
	}

	
	public ArticleReactionVO getOne (Integer articleReactionNo ) {
		return dao.findByPrimaryKey(articleReactionNo);
	}
}
