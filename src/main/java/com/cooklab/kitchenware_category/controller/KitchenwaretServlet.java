package com.cooklab.kitchenware_category.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.kitchenware_category.model.KitchenwareCategoryService;
import com.cooklab.kitchenware_category.model.KitchenwareCategoryVO;
import com.google.gson.Gson;

@WebServlet("/KitchenwaretServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class KitchenwaretServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("searchKitchenwareTags".equals(action)) { //

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
			KitchenwareCategoryService KitchenwareSvc = new KitchenwareCategoryService();
			List<KitchenwareCategoryVO> KitchenwareCategoryVO = KitchenwareSvc.getAll();
			if (KitchenwareCategoryVO == null) {
				errorMsgs.add("查無資料");
			}

			// 创建一个列表来存储 HashMap
			List<Map<String, String>> dataMapList = new ArrayList<>();

//			 遍历 listalltags
			// 遍历 listingredientCategoryVO
			for (KitchenwareCategoryVO item : KitchenwareCategoryVO) {
				// 创建一个 HashMap 来存储当前项的数据
				Map<String, String> itemMap = new HashMap<>();

				// 获取数据并放入 HashMap
				String categoryNo = item.getKitchenwareCategoryNo().toString(); // 假设您在 IngredientCategoryVO 中有一个名为
																				// getCategoryTag 的方法
				String categoryName = item.getCategoryName(); // 假设您在 IngredientCategoryVO 中有一个名为 getCategoryName 的方法
				itemMap.put("kitchenwareCategoryNo", categoryNo); // 使用 "ingredientCategoryNo" 作为键
				itemMap.put("categoryName", categoryName);
				itemMap.put("categoryTag", "廚具");

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
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1接收請求參數
			 **********************/
			// 从前端获取数据

			String updatedCategoryName = req.getParameter("categoryName");
			Integer categoryId = Integer.parseInt(req.getParameter("categoryId"));

			KitchenwareCategoryService kitchenwareSvc = new KitchenwareCategoryService();
			KitchenwareCategoryVO kitchenwareCategory = new KitchenwareCategoryVO();
			kitchenwareCategory.setKitchenwareCategoryNo(categoryId);
			kitchenwareCategory.setCategoryName(updatedCategoryName);
			kitchenwareSvc.update(kitchenwareCategory);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			/// 创建响应数据，可以返回JSON响应以通知前端操作成功或失败
			Map<String, String> responseData = new HashMap<>();
			boolean success = true; // 在操作成功时设置为true，在失败时设置为false
			responseData.put("success", success ? "true" : "false");
			responseData.put("message", success ? "更新成功" : "更新失败");
			String json = new Gson().toJson(responseData);
			System.out.println(kitchenwareCategory);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);

		}

		if ("insert".equals(action)) {

			String categoryName = req.getParameter("categoryName");

			KitchenwareCategoryService kitchenwareSvc = new KitchenwareCategoryService();
			KitchenwareCategoryVO kitchenwareCategory = new KitchenwareCategoryVO();
			kitchenwareCategory.setCategoryName(categoryName);
			kitchenwareSvc.insert(kitchenwareCategory);

			System.out.println(kitchenwareCategory);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			/// 创建响应数据，可以返回JSON响应以通知前端操作成功或失败
			Map<String, String> responseData = new HashMap<>();
			boolean success = true; // 在操作成功时设置为true，在失败时设置为false
			responseData.put("success", success ? "true" : "false");
			responseData.put("message", success ? "更新成功" : "更新失败");
			String json = new Gson().toJson(responseData);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);

		}
		
		if ("delete".equals(action)) {
		    Integer categoryId = Integer.parseInt(req.getParameter("categoryId"));
		    
		    KitchenwareCategoryService kitchenwareSvc = new KitchenwareCategoryService();
		    KitchenwareCategoryVO kitchenwareCategory = new KitchenwareCategoryVO();
		    kitchenwareCategory.setKitchenwareCategoryNo(categoryId);
		    
		    String message = kitchenwareSvc.deleteCategory(kitchenwareCategory);

		    if ("删除成功".equals(message)) {
		        // 删除成功，返回成功消息
		        System.out.println("删除成功");
		    } else {
		        // 删除失败，返回错误消息
		        System.out.println("删除失败：" + message);
		    }

		    // 创建响应数据，可以返回JSON响应以通知前端操作成功或失败
		    Map<String, String> responseData = new HashMap<>();
		    boolean success = "成功".equals(message); // 检查消息是否为成功
		    responseData.put("success", success ? "true" : "false");
		    responseData.put("message", message);
		    String json = new Gson().toJson(responseData);
		    res.setContentType("application/json");
		    res.setCharacterEncoding("UTF-8");
		    res.getWriter().write(json);
		}
		
		if ("checkName".equals(action)) {
			
			String categoryName = req.getParameter("categoryName");
		    
		    KitchenwareCategoryService kitchenwareSvc = new KitchenwareCategoryService();
		    KitchenwareCategoryVO kitchenwareCategory = new KitchenwareCategoryVO();
		    kitchenwareCategory.setCategoryName(categoryName);
		    
		    boolean nameExists = kitchenwareSvc.findByName(kitchenwareCategory) != null;
		  

		    // 创建响应数据，根据 `nameExists` 决定操作是否成功
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
