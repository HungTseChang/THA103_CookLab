package com.cooklab.article_report.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import idv.david.emp.entity.Emp;

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
		case "compositeQuery":
			forwardPath = null;
			break;
		default:
			forwardPath = "WCC_article_report.jsp";
	}
//		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

		
	}

	
	
	private String changeData(HttpServletRequest req, HttpServletResponse res) {
		int articleReportNo = Integer.valueOf(req.getParameter("articleReportNo"));
		 ArticleReportJDBCDAOIm arjm = new ArticleReportJDBCDAOIm();
		 ArticleReportVO ArticleReportVO = arjm.findByPrimaryKey(articleReportNo);
			req.setAttribute("ArticleReportVO", ArticleReportVO);


		
		return "/dashboard/memeber/WCC_article_report_info.jsp";
	}
}
