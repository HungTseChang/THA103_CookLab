package com.cooklab.members;

import java.sql.Timestamp;

import com.cooklab.article.model.ArticleVO;


public class MemberArticleOverViewDTO {
	private Integer articleNo;
	private String articleCategory;
	private String articleTitle;
	private String articleStatus;
	private String memberNickname;
	
	public MemberArticleOverViewDTO(ArticleVO articleVO){
		this.articleNo=articleVO.getArticleNo();
		this.articleCategory=articleVO.getArticleCategory().getArticleCategory();
		this.articleTitle=articleVO.getArticleTitle();
		this.articleStatus=articleVO.getArticleStatusEnum();
		this.memberNickname = articleVO.getMembers().getMemberNickname();
	
	}
}
