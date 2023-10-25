package com.cooklab.article_sub_report.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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
import com.cooklab.article_report.model.ArticleReportJDBCDAOIm;
import com.cooklab.article_report.model.ArticleReportService;
import com.cooklab.article_report.model.ArticleReportVO;
import com.cooklab.article_sub_report.model.*;
import com.cooklab.article_sub.model.*;

import com.google.gson.Gson;

import javax.servlet.http.*;

@WebServlet("/ArticleSubReportServlet")
public class ArticleSubReportServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	
private List<ArticleSubReportVO> thislist;


	public ArticleSubReportServlet() {
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
		case "getArticleSubReport":
			forwardPath = getArticleSubReport(req, res);
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


	private String getArticleSubReport(HttpServletRequest req, HttpServletResponse res) {
		ArticleSubReportService ahbdao = new ArticleSubReportService();
		 List<ArticleSubReportVO> list1=   ahbdao.getAll();
		 List<ArticleSubReportVOFake> list2 = new ArrayList<ArticleSubReportVOFake>();
		 for(int i = 0 ; i < list1.size();i++) {
			 ArticleSubReportVOFake ArticleReportVOFake = new ArticleSubReportVOFake(list1.get(i));
			 list2.add(ArticleReportVOFake);
		 }
		 String json = new Gson().toJson(list2);

this.thislist = list1;
		 req.setAttribute("json",json);


		
		return "/dashboard/article_sub_report/WCC_article_sub_report.jsp";
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




private class ArticleSubReportVOFake{
	
		private Integer articleSubReportNo;
	
		private Integer articleSubNo;
		
		private Integer articleNo;

		
		private String articleTitle;
	
		private Integer reporterId;
		private String  reporterAccount;
		private String reporterNickname;
		
		private String reportingReason;
	
		private Byte reportingStatus ;
	
	private String reportingAnswer;
	
	private Timestamp createdTimestamp;

	
		public ArticleSubReportVOFake(ArticleSubReportVO ArticleSubReportVO) {
		super();
		this.articleSubReportNo = ArticleSubReportVO.getArticleSubReportNo();
		this.articleSubNo = ArticleSubReportVO.getArticleSubNo();
		this.articleNo = ArticleSubReportVO.getArticleSub().getArticleNo();
		this.articleTitle = ArticleSubReportVO.getArticleSub().getArticle().getArticleTitle();
		this.reporterId = ArticleSubReportVO.getReporterId();
		this.reportingReason = ArticleSubReportVO.getReportingReason();
		this.reportingStatus = ArticleSubReportVO.getReportingStatus();
		this.reportingAnswer = ArticleSubReportVO.getReportingAnswer();
		this.reporterAccount = ArticleSubReportVO.getMembers().getMemberAccount();
		this.reporterNickname = ArticleSubReportVO.getMembers().getMemberNickname();
		this.createdTimestamp = ArticleSubReportVO.getCreatedTimestamp();
	}



		public Timestamp getCreatedTimestamp() {
			return createdTimestamp;
		}



		public void setCreatedTimestamp(Timestamp createdTimestamp) {
			this.createdTimestamp = createdTimestamp;
		}



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



		public Integer getArticleSubReportNo() {
			return articleSubReportNo;
		}



		public void setArticleSubReportNo(Integer articleSubReportNo) {
			this.articleSubReportNo = articleSubReportNo;
		}



		public Integer getArticleSubNo() {
			return articleSubNo;
		}



		public void setArticleSubNo(Integer articleSubNo) {
			this.articleSubNo = articleSubNo;
		}



		public Integer getArticleNo() {
			return articleNo;
		}



		public void setArticleNo(Integer articleNo) {
			this.articleNo = articleNo;
		}



		public String getArticleTitle() {
			return articleTitle;
		}



		public void setArticleTitle(String articleTitle) {
			this.articleTitle = articleTitle;
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



}
}
