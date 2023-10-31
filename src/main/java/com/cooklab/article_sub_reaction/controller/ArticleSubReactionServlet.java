package com.cooklab.article_sub_reaction.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.article.model.*;
import com.cooklab.article_sub_reaction.model.*;

@WebServlet("/ArticleSubReactionServlet")
public class ArticleSubReactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public ArticleSubReactionServlet() {
        super();
 
    }
	
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
			
			String articleSubNoStr = req.getParameter("articleSubNo");
			Integer articleSubNo =null;
			if (articleSubNoStr != null && !articleSubNoStr.isEmpty()) {
				articleSubNo = Integer.parseInt(articleSubNoStr);
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
			
			ArticleSubReactionVO r1 = new ArticleSubReactionVO();
			r1.setArticleSubNo(articleSubNo);
			r1.setMemberId(memberId);
			r1.setStatus(status);

			// Send the use back to the form, if there were errors

			/*************************** 2.開始修改資料 *****************************************/
			ArticleSubReactionService artSvc = new ArticleSubReactionService();
			artSvc.create(memberId, articleSubNo, status);
			System.out.println("回文的按讚資料新增或修改完成");
			
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			String success = "{\"message\": \"回文按讚新增或修改完成\"}";
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
			
			String articleSubNoStr = req.getParameter("articleSubNo");
			Integer articleSubNo =null;
			if (articleSubNoStr != null && !articleSubNoStr.isEmpty()) {
				articleSubNo = Integer.parseInt(articleSubNoStr);
			} else {
				System.out.println("文章號碼錯誤");
			}
			/*************************** 2.開始查詢資料 *****************************************/
			ArticleSubReactionService artSvc = new ArticleSubReactionService();
			artSvc.findTwo(memberId, articleSubNo);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("artVO", artVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url ="frontstage/article/article_content.jsp";
			
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
		
	}
	
	


}
