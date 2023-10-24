package com.cooklab.recipe.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.cooklab.members.model.*;
import com.cooklab.recipe.model.RecipeService;
import com.cooklab.recipe.model.RecipeVO;
import com.google.gson.Gson;

@WebServlet("/RecipeServlet")
//@MultipartConfig
public class RecipeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		String action = req.getParameter("action");
//		HttpSession session = req.getSession();
//		MembersVO members = (MembersVO) session.getAttribute("members");
		MembersService membersSvc = new MembersService();// 測試
		MembersVO members = membersSvc.getOneMember(1); // 測試

		if ("insert".equals(action)) {

			/* ================================ 驗證格式 ================================ */
			// 食譜名稱
			String recipeName = req.getParameter("recipeName").trim();
			if (recipeName.equals("")) {
				res.getWriter().write(gson.toJson("請輸入食譜名稱"));
				return;
			}
			// 成品圖片
			byte[] coverImage;
			if (req.getParameter("coverImage") == null) {
				res.getWriter().write(gson.toJson("請放成品圖片"));
				return;
			} else {
				coverImage = Base64.getDecoder().decode(req.getParameter("coverImage"));
			}
			// 簡介
			String introduction = req.getParameter("introduction");
			if (introduction.equals("")) {
				res.getWriter().write(gson.toJson("請輸入簡介"));
				return;
			}
			// 補充說明
			String additionalExplanation = req.getParameter("additionalExplanation");
			// 地區
			String region = req.getParameter("region");
			// 食譜狀態
			Byte recipeStatus = Byte.valueOf(req.getParameter("recipeStatus"));
			// 食譜份量
			Byte recipeQuantity = Byte.valueOf(req.getParameter("recipeQuantity"));
			// 食材
			String[] ingredient = gson.fromJson(req.getParameter("ingredient"), String[].class);
			for (String string : ingredient) {
				if (string.equals("")) {
					res.getWriter().write(gson.toJson("請輸入食材"));
					return;
				}
			}
			// 食材份量
			String[] ingredientQuantity = gson.fromJson(req.getParameter("ingredientQuantity"), String[].class);
			for (String string : ingredientQuantity) {
				if (string.equals("")) {
					res.getWriter().write(gson.toJson("請輸入食材份量"));
					return;
				}
			}
			// 廚具
			String[] kitchenware = gson.fromJson(req.getParameter("kitchenware"), String[].class);
			for (String string : kitchenware) {
				if (string.equals("")) {
					res.getWriter().write(gson.toJson("請輸入廚具"));
					return;
				}
			}
			// 步驟時間
			String[] stepTime = gson.fromJson(req.getParameter("stepTime"), String[].class);
			for (String string : stepTime) {
				String regex = "^[1-9]\\d*$";
				if (!string.trim().matches(regex)) {
					res.getWriter().write(gson.toJson("步驟時間為空或格式錯誤"));
					return;
				}
			}
			// 步驟內容
			String[] stepContent = gson.fromJson(req.getParameter("stepContent"), String[].class);
			for (String string : stepContent) {
				if (string.equals("")) {
					res.getWriter().write(gson.toJson("請輸入步驟內容"));
					return;
				}
			}

			/* ================================ 新增食譜 ================================ */
			RecipeService reipceSvc = new RecipeService();
			RecipeVO recipeVO = reipceSvc.addRecipe(members, recipeName, coverImage, introduction,
					additionalExplanation, region, recipeStatus, recipeQuantity);
			/* ================================ 轉導到新增食材 ================================ */
			req.setAttribute("recipe", recipeVO);
			RequestDispatcher addRecipeIngredient = req.getRequestDispatcher("/RecipeIngredientServlet");
			addRecipeIngredient.forward(req, res);
			return;
		}
		if ("browse".equals(action)) {
			RecipeVO recipeVO = new RecipeService().getOneRecipe(Integer.valueOf(req.getParameter("recipeNo").trim()));
			Map<String, List<String>> mapRecipe =new HashMap<>();
			List<String>[] listRecipe = new ArrayList[10];
			
			
			listRecipe[0].add(recipeVO.getRecipeName());
			mapRecipe.put("rcipeName",listRecipe[0]);
			listRecipe[1].add(Base64.getEncoder().encodeToString(recipeVO.getCoverImage()));
			mapRecipe.put("coverImage",listRecipe[1]);
			
			
		}
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			String str = req.getParameter("recipe_no");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.add("請輸入食譜編號");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/recipe/select_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			Integer recipeNo = null;
//			try {
//				recipeNo = Integer.valueOf(str);
//			} catch (Exception e) {
//				errorMsgs.add("食譜編號格式不正確");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/recipe/select_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			/*************************** 2.開始查詢資料 *****************************************/
//			RecipeService recipeSvc = new RecipeService();
//			RecipeVO recipeVO = recipeSvc.getOneRecipe(recipeNo);
//			if (recipeVO == null) {
//				errorMsgs.add("查無資料");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/recipe/select_page.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("recipeVO", recipeVO); // 資料庫取出的empVO物件,存入req
//			String url = "/recipe/listOneRecipe.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
//		}
//
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 ****************************************/
//			Integer recipeNo = Integer.valueOf(req.getParameter("recipe_no"));
//
//			/*************************** 2.開始查詢資料 ****************************************/
//			RecipeService recipeSvc = new RecipeService();
//			RecipeVO recipeVO = recipeSvc.getOneRecipe(recipeNo);
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//			req.setAttribute("recipeVO", recipeVO); // 資料庫取出的empVO物件,存入req
//			String url = "/recipe/update_recipe_input.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//			successView.forward(req, res);
//		}
//
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			Integer recipeNo = Integer.valueOf(req.getParameter("recipe_no").trim());
//			MembersVO members = (MembersVO) session.getAttribute("members");
//			String recipeName = req.getParameter("member_id");
//			String recipeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
////			byte[] coverImage = req.getParameter("cover_image").trim().getBytes();
//			Part filePart = req.getPart("cover_image");
//			InputStream in = filePart.getInputStream();
//			byte[] coverImage;
//			System.out.println(filePart.getSize());
//			if (filePart.getSize() > 0)
//				coverImage = in.readAllBytes();
//			else
//				coverImage = null;
//
//			String introduction = req.getParameter("introduction");
//			String additionalExplanation = req.getParameter("additional_explanation");
//			String region = req.getParameter("region");
//			Byte recipeStatus = Byte.valueOf(req.getParameter("recipe_status"));
//			Integer reportCount = Integer.valueOf(req.getParameter("report_count"));
//			Integer viewCount = Integer.valueOf(req.getParameter("view_count"));
//			Byte recipeQuantity = Byte.valueOf(req.getParameter("recipe_quantity"));
//			Timestamp lastEditTimestamp = new Timestamp(new Date().getTime());
//
//			RecipeVO recipeVO = new RecipeVO();
//			recipeVO.setRecipeNo(recipeNo);
//			recipeVO.setMembers(members);
//			recipeVO.setRecipeName(recipeName);
//			recipeVO.setCoverImage(coverImage);
//			recipeVO.setIntroduction(introduction);
//			recipeVO.setAdditionalExplanation(additionalExplanation);
//			recipeVO.setRegion(region);
//			recipeVO.setRecipeStatus(recipeStatus);
//			recipeVO.setReportCount(reportCount);
//			recipeVO.setViewCount(viewCount);
//			recipeVO.setRecipeQuantity(recipeQuantity);
//			recipeVO.setLastEditTimestamp(lastEditTimestamp);
//			in.close();
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("recipeVO", recipeVO); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req.getRequestDispatcher("recipe/update_recipe_input.jsp");
//				failureView.forward(req, res);
//				return; // 程式中斷
//			}
//
//			/*************************** 2.開始修改資料 *****************************************/
//			RecipeService recipeSvc = new RecipeService();
//			recipeVO = recipeSvc.updateRecipe(recipeNo, memberId, recipeName, coverImage, introduction,
//					additionalExplanation, region, recipeStatus, reportCount, viewCount, recipeQuantity,
//					lastEditTimestamp);
//
//			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("recipeVO", recipeVO); // 資料庫update成功後,正確的的empVO物件,存入req
//			String url = "/recipe/listOneRecipe.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//			successView.forward(req, res);
//		}
//
//		
//
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 ***************************************/
//			Integer recipeNo = Integer.valueOf(req.getParameter("recipe_no"));
//
//			/*************************** 2.開始刪除資料 ***************************************/
//			RecipeService recipeSvc = new RecipeService();
//			recipeSvc.deleteRecipe(recipeNo);
//
//			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//			String url = "/recipe/listAllRecipe.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//			successView.forward(req, res);
//		}
	}

}
