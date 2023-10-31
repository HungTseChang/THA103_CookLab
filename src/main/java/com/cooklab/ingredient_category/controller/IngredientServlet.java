package com.cooklab.ingredient_category.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.ingredient_category.model.IngredientCategoryVO;
import com.cooklab.ingredient_category.model.IngredientService;
import com.cooklab.kitchenware_category.model.KitchenwareCategoryService;
import com.cooklab.kitchenware_category.model.KitchenwareCategoryVO;
import com.google.gson.Gson;

@WebServlet("/IngredientServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class IngredientServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("search".equals(action)) { //

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1接收請求參數
			 **********************/

			/***************************
			 * 2.開始查詢資料
			 *****************************************/
			IngredientService IngredientSvc = new IngredientService();

			List<Object[]> listalltags = IngredientSvc.getIngredientAndKitchenwareTags();
			if (listalltags == null) {
				errorMsgs.add("查無資料");
			}


			List<Map<String, String>> dataMapList = new ArrayList<>();
			for (Object[] item : listalltags) {
				// 创建一个 HashMap 来存储当前项的数据
				Map<String, String> itemMap = new HashMap<>();

				String categoryTag = (String) item[0];
				String categoryId = String.valueOf(item[1]); 
				String categoryName = (String) item[2];

				itemMap.put("categoryTag", categoryTag);
				itemMap.put("categoryId", categoryId);
				itemMap.put("categoryName", categoryName);

				// 将 HashMap 放入列表
				dataMapList.add(itemMap);
			}

			// 输出查询结果到控制台
//	        System.out.println("查询成功，找到 " + listingredientCategoryVO.size() + " 条记录。");
			System.out.println(dataMapList);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			// 将查询结果列表转换为JSON格式
			Gson gson = new Gson();
			String jsonData = gson.toJson(dataMapList);
			System.out.println(jsonData);

			// 将JSON数据写入响应
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonData);

		}

		if ("searchFoodTags".equals(action)) { //

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1接收請求參數
			 **********************/

			/***************************
			 * 2.開始查詢資料
			 *****************************************/
			IngredientService IngredientSvc = new IngredientService();
			List<IngredientCategoryVO> listingredientCategoryVO = IngredientSvc.getAll();
			if (listingredientCategoryVO == null) {
				errorMsgs.add("查無資料");
			}

			// 创建一个列表来存储 HashMap
			List<Map<String, String>> dataMapList = new ArrayList<>();

//			 遍历 listalltags
			// 遍历 listingredientCategoryVO
			for (IngredientCategoryVO item : listingredientCategoryVO) {
				// 创建一个 HashMap 来存储当前项的数据
				Map<String, String> itemMap = new HashMap<>();

				// 获取数据并放入 HashMap
				String categoryNo = item.getIngredientCategoryNo().toString(); // 假设您在 IngredientCategoryVO 中有一个名为
																				// getCategoryTag 的方法
				String categoryName = item.getCategoryName(); // 假设您在 IngredientCategoryVO 中有一个名为 getCategoryName 的方法
				itemMap.put("ingredientCategoryNo", categoryNo); // 使用 "ingredientCategoryNo" 作为键
				itemMap.put("categoryName", categoryName);
				itemMap.put("categoryTag", "食材");
				// 将 HashMap 放入列表
				dataMapList.add(itemMap);
			}

			// 输出查询结果到控制台
			System.out.println(dataMapList);
//	        System.out.println(dataMapList);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			// 将查询结果列表转换为JSON格式
			Gson gson = new Gson();
			String jsonData = gson.toJson(dataMapList);
			System.out.println(jsonData);

			// 将JSON数据写入响应
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonData);

		}

		if ("update".equals(action)) {
			 
			String updatedCategoryTag = req.getParameter("categoryTag");
			String updatedCategoryName = req.getParameter("categoryName");
			Integer categoryId = Integer.parseInt(req.getParameter("categoryId"));
			System.out.println(updatedCategoryTag);

			Map<String, String> responseData = new HashMap<>();

			IngredientService ingredientSvc = new IngredientService();
			IngredientCategoryVO ingredientCategory = new IngredientCategoryVO();
			
			ingredientCategory.setIngredientCategoryNo(categoryId);
			ingredientCategory.setCategoryName(updatedCategoryName);
			ingredientSvc.update(ingredientCategory);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			/// 创建响应数据，可以返回JSON响应以通知前端操作成功或失败

			boolean success = true; 
			responseData.put("success", success ? "true" : "false");
			responseData.put("message", success ? "更新成功" : "更新失败");
			
			String json = new Gson().toJson(responseData);
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);

		}

		if ("insert".equals(action)) {

			String categoryName = req.getParameter("categoryName");

			IngredientService ingredientSvc = new IngredientService();
			IngredientCategoryVO ingredientCategory = new IngredientCategoryVO();

			Map<String, String> responseData = new HashMap<>();

			// 先查詢
			ingredientCategory.setCategoryName(categoryName);
			boolean exist = (ingredientSvc.findByName(ingredientCategory)) != null;
			if (!exist) {
				ingredientSvc.insert(ingredientCategory);
				responseData.put("message", "true");
			} else {
				responseData.put("message", "false");
			}
			System.out.println(ingredientCategory);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			/// 创建响应数据，可以返回JSON响应以通知前端操作成功或失败
			String json = new Gson().toJson(responseData);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);

		}
		if ("delete".equals(action)) {
			Integer categoryId = Integer.parseInt(req.getParameter("categoryId"));

			IngredientService ingredientSvc = new IngredientService();
			IngredientCategoryVO ingredientCategory = new IngredientCategoryVO();
			ingredientCategory.setIngredientCategoryNo(categoryId);

			
			Map<String, String> responseData = new HashMap<>();
			
			String message = ingredientSvc.deleteCategory(ingredientCategory);

			if ("true".equals(message)) {
				responseData.put("message", "true");
			} else {
				responseData.put("message", "false");
			}
			String json = new Gson().toJson(responseData);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);
		}
		if ("checkName".equals(action)) {
			String categoryName = req.getParameter("categoryName");

			IngredientService ingredientSvc = new IngredientService();
			IngredientCategoryVO ingredientCategory = new IngredientCategoryVO();

			ingredientCategory.setCategoryName(categoryName);

			boolean nameExists = (ingredientSvc.findByName(ingredientCategory) != null);

			System.out.println(nameExists);

			Map<String, String> responseData = new HashMap<>();
			responseData.put("success", nameExists ? "false" : "true");
			responseData.put("message", nameExists ? "已有相同名稱" : "名稱可用");

			String json = new Gson().toJson(responseData);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);
		}
	}

}
