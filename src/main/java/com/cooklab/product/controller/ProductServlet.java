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
import java.util.Set;

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
import com.cooklab.product.model.Pair;
import com.cooklab.product.model.ProductService;
import com.cooklab.product.model.ProductVO;
import com.cooklab.util.JedisUtil;
import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

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

			List<Map<String, String>> dataMapList = new ArrayList<>();

			for (ProductVO item : listproductVO) {

				Map<String, String> itemMap = new HashMap<>();

				String productNo = item.getProductNo().toString();
				itemMap.put("productNo", productNo);

				String productName = item.getProductName();
				itemMap.put("productName", productName);

				byte[] productPicture = item.getProductPicture();
				if (productPicture != null) {
					String productImage = Base64.getEncoder().encodeToString(productPicture);
					itemMap.put("productImage", productImage);
				} else {
					itemMap.put("productImage", "");
				}

				String productDec = item.getProductDec();
				itemMap.put("productDec", productDec);

				String productIntroduction = item.getProductIntroduction();
				itemMap.put("productIntroduction", productIntroduction);

				String productPrice = item.getProductPrice().toString();
				itemMap.put("productPrice", productPrice);

				String storageQty = item.getStorageQty().toString();
				itemMap.put("storageQty", storageQty);

				Integer searchCount = item.getStorageQty();
				String searchCountString = (searchCount != null) ? searchCount.toString() : "0";
				itemMap.put("searchCount", searchCountString);

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
				// HashMap 放入列表
				dataMapList.add(itemMap);
			}
			System.out.println(dataMapList);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			Gson gson = new Gson();
			String jsonData = gson.toJson(dataMapList);
			System.out.println(jsonData);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonData);
		}

		if ("searchkeyword".equals(action)) {

			String keywords = req.getParameter("keywords");

			int page = Integer.parseInt(req.getParameter("page"));
			int pageSize = Integer.parseInt(req.getParameter("pageSize"));
			/***************************
			 * 2.開始查詢資料
			 *****************************************/

			ProductService productSvc = new ProductService();
			Pair<List<ProductVO>, Long> pair = productSvc.findByKeywordWithPagination(keywords,page,pageSize);
			
			Long totalProductCount = pair.getSecond();
			List<ProductVO> listproduct = pair.getFirst();
			for (ProductVO item : listproduct) {
				try {
					incrementProductSearchCount(item.getProductNo());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			List<Map<String, String>> dataMapList = new ArrayList<>();
			for (ProductVO item : listproduct) {
					Map<String, String> itemMap = new HashMap<>();
					itemMap.put("totalProductCount", String.valueOf(totalProductCount));
					itemMap.put("currentPage", String.valueOf(page));
					itemMap.put("pageSize", String.valueOf(pageSize));
					itemMap.put("products", String.valueOf(listproduct));

					String productNo = item.getProductNo().toString();
					itemMap.put("productNo", productNo);

					String productName = item.getProductName();
					itemMap.put("productName", productName);

					byte[] productPicture = item.getProductPicture();
					if (productPicture != null) {
						String productImage = Base64.getEncoder().encodeToString(productPicture);
						itemMap.put("productImage", productImage);
					} else {
						itemMap.put("productImage", "");
					}

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
						itemMap.put("Categorytype", "ingredientCategory");
					}

					if (item.getKitchenwareCategory() != null) {
						String kitchenwareCategory = item.getKitchenwareCategory().getCategoryName();
						itemMap.put("Category", kitchenwareCategory);
						itemMap.put("Categorytype", "kitchenwareCategory");
					}

					dataMapList.add(itemMap);
			}
			System.out.println(dataMapList);
			System.out.println(totalProductCount);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			Gson gson = new Gson();
			String jsonData = gson.toJson(dataMapList);
			System.out.println(jsonData);

			res.setContentType("application/json; charset :UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonData);
		}

		if ("Indexget".equals(action)) {
			ProductService productSvc = new ProductService();
			List<ProductVO> listproduct = productSvc.getAll();

			List<Map<String, String>> dataMapList = new ArrayList<>();

			Timestamp currentTime = new Timestamp(System.currentTimeMillis());

			for (ProductVO item : listproduct) {
				Timestamp shelfTimes = item.getShelfTime();
				Timestamp offsaleTimes = item.getOffsaleTime();
				if (shelfTimes == null || offsaleTimes == null
						|| (shelfTimes.before(currentTime) && currentTime.before(offsaleTimes))) {
					Map<String, String> itemMap = new HashMap<>();

					String productNo = item.getProductNo().toString();
					itemMap.put("productNo", productNo);

					String productName = item.getProductName();
					itemMap.put("productName", productName);

					byte[] productPicture = item.getProductPicture();
					if (productPicture != null) {
						String productImage = Base64.getEncoder().encodeToString(productPicture);
						itemMap.put("productImage", productImage);
					} else {
						itemMap.put("productImage", "");
					}

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
					dataMapList.add(itemMap);
				}
			}
			System.out.println(dataMapList);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			Gson gson = new Gson();
			String jsonData = gson.toJson(dataMapList);
			System.out.println(jsonData);

			res.setContentType("application/json; charset:UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonData);
		}

		if ("insertOne".equals(action)) {

			// =============接收參數====================

			// 利用MAP物件放入錯誤訊息
			Map<String, String> errorMsgs = new HashMap<String, String>();

			String productName = req.getParameter("productname");
			String productNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (productName == null || productName.trim().length() == 0) {
				errorMsgs.put("errProductName", "商品請勿空白");
			} else if (!productName.trim().matches(productNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("errProductName", "商品只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String productPriceParam = req.getParameter("productprice");
			Integer productPrice = null;
			if (productPriceParam != null && !productPriceParam.isEmpty()) {
				try {
					productPrice = Integer.valueOf(productPriceParam);
					if (productPrice <= 0) {
						errorMsgs.put("errProductPrice", "商品售價必須為正數且不為零");
					}
				} catch (NumberFormatException e) {
					errorMsgs.put("errProductPrice", "商品售價必須是有效的數字");
				}
			} else {
				errorMsgs.put("errProductPrice", "商品售價不能為空");
			}

			String storageQtyParam = req.getParameter("storageQty");
			Integer storageQty = null;
			if (storageQtyParam != null && !storageQtyParam.isEmpty()) {
				try {
					storageQty = Integer.valueOf(storageQtyParam);
					if (storageQty <= 0) {
						errorMsgs.put("errStorageQty", "庫存數量必須為正數且不為零");
					}
				} catch (NumberFormatException e) {
					errorMsgs.put("errStorageQty", "庫存數量必須是有效的數字");
				}
			} else {
				errorMsgs.put("errStorageQty", "庫存數量不能為空");
			}

			java.sql.Timestamp shelfTime = null;
			String shelfTimeStr = req.getParameter("uptime");
			if (shelfTimeStr != null && shelfTimeStr != "") {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(shelfTimeStr, formatter);
					shelfTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					errorMsgs.put("errShelfTime", "上架時間請勿空白");
					shelfTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {
				errorMsgs.put("errShelfTime", "上架時間請勿空白");
			}

			java.sql.Timestamp offsaleTime = null;
			String offsaleTimeStr = req.getParameter("downtime");
			if (offsaleTimeStr != null && offsaleTimeStr != "") {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(offsaleTimeStr, formatter);
					System.out.println(localDateTime);
					offsaleTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					errorMsgs.put("erroffsaleTime", "下架時間請勿空白");
					offsaleTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {
				errorMsgs.put("erroffsaleTime", "下架時間請勿空白");
			}

			String productIntroduction = req.getParameter("productIntroduction");
			if (productIntroduction == null || productIntroduction.trim().length() == 0) {
				errorMsgs.put("errIntro", "商品詳請請勿空白");
			}
			String productDec = req.getParameter("productDescription");
			if (productDec == null || productDec.trim().length() == 0) {
				errorMsgs.put("errProductDec", "商品簡介請勿空白");
			}

			String category = req.getParameter("selectedPart");
			if (category.equals("none")) {
				errorMsgs.put("errCategory", "請選擇商品類別");
			}

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
			byte[] imageBytes = null;
			if (filePart.getSize() == 0 || filePart.getSize() == 9) {
				errorMsgs.put("errProductImage", "請選擇圖片");
			} else {
				String header = filePart.getHeader("Content-Disposition");
				if (header != null) {
					String[] tokens = header.split(";");
					for (String token : tokens) {
						if (token.trim().startsWith("filename")) {
							String fileName = token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
							if (!fileName.matches("(?i).+\\.(jpg|png)")) {
								errorMsgs.put("errProductImage", "請選擇有效的JPG、PNG檔案");
							}
							InputStream fileContent = filePart.getInputStream();
							ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
							byte[] buffer = new byte[4096];
							int bytesRead;
							while ((bytesRead = fileContent.read(buffer)) != -1) {
								outputStream.write(buffer, 0, bytesRead);
							}
							imageBytes = outputStream.toByteArray();
							if (imageBytes.length == 0) {
								errorMsgs.put("errProductImage", "上傳的圖片檔案為空");
							}
						}
					}
				} else {
					errorMsgs.put("errProductImage", "請選擇圖片");
				}
			}

			// 錯誤驗證的訊息收集及回傳

			if (!errorMsgs.isEmpty()) {
				errorMsgs.put("message", "error");
				Gson gson = new Gson();
				String errjson = gson.toJson(errorMsgs);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errjson);
				return;
			}
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

			Map<String, Object> responseMap = new HashMap<>();
			if(message!= null) {
				responseMap.put("message", message);
			}else{
				responseMap.put("message", "false");
			}
			responseMap.put("productInfo", productVO);


			Gson gson = new Gson();
			String productJson = gson.toJson(responseMap);

			res.setContentType("application/json; charset=UTF-8");
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
			productDetailMap.put("storageQty", productVO.getStorageQty());
			productDetailMap.put("shelfTime", productVO.getShelfTime());
			productDetailMap.put("offsaleTime", productVO.getOffsaleTime());
			productDetailMap.put("productIntroduction", productVO.getProductIntroduction());
			productDetailMap.put("productDescription", productVO.getProductDec());

			Integer searchCount = productVO.getStorageQty();
			String searchCountString = (searchCount != null) ? searchCount.toString() : "0";
			productDetailMap.put("searchCount", searchCountString);

			if (productVO.getIngredientCategory() != null) {
				productDetailMap.put("selectedPart", "foodType");
				productDetailMap.put("selectedFoodType", productVO.getIngredientCategoryNo());
			} else {
				productDetailMap.put("selectedPart", "kitchenType");
				productDetailMap.put("selectedKitchenType", productVO.getKitchenwareCategoryNo());
			}

			byte[] productPicture = productVO.getProductPicture();
			if (productPicture != null) {
				String productImage = Base64.getEncoder().encodeToString(productPicture);
				productDetailMap.put("productImage", productImage);
			} else {

				productDetailMap.put("productImage", "");
			}
			productDetailMap.put("foodTypeOptions", dataMapList2);
			productDetailMap.put("kitchenTypeOptions", dataMapList);

			Gson gson = new Gson();
			String productDetailJson = gson.toJson(productDetailMap);

			System.out.println(productDetailJson);

			res.setContentType("application/json; charset=UTF-8");
			res.getWriter().write(productDetailJson);

		}
		if ("getDetail2".equals(action)) {

			Integer productNo = Integer.valueOf(req.getParameter("productNo").trim());
			System.out.println(productNo);
			/***************************
			 * 2.開始查詢資料
			 *****************************************/
			ProductService productSvc = new ProductService();
			ProductVO productVO = productSvc.getOneProduct(productNo);

			try {
				incrementProductSearchCount(productNo);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Map<String, Object> productDetailMap = new HashMap<>();

			productDetailMap.put("productName", productVO.getProductName());
			productDetailMap.put("productPrice", productVO.getProductPrice());
			productDetailMap.put("storageQty", productVO.getStorageQty());
			productDetailMap.put("shelfTime", productVO.getShelfTime());
			productDetailMap.put("offsaleTime", productVO.getOffsaleTime());
			productDetailMap.put("productIntroduction", productVO.getProductIntroduction());
			productDetailMap.put("productDescription", productVO.getProductDec());
			Integer searchCount = productVO.getStorageQty();
			String searchCountString = (searchCount != null) ? searchCount.toString() : "0";
			productDetailMap.put("searchCount", searchCountString);
			if (productVO.getIngredientCategory() != null) {
				productDetailMap.put("selectedPart", "foodType");
				productDetailMap.put("selectedFoodType", productVO.getIngredientCategoryNo());
			} else {
				productDetailMap.put("selectedPart", "kitchenType");
				productDetailMap.put("selectedKitchenType", productVO.getKitchenwareCategoryNo());
			}

			byte[] productPicture = productVO.getProductPicture();
			if (productPicture != null) {
				String productImage = Base64.getEncoder().encodeToString(productPicture);
				productDetailMap.put("productImage", productImage);
			} else {
				productDetailMap.put("productImage", "");
			}

			Gson gson = new Gson();
			String productDetailJson = gson.toJson(productDetailMap);

			System.out.println(productDetailJson);
			// 3. 设置响应的内容类型为JSON
			res.setContentType("application/json; charset=UTF-8");

			// 将JSON字符串作为响应写入输出流
			res.getWriter().write(productDetailJson);

		}
		if ("updateProduct".equals(action)) {

			// 利用MAP物件放入錯誤訊息
			Map<String, String> errorMsgs = new HashMap<String, String>();
			Integer productNo = Integer.valueOf(req.getParameter("productNo").trim());

			String productName = req.getParameter("productname");
			String productNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (productName == null || productName.trim().length() == 0) {
				errorMsgs.put("errProductName", "商品請勿空白");
			} else if (!productName.trim().matches(productNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("errProductName", "商品只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String productPriceParam = req.getParameter("productprice");
			Integer productPrice = null;
			if (productPriceParam != null && !productPriceParam.isEmpty()) {
				try {
					productPrice = Integer.valueOf(productPriceParam);
					if (productPrice <= 0) {
						errorMsgs.put("errProductPrice", "商品售價必須為正數且不為零");
					}
				} catch (NumberFormatException e) {
					errorMsgs.put("errProductPrice", "商品售價必須是有效的數字");
				}
			} else {
				errorMsgs.put("errProductPrice", "商品售價不能為空");
			}

			String storageQtyParam = req.getParameter("storageQty");
			Integer storageQty = null;
			if (storageQtyParam != null && !storageQtyParam.isEmpty()) {
				try {
					storageQty = Integer.valueOf(storageQtyParam);
					if (storageQty <= 0) {
						errorMsgs.put("errStorageQty", "庫存數量必須為正數且不為零");
					}
				} catch (NumberFormatException e) {
					errorMsgs.put("errStorageQty", "庫存數量必須是有效的數字");
				}
			} else {
				errorMsgs.put("errStorageQty", "庫存數量不能為空");
			}

			java.sql.Timestamp shelfTime = null;
			String shelfTimeStr = req.getParameter("uptime");
			if (shelfTimeStr != null && shelfTimeStr != "") {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(shelfTimeStr, formatter);
					shelfTime = Timestamp.valueOf(localDateTime);
					System.out.println(shelfTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					errorMsgs.put("errShelfTime", "上架時間請勿空白");
					shelfTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {
				errorMsgs.put("errShelfTime", "上架時間請勿空白");
			}

			java.sql.Timestamp offsaleTime = null;
			String offsaleTimeStr = req.getParameter("downtime");
			if (offsaleTimeStr != null && offsaleTimeStr != "") {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(offsaleTimeStr, formatter);
					System.out.println(localDateTime);
					offsaleTime = Timestamp.valueOf(localDateTime);
					System.out.println(offsaleTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					errorMsgs.put("erroffsaleTime", "下架時間請勿空白");
					offsaleTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {
				errorMsgs.put("erroffsaleTime", "下架時間請勿空白");
			}

			String productIntroduction = req.getParameter("productIntroduction");
			if (productIntroduction == null || productIntroduction.trim().length() == 0) {
				errorMsgs.put("errIntro", "商品詳請請勿空白");
			}
			String productDec = req.getParameter("productDescription");
			if (productDec == null || productDec.trim().length() == 0) {
				errorMsgs.put("errProductDec", "商品簡介請勿空白");
			}
			System.out.println(productDec);
			String category = req.getParameter("selectedPart");
			if (category.equals("none")) {
				errorMsgs.put("errCategory", "請選擇商品類別");
			}

			String selectedFoodType = req.getParameter("selectedFoodType");
			String selectedKitchenType = req.getParameter("selectedKitchenType");

			Integer ingredientCategoryNo = null;
			Integer kitchenwareCategoryNo = null;

			if (category.equals("foodType")) {
				try {
					ingredientCategoryNo = Integer.valueOf(selectedFoodType);
					kitchenwareCategoryNo = null;
				} catch (NumberFormatException e) {
					// 处理无效的整数值
					ingredientCategoryNo = null; // 设置为0
				}
			} else {
				kitchenwareCategoryNo = Integer.valueOf(selectedKitchenType);
				ingredientCategoryNo = null;
			}

			Part filePart = req.getPart("productImage");
			String fileName = filePart.getSubmittedFileName();
			byte[] imageBytes = null;

			if (filePart != null) {
				fileName = filePart.getSubmittedFileName();
				if (fileName != null && !fileName.isEmpty()) {
					// 处理新文件上传逻辑
					System.out.println("New file uploaded");
					String header = filePart.getHeader("Content-Disposition");
					if (header != null) {
						String[] tokens = header.split(";");
						boolean validFile = false;
						for (String token : tokens) {
							if (token.trim().startsWith("filename")) {
								String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
								if (fileExtension.equals("jpg") || fileExtension.equals("png")) {
									validFile = true;
									break;
								}
							}
						}
						if (!validFile) {
							errorMsgs.put("errProductImage", "請選擇有效的JPG、PNG檔案");
						} else {
							InputStream fileContent = filePart.getInputStream();
							ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
							byte[] buffer = new byte[4096];
							int bytesRead;
							while ((bytesRead = fileContent.read(buffer)) != -1) {
								outputStream.write(buffer, 0, bytesRead);
							}
							imageBytes = outputStream.toByteArray();
						}
					}
				} else {
					System.out.println("No new file uploaded");
					ProductService productSvc = new ProductService();
					imageBytes = productSvc.getOneProduct(productNo).getProductPicture();
					System.out.println("old picture");
				}
			}

			if (!errorMsgs.isEmpty()) {
				errorMsgs.put("message", "error");
				Gson gson = new Gson();
				String errjson = gson.toJson(errorMsgs);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errjson);
				return;
			}

//			***************************
//			 * 2.開始新增資料
//			 *****************************************/
			ProductService productSvc = new ProductService();
			ProductVO productVO = new ProductVO();
			productVO.setProductNo(productNo);
			productVO.setProductName(productName);
			productVO.setProductPrice(productPrice);
			productVO.setProductDec(productDec);
			productVO.setProductIntroduction(productIntroduction);
			productVO.setProductPicture(imageBytes);
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

			String message = productSvc.update(productVO);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			Map<String, Object> responseMap = new HashMap<>();
			if(message!=null) {
				responseMap.put("message", message);
			}else {
				responseMap.put("message", "false");
			}
			responseMap.put("productInfo", productVO);


			Gson gson = new Gson();
			String productJson = gson.toJson(responseMap);

			res.setContentType("application/json; charset=UTF-8");
			res.getWriter().write(productJson);
		}

		if ("getHotKeywords".equals(action)) {
			JedisPool jedisPool = JedisUtil.getJedisPool();
			Jedis jedis = jedisPool.getResource();
			List<Map<String, String>> hotKeywordsList = new ArrayList<>();
			try {
				jedis.select(3);
				Set<String> hotKeywordSet = jedis.smembers("daily_random_product_names");

				for (String keyword : hotKeywordSet) {
					Map<String, String> keywordMap = new HashMap<>();
					keywordMap.put("productName", keyword);
					keywordMap.put("keyword", keyword);
					hotKeywordsList.add(keywordMap);
				}
			} finally {
				jedis.close();
			}


			Gson gson = new Gson();
			String productJson = gson.toJson(hotKeywordsList);


			res.setContentType("application/json; charset=UTF-8");
			res.getWriter().write(productJson);

		}

		if ("catergorysearch".equals(action)) {
			String keywords = req.getParameter("keywords");
			System.out.println(keywords);
			int page = Integer.parseInt(req.getParameter("page"));
			int pageSize = Integer.parseInt(req.getParameter("pageSize"));
			int type = 0;
			/***************************
			 * 2.開始查詢資料
			 *****************************************/

			ProductService productSvc = new ProductService();
			List<ProductVO> totallistproduct;
			
			// 判斷種類
			if (keywords.equals("ingredient")) {
				type = 1;
			} else {
				type = 0;
			}

			Pair<List<ProductVO>, Long> pair = productSvc.findByCategoryKeywordWithPagination(type, page, pageSize);
			Long totalProductCount = pair.getSecond();
			List<ProductVO> listproduct = pair.getFirst();


			List<Map<String, String>> dataMapList = new ArrayList<>();
			for (ProductVO item : listproduct) {

				Map<String, String> itemMap = new HashMap<>();
				itemMap.put("totalProductCount", String.valueOf(totalProductCount));
				itemMap.put("currentPage", String.valueOf(page));
				itemMap.put("pageSize", String.valueOf(pageSize));
				itemMap.put("products", String.valueOf(listproduct));

				String productNo = item.getProductNo().toString();
				itemMap.put("productNo", productNo);

				String productName = item.getProductName();
				itemMap.put("productName", productName);

				byte[] productPicture = item.getProductPicture();
				if (productPicture != null) {
					String productImage = Base64.getEncoder().encodeToString(productPicture);
					itemMap.put("productImage", productImage);
				} else {
					itemMap.put("productImage", "");
				}

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
					itemMap.put("Categorytype", "ingredientCategory");
				}

				if (item.getKitchenwareCategory() != null) {
					String kitchenwareCategory = item.getKitchenwareCategory().getCategoryName();
					itemMap.put("Category", kitchenwareCategory);
					itemMap.put("Categorytype", "kitchenwareCategory");
				}

				dataMapList.add(itemMap);
			}

			System.out.println(dataMapList);
			System.out.println(totalProductCount);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			Gson gson = new Gson();
			String jsonData = gson.toJson(dataMapList);
			System.out.println(jsonData);

			res.setContentType("application/json; charset :UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonData);
		}

		if ("mutilseletingredient".equals(action)) {
			String keywords = req.getParameter("keywords");

			int page = Integer.parseInt(req.getParameter("page"));
			int pageSize = Integer.parseInt(req.getParameter("pageSize"));
			/***************************
			 * 2.開始查詢資料
			 *****************************************/

			ProductService productSvc = new ProductService();
			Pair<List<ProductVO>, Long> pair = productSvc
					.findByKeywordWithCategorywithingredientCategoryPagination(keywords, page, pageSize);

			Long totalProductCount = pair.getSecond();
			List<ProductVO> listproduct = pair.getFirst();

			List<Map<String, String>> dataMapList = new ArrayList<>();
			for (ProductVO item : listproduct) {

				Map<String, String> itemMap = new HashMap<>();
				itemMap.put("totalProductCount", String.valueOf(totalProductCount));
				itemMap.put("currentPage", String.valueOf(page));
				itemMap.put("pageSize", String.valueOf(pageSize));
				itemMap.put("products", String.valueOf(listproduct));

				String productNo = item.getProductNo().toString();
				itemMap.put("productNo", productNo);

				String productName = item.getProductName();
				itemMap.put("productName", productName);

				byte[] productPicture = item.getProductPicture();
				if (productPicture != null) {
					String productImage = Base64.getEncoder().encodeToString(productPicture);
					itemMap.put("productImage", productImage);
				} else {
					itemMap.put("productImage", "");
				}

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
					itemMap.put("Categorytype", "ingredientCategory");
				}

				if (item.getKitchenwareCategory() != null) {
					String kitchenwareCategory = item.getKitchenwareCategory().getCategoryName();
					itemMap.put("Category", kitchenwareCategory);
					itemMap.put("Categorytype", "kitchenwareCategory");
				}
				dataMapList.add(itemMap);
			}

			System.out.println(dataMapList);
			System.out.println(totalProductCount);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			Gson gson = new Gson();
			String jsonData = gson.toJson(dataMapList);
			System.out.println(jsonData);

			res.setContentType("application/json; charset :UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonData);
		}

		if ("mutilseletkitchenware".equals(action)) {
			String keywords = req.getParameter("keywords");

			int page = Integer.parseInt(req.getParameter("page"));
			int pageSize = Integer.parseInt(req.getParameter("pageSize"));
			/***************************
			 * 2.開始查詢資料
			 *****************************************/
			ProductService productSvc = new ProductService();
			Pair<List<ProductVO>, Long> pair = productSvc.findByKeywordWithCategorywithkitchCategoryPagination(keywords,
					page, pageSize);

			Long totalProductCount = pair.getSecond();
			List<ProductVO> listproduct = pair.getFirst();

			System.out.println(totalProductCount);

			List<Map<String, String>> dataMapList = new ArrayList<>();
			for (ProductVO item : listproduct) {

				Map<String, String> itemMap = new HashMap<>();
				itemMap.put("totalProductCount", String.valueOf(totalProductCount));
				itemMap.put("currentPage", String.valueOf(page));
				itemMap.put("pageSize", String.valueOf(pageSize));
				itemMap.put("products", String.valueOf(listproduct));

				String productNo = item.getProductNo().toString();
				itemMap.put("productNo", productNo);

				String productName = item.getProductName();
				itemMap.put("productName", productName);

				byte[] productPicture = item.getProductPicture();
				if (productPicture != null) {
					String productImage = Base64.getEncoder().encodeToString(productPicture);
					itemMap.put("productImage", productImage);
				} else {

					itemMap.put("productImage", "");
				}

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
					itemMap.put("Categorytype", "ingredientCategory");
				}

				if (item.getKitchenwareCategory() != null) {
					String kitchenwareCategory = item.getKitchenwareCategory().getCategoryName();
					itemMap.put("Category", kitchenwareCategory);
					itemMap.put("Categorytype", "kitchenwareCategory");
				}
				dataMapList.add(itemMap);
			}
			System.out.println(dataMapList);
			System.out.println(totalProductCount);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			Gson gson = new Gson();
			String jsonData = gson.toJson(dataMapList);
			System.out.println(jsonData);

			res.setContentType("application/json; charset :UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonData);
		}

		if ("hotProduct".equals(action)) {
			int page = Integer.parseInt(req.getParameter("page"));
			int pageSize = Integer.parseInt(req.getParameter("pageSize"));

			ProductService productSvc = new ProductService();
			Pair pair = productSvc.findHotTopSearchCountProduct(page, pageSize);

			List<ProductVO> listproduct = (List<ProductVO>) pair.getFirst();
			Long totalProductCount = (Long) pair.getSecond();
			System.out.println(totalProductCount);

			List<Map<String, String>> dataMapList = new ArrayList<>();
			for (ProductVO item : listproduct) {

				Map<String, String> itemMap = new HashMap<>();
				itemMap.put("totalProductCount", String.valueOf(totalProductCount));
				itemMap.put("currentPage", String.valueOf(page));
				itemMap.put("pageSize", String.valueOf(pageSize));
				itemMap.put("products", String.valueOf(listproduct));

				String productNo = item.getProductNo().toString();
				itemMap.put("productNo", productNo);

				String productName = item.getProductName();
				itemMap.put("productName", productName);

				byte[] productPicture = item.getProductPicture();
				if (productPicture != null) {
					String productImage = Base64.getEncoder().encodeToString(productPicture);
					itemMap.put("productImage", productImage);
				} else {
					itemMap.put("productImage", "");
				}

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
					itemMap.put("Categorytype", "ingredientCategory");
				}

				if (item.getKitchenwareCategory() != null) {
					String kitchenwareCategory = item.getKitchenwareCategory().getCategoryName();
					itemMap.put("Category", kitchenwareCategory);
					itemMap.put("Categorytype", "kitchenwareCategory");
				}

				dataMapList.add(itemMap);
			}

			System.out.println(dataMapList);
			System.out.println(totalProductCount);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			Gson gson = new Gson();
			String jsonData = gson.toJson(dataMapList);
			System.out.println(jsonData);

			res.setContentType("application/json; charset :UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonData);

		}

	}

	// 搜尋次數
	private void incrementProductSearchCount(int productNo) {
		JedisPool jedisPool = JedisUtil.getJedisPool();
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.select(2);
			String productKey = "product:" + productNo;
			jedis.hincrBy(productKey, "searchCount", 1);
			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
	}

}
