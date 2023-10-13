package com.cooklab.article_report.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import com.cooklab.members.model.*;
import com.cooklab.article.model.*;
import com.google.gson.Gson;

import javax.servlet.http.*;

@WebServlet("/ArticleReportServlet")
public class ArticleReportServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;
       

    public ArticleReportServlet() {
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
		case "getArticleReport":
			forwardPath = getArticleReport(req, res);
			break;
		case "confirmArticleReport":
		   forwardPath =confirmArticleReport(req, res);
		default:
			forwardPath = "/dashboard/article_report/WCC_article_report.jsp";
	}
//		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

		}

	
	
	private String changeData(HttpServletRequest req, HttpServletResponse res) {
		int articleReportNo = Integer.valueOf(req.getParameter("articleReportNo"));
		ArticleReportHBDAO aehbdao = new ArticleReportHBDAO();
		 ArticleReportVO ArticleReportVO = aehbdao.findByPrimaryKey(articleReportNo);
			req.setAttribute("ArticleReportVO", ArticleReportVO);
			MembersVO MembersVO = ArticleReportVO.getArticleVO().getMembersVO();
			req.setAttribute("MembersVO", MembersVO);
			System.out.println(MembersVO);
		return "/dashboard/article_report/WCC_article_report_info.jsp";
	}


	private String getArticleReport(HttpServletRequest req, HttpServletResponse res) {
		ArticleReportJDBCDAOIm ahbdao = new ArticleReportJDBCDAOIm();
		 List<ArticleReportVO> list1=   ahbdao.getAll();
		 List<String> listtitle =new ArrayList<String>();
		 List<String> listnickname = new ArrayList<String>();
		 for(int i = 0 ; i < list1.size();i++) {
			 listtitle.add(list1.get(i).getArticleVO().getArticleTitle());
			 listnickname.add( list1.get(i).getMembersVO().getMemberNickname()  );
		 }
		 String json = new Gson().toJson(list1);
		 String title = new Gson().toJson(listtitle);
		 String nickname =new Gson().toJson(listnickname);
		 System.out.print(title);
		 System.out.print(nickname);

		 req.setAttribute("json",json);
		 req.setAttribute("title",title);
		 req.setAttribute("nickname",nickname);

		
		return "/dashboard/article_report/WCC_article_report.jsp";
	}
	


private String confirmArticleReport(HttpServletRequest req, HttpServletResponse res) {
	ArticleReportJDBCDAOIm ahbdao = new ArticleReportJDBCDAOIm();
	Integer articleReportNo = Integer.valueOf(req.getParameter("articleReportNo"));
	Byte reportingStatus = Byte.valueOf(req.getParameter("status"));
	String reportingAnswer = req.getParameter("reportingAnswer");
//	ArticleReportVO ArticleReportVO1 = new ArticleReportVO();
	 ArticleReportVO ArticleReportVO = ahbdao.findByPrimaryKey(articleReportNo);
//	 	 ArticleReportVO1.setArticleReportNo(articleReportNo);
//		ArticleReportVO1.setArticleNo(ArticleReportVO.getArticleNo());
//		ArticleReportVO1.setReporterId(ArticleReportVO.getReporterId());
//		ArticleReportVO1.setReportingReason(ArticleReportVO.getReportingReason());
		ArticleReportVO.setReportingStatus(reportingStatus);
		 ArticleReportVO.setReportingAnswer(reportingAnswer);
	 ahbdao.update(ArticleReportVO);

	
	return  "/dashboard/article_report/WCC_article_report_info.jsp";
}
}
