package com.cooklab.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.cooklab.product.model.ProductService;
import com.cooklab.product.model.ProductVO;

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
					String url = "/product/update_product_input.jsp";
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
			try {
				offsaleTime = java.sql.Timestamp.valueOf(req.getParameter("offsaleTime").trim());
			} catch (IllegalArgumentException e) {
				offsaleTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			java.sql.Timestamp shelfTime = null;
			try {
				shelfTime = java.sql.Timestamp.valueOf(req.getParameter("shelfTime").trim());
			} catch (IllegalArgumentException e) {
				shelfTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
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
			byte[] buf = new byte[in.available()];   // byte[] buf = in.readAllBytes();  // Java 9 的新方法
			in.read(buf);
			in.close();

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
					searchCount,buf);

			/***************************
			 * 3.�ק粒��,�ǳ����(Send the Success view)
			 *************/
			req.setAttribute("productVO", productVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
			String url = "/product/listOneProduct.jsp";
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
			try {
				offsaleTime = java.sql.Timestamp.valueOf(req.getParameter("offsaleTime").trim());
			} catch (IllegalArgumentException e) {
				offsaleTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			java.sql.Timestamp shelfTime = null;
			try {
				shelfTime = java.sql.Timestamp.valueOf(req.getParameter("shelfTime").trim());
			} catch (IllegalArgumentException e) {
				shelfTime = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
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
			byte[] buf = new byte[in.available()];   // byte[] buf = in.readAllBytes();  // Java 9 的新方法
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
					offsaleTime, shelfTime, storageQty, ingredientCategoryNo, kitchenwareCategoryNo,buf);

			/***************************
			 * 3.�s�W����,�ǳ����(Send the Success view)
			 ***********/
			String url = "/product/listAllProduct.jsp";
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
