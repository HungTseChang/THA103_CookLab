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
import com.cooklab.article_category.model.ArticleCategoryService;
import com.cooklab.article_category.model.ArticleCategoryVO;
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
		
//		String quillContent = req.getParameter("quillContent");//我是quill文本編輯器
		
		if ("getOne_For_Display".equals(action)) { 

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
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_main.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_main.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			/*************************** 2-1.開始查詢資料 *****************************************/
			ArticleService artSvc = new ArticleService();
			ArticleVO artVO = artSvc.getOneArt(articleNo);
			if (artVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_main.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			/*************************** 2-2.修改點擊數資料 *****************************************/
//			ArticleService artSvc2 = new ArticleService();
//			artSvc2.updateViewCount(articleNo, viewCount);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO", artVO); // 資料庫取出的empVO物件,存入req
			String url = "/frontstage/article/article_content.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Display2".equals(action)) { // 來自select_page.jsp的請求

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
				RequestDispatcher failureView = req.getRequestDispatcher("/dashboard/article/HO_discussion_allview.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/dashboard/article/HO_discussion_allview.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/dashboard/article/HO_discussion_allview.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO", artVO); // 資料庫取出的empVO物件,存入req
			String url = "frontstage/article/article_sub_edit.jsp";
			
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
			String url = "frontstage/article/update_Art_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Art_input.jsp
			successView.forward(req, res);
		}
if ("getViewCount".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer articleNo = Integer.valueOf(req.getParameter("articleNo"));
			
			Integer viewCount = Integer.valueOf(req.getParameter("viewCount"))+1;
			System.out.println(viewCount);
			ArticleVO u1 = new ArticleVO();
			u1.setArticleNo(articleNo); 
			u1.setViewCount(viewCount);
			/*************************** 2.開始修改資料 ****************************************/
			ArticleService artSvc = new ArticleService();
			artSvc.updateViewCount(articleNo, viewCount);
			/*************************** 2-2開始查詢 ****************************************/
			ArticleService artSvc2 = new ArticleService();
			ArticleVO artVO2 = artSvc2.getOneArt(articleNo);
			/*************************** 3.新增完成,準備轉交 ******************************/
			req.setAttribute("artVO", artVO2); // 資料庫取出的empVO物件,存入req
			String url ="frontstage/article/article_content.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
if ("getStatusUpdate".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer articleNo = Integer.valueOf(req.getParameter("articleNo"));
			
			Byte articleStatus =null;
			try {
				String articleStatusStr = req.getParameter("articleStatus");
				articleStatus=Byte.valueOf(articleStatusStr);
			}catch (NumberFormatException e) {
				errorMsgs.add("選擇狀態.");
			}
			ArticleVO updatedArtVO = new ArticleVO();
			updatedArtVO.setArticleNo(articleNo); 
			updatedArtVO.setArticleStatus(articleStatus) ;
//			ArticleVO updatedArtVO = new ArticleService().getOneArt(articleNo);
//			updatedArtVO.setArticleStatus(articleStatus) ;
//			new ArticleService().updateArt(updatedArtVO);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("updatedArtVO", updatedArtVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/dashboard/article/HO_discussion_allview.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			
			/*************************** 2.開始修改資料 ****************************************/
			ArticleService artSvc = new ArticleService();
			artSvc.updateArticleStatus(articleNo, articleStatus);

			/*************************** 3.新增完成,準備轉交 ******************************/
			req.setAttribute("updatedArtVO", updatedArtVO); // 資料庫取出的empVO物件,存入req
			String url = "/dashboard/article/HO_discussion_allview.jsp";
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

			
			Integer articleCategoryNo = null;
			String articleCategoryStr = req.getParameter("articleCategory");
			if (articleCategoryStr != null && !articleCategoryStr.trim().isEmpty()) {
				articleCategoryNo = Integer.valueOf(articleCategoryStr.trim());
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
			//=======================================================================
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

			artVO.setArticleCategoryNo(articleCategoryNo);
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
//			artVO = artSvc.updateArt( articleTitle, memberId, articleStatus, articleContent,
//					articleCount, viewCount, articleNo);
			artSvc.updateArt(artVO);
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
			Integer articleCategoryNo = null;
			if (articleCategoryStr != null && !articleCategoryStr.trim().isEmpty()) {
			    try {
			    	articleCategoryNo = Integer.valueOf(articleCategoryStr.trim());
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
			
				errorMsgs.add("會員編號請填數字.");
			}

			String articleStatusStr = req.getParameter("articleStatus");
			Byte articleStatus = null;
			if (articleStatusStr != null && !articleStatusStr.trim().isEmpty()) {
				try {
					articleStatus = Byte.valueOf(articleStatusStr.trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("文章狀態請勿空白");

				}
			}

			String articleContent = req.getParameter("articleContent");
			if (articleContent == null || articleContent.trim().length() == 0) {
				errorMsgs.add("內容請勿空白");
			}
			
			
			Integer articleCount = null;
			String articleCountStr = req.getParameter("articleCount");
			articleCount = Integer.valueOf(articleCountStr.trim());
			if (articleCountStr != null && !articleCountStr.trim().isEmpty()) {
				articleCount = Integer.valueOf(articleCountStr.trim());
			} else {
				errorMsgs.add("請勿空白或未提供");
			}
			
			Integer viewCount = 0;
			String viewCountParam = req.getParameter("viewCount");
			viewCount = Integer.parseInt(viewCountParam.trim());

			

			ArticleVO artVO = new ArticleVO();
			artVO.setArticleCategoryNo(articleCategoryNo);
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
						.getRequestDispatcher("frontstage/article/article_edit.jsp");
				failureView.forward(req, res);
				return;
			}
//			("/article/article_edit.jsp"); "/article/addArt.jsp"


			/*************************** 2.開始新增資料 ***************************************/
			ArticleService artSvc = new ArticleService();
//			artVO = artSvc.addArt(articleCategory, articleTitle, memberId, articleStatus, articleContent, articleCount,
//					viewCount);
			artSvc.addArt(artVO);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url ="frontstage/article/article_main.jsp";
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