package com.cooklab.product.controller;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

import com.cooklab.product.model.ProductService;
import com.cooklab.product.model.ProductVO;

@WebServlet("/ProductServlet2")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class ProductServlet2 extends HttpServlet {

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
				RequestDispatcher failureView = req.getRequestDispatcher("/frontstage/shopstage/shop-details.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/frontstage/shopstage/shop-details.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/frontstage/shopstage/shop-details.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("productVO", productVO); // 資料庫取出的productVO物件 存入req
			String url = "/frontstage/shopstage/shop-details.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("search".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***************************
			 * 1接收請求參數
			 **********************/
			String keywords = req.getParameter("keywords");

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/frontstage/shopstage/shop-details.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 2.開始查詢資料
			 *****************************************/
			ProductService productSvc = new ProductService();
			List<ProductVO> list = productSvc.findByKeyword(keywords);
			if (list == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/frontstage/shopstage/shop-details.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			


		}

	}

}
