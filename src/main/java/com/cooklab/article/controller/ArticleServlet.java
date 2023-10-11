package com.cooklab.article.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.article.model.ArticleVO;
import com.cooklab.article.model.ArticleService;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;
@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		String quillContent = req.getParameter("quillContent");//我是quill文本編輯器
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("articleNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入文章編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/article/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer articleNo = null;
			try {
				articleNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("文章編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/article/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ArticleService artSvc = new ArticleService();
			ArticleVO artVO = artSvc.getOneArt(articleNo);
			if (artVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/article/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO", artVO); // 資料庫取出的empVO物件,存入req
			String url = "/article/listOneArt.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer articleNo = Integer.valueOf(req.getParameter("articleNo"));

			/*************************** 2.開始查詢資料 ****************************************/
			ArticleService artSvc = new ArticleService();
			ArticleVO artVO = artSvc.getOneArt(articleNo);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("artVO", artVO); // 資料庫取出的empVO物件,存入req
			String url = "/article/update_Art_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Art_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer articleNo = Integer.valueOf(req.getParameter("articleNo").trim());

			
			Integer articleCategory = null;
			String articleCategoryStr = req.getParameter("articleCategory");
			if (articleCategoryStr != null && !articleCategoryStr.trim().isEmpty()) {
				articleCategory = Integer.valueOf(articleCategoryStr.trim());
			} else {
				errorMsgs.add("文章分類輸入數字請勿空白");
			}
			

			String articleTitle = req.getParameter("articleTitle").trim();
			if (articleTitle == null || articleTitle.trim().length() == 0) {
				errorMsgs.add("標題請勿空白");
			}
//			Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());
//			if (memberId == null) {
//				errorMsgs.add("會員id: 請勿空白");
//			}
			//===========這邊是Integer  memberId轉換
			String memberIdStr = req.getParameter("memberId");
			Integer memberId = null;
			if (memberIdStr != null && !memberIdStr.trim().isEmpty()) {
				memberId = Integer.valueOf(memberIdStr.trim());
			} else {
				errorMsgs.add("會員id: 請勿空白或未提供");
			}
			
			//===========這邊是Byte  articleStatus轉換
			Byte articleStatus = null;
			String articleStatusStr = req.getParameter("articleStatus");
			if (articleStatusStr != null && !articleStatusStr.trim().isEmpty()) {
				try {
					articleStatus = Byte.valueOf(articleStatusStr.trim());
				} catch (NumberFormatException e) {
					errorMsgs.add(" 文章狀態請勿空白");

				}
			}

			String articleContent = req.getParameter("articleContent");
			if (articleContent == null || articleContent.trim().length() == 0) {
				errorMsgs.add("文章內容請勿空白");
			}
			
			//===========這邊是Integer  articleCount轉換
			
			Integer articleCount = null;
			String articleCountStr = req.getParameter("articleCount");
			if (articleCountStr != null && !articleCountStr.trim().isEmpty()) {
				articleCount = Integer.valueOf(articleCountStr.trim());
			} else {
				errorMsgs.add("回文數量請勿空白");
			}
			//===========這邊是Integer  viewCount轉換
			Integer viewCount = null;
			String viewCountParam = req.getParameter("viewCount");

			if (viewCountParam != null && !viewCountParam.trim().isEmpty()) {
			    try {
			        viewCount = Integer.parseInt(viewCountParam.trim());
			    } catch (NumberFormatException e) {
			        errorMsgs.add("點擊次數請填入數字");
			    }
			}

			
			ArticleVO artVO = new ArticleVO();

			artVO.setArticleCategory(articleCategory);
			artVO.setArticleTitle(articleTitle);
			artVO.setMemberId(memberId);
			artVO.setArticleStatus(articleStatus);
			artVO.setArticleContent(articleContent);
			artVO.setArticleCount(articleCount);
			artVO.setViewCount(viewCount);
			artVO.setArticleNo(articleNo);
			
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("artVO", artVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/article/update_Art_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ArticleService artSvc = new ArticleService();
			artVO = artSvc.updateArt(articleCategory, articleTitle, memberId, articleStatus, articleContent,
					articleCount, viewCount, articleNo);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			// 获取当前时间并格式化
			String formattedTimestamp = sdf.format(new Date());

			Timestamp lastEditTimestamp = Timestamp.valueOf(formattedTimestamp);
			artVO.setLastEditTimestamp(lastEditTimestamp);
			

			
			req.setAttribute("artVO", artVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/article/listOneArt.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		
if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String articleCategoryStr = req.getParameter("articleCategory");
			Integer articleCategory = null;
			
			if (articleCategoryStr != null && !articleCategoryStr.trim().isEmpty()) {
			    try {
			        articleCategory = Integer.valueOf(articleCategoryStr.trim());
			    } catch (NumberFormatException e) {
			        // 處理無法解析的情況，例如添加錯誤消息
			        errorMsgs.add("文章分類輸入數字以外無效");
			    }
			} else {
			    errorMsgs.add("文章分類輸入數字請勿空白");
			}

			String articleTitle = req.getParameter("articleTitle");
			if (articleTitle == null || articleTitle.trim().length() == 0) {
				errorMsgs.add("文章標題請勿空白");
			}
//			Integer memberId =Integer.valueOf(req.getParameter("member_id"));
//			if (memberId == null ) {
//					errorMsgs.add("會員id: 請勿空白");
//			}
			Integer memberId = null;
			try {
				memberId = Integer.valueOf(req.getParameter("memberId").trim());
			} catch (NumberFormatException e) {
			
				errorMsgs.add("員工編號請填數字.");
			}

//			String memberIdStr = req.getParameter("memberId");
//			Integer memberId = null;
//			if (memberIdStr != null && !memberIdStr.trim().isEmpty()) {
//				memberId = Integer.valueOf(memberIdStr.trim());
//			} else {
//				errorMsgs.add("會員id: 請勿空白或未提供");
//			}

//	Byte articleStatus = Byte.valueOf(req.getParameter("article_status"));	
//	if (articleStatus == null ) {
//		errorMsgs.add(" 請勿空白");
//	}

			String articleStatusStr = req.getParameter("articleStatus");
			Byte articleStatus = null;
			if (articleStatusStr != null && !articleStatusStr.trim().isEmpty()) {
				try {
					articleStatus = Byte.valueOf(articleStatusStr.trim());
				} catch (NumberFormatException e) {
					errorMsgs.add(" 請勿空白");

				}
			}

			String articleContent = req.getParameter("articleContent");
			if (articleContent == null || articleContent.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			
			String articleCountStr = req.getParameter("articleCount");
			Integer articleCount = null;
			if (articleCountStr != null && !articleCountStr.trim().isEmpty()) {
				articleCount = Integer.valueOf(articleCountStr.trim());
			} else {
				errorMsgs.add("請勿空白或未提供");
			}
			
			Integer viewCount = 0;
			String viewCountParam = req.getParameter("viewCount");

			if (viewCountParam != null && !viewCountParam.trim().isEmpty()) {
			    try {
			        viewCount = Integer.parseInt(viewCountParam.trim());
			    } catch (NumberFormatException e) {
			        errorMsgs.add("填入數字");
			    }
			}


			
//			Double sal = null;
//			try {
//sal = Double.valueOf(req.getParameter("sal").trim());
//			} catch (NumberFormatException e) {
//				sal = 0.0;
//				errorMsgs.add("薪水請填數字.");
//			}
			

			ArticleVO artVO = new ArticleVO();

			artVO.setArticleCategory(articleCategory);
			artVO.setArticleTitle(articleTitle);
			artVO.setMemberId(memberId);
			artVO.setArticleStatus(articleStatus);
			artVO.setArticleContent(articleContent);
			artVO.setArticleCount(articleCount);
			artVO.setViewCount(viewCount);
		

			// Send the use back to the form, if there were errors
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
req.setAttribute("artVO", artVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/addArt.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ArticleService artSvc = new ArticleService();
			artVO = artSvc.addArt(articleCategory, articleTitle, memberId, articleStatus, articleContent, articleCount,
					viewCount);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url ="/article/listAllArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		/*******************************   insert結束   **************************************/

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer articleNo = Integer.valueOf(req.getParameter("articleNo"));

			/*************************** 2.開始刪除資料 ***************************************/
			ArticleService artSvc = new ArticleService();
			artSvc.deleteArt(articleNo);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/article/listAllArticle.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}


}