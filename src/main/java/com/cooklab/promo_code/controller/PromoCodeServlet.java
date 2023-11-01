package com.cooklab.promo_code.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.cooklab.promo_code.model.PromoCodeService;
import com.cooklab.promo_code.model.PromoCodeVO;
import java.util.Random;
import java.security.SecureRandom;





/**
 * Servlet implementation class PromoCodeServlet
 */
@WebServlet("/PromoCodeServlet")
public class PromoCodeServlet extends HttpServlet {

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
			String str = req.getParameter("promo_code_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入優惠碼編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/dashboard/promo_code/promo_code_allview.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer promocodeno = null;
			try {
				promocodeno = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("優惠碼編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/dashboard/promo_code/promo_code_allview.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			PromoCodeService promoCodeSvc = new PromoCodeService();
			PromoCodeVO promoCodeVO = promoCodeSvc.getOnePc(promocodeno);
			if (promoCodeVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/dashboard/promo_code/promo_code_allview.jsp");
				System.out.println(promocodeno);
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("promoCodeVO", promoCodeVO); // 資料庫取出的empVO物件,存入req
			String url = "/dashboard/promo_code/promo_code_getone.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*************************** 1.接收請求參數 ****************************************/
			Integer promoCodeNo = Integer.valueOf(req.getParameter("promo_code_no"));
			
			PromoCodeVO promoCodeVO = new PromoCodeVO();
		
            System.out.println(promoCodeVO.getPromoCodeNo());
	
		
			/*************************** 2.開始查詢資料 ****************************************/
			PromoCodeService PcSvc = new PromoCodeService();
			PcSvc.getOnePc(promoCodeNo);
		
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			
			String url = "/dashboard/promo_code/promo_code_allview.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_promoCode_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Enumeration<String> aa = req.getParameterNames();
			while(aa.hasMoreElements()) {
				String name = aa.nextElement();
				System.out.println(name);
			}
			System.out.println("編號為:"+req.getParameter("promo_code_no"));
			Integer promoCodeNo = Integer.valueOf(req.getParameter("promo_code_no").trim());
			String promoCodeSerialNumber = (req.getParameter("promo_code_serial_number"));
			java.sql.Timestamp startTime = null;
			String startTimeStr = req.getParameter("start_time");
			
			if (startTimeStr != null) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(startTimeStr, formatter);
					startTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					startTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {
				// 处理参数为 null 的情况，可以给出错误提示或执行适当的操作
			}
			String promoCodeSerialNumberReg = "^[(a-zA-Z0-9_)]{10}$";
			if (promoCodeSerialNumber == null || promoCodeSerialNumber.trim().length() == 0) {
				errorMsgs.add("優惠碼序號: 請勿空白");
			} else if (!promoCodeSerialNumber.trim().matches(promoCodeSerialNumberReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("優惠碼序號: 只能是英數字 , 且長度必需為10個字元");
			}
//			byte[] coverImage = req.getParameter("cover_image").trim().getBytes();
//			byte[] coverImage = null;
			java.sql.Timestamp endTime = null;
			String endTimeStr = req.getParameter("end_time");
			if (endTimeStr != null) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(endTimeStr, formatter);
					endTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					endTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {
				// 处理参数为 null 的情况，可以给出错误提示或执行适当的操作
			}
			String test = req.getParameter("percentage_discount_amount");
			
			String testReq = "^(0(\\.\\d*)?|1(\\.0*)?)$";
			if (test == null || test.trim().length() == 0) {
				errorMsgs.add("百分比折價金額: 請勿空白");
			} else if (!test.trim().matches(testReq)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("百分比折價金額: 只能是0~1的小數或整數");
			}
			
			BigDecimal percentageDiscountAmount = BigDecimal.valueOf(Double.valueOf(req.getParameter("percentage_discount_amount")));
			BigDecimal fixedDiscountAmount =BigDecimal.valueOf(Double.valueOf(req.getParameter("fixed_discount_amount")));
			Integer usagesAllowed = Integer.valueOf(req.getParameter("usages_allowed"));
			Integer minimumConsumption = Integer.valueOf(req.getParameter("minimum_consumption"));
			
	
			
			PromoCodeVO promoCodeVO = new PromoCodeVO();
			promoCodeVO.setPromoCodeNo(promoCodeNo);
			promoCodeVO.setPromoCodeSerialNumber(promoCodeSerialNumber);
			promoCodeVO.setStartTime(startTime);
			promoCodeVO.setEndTime(endTime);
			promoCodeVO.setPercentageDiscountAmount(percentageDiscountAmount);
			promoCodeVO.setFixedDiscountAmount(fixedDiscountAmount);
			promoCodeVO.setUsagesAllowed(usagesAllowed);
			promoCodeVO.setMinimumConsumption(minimumConsumption);

		
			System.out.println("==="+promoCodeVO+"");


			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("promoCodeVO", promoCodeVO); // 含有輸入格式錯誤的empVO物件,也存入req
				System.out.println(errorMsgs);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dashboard/promo_code/promo_code_getone.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			PromoCodeService PcSvc = new PromoCodeService();
			System.out.println("開始更新");
			PcSvc.updatePc(promoCodeVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("promocodeVO", promoCodeVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/dashboard/promo_code/promo_code_allview.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addpromocode.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String promoCodeSerialNumber = (req.getParameter("promo_code_serial_number"));
			
			java.sql.Timestamp startTime = null;
			String startTimeStr = req.getParameter("start_time");
			
			if (startTimeStr != null) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(startTimeStr, formatter);
					startTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					startTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {
				// 处理参数为 null 的情况，可以给出错误提示或执行适当的操作
			}
			String promoCodeSerialNumberReg = "^[(a-zA-Z0-9_)]{2,10}$";
			if (promoCodeSerialNumber == null || promoCodeSerialNumber.trim().length() == 0) {
				errorMsgs.add("優惠碼序號: 請勿空白");
			} else if (!promoCodeSerialNumber.trim().matches(promoCodeSerialNumberReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("優惠碼序號: 只能是英數字 , 且長度必需在2到10之間");
			}
		
			
			java.sql.Timestamp endTime = null;
			String endTimeStr = req.getParameter("end_time");
			if (endTimeStr != null) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
					LocalDateTime localDateTime = LocalDateTime.parse(endTimeStr, formatter);
					endTime = Timestamp.valueOf(localDateTime);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					endTime = new java.sql.Timestamp(System.currentTimeMillis());
				}
			} else {
				// 处理参数为 null 的情况，可以给出错误提示或执行适当的操作
			}
			BigDecimal percentageDiscountAmount = BigDecimal.valueOf(Double.valueOf(req.getParameter("percentage_discount_amount")));
			
			BigDecimal fixedDiscountAmount =BigDecimal.valueOf(Double.valueOf(req.getParameter("fixed_discount_amount")));
			Integer usagesAllowed = Integer.valueOf(req.getParameter("usages_allowed"));
			Integer minimumConsumption = Integer.valueOf(req.getParameter("minimum_consumption"));

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

			PromoCodeVO promoCodeVO = new PromoCodeVO();
		
			promoCodeVO.setPromoCodeSerialNumber(promoCodeSerialNumber);
			promoCodeVO.setStartTime(startTime);
			promoCodeVO.setEndTime(endTime);
			promoCodeVO.setPercentageDiscountAmount(percentageDiscountAmount);
			promoCodeVO.setFixedDiscountAmount(fixedDiscountAmount);
			promoCodeVO.setUsagesAllowed(usagesAllowed);
			promoCodeVO.setMinimumConsumption(minimumConsumption);

		

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("promocodeVO", promoCodeVO); 
				System.out.println(errorMsgs);// 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/dashboard/promo_code/promo_code_set.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			PromoCodeService PcSvc = new PromoCodeService();
			PcSvc.addPc(promoCodeVO);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/dashboard/promo_code/promo_code_allview.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllPromoCode.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer promoCodeNo = Integer.valueOf(req.getParameter("promo_code_no"));
			PromoCodeVO promoCodeVO = new PromoCodeVO();
		
            System.out.println(promoCodeVO.getPromoCodeNo());
			/*************************** 2.開始刪除資料 ***************************************/
			PromoCodeService PcSvc = new PromoCodeService();
			promoCodeVO = PcSvc.getOnePc(promoCodeNo);
			PcSvc.deletePc(promoCodeVO);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/dashboard/promo_code/promo_code_allview.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}

}
