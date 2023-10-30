package com.cooklab.recipe.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;

import com.cooklab.members.model.*;
import com.cooklab.recipe.model.*;
import com.cooklab.recipe_collection.model.RecipeCollectionVO;
import com.cooklab.recipe_comments.model.RecipeCommentsVO;
import com.cooklab.recipe_hashtag.model.RecipeHashtagVO;
import com.cooklab.recipe_ingredient.model.RecipeIngredientVO;
import com.cooklab.recipe_kitchenware.model.RecipeKitchenwareVO;
import com.cooklab.recipe_reaction.model.RecipeReactionVO;
import com.cooklab.recipe_report.model.RecipeReportVO;
import com.cooklab.recipe_step.model.RecipeStepVO;
import com.cooklab.article.model.*;


import com.google.gson.Gson;

import javax.servlet.http.*;
@WebServlet("/DashboardRecipeServlet")
public class DashboardRecipeServlet  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
private List<RecipeVO> thislist;


	public DashboardRecipeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "geAllRecipe":
			forwardPath = geAllRecipe(req, res);
			break;
		case "changeData":
			forwardPath = changeData(req, res);
			break;
		case "confirmArticleSubReport":
		   forwardPath =confirmArticleSubReport(req, res);
		   break;
		default:
			forwardPath = "/dashboard/article_sub_report/WCC_article_sub_report.jsp";
	}
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

		}

	private String geAllRecipe(HttpServletRequest req, HttpServletResponse res) {
		RecipeServiceIm RSIdao = new RecipeServiceIm();
		 List<RecipeVO> list1=   RSIdao.getAll();
		 List<RecipeVOFake> list2 = new ArrayList<RecipeVOFake>();
		 for(int i = 0 ; i < list1.size();i++) {
			 RecipeVOFake RecipeVOFake = new RecipeVOFake(list1.get(i));
			 list2.add(RecipeVOFake);
		 }
		 String json = new Gson().toJson(list2);

this.thislist = list1;
		 req.setAttribute("json",json);


		
		return "/dashboard/recipe/WCC_recipe.jsp";
	}


	private String changeData(HttpServletRequest req, HttpServletResponse res) {
		Integer articleSubReportNo = Integer.valueOf(req.getParameter("articleSubReportNo"));
		ArticleSubReportVO ArticleSubReportVO; 
		if(this.thislist == null) {
		ArticleSubReportService aehbdao = new ArticleSubReportService();
		  ArticleSubReportVO = aehbdao.getOne(articleSubReportNo);
		}else {
			Optional<ArticleSubReportVO> result;
			result =this.thislist.stream().filter(e->e.getArticleSubReportNo().equals(articleSubReportNo)).findFirst();
			ArticleSubReportVO =result.get();
		}
			req.setAttribute("ArticleSubReportVO", ArticleSubReportVO);
			ArticleVO Article=ArticleSubReportVO.getArticleSub().getArticle();
			req.setAttribute("Article", Article);
			MembersVO MembersVO = ArticleSubReportVO.getArticleSub().getMembers();
			req.setAttribute("MembersVO", MembersVO);
		return "/dashboard/article_sub_report/WCC_article_sub_report_info.jsp";
	}



	


private String confirmArticleSubReport(HttpServletRequest req, HttpServletResponse res) {
	ArticleSubReportService ahbdao = new ArticleSubReportService();
	Integer articleSubReportNo = Integer.valueOf(req.getParameter("articleSubReportNo"));
	Byte reportingStatus = Byte.valueOf(req.getParameter("status"));
	String reportingAnswer = req.getParameter("reportingAnswer");
	ArticleSubReportVO ArticleSubReportVO;
	if(this.thislist == null) {
	  ArticleSubReportVO = ahbdao.getOne(articleSubReportNo);

	}else {
		Optional<ArticleSubReportVO> result;
		result =this.thislist.stream().filter(e->e.getArticleSubReportNo().equals(articleSubReportNo)).findFirst();
		ArticleSubReportVO =result.get();	
		}


		ArticleSubReportVO.setReportingStatus(reportingStatus);
		 ArticleSubReportVO.setReportingAnswer(reportingAnswer);
	 ahbdao.update(ArticleSubReportVO);

	
	return  "/dashboard/article_sub_report/WCC_article_sub_report.jsp";
}




