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
			// 创建一个列表来存储 HashMap
			List<Map<String, String>> dataMapList = new ArrayList<>();

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
			// 获取当前时间的 Timestamp 对象
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			int totalProductCount = 0; // 初始化经过时间筛选的商品数量为0
			// 查询全部商品
			ProductService productSvc = new ProductService();
			List<ProductVO> totallistproduct = productSvc.findByKeyword(keywords);
			// 遍历商品数据
			for (ProductVO item : totallistproduct) {
				Timestamp shelfTimes = item.getShelfTime();
				Timestamp offsaleTimes = item.getOffsaleTime();
				if (shelfTimes == null || offsaleTimes == null
						|| (shelfTimes.before(currentTime) && currentTime.before(offsaleTimes))) {
					// 仅当商品符合时间条件时，递增经过时间筛选的商品数量
					totalProductCount++;
				}
			}
			System.out.println(totalProductCount);

			// 查询当前页的商品数据
			List<ProductVO> listproduct = productSvc.findByKeywordWithPagination(keywords, page, pageSize);
			// 创建一个列表来存储 HashMap
			List<Map<String, String>> dataMapList = new ArrayList<>();
			for (ProductVO item : listproduct) {
				Timestamp shelfTimes = item.getShelfTime();
				Timestamp offsaleTimes = item.getOffsaleTime();
				if (shelfTimes == null || offsaleTimes == null
						|| (shelfTimes.before(currentTime) && currentTime.before(offsaleTimes))) {
					// 创建一个 HashMap 来存储当前项的数据
					Map<String, String> itemMap = new HashMap<>();
					itemMap.put("totalProductCount", String.valueOf(totalProductCount));
					itemMap.put("currentPage", String.valueOf(page));
					itemMap.put("pageSize", String.valueOf(pageSize));
					itemMap.put("products", String.valueOf(listproduct));

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
			}
			// 输出查询结果到控制台
			System.out.println(dataMapList);
			System.out.println(totalProductCount);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			// 将查询结果列表转换为JSON格式
			Gson gson = new Gson();
			String jsonData = gson.toJson(dataMapList);
			System.out.println(jsonData);

			// 将JSON数据写入响应
			res.setContentType("application/json; charset :UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonData);
		}

		if ("Indexget".equals(action)) {
			ProductService productSvc = new ProductService();
			List<ProductVO> listproduct = productSvc.getAll();

			// 创建一个列表来存储 HashMap
			List<Map<String, String>> dataMapList = new ArrayList<>();
			// 获取当前时间的 Timestamp 对象
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());

			for (ProductVO item : listproduct) {
				// 获取商品的上架时间和下架时间
				Timestamp shelfTimes = item.getShelfTime();
				Timestamp offsaleTimes = item.getOffsaleTime();
				if (shelfTimes == null || offsaleTimes == null
						|| (shelfTimes.before(currentTime) && currentTime.before(offsaleTimes))) {
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

			}

			// 输出查询结果到控制台
			System.out.println(dataMapList);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			// 将查询结果列表转换为JSON格式
			Gson gson = new Gson();
			String jsonData = gson.toJson(dataMapList);
			System.out.println(jsonData);

			// 将JSON数据写入响应
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

			String saleQtyParam = req.getParameter("saleQty");
			Integer saleQty = null;
			if (saleQtyParam != null && !saleQtyParam.isEmpty()) {
				try {
					saleQty = Integer.valueOf(saleQtyParam);
					if (saleQty <= 0) {
						errorMsgs.put("errSaleQty", "商品數量必須為正數且不為零");
					}
				} catch (NumberFormatException e) {
					errorMsgs.put("errSaleQty", "商品數量必須是有效的數字");
				}
			} else {
				errorMsgs.put("errSaleQty", "商品數量不能為空");
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
			String offsaleTimeStr = req.getParameter("uptime");
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
			// 读取图像文件并编码为Base64字符串
			byte[] productPicture = productVO.getProductPicture();
			if (productPicture != null) {
				String productImage = Base64.getEncoder().encodeToString(productPicture);
				productDetailMap.put("productImage", productImage);
			} else {
				// 处理 productPicture 为 null 的情况，例如给出一个默认值或者其他操作
				productDetailMap.put("productImage", "");
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

			String saleQtyParam = req.getParameter("saleQty");
			Integer saleQty = null;
			if (saleQtyParam != null && !saleQtyParam.isEmpty()) {
				try {
					saleQty = Integer.valueOf(saleQtyParam);
					if (saleQty <= 0) {
						errorMsgs.put("errSaleQty", "商品數量必須為正數且不為零");
					}
				} catch (NumberFormatException e) {
					errorMsgs.put("errSaleQty", "商品數量必須是有效的數字");
				}
			} else {
				errorMsgs.put("errSaleQty", "商品數量不能為空");
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
			String offsaleTimeStr = req.getParameter("uptime");
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

			String message = productSvc.update(productVO);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			// 创建一个 Map 来存储消息和 productVO 数据
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("message", message);
			responseMap.put("productInfo", productVO);

			// 使用 Gson 将对象转换为 JSON 字符串
			Gson gson = new Gson();
			String productJson = gson.toJson(responseMap);

			// 设置响应的内容类型为 JSON
			res.setContentType("application/json; charset=UTF-8");

			// 将 JSON 字符串写入响应输出流
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

			// 使用 Gson 将对象转换为 JSON 字符串
			Gson gson = new Gson();
			String productJson = gson.toJson(hotKeywordsList);

			// 设置响应的内容类型为 JSON
			res.setContentType("application/json; charset=UTF-8");

			// 将 JSON 字符串写入响应输出流
			res.getWriter().write(productJson);

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
