package com.cooklab.recipe_comments;

import java.sql.Timestamp;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe_comments.model.RecipeCommentsVO;

public class RecipeCommentsDTO {
	private Integer memberId;
	private String name;
	private String content;
	private Timestamp createdTime;

	public RecipeCommentsDTO() {
	}

	public RecipeCommentsDTO(RecipeCommentsVO recipeCommentsVO) {
		MembersVO membersVO = recipeCommentsVO.getMembers();
		this.memberId = membersVO.getMemberId();
		this.name = membersVO.getMemberNickname();
		this.content = recipeCommentsVO.getCommentContent();
		this.createdTime = recipeCommentsVO.getCreatedTimestamp();
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

}