private class RecipeVOFake implements java.io.Serializable{
		private Integer recipeNo; // 食譜編號(PK)
		private Integer memberID; // 會員編號(FK)
		private String  memberAccount; // 會員帳號(FK)
		private String  memberNickname; // 會員暱稱(FK)
		private String recipeName; // 食譜名稱
		private byte[] coverImage; // 食譜封面
		private Integer recipeReaction; // 點讚人數
		private String introduction; // 簡介
		private String additionalExplanation; // 補充說明
		private String region; // 地區
		private Byte recipeStatus; // 食譜狀態
		private Integer reportCount; // 檢舉次數
		private Integer viewCount; // 瀏覽人次
		private Byte recipeQuantity; // 食譜份量
		private Timestamp lastEditTimestamp; // 最後編輯時間
		private Timestamp createdTimestamp; // 建立時間
		
		
		
		
		public RecipeVOFake(RecipeVO RecipeVO) {
			super();
			this.recipeNo = RecipeVO.getRecipeNo();
			this.memberID = RecipeVO.getMembers().getMemberId();
			this.memberAccount = RecipeVO.getMembers().getMemberAccount();
			this.memberNickname = RecipeVO.getMembers().getMemberNickname();
			this.recipeName = RecipeVO.getRecipeName();
			this.coverImage = RecipeVO.getCoverImage();
			this.recipeReaction = RecipeVO.getReaction().size();
			this.introduction = RecipeVO.getIntroduction();
			this.additionalExplanation = RecipeVO.getAdditionalExplanation();
			this.region = RecipeVO.getRegion();
			this.recipeStatus = RecipeVO.getRecipeStatus();
			this.reportCount = RecipeVO.getReportCount();
			this.viewCount = RecipeVO.getViewCount();
			this.recipeQuantity = RecipeVO.getRecipeQuantity();
			this.lastEditTimestamp = RecipeVO.getLastEditTimestamp();
			this.createdTimestamp = RecipeVO.getCreatedTimestamp();
		}
		public String getMemberAccount() {
			return memberAccount;
		}
		public Integer getMemberID() {
			return memberID;
		}
		public void setMemberAccount(String memberAccount) {
			this.memberAccount = memberAccount;
		}
		public String getMemberNickname() {
			return memberNickname;
		}
		public void setMembersNickname(String memberNickname) {
			this.memberNickname = memberNickname;
		}
		public Integer getrecipeReaction() {
			return recipeReaction;
		}
		public void setrecipeReaction(Integer recipeReaction) {
			this.recipeReaction = recipeReaction;
		}
		public void setMemberID(Integer members) {
			this.memberID = members;
		}
		public Integer getRecipeNo() {
			return recipeNo;
		}
		public void setRecipeNo(Integer recipeNo) {
			this.recipeNo = recipeNo;
		}
		public String getRecipeName() {
			return recipeName;
		}
		public void setRecipeName(String recipeName) {
			this.recipeName = recipeName;
		}
		public byte[] getCoverImage() {
			return coverImage;
		}
		public void setCoverImage(byte[] coverImage) {
			this.coverImage = coverImage;
		}
		public String getIntroduction() {
			return introduction;
		}
		public void setIntroduction(String introduction) {
			this.introduction = introduction;
		}
		public String getAdditionalExplanation() {
			return additionalExplanation;
		}
		public void setAdditionalExplanation(String additionalExplanation) {
			this.additionalExplanation = additionalExplanation;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public Byte getRecipeStatus() {
			return recipeStatus;
		}
		public void setRecipeStatus(Byte recipeStatus) {
			this.recipeStatus = recipeStatus;
		}
		public Integer getReportCount() {
			return reportCount;
		}
		public void setReportCount(Integer reportCount) {
			this.reportCount = reportCount;
		}
		public Integer getViewCount() {
			return viewCount;
		}
		public void setViewCount(Integer viewCount) {
			this.viewCount = viewCount;
		}
		public Byte getRecipeQuantity() {
			return recipeQuantity;
		}
		public void setRecipeQuantity(Byte recipeQuantity) {
			this.recipeQuantity = recipeQuantity;
		}
		public Timestamp getLastEditTimestamp() {
			return lastEditTimestamp;
		}
		public void setLastEditTimestamp(Timestamp lastEditTimestamp) {
			this.lastEditTimestamp = lastEditTimestamp;
		}
		public Timestamp getCreatedTimestamp() {
			return createdTimestamp;
		}
		public void setCreatedTimestamp(Timestamp createdTimestamp) {
			this.createdTimestamp = createdTimestamp;
		}
		


}
}
