package com.cooklab.recipe.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import java.util.Base64;
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
import com.cooklab.util.HibernateUtil;
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
			forwardPath = getOneForUpdate(req, res);
			break;
		case "update":
			forwardPath = update(req, res);
			return;
			
			
			
		default:
			forwardPath =  "/dashboard/recipe/WCC_recipe.jsp";
	}
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

		}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		 String response = "fail";
			Integer recipeNo =Integer.valueOf( req.getParameter("recipeNo"));
			Byte statusvalue = Byte.valueOf(req.getParameter("statusvalue"));	
			RecipeVO RecipeVO 	= this.thislist.stream().filter(e->e.getRecipeNo().equals(recipeNo)).findFirst().get();
			RecipeVO.setRecipeStatus(statusvalue);
			RecipeHDAOIm RSIdao = new RecipeHDAOIm(HibernateUtil.getSessionFactory());
			boolean answer=	RSIdao.update(RecipeVO);
			
			if(answer) {
				System.out.println("更新完成");
				 response="success";
			}else {
				System.out.println("更新失敗");
				 response="fails";

			}
			
			 try {
				 res.setCharacterEncoding("UTF-8");
				 res.setContentType("text/plain");
				 PrintWriter writer = res.getWriter();
				 System.out.println("傳送訊息給前端:"+response);
				 writer.write(response);
				 writer.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}

	private String geAllRecipe(HttpServletRequest req, HttpServletResponse res) {
		RecipeServiceIm RSIdao = new RecipeServiceIm();
		 List<RecipeVO> list1=   RSIdao.getAll();
		 List<RecipeVOFake> list2 = new ArrayList<RecipeVOFake>();
		 for(int i = 0 ; i < list1.size();i++) {
			 RecipeVOFake RecipeVOFake = new RecipeVOFake(list1.get(i));
			 list2.add(RecipeVOFake);
			 System.out.println("資料轉換中");
		 }
		 String json = new Gson().toJson(list2);

this.thislist = list1;
System.out.println(json);
		 req.setAttribute("json",json);


		
		return "/dashboard/recipe/WCC_recipe.jsp";
	}


	private String getOneForUpdate(HttpServletRequest req, HttpServletResponse res) {
		Integer recipeNo = Integer.valueOf(req.getParameter("recipeNo"));
		RecipeVO RecipeVO; 
		if(this.thislist == null) {
			RecipeServiceIm RSIdao = new RecipeServiceIm();
			RecipeVO = RSIdao.getOneRecipe(recipeNo);
		}else {
			Optional<RecipeVO> result;
			result =this.thislist.stream().filter(e->e.getRecipeNo().equals(recipeNo)).findFirst();
			RecipeVO =result.get();
		}
		RecipeVOFake RecipeVOFake = new RecipeVOFake(RecipeVO);
		
		if( RecipeVOFake.getCoverImage() != null) {
			byte[] imageBytes =  RecipeVOFake.getCoverImage(); 
			String base64Image = Base64.getEncoder().encodeToString(imageBytes);
			String picture = new Gson().toJson(base64Image);
			req.setAttribute("picture", picture);
			}
		
		
			req.setAttribute("RecipeVOFake", RecipeVOFake);
		return "/dashboard/recipe/WCC_recipe_info.jsp";
	}







private class RecipeVOFake implements java.io.Serializable{
		private Integer recipeNo; // 食譜編號(PK).
		private Integer memberID; // 會員編號(FK).
		private String  memberAccount; // 會員帳號(FK).
		private String  memberNickname; // 會員暱稱(FK).
		private String recipeName; // 食譜名稱.
		private Integer recipeReaction; // 點讚人數
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
			this.memberAccount = RecipeVO.getMembers().getMemberAccount().trim();
			this.memberNickname = RecipeVO.getMembers().getMemberNickname().trim();
			this.recipeName = RecipeVO.getRecipeName().trim();
			this.recipeReaction = RecipeVO.getReaction().size();
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
