package com.cooklab.article_reaction.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.article_reaction.model.*;

@WebServlet("/ArticleReactionServlet")
public class ArticleReactionServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			Integer articleCategoryNo = Integer.valueOf(req.getParameter("articleNo").trim());

			String articleCategoryNoString = req.getParameter("articleCategoryNo");
			Integer articleCategoryNo =null;
			if (articleCategoryNoString != null && !articleCategoryNoString.isEmpty()) {
			    articleCategoryNo = Integer.parseInt(articleCategoryNoString);
		
			} else {
				errorMsgs.add("文章分類號碼錯誤");
			}
			
		
	

			// Send the use back to the form, if there were errors

			/*************************** 2.開始修改資料 *****************************************/
			ArticleCategoryService artSvc = new ArticleCategoryService();


			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO", artVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url ="/mazer-main/dist/article/HO_dscussion_cate.jsp";
			
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}

}
