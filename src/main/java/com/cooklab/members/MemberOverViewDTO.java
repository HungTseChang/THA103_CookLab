package com.cooklab.members;

import java.util.Base64;

import com.cooklab.article.model.ArticleVO;
import com.cooklab.members.model.MembersVO;

public class MemberOverViewDTO {
	private String memberAccount;
	private String memberPicture;
	private String memberNickName;
	private String memberIntroduce;
	
	public MemberOverViewDTO(MembersVO membersVO){
		this.memberAccount=membersVO.getMemberAccount();
		this.memberPicture=Base64.getEncoder().encodeToString(membersVO.getMemberPicture());
		this.memberNickName=membersVO.getMemberNickname();
		this.memberIntroduce=membersVO.getMemberIntroduce();
	
	}

}
