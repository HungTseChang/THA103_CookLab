package com.cooklab.article_category.controller;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.article_category.model.ArticleCategoryService;
import com.cooklab.article_category.model.ArticleCategoryVO;

@WebServlet("/ArticleCategoryServlet")

public class ArticleCategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 ****************************************/
//			Integer articleCategoryNo = Integer.valueOf(req.getParameter("articleCategoryNo"));
//
//			/*************************** 2.開始查詢資料 ****************************************/
//			ArticleCategoryService artSvc = new ArticleCategoryService();
//			ArticleCategoryVO artVO = artSvc.get
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			req.setAttribute("artVO", artVO); // 資料庫取出的empVO物件,存入req
//			String url = "/mazer-main/dist/article/HO_dscussion_cate.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//			successView.forward(req, res);
//		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

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
			
		
			
			byte categoryStatus = (byte)1;
			
			ArticleCategoryVO artVO = new ArticleCategoryVO();
			
			artVO.setCategoryStatus(categoryStatus);
			artVO.setArticleCategoryNo(articleCategoryNo);
			// Send the use back to the form, if there were errors

			/*************************** 2.開始修改資料 *****************************************/
			ArticleCategoryService artSvc = new ArticleCategoryService();
			artSvc.updateArt(artVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artVO", artVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url ="/mazer-main/dist/article/HO_dscussion_cate.jsp";
			
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}

		
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String  articleCategory= req.getParameter("articleCategory");
			String  artrule= "^[(\u4e00-\u9fa5)]{2,8}$";
			if (articleCategory == null || articleCategory.trim().length() == 0) {
				errorMsgs.add("文章分類不能空白");
			}else if (!articleCategory.trim().matches(artrule)) {
				errorMsgs.add("文章分類只能是中文");
			}
			byte categoryStatus = (byte)0;
			ArticleCategoryVO artVO = new ArticleCategoryVO();
			
			artVO.setArticleCategory(articleCategory);
			artVO.setCategoryStatus(categoryStatus);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("artVO", artVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/mazer-main/dist/article/HO_dscussion_cate.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ArticleCategoryService artSvc = new ArticleCategoryService();
			artSvc.add(artVO);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url ="/mazer-main/dist/article/HO_dscussion_cate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}

		
	}

}
