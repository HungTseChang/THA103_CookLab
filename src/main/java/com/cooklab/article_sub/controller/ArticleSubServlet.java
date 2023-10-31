package com.cooklab.article_sub.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cooklab.article.model.ArticleService;
import com.cooklab.article.model.ArticleVO;
import com.cooklab.article_category.model.ArticleCategoryService;
import com.cooklab.article_category.model.ArticleCategoryVO;
import com.cooklab.article_sub.model.*;
import com.cooklab.article_report.model.*;

@WebServlet("/ArticleSubServlet")
public class ArticleSubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ArticleSubServlet() {
        super();

    }

	public  void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req,res);
	}
	public  void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
	    HttpSession session = req.getSession();                    
	    session.getAttribute("account");                    
	    session.getAttribute("membersVO");
	    session.getAttribute("userId" );
	    
	    
		req.setCharacterEncoding("UTF-8");
		String action =req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 後台文章管理的回文觀看

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("articleSubNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入文章編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/mazer-main/dist/article/HO_discussion_allview.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer articleSubNo = null;
			try {
				articleSubNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("文章編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/mazer-main/dist/article/HO_discussion_allview.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ArticleSubService artSvc = new ArticleSubService();
			ArticleSubVO artVO = artSvc.getOneSubArt(articleSubNo);
			if (artVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/mazer-main/dist/article/HO_discussion_allview.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO", artVO); // 資料庫取出的empVO物件,存入req
			String url = "frontstage/article/article_sub_edit2.jsp";
			
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}	
		
		if ("getStatusUpdate".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer articleSubNo = Integer.valueOf(req.getParameter("articleSubNo"));
			
			Byte articleSubStatus =null;
			try {
				String articleStatusStr = req.getParameter("articleSubStatus");
				articleSubStatus=Byte.valueOf(articleStatusStr);
			}catch (NumberFormatException e) {
					errorMsgs.add("選擇狀態.");
				}
	
			
			ArticleSubVO updatedArtVO = new ArticleSubVO();
			updatedArtVO.setArticleSubNo(articleSubNo);
			updatedArtVO.setArticleSubStatus(articleSubStatus);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("updatedArtVO", updatedArtVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/mazer-main/dist/article/HO_discussion_allview.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始修改資料 ****************************************/
			ArticleSubService artSvc = new ArticleSubService();
			artSvc.updateArticleStatus(articleSubNo, articleSubStatus);

			/*************************** 3.新增完成,準備轉交 ******************************/
			req.setAttribute("updatedArtVO", updatedArtVO); // 資料庫取出的empVO物件,存入req
			String url = "/mazer-main/dist/article/HO_discussion_allview.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Art_input.jsp
			successView.forward(req, res);
		}
		
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer articleSubNo = Integer.valueOf(req.getParameter("articleSubNo").trim());
			
			Integer memberId = null;
			try {
				memberId = Integer.valueOf(req.getParameter("memberId").trim());
			} catch (NumberFormatException e) {
			
				errorMsgs.add("員工編號請填數字.");
			}
			
			String articleSubContent = req.getParameter("articleSubContent");
			if (articleSubContent == null || articleSubContent.trim().length() == 0) {
				errorMsgs.add("內容請勿空白");
			}

			
			Byte articleSubStatus = null;
			String articleStatusStr = req.getParameter("articleSubStatus");
			if (articleStatusStr != null && !articleStatusStr.trim().isEmpty()) {
				try {
					articleSubStatus = Byte.valueOf(articleStatusStr.trim());
				} catch (NumberFormatException e) {
					errorMsgs.add(" 文章狀態請勿空白");

				}
			}
			
			ArticleSubVO artVO = new ArticleSubVO();
			artVO.setArticleNo(articleSubNo);
			artVO.setMemberId(memberId);
			artVO.setArticleSubStatus(articleSubStatus);
			artVO.setArticleSubContent(articleSubContent);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("artVO", artVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/mazer-main/dist/article/HO_dscussion_cate.jsp");
				failureView.forward(req, res);
				return;
			}

			

			/*************************** 2.開始修改資料 *****************************************/
			ArticleSubService artSvc = new ArticleSubService();
			artSvc.update(artVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO", artVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url ="frontstage/article/article_content.jsp";
			
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}

	
		if ("insert".equals(action)) { // article_content用於快速回覆

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer articleNo = Integer.valueOf(req.getParameter("articleNo").trim());
			
			Integer memberId = null;
			try {
				memberId = Integer.valueOf(req.getParameter("memberId").trim());
			} catch (NumberFormatException e) {
			
				errorMsgs.add("員工編號請填數字.");
			}
			
			String articleSubContent = req.getParameter("articleSubContent");
			if (articleSubContent == null || articleSubContent.trim().length() == 0) {
				errorMsgs.add("內容請勿空白");
			}			
			byte articleSubStatus = (byte)0;
			
			ArticleSubVO artVO = new ArticleSubVO();
			
			artVO.setArticleNo(articleNo);
			artVO.setMemberId(memberId);
			artVO.setArticleSubStatus(articleSubStatus);
			artVO.setArticleSubContent(articleSubContent);
			
//			ArticleVO artVONo = new ArticleVO();
//			artVONo.setArticleNo(articleNo);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("artVO", artVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_content.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ArticleSubService artSvc = new ArticleSubService();
			artSvc.add(artVO);
			/*************************** 2-2.開始查詢資料 ***************************************/
			ArticleService artSvc2 = new ArticleService();
			ArticleVO artVO2 = artSvc2.getOneArt(articleNo);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url ="frontstage/article/article_content.jsp";
			req.setAttribute("artVO", artVO2); //取得查詢用參數，資料庫取出的empVO物件,存入req
			//這邊req.setAttribute("arVO"這邊指的是傳到網頁的名稱,artVO2這邊是後端接收資料)
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}
		
		
		if ("insertReport".equals(action)) { // article_content用於快速回覆

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer articleNo = null;
			try {
				articleNo = Integer.valueOf(req.getParameter("articleNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("請填數字.");
			}
//			Integer articleNo = Integer.valueOf(req.getParameter("articleNo").trim());
			
			Integer reporterId = null;
			try {
				reporterId = Integer.valueOf(req.getParameter("reporterId").trim());
			} catch (NumberFormatException e) {
			
				errorMsgs.add("員工編號請填數字.");
			}
			
			String reportingReason = req.getParameter("reportingReason");
			if (reportingReason == null || reportingReason.trim().length() == 0) {
				errorMsgs.add("內容請勿空白");
			}
			
			
			byte reportingStatus = (byte)0;
			
			ArticleReportVO repVO = new ArticleReportVO();
			
			repVO.setArticleNo(articleNo);
			repVO.setReporterId(reporterId);
			repVO.setReportingReason(reportingReason);
			repVO.setReportingStatus(reportingStatus);
			
			//發生錯誤時，帶錯誤訊息去前端
			ArticleService artErrSvc = new ArticleService();
			ArticleVO artErr = artErrSvc.getOneArt(articleNo);
	
//			 Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("artErr", artErr); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_report_user.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ArticleReportService artSvc =  new ArticleReportService();
			artSvc.add(articleNo, reporterId, reportingReason, reportingStatus, null, null);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			req.setAttribute("artVO", artErr); 
			String url ="frontstage/article/article_main.jsp";
//			這邊req.setAttribute("arVO"這邊指的是傳到網頁的名稱,artVO2這邊是後端接收資料)
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}
		
		
		
		if ("insertReport2".equals(action)) { // article_content用於快速回覆

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer articleSubNo = null;
			try {
				articleSubNo = Integer.valueOf(req.getParameter("articleSubNo").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("請填數字.");
			}

			
			Integer reporterId = null;
			try {
				reporterId = Integer.valueOf(req.getParameter("reporterId").trim());
			} catch (NumberFormatException e) {
			
				errorMsgs.add("請填數字.");
			}
			
			String reportingReason = req.getParameter("reportingReason");
			if (reportingReason == null || reportingReason.trim().length() == 0) {
				errorMsgs.add("內容請勿空白");
			}
			
			
			byte reportingStatus = (byte)0;
			
			ArticleReportVO repVO = new ArticleReportVO();
			
			repVO.setArticleNo(articleSubNo);
			repVO.setReporterId(reporterId);
			repVO.setReportingReason(reportingReason);
			repVO.setReportingStatus(reportingStatus);
			
			//發生錯誤時，帶錯誤訊息去前端
			ArticleSubService artErrSvc2 = new ArticleSubService();
			ArticleSubVO artErr2 = artErrSvc2.getOneSubArt(articleSubNo);
	
//			 Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("artErr2", artErr2); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_report_user2.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ArticleReportService artSvc =  new ArticleReportService();
			artSvc.add(articleSubNo, reporterId, reportingReason, reportingStatus, null, null);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url ="frontstage/article/article_main.jsp";
			//這邊req.setAttribute("arVO"這邊指的是傳到網頁的名稱,artVO2這邊是後端接收資料)
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}
		
		
		if ("reportSearch".equals(action)) { //用於跳轉到檢舉表單先做查詢

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
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_content.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_content.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_content.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO", artVO); // 資料庫取出的empVO物件,存入req
			String url = "frontstage/article/article_report_user.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if ("reportSearch2".equals(action)) { //用於跳轉到檢舉表單先做查詢

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("articleSubNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入文章編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_content.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer articleSubNo = null;
			try {
				articleSubNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("文章編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_content.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ArticleSubService artSvc = new ArticleSubService();
			ArticleSubVO artVO2 = artSvc.getOneSubArt(articleSubNo);
			if (artVO2 == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_content.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO2", artVO2); // 資料庫取出的empVO物件,存入req
			String url = "frontstage/article/article_report_user2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		
		
		
		if ("subSearch".equals(action)) { //article_content.jsp用於主文回覆

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

			/*************************** 2.開始查詢資料 *****************************************/
			ArticleService artSvc = new ArticleService();
			ArticleVO artVO = artSvc.getOneArt(articleNo);
			if (artVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_content.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO", artVO); // 資料庫取出的empVO物件,存入req
			String url = "frontstage/article/article_sub_edit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		
		
		
		if ("subSearch2".equals(action)) { //article_content.jsp用於回文的回覆

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("articleSubNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入文章編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_main.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer articleSubNo = null;
			try {
				articleSubNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("文章編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_main.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ArticleSubService artSvc = new ArticleSubService();
			ArticleSubVO artVO2 = artSvc.getOneSubArt(articleSubNo);
			if (artVO2 == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("frontstage/article/article_content.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO2", artVO2); // 資料庫取出的empVO物件,存入req
			String url = "frontstage/article/article_sub_edit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
				
	}
	
	
	

}
