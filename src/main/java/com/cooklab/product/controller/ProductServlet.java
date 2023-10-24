package com.cooklab.product.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Base64;
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
import javax.servlet.http.Part;

import com.cooklab.ingredient_category.model.IngredientCategoryVO;
import com.cooklab.ingredient_category.model.IngredientService;
import com.cooklab.kitchenware_category.model.KitchenwareCategoryService;
import com.cooklab.kitchenware_category.model.KitchenwareCategoryVO;
import com.cooklab.product.model.ProductService;
import com.cooklab.product.model.ProductVO;
import com.google.gson.Gson;

@WebServlet("/ProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("search".equals(action)) {

			/***************************
			 * 2.開始查詢資料
			 *****************************************/
			ProductService productSvc = new ProductService();
			List<ProductVO> listproductVO = productSvc.getAll();

			// 创建一个列表来存储 HashMap
			List<Map<String, String>> dataMapList = new ArrayList<>();

//						 遍历 listalltags
			// 遍历 listingredientCategoryVO
			for (ProductVO item : listproductVO) {
				// 创建一个 HashMap 来存储当前项的数据
				Map<String, String> itemMap = new HashMap<>();

				// 获取数据并放入 HashMap
				String productNo = item.getProductNo().toString();
				itemMap.put("productNo", productNo);

				String productName = item.getProductName();
				itemMap.put("productName", productName);

				// 读取图像文件并编码为Base64字符串
				byte[] productPicture = item.getProductPicture();
				if (productPicture != null) {
					String productImage = Base64.getEncoder().encodeToString(productPicture);
					itemMap.put("productImage", productImage);
				} else {
					// 处理 productPicture 为 null 的情况，例如给出一个默认值或者其他操作
					itemMap.put("productImage", "");
				}

				String saleQty = item.getSaleQty().toString();
				itemMap.put("saleQty", saleQty);

				String productDec = item.getProductDec();
				itemMap.put("productDec", productDec);

				String productIntroduction = item.getProductIntroduction();
				itemMap.put("productIntroduction", productIntroduction);

				String productPrice = item.getProductPrice().toString();
				itemMap.put("productPrice", productPrice);

				if (item.getOffsaleTime() != null) {
					String offsaleTime = item.getOffsaleTime().toString();
					itemMap.put("offsaleTime", offsaleTime);
				} else {
					itemMap.put("offsaleTime", "無設定");
				}

				if (item.getShelfTime() != null) {
					String shelfTime = item.getShelfTime().toString();
					itemMap.put("shelfTime", shelfTime);
				} else {
					itemMap.put("shelfTime", "無設定");
				}

				if (item.getIngredientCategory() != null) {
					String ingredientCategory = item.getIngredientCategory().getCategoryName();
					itemMap.put("Category", ingredientCategory);
				}

				if (item.getKitchenwareCategory() != null) {
					String kitchenwareCategory = item.getKitchenwareCategory().getCategoryName();
					itemMap.put("Category", kitchenwareCategory);
				}
				// 将 HashMap 放入列表
				dataMapList.add(itemMap);
			}

			// 输出查询结果到控制台
			System.out.println(dataMapList);
//				        System.out.println(dataMapList);

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

		if ("insertOne".equals(action)) {

			// =============接收參數====================
			String productName = req.getParameter("productname");
			Integer productPrice = Integer.valueOf(req.getParameter("productprice"));
			Integer saleQty = Integer.valueOf(req.getParameter("saleQty"));
			Integer storageQty = Integer.valueOf(req.getParameter("storageQty"));

			java.sql.Timestamp shelfTime = null;
			String shelfTimeStr = req.getParameter("uptime");
			if (shelfTimeStr != null) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(shelfTimeStr, formatter);
					shelfTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					shelfTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {
				// 处理参数为 null 的情况，可以给出错误提示或执行适当的操作
			}

			java.sql.Timestamp offsaleTime = null;
			String offsaleTimeStr = req.getParameter("uptime");
			if (offsaleTimeStr != null) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(offsaleTimeStr, formatter);
					System.out.println(localDateTime);
					offsaleTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					offsaleTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {

			}

			String productIntroduction = req.getParameter("productIntroduction");
			String productDec = req.getParameter("productDescription");

			String category = req.getParameter("selectedFoodType");

			String selectedFoodType = req.getParameter("selectedFoodType");
			String selectedKitchenType = req.getParameter("selectedKitchenType");

			Integer ingredientCategoryNo = null;
			Integer kitchenwareCategoryNo = null;

			if (selectedFoodType != null && !selectedFoodType.isEmpty()) {
				try {
					ingredientCategoryNo = Integer.valueOf(selectedFoodType);
				} catch (NumberFormatException e) {
					// 处理无效的整数值
					ingredientCategoryNo = null; // 设置为0
				}
			}

			if (selectedKitchenType != null && !selectedKitchenType.isEmpty()) {
				try {
					kitchenwareCategoryNo = Integer.valueOf(selectedKitchenType);
				} catch (NumberFormatException e) {
					// 处理无效的整数值
					kitchenwareCategoryNo = null; // 设置为0
				}
			}

			Part filePart = req.getPart("productImage");
			InputStream fileContent = filePart.getInputStream();

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = fileContent.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();

//			System.out.println(imageBytes);
//			System.out.println(Category2);

//			***************************
//			 * 2.開始新增資料
//			 *****************************************/
			ProductService productSvc = new ProductService();
			ProductVO productVO = new ProductVO();
			productVO.setProductName(productName);
			productVO.setProductPrice(productPrice);
			productVO.setProductDec(productDec);
			productVO.setProductIntroduction(productIntroduction);
			productVO.setProductPicture(imageBytes);
			productVO.setSaleQty(saleQty);
			productVO.setStorageQty(storageQty);
			productVO.setOffsaleTime(offsaleTime);
			productVO.setShelfTime(shelfTime);

			if (category.equals("foodType")) {
				productVO.setIngredientCategoryNo(ingredientCategoryNo);
				productVO.setKitchenwareCategoryNo(kitchenwareCategoryNo);
			} else {
				productVO.setKitchenwareCategoryNo(kitchenwareCategoryNo);
				productVO.setIngredientCategoryNo(ingredientCategoryNo);
			}
			;

			String message = productSvc.insert(productVO);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			// 创建一个 Map 来存储消息和 productVO 数据
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("message", message);
			responseMap.put("productInfo", productVO);

			// 使用 Gson 将对象转换为 JSON 字符串
			Gson gson = new Gson();
			String productJson = gson.toJson(responseMap);

			// 设置响应的内容类型为 JSON
			res.setContentType("application/json");

			// 将 JSON 字符串写入响应输出流
			res.getWriter().write(productJson);

		}

		if ("getDetail".equals(action)) {

			Integer productNo = Integer.valueOf(req.getParameter("productNo").trim());
			/***************************
			 * 2.開始查詢資料
			 *****************************************/
			ProductService productSvc = new ProductService();
			ProductVO productVO = productSvc.getOneProduct(productNo);

			KitchenwareCategoryService KitchenwareSvc = new KitchenwareCategoryService();
			List<KitchenwareCategoryVO> KitchenwareCategoryVO = KitchenwareSvc.getAll();

			List<Map<String, String>> dataMapList = new ArrayList<>();
			for (KitchenwareCategoryVO item : KitchenwareCategoryVO) {
				Map<String, String> itemMap = new HashMap<>();

				String categoryNo = item.getKitchenwareCategoryNo().toString();
				String categoryName = item.getCategoryName();
				itemMap.put("kitchenwareCategoryNo", categoryNo);
				itemMap.put("categoryName", categoryName);
				itemMap.put("categoryTag", "廚具");

				// 将 HashMap 放入列表
				dataMapList.add(itemMap);
			}

			IngredientService IngredientSvc = new IngredientService();
			List<IngredientCategoryVO> listingredientCategoryVO = IngredientSvc.getAll();

			List<Map<String, String>> dataMapList2 = new ArrayList<>();
			for (IngredientCategoryVO item : listingredientCategoryVO) {

				Map<String, String> itemMap = new HashMap<>();

				String categoryNo = item.getIngredientCategoryNo().toString();
																			
				String categoryName = item.getCategoryName(); 
				itemMap.put("ingredientCategoryNo", categoryNo); 
				itemMap.put("categoryName", categoryName);
				itemMap.put("categoryTag", "食材");

				dataMapList2.add(itemMap);
			}

			Map<String, Object> productDetailMap = new HashMap<>();

			productDetailMap.put("productName", productVO.getProductName());
			productDetailMap.put("productPrice", productVO.getProductPrice());
			productDetailMap.put("saleQty", productVO.getSaleQty());
			productDetailMap.put("storageQty", productVO.getStorageQty());
			productDetailMap.put("shelfTime", productVO.getShelfTime());
			productDetailMap.put("offsaleTime", productVO.getOffsaleTime());
			productDetailMap.put("productIntroduction", productVO.getProductIntroduction());
			productDetailMap.put("productDescription", productVO.getProductDec());

			if (productVO.getIngredientCategory() != null) {
				productDetailMap.put("selectedPart", "foodType");
				productDetailMap.put("selectedFoodType", productVO.getIngredientCategoryNo());
			} else {
				productDetailMap.put("selectedPart", "kitchenType");
				productDetailMap.put("selectedKitchenType", productVO.getKitchenwareCategoryNo());
			}

			productDetailMap.put("foodTypeOptions", dataMapList2);
			productDetailMap.put("kitchenTypeOptions", dataMapList);
			// 2. 使用Gson将Map对象转换为JSON字符串
			Gson gson = new Gson();
			String productDetailJson = gson.toJson(productDetailMap);

			System.out.println(productDetailJson);
			// 3. 设置响应的内容类型为JSON
			res.setContentType("application/json; charset=UTF-8");

			// 将JSON字符串作为响应写入输出流
			res.getWriter().write(productDetailJson);

		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1接收請求參數
			 **********************/
			String str = req.getParameter("productNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入商品編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer productNo = null;
			try {
				productNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("商品編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 2.開始查詢資料
			 *****************************************/
			ProductService productSvc = new ProductService();
			ProductVO productVO = productSvc.getOneProduct(productNo);
			if (productVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/product/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("productVO", productVO); // 資料庫取出的productVO物件 存入req
			String url = "/product/listOneProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String productNoStr = req.getParameter("productNo");
			if (productNoStr != null && !productNoStr.isEmpty()) {
				try {
					Integer productNo = Integer.valueOf(productNoStr);
					// 接下來的處理
					ProductService productSvc = new ProductService();
					ProductVO productVO = productSvc.getOneProduct(productNo);

					req.setAttribute("productVO", productVO); // ��Ʈw���X��empVO����,�s�Jreq
					String url = "/mazer-main/dist/product/shopupdate.jsp";
					System.out.println("ProductNo: " + req.getParameter("productNo"));
					RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
					successView.forward(req, res);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
			 **********************/
			System.out.println("ProductNo: " + req.getParameter("productNo"));

			Integer productNo = Integer.valueOf(req.getParameter("productNo").trim());

			String productName = req.getParameter("productName");
			String productNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (productName == null || productName.trim().length() == 0) {
				errorMsgs.add("商品名稱: 請勿空白");
			} else if (!productName.trim().matches(productNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String productDec = req.getParameter("productDec").trim();
			if (productDec == null || productDec.trim().length() == 0) {
				errorMsgs.add("商品描述請勿空白");
			}

			String productIntroduction = req.getParameter("productIntroduction").trim();
			if (productIntroduction == null || productIntroduction.trim().length() == 0) {
				errorMsgs.add("商品簡介請勿空白");
			}

			Integer saleQty = null;
			try {
				saleQty = Integer.valueOf(req.getParameter("saleQty").trim());
			} catch (NumberFormatException e) {
				saleQty = 0;
				errorMsgs.add("商品數量請填數字");
			}

			Integer productPrice = null;
			try {
				productPrice = Integer.valueOf(req.getParameter("productPrice").trim());
			} catch (NumberFormatException e) {
				productPrice = 0;
				errorMsgs.add("商品價格請填數字");
			}

			java.sql.Timestamp offsaleTime = null;
			String offsaleTimeStr = req.getParameter("offsaleTime");
			if (offsaleTimeStr != null) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(offsaleTimeStr, formatter);
					System.out.println(localDateTime);
					offsaleTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					offsaleTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入有效日期時間!");
				}
			} else {
				// 处理参数为 null 的情况，可以给出错误提示或执行适当的操作
			}

			java.sql.Timestamp shelfTime = null;
			String shelfTimeStr = req.getParameter("shelfTime");
			if (shelfTimeStr != null) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(shelfTimeStr, formatter);
					shelfTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					shelfTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入有效日期時間!");
				}
			} else {
				// 处理参数为 null 的情况，可以给出错误提示或执行适当的操作
			}

			Integer storageQty = null;
			try {
				storageQty = Integer.valueOf(req.getParameter("storageQty").trim());
			} catch (NumberFormatException e) {
				storageQty = 0;
				errorMsgs.add("庫存請填數字");
			}

			Integer ingredientCategoryNo = null;
			try {
				ingredientCategoryNo = Integer.valueOf(req.getParameter("ingredientCategoryNo").trim());
			} catch (NumberFormatException e) {
				ingredientCategoryNo = 0;
				errorMsgs.add("食材編號請填數字");
			}

			Integer kitchenwareCategoryNo = null;
			try {
				kitchenwareCategoryNo = Integer.valueOf(req.getParameter("kitchenwareCategoryNo").trim());
			} catch (NumberFormatException e) {
				kitchenwareCategoryNo = 0;
				errorMsgs.add("廚具編號請填數字.");
			}

			Integer searchCount = null;
			String searchCountParam = req.getParameter("searchCount");

			if (searchCountParam != null) {
				try {
					searchCount = Integer.valueOf(searchCountParam.trim());
				} catch (NumberFormatException e) {
					searchCount = 0;
					errorMsgs.add("搜尋次數請填數字.");
				}
			} else {
				searchCount = 0; // 或者根据你的业务逻辑设置一个默认值
			}

			// 處理上傳圖片
			Part filePart = req.getPart("productPicture");
			byte[] buf;
			InputStream in = null; // 在条件之外初始化

			if (filePart.getSize() == 0) {
				ProductService productSvc = new ProductService();
				buf = productSvc.getOneProduct(productNo).getProductPicture();
				System.out.println(0);
			} else {
				in = filePart.getInputStream();
				buf = new byte[in.available()]; // 在这里初始化
				in.read(buf);
				in.close();
				System.out.println(1);
			}

			ProductVO productVO = new ProductVO();

			productVO.setProductNo(productNo);
			productVO.setProductName(productName);
			productVO.setProductDec(productDec);
			productVO.setProductIntroduction(productIntroduction);
			productVO.setProductPrice(productPrice);
			productVO.setOffsaleTime(offsaleTime);
			productVO.setShelfTime(shelfTime);
			productVO.setStorageQty(storageQty);
			productVO.setIngredientCategoryNo(ingredientCategoryNo);
			productVO.setKitchenwareCategoryNo(kitchenwareCategoryNo);
			productVO.setSearchCount(searchCount);
			productVO.setProductPicture(buf);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("productVO", productVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product_input.jsp");
				failureView.forward(req, res);
				return; // �{�����_
			}

			/***************************
			 * 2.�}�l�ק���
			 *****************************************/
			ProductService productSvc = new ProductService();
			productVO = productSvc.updateProduct(productNo, productName, saleQty, productDec, productIntroduction,
					productPrice, offsaleTime, shelfTime, storageQty, ingredientCategoryNo, kitchenwareCategoryNo,
					searchCount, buf);

			/***************************
			 * 3.�ק粒��,�ǳ����(Send the Success view)
			 *************/
			req.setAttribute("productVO", productVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
			String url = "/mazer-main/dist/product/shopview.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***********************
			 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
			 *************************/

			String productName = req.getParameter("productName");
			String productNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (productName == null || productName.trim().length() == 0) {
				errorMsgs.add("商品名稱: 請勿空白");
			} else if (!productName.trim().matches(productNameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String productDec = req.getParameter("productDec").trim();
			if (productDec == null || productDec.trim().length() == 0) {
				errorMsgs.add("商品描述請勿空白");
			}

			String productIntroduction = req.getParameter("productIntroduction").trim();
			System.out.println(productIntroduction);
			if (productIntroduction == null || productIntroduction.trim().length() == 0) {
				errorMsgs.add("商品簡介請勿空白");
			}

			Integer saleQty = null;
			try {
				saleQty = Integer.valueOf(req.getParameter("saleQty").trim());
			} catch (NumberFormatException e) {
				saleQty = 0;
				errorMsgs.add("商品數量請填數字");
			}

			Integer productPrice = null;
			try {
				productPrice = Integer.valueOf(req.getParameter("productPrice").trim());
			} catch (NumberFormatException e) {
				productPrice = 0;
				errorMsgs.add("商品價格請填數字");
			}

			java.sql.Timestamp offsaleTime = null;
			String offsaleTimeStr = req.getParameter("offsaleTime");
			if (offsaleTimeStr != null) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(offsaleTimeStr, formatter);
					System.out.println(localDateTime);
					offsaleTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					offsaleTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入有效日期時間!");
				}
			} else {
				// 处理参数为 null 的情况，可以给出错误提示或执行适当的操作
			}

			java.sql.Timestamp shelfTime = null;
			String shelfTimeStr = req.getParameter("shelfTime");
			if (shelfTimeStr != null) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(shelfTimeStr, formatter);
					shelfTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					shelfTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入有效日期時間!");
				}
			} else {
				// 处理参数为 null 的情况，可以给出错误提示或执行适当的操作
			}

			Integer storageQty = null;
			try {
				storageQty = Integer.valueOf(req.getParameter("storageQty").trim());
			} catch (NumberFormatException e) {
				storageQty = 0;
				errorMsgs.add("庫存請填數字");
			}

			Integer ingredientCategoryNo = null;
			try {
				ingredientCategoryNo = Integer.valueOf(req.getParameter("ingredientCategoryNo").trim());
			} catch (NumberFormatException e) {
				ingredientCategoryNo = 0;
				errorMsgs.add("食材編號請填數字");
			}

			Integer kitchenwareCategoryNo = null;
			try {
				kitchenwareCategoryNo = Integer.valueOf(req.getParameter("kitchenwareCategoryNo").trim());
			} catch (NumberFormatException e) {
				kitchenwareCategoryNo = 0;
				errorMsgs.add("廚具編號請填數字.");
			}

			Integer searchCount = null;
			String searchCountParam = req.getParameter("searchCount");

			if (searchCountParam != null) {
				try {
					searchCount = Integer.valueOf(searchCountParam.trim());
				} catch (NumberFormatException e) {
					searchCount = 0;
					errorMsgs.add("搜尋次數請填數字.");
				}
			} else {
				searchCount = 0; // 或者根据你的业务逻辑设置一个默认值
			}

			// 處理上傳圖片
			Part filePart = req.getPart("productPicture");
			InputStream in = filePart.getInputStream();
			byte[] buf = new byte[in.available()]; // byte[] buf = in.readAllBytes(); // Java 9 的新方法
			in.read(buf);
			in.close();

			ProductVO productVO = new ProductVO();

			productVO.setProductName(productName);
			productVO.setProductDec(productDec);
			productVO.setProductIntroduction(productIntroduction);
			productVO.setProductPrice(productPrice);
			productVO.setOffsaleTime(offsaleTime);
			productVO.setShelfTime(shelfTime);
			productVO.setStorageQty(storageQty);
			productVO.setIngredientCategoryNo(ingredientCategoryNo);
			productVO.setKitchenwareCategoryNo(kitchenwareCategoryNo);
			productVO.setSearchCount(searchCount);
			productVO.setProductPicture(buf);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("productVO", productVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/product/addProduct.jsp");
				failureView.forward(req, res);
				return;
			}

			/***************************
			 * 2.�}�l�s�W���
			 ***************************************/
			ProductService productSvc = new ProductService();
			productVO = productSvc.addProduct(productName, saleQty, productDec, productIntroduction, productPrice,
					offsaleTime, shelfTime, storageQty, ingredientCategoryNo, kitchenwareCategoryNo, buf);

			/***************************
			 * 3.�s�W����,�ǳ����(Send the Success view)
			 ***********/
			String url = "/mazer-main/dist/product/shopview.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1.�����ШD�Ѽ�
			 ***************************************/
			Integer productNo = Integer.valueOf(req.getParameter("productNo"));

			/***************************
			 * 2.�}�l�R�����
			 ***************************************/
			ProductService productSvc = new ProductService();
			productSvc.deleteProduct(productNo);

			/***************************
			 * 3.�R������,�ǳ����(Send the Success view)
			 ***********/
			String url = "/product/listAllProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
			successView.forward(req, res);
		}
	}

}
