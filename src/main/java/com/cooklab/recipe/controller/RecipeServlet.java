package com.cooklab.recipe.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.cooklab.recipe.model.RecipeService;
import com.cooklab.recipe.model.RecipeVO;

@WebServlet(urlPatterns = "/RecipeServlet")
@MultipartConfig
public class RecipeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("recipe_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入食譜編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/recipe/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer recipeNo = null;
			try {
				recipeNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("食譜編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/recipe/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			RecipeService recipeSvc = new RecipeService();
			RecipeVO recipeVO = recipeSvc.getOneRecipe(recipeNo);
			if (recipeVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/recipe/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("recipeVO", recipeVO); // 資料庫取出的empVO物件,存入req
			String url = "/recipe/listOneRecipe.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer recipeNo = Integer.valueOf(req.getParameter("recipe_no"));

			/*************************** 2.開始查詢資料 ****************************************/
			RecipeService recipeSvc = new RecipeService();
			RecipeVO recipeVO = recipeSvc.getOneRecipe(recipeNo);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("recipeVO", recipeVO); // 資料庫取出的empVO物件,存入req
			String url = "/recipe/update_recipe_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer recipeNo = Integer.valueOf(req.getParameter("recipe_no").trim());
			Integer memberId = Integer.valueOf(req.getParameter("member_id"));
			String recipeName = req.getParameter("recipe_name");
			String recipeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (recipeName == null || recipeName.trim().length() == 0) {
				errorMsgs.add("食譜名稱: 請勿空白");
			} else if (!recipeName.trim().matches(recipeNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("食譜名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
//			byte[] coverImage = req.getParameter("cover_image").trim().getBytes();
			Part filePart = req.getPart("cover_image");
			InputStream in = filePart.getInputStream();
			byte[] coverImage;
			System.out.println(filePart.getSize());
			if (filePart.getSize() > 0)
				coverImage = in.readAllBytes();
			else
				coverImage = null;

			String introduction = req.getParameter("introduction");
			String additionalExplanation = req.getParameter("additional_explanation");
			String region = req.getParameter("region");
			Byte recipeStatus = Byte.valueOf(req.getParameter("recipe_status"));
			Integer reportCount = Integer.valueOf(req.getParameter("report_count"));
			Integer viewCount = Integer.valueOf(req.getParameter("view_count"));
			Byte recipeQuantity = Byte.valueOf(req.getParameter("recipe_quantity"));
			Timestamp lastEditTimestamp = new Timestamp(new Date().getTime());

			RecipeVO recipeVO = new RecipeVO();
			recipeVO.setRecipeNo(recipeNo);
			recipeVO.setMemberId(memberId);
			recipeVO.setRecipeName(recipeName);
			recipeVO.setCoverImage(coverImage);
			recipeVO.setIntroduction(introduction);
			recipeVO.setAdditionalExplanation(additionalExplanation);
			recipeVO.setRegion(region);
			recipeVO.setRecipeStatus(recipeStatus);
			recipeVO.setReportCount(reportCount);
			recipeVO.setViewCount(viewCount);
			recipeVO.setRecipeQuantity(recipeQuantity);
			recipeVO.setLastEditTimestamp(lastEditTimestamp);
			in.close();
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("recipeVO", recipeVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("recipe/update_recipe_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			RecipeService recipeSvc = new RecipeService();
			recipeVO = recipeSvc.updateRecipe(recipeNo, memberId, recipeName, coverImage, introduction,
					additionalExplanation, region, recipeStatus, reportCount, viewCount, recipeQuantity,
					lastEditTimestamp);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("recipeVO", recipeVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/recipe/listOneRecipe.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer memberId = Integer.valueOf(req.getParameter("member_id"));
			String recipeName = req.getParameter("recipe_name");
			String recipeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (recipeName == null || recipeName.trim().length() == 0) {
				errorMsgs.add("食譜名稱: 請勿空白");
			} else if (!recipeName.trim().matches(recipeNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("食譜名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
//			byte[] coverImage = req.getParameter("cover_image").trim().getBytes();
			Part filePart = req.getPart("cover_image");
			InputStream in = filePart.getInputStream();
			byte[] coverImage = in.readAllBytes();
			in.close();
			String introduction = req.getParameter("introduction");
			String additionalExplanation = req.getParameter("additional_explanation");
			String region = req.getParameter("region");
			Byte recipeStatus = Byte.valueOf(req.getParameter("recipe_status"));
			Integer reportCount = Integer.valueOf(req.getParameter("report_count"));
			Integer viewCount = Integer.valueOf(req.getParameter("view_count"));
			Byte recipeQuantity = Byte.valueOf(req.getParameter("recipe_quantity"));
			Timestamp lastEditTimestamp = new Timestamp(new Date().getTime());
//			String job = req.getParameter("job").trim();
//			if (job == null || job.trim().length() == 0) {
//				errorMsgs.add("職位請勿空白");
//			}
//
//			java.sql.Date hiredate = null;
//			try {
//				hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//			} catch (IllegalArgumentException e) {
//				hiredate = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}
//
//			Double sal = null;
//			try {
//				sal = Double.valueOf(req.getParameter("sal").trim());
//			} catch (NumberFormatException e) {
//				sal = 0.0;
//				errorMsgs.add("薪水請填數字.");
//			}
//
//			Double comm = null;
//			try {
//				comm = Double.valueOf(req.getParameter("comm").trim());
//			} catch (NumberFormatException e) {
//				comm = 0.0;
//				errorMsgs.add("獎金請填數字.");
//			}
//
//			Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());

			RecipeVO recipeVO = new RecipeVO();
			recipeVO.setMemberId(memberId);
			recipeVO.setRecipeName(recipeName);
			recipeVO.setCoverImage(coverImage);
			recipeVO.setIntroduction(introduction);
			recipeVO.setAdditionalExplanation(additionalExplanation);
			recipeVO.setRegion(region);
			recipeVO.setRecipeStatus(recipeStatus);
			recipeVO.setReportCount(reportCount);
			recipeVO.setViewCount(viewCount);
			recipeVO.setRecipeQuantity(recipeQuantity);
			recipeVO.setLastEditTimestamp(lastEditTimestamp);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("recipeVO", recipeVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("recipe/addRecipe.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			RecipeService reipceSvc = new RecipeService();
			recipeVO = reipceSvc.addRecipe(memberId, recipeName, coverImage, introduction, additionalExplanation,
					region, recipeStatus, reportCount, viewCount, recipeQuantity, lastEditTimestamp);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/recipe/listAllRecipe.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer recipeNo = Integer.valueOf(req.getParameter("recipe_no"));

			/*************************** 2.開始刪除資料 ***************************************/
			RecipeService recipeSvc = new RecipeService();
			recipeSvc.deleteRecipe(recipeNo);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/recipe/listAllRecipe.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}

}
