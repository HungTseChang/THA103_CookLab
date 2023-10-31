package com.cooklab.recipe_report.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;

import com.cooklab.members.model.*;
import com.cooklab.article.model.*;
import com.cooklab.recipe_report.model.*;
import com.cooklab.recipe.model.*;
import com.google.gson.Gson;

import javax.servlet.http.*;

@WebServlet("/RecipeReportServlet")
public class RecipeReportServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;
       private  List<RecipeReportVO> list1;

    public RecipeReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "changeData":
			forwardPath = changeData(req, res);
			break;
		case "getRecipeReport":
			forwardPath = getRecipeReport(req, res);
			break;
		case "confirmRecipeReport":
		   forwardPath =confirmRecipeReport(req, res);
		   break;
		default:
			forwardPath =  "/dashboard/recipe_report/WCC_recipe_report.jsp";
	}
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

		}




	private String changeData(HttpServletRequest req, HttpServletResponse res) {
		int recipeReportNo = Integer.valueOf(req.getParameter("recipeReportNo"));
		RecipeReportVO RecipeReportVO;
		if(this.list1==null) {
		RecipeReportService aehbdao = new RecipeReportService();
		 RecipeReportVO = aehbdao.getOne(recipeReportNo);
		}else {
	   RecipeReportVO	 =this.list1.stream().filter(e-> e.getRecipeReportNo().equals(recipeReportNo)).findFirst().get();
			
		}
//		RecipeReportVOFake RecipeReportVOFake = new RecipeReportVOFake(RecipeReportVO);
			req.setAttribute("RecipeReportVO", RecipeReportVO);
			MembersVO MembersVO = RecipeReportVO.getRecipe().getMembers();
			MembersVO.setMemberPicture(null);;
			req.setAttribute("MembersVO", MembersVO);
			System.out.println(MembersVO);
		return "/dashboard/recipe_report/WCC_recipe_report_info.jsp";
	}


	private String getRecipeReport(HttpServletRequest req, HttpServletResponse res) {
		RecipeReportService aehbdao = new RecipeReportService();
	       this.list1=null;
		 List<RecipeReportVO> list1=   aehbdao.getAll();
		 List<String> listtitle =new ArrayList<String>();
		 List<String> listnickname = new ArrayList<String>();
		 List<String> listaccount = new ArrayList<String>();
		 List<RecipeReportVOFake> list2 = new ArrayList<RecipeReportVOFake>();
		 for(int i = 0 ; i < list1.size();i++) {
			 RecipeReportVOFake RecipeReportVOFake = new RecipeReportVOFake(list1.get(i));
			 list2.add(RecipeReportVOFake);
			 listtitle.add(list1.get(i).getRecipe().getRecipeName());
			 listnickname.add( list1.get(i).getMembers().getMemberNickname()  );
			 listaccount.add( list1.get(i).getMembers().getMemberAccount()  );
		 }
		 String json = new Gson().toJson(list2);
		 String title = new Gson().toJson(listtitle);
		 String nickname =new Gson().toJson(listnickname);
		 String account =new Gson().toJson(listaccount);

		 req.setAttribute("json",json);
		 req.setAttribute("title",title);
		 req.setAttribute("nickname",nickname);
		 req.setAttribute("account",account);
       this.list1=list1;
		
		return "/dashboard/recipe_report/WCC_recipe_report.jsp";
	}
	


private String confirmRecipeReport(HttpServletRequest req, HttpServletResponse res) {
	int recipeReportNo = Integer.valueOf(req.getParameter("recipeReportNo"));
	Byte reportingStatus = Byte.valueOf(req.getParameter("status"));
	String reportingAnswer = req.getParameter("reportingAnswer");
	RecipeReportVO RecipeReportVO; 
	RecipeReportService aehbdao = new RecipeReportService();
if(this.list1==null) {
	 RecipeReportVO = aehbdao.getOne(recipeReportNo);
}else {
	   RecipeReportVO	 =this.list1.stream().filter(e-> e.getRecipeReportNo().equals(recipeReportNo)).findFirst().get();
}
	RecipeReportVO.setReportingStatus(reportingStatus);
	RecipeReportVO.setReportingAnswer(reportingAnswer);
	aehbdao.update(RecipeReportVO);

	
	return "/dashboard/recipe_report/WCC_recipe_report.jsp";
}



private class RecipeReportVOFake{
	
		private Integer recipeReportNo;
	
		private Integer recipeNo;
	
		private Integer reporterId;
		private String reporterAccount;
		private String reporterNickname;

		private String reportingReason;
		private String recipeName;
	

		private Byte reportingStatus ;
	
	private String reportingAnswer;
	
	private Timestamp credcreatedTimestamp; //建立時間

	
		private  RecipeReportVOFake(RecipeReportVO RecipeReportVO) {
		super();
		this.recipeReportNo = RecipeReportVO.getRecipeReportNo();
		this.recipeNo = RecipeReportVO.getRecipe().getRecipeNo();
		this.reporterId = RecipeReportVO.getMembers().getMemberId();
		this.reporterAccount=RecipeReportVO.getMembers().getMemberAccount();
		this.reporterNickname=RecipeReportVO.getMembers().getMemberNickname();
		this.recipeName=RecipeReportVO.getRecipe().getRecipeName();
		this.reportingReason = RecipeReportVO.getReportingReason();
		this.reportingStatus = RecipeReportVO.getReportingStatus();
		this.reportingAnswer = RecipeReportVO.getReportingAnswer();
		this.createdTimestamp = RecipeReportVO.getCredcreatedTimestamp();
	}

		

	public String getRecipeName() {
			return recipeName;
		}



		public void setRecipeName(String recipeName) {
			this.recipeName = recipeName;
		}



	public Integer getRecipeReportNo() {
			return recipeReportNo;
		}



		public void setRecipeReportNo(Integer recipeReportNo) {
			this.recipeReportNo = recipeReportNo;
		}



		public Integer getRecipeNo() {
			return recipeNo;
		}



		public void setRecipeNo(Integer recipeNo) {
			this.recipeNo = recipeNo;
		}



		public Timestamp getCredcreatedTimestamp() {
			return credcreatedTimestamp;
		}



		public void setCredcreatedTimestamp(Timestamp credcreatedTimestamp) {
			this.credcreatedTimestamp = credcreatedTimestamp;
		}



	public Integer getReporterId() {
		return reporterId;
	}

	public void setReporterId(Integer reporterId) {
		this.reporterId = reporterId;
	}

	public String getReportingReason() {
		return reportingReason;
	}

	public void setReportingReason(String reportingReason) {
		this.reportingReason = reportingReason;
	}

	public Byte getReportingStatus() {
		return reportingStatus;
	}

	public void setReportingStatus(Byte reportingStatus) {
		this.reportingStatus = reportingStatus;
	}

	public String getReportingAnswer() {
		return reportingAnswer;
	}

	public void setReportingAnswer(String reportingAnswer) {
		this.reportingAnswer = reportingAnswer;
	}

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

		private Timestamp createdTimestamp;
		public String getReporterAccount() {
			return reporterAccount;
		}



		public void setReporterAccount(String reporterAccount) {
			this.reporterAccount = reporterAccount;
		}



		public String getReporterNickname() {
			return reporterNickname;
		}



		public void setReporterNickname(String reporterNickname) {
			this.reporterNickname = reporterNickname;
		}
}







}
