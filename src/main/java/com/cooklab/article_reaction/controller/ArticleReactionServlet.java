package com.cooklab.article_reaction.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.article_reaction.model.*;
import com.cooklab.article.model.*;


@WebServlet("/ArticleReactionServlet")
public class ArticleReactionServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("saveOrUpdate".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			Integer articleCategoryNo = Integer.valueOf(req.getParameter("articleNo").trim());

			String memberIdStr = req.getParameter("memberId");
			Integer memberId =null;
			if (memberIdStr != null && !memberIdStr.isEmpty()) {
				memberId = Integer.parseInt(memberIdStr);
			} else {
				System.out.println("會員號碼錯誤");
			}
			
			String articleNoStr = req.getParameter("articleNo");
			Integer articleNo =null;
			if (articleNoStr != null && !articleNoStr.isEmpty()) {
				articleNo = Integer.parseInt(articleNoStr);
			} else {
				System.out.println("文章號碼錯誤");
			}
			
			String statusStr = req.getParameter("status");
			Byte status =null;
			if (statusStr != null && !statusStr.isEmpty()) {
				status = Byte.parseByte(statusStr);
			} else {
				System.out.println("狀態號碼錯誤");
			}
			
			ArticleReactionVO r1 = new ArticleReactionVO();
			r1.setArticleNo(articleNo);
			r1.setMemberId(memberId);
			r1.setStatus(status);

			// Send the use back to the form, if there were errors

			/*************************** 2.開始修改資料 *****************************************/
			ArticleReactionService artSvc = new ArticleReactionService();
			artSvc.create(memberId, articleNo, status);
			System.out.println("按讚資料新增或修改完成");
			
			//我也忘了做搜尋要幹嘛.....哈哈哈
//			ArticleService artSvc2 = new ArticleService();
//			ArticleVO artVO = artSvc2.getOneArt(articleNo);
//			System.out.println("單一搜尋完成");
			
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("artVO", artVO); // 資料庫update成功後,正確的的empVO物件,存入req
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			String success = "{\"message\": \"新增或修改完成\"}";
			res.getWriter().write(success);
			
			//使用ajax 不能跳轉
//			String url ="frontstage/article/article_content.jsp";
//			
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
		}
		
		if ("findtwo".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			Integer articleCategoryNo = Integer.valueOf(req.getParameter("articleNo").trim());

			String memberIdStr = req.getParameter("memberId");
			Integer memberId =null;
			if (memberIdStr != null && !memberIdStr.isEmpty()) {
				memberId = Integer.parseInt(memberIdStr);
			} else {
				System.out.println("會員號碼錯誤");
			}
			
			String articleNoStr = req.getParameter("articleNo");
			Integer articleNo =null;
			if (articleNoStr != null && !articleNoStr.isEmpty()) {
				articleNo = Integer.parseInt(articleNoStr);
			} else {
				System.out.println("文章號碼錯誤");
			}			
//			ArticleSubReactionVO r1 = new ArticleSubReactionVO();
//			r1.setArticleNo(articleNo);
//			r1.setMemberId(memberId);
				

			/*************************** 2.開始查詢資料 *****************************************/
			ArticleReactionService artSvc = new ArticleReactionService();
			artSvc.findTwo(memberId, articleNo);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("artVO", artVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url ="frontstage/article/article_content.jsp";
			
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
		
	}
	
	


}
