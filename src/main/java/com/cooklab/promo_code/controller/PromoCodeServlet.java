package com.cooklab.promo_code.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
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
			String str = req.getParameter("promo_ode_no");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入優惠碼編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/promocode/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer promoCodeNo = null;
			try {
				promoCodeNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("優惠碼編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/promocode/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			PromoCodeService promoCodeSvc = new PromoCodeService();
			PromoCodeVO promoCodeVO = promoCodeSvc.getOnePromoCode(promoCodeNo);
			if (promoCodeVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/promocode/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("promoCodeVO", promoCodeVO); // 資料庫取出的empVO物件,存入req
			String url = "/promocode/listOnePromoCode.jsp";
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

			/*************************** 2.開始查詢資料 ****************************************/
			PromoCodeService promoCodeSvc = new PromoCodeService();
			PromoCodeVO promocodeVO = promoCodeSvc.getOnePromoCode(promoCodeNo);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("promocodeVO", promocodeVO); // 資料庫取出的empVO物件,存入req
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
			Integer promoCodeNo = Integer.valueOf(req.getParameter("promo_code_no").trim());
			String promoCodeSerialNumber = (req.getParameter("promo_code_serial_number"));
			Timestamp startTime = Timestamp.valueOf(req.getParameter("start_time"));
			String promoCodeSerialNumberReg = "^[(0-9)]{2,10}$";
			if (promoCodeSerialNumber == null || promoCodeSerialNumber.trim().length() == 0) {
				errorMsgs.add("優惠碼編號: 請勿空白");
			} else if (!promoCodeSerialNumber.trim().matches(promoCodeSerialNumberReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("優惠碼編號: 只能是數字 , 且長度必需在2到10之間");
			}
//			byte[] coverImage = req.getParameter("cover_image").trim().getBytes();
			byte[] coverImage = null;
			Timestamp endTime = Timestamp.valueOf(req.getParameter("end_time"));
			BigDecimal percentageDiscountAmount = BigDecimal.valueOf(req.getParameter("percentage_discount_amount"));
			BigDecimal fixedDiscountAmount =BigDecimal.valueOf(req.getParameter("fixed_discount_amount"));
			Integer usagesAllowed = Integer.valueOf(req.getParameter("usages_allowed"));
			Integer minimumConsumption = Integer.valueOf(req.getParameter("minimum_consumption"));
			Timestamp createdTimestamp = Timestamp.valueOf(req.getParameter("created_timestamp"));
			
			PromoCodeVO promoCodeVO = new PromoCodeVO();
			promoCodeVO.setPromoCodeNo(promoCodeNo);
			promoCodeVO.setPromoCodeSerialNumber(promoCodeSerialNumber);
			promoCodeVO.setStartTime(startTime);
			promoCodeVO.setEndTime(endTime);
			promoCodeVO.setPercentageDiscountAmount(percentageDiscountAmount);
			promoCodeVO.setFixedDiscountAmount(fixedDiscountAmount);
			promoCodeVO.setUsagesAllowed(usagesAllowed);
			promoCodeVO.setMinimumConsumption(minimumConsumption);
			promoCodeVO.setCreatedTimestamp(createdTimestamp);
		
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("promoCodeVO", promoCodeVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("promocode/update_promoCode_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			PromoCodeService promocodeSvc = new PromoCodeService();
			promoCodeVO = promocodeSvc.updatePromoCode(promoCodeNo, promoCodeSerialNumber, startTime, endTime, percentageDiscountAmount,
					fixedDiscountAmount, usagesAllowed, minimumConsumption, createdTimestamp);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("promocodeVO", promoCodeVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/promocode/listOnePromoCode.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer promoCodeNo = Integer.valueOf(req.getParameter("promo_code_no").trim());
			String promoCodeSerialNumber = (req.getParameter("promo_code_serial_number"));
			Timestamp startTime = Timestamp.valueOf(req.getParameter("start_time"));
			String promoCodeSerialNumberReg = "^[(0-9)]{2,10}$";
			if (promoCodeSerialNumber == null || promoCodeSerialNumber.trim().length() == 0) {
				errorMsgs.add("優惠碼編號: 請勿空白");
			} else if (!promoCodeSerialNumber.trim().matches(promoCodeSerialNumberReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("優惠碼編號: 只能是數字 , 且長度必需在2到10之間");
			}
//			byte[] coverImage = req.getParameter("cover_image").trim().getBytes();
			byte[] coverImage = null;
			Timestamp endTime = Timestamp.valueOf(req.getParameter("end_time"));
			BigDecimal percentageDiscountAmount = BigDecimal.valueOf(req.getParameter("percentage_discount_amount"));
			BigDecimal fixedDiscountAmount =BigDecimal.valueOf(req.getParameter("fixed_discount_amount"));
			Integer usagesAllowed = Integer.valueOf(req.getParameter("usages_allowed"));
			Integer minimumConsumption = Integer.valueOf(req.getParameter("minimum_consumption"));
			Timestamp createdTimestamp = Timestamp.valueOf(req.getParameter("created_timestamp"));
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
			promoCodeVO.setPromoCodeNo(promoCodeNo);
			promoCodeVO.setPromoCodeSerialNumber(promoCodeSerialNumber);
			promoCodeVO.setStartTime(startTime);
			promoCodeVO.setEndTime(endTime);
			promoCodeVO.setPercentageDiscountAmount(percentageDiscountAmount);
			promoCodeVO.setFixedDiscountAmount(fixedDiscountAmount);
			promoCodeVO.setUsagesAllowed(usagesAllowed);
			promoCodeVO.setMinimumConsumption(minimumConsumption);
			promoCodeVO.setCreatedTimestamp(createdTimestamp);
		

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("promocodeVO", promoCodeVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("promocode/addPromoCode.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			PromoCodeService promocodeSvc = new PromoCodeService();
			promoCodeVO = promocodeSvc.addPromoCode(promoCodeNo, promoCodeSerialNumber, startTime, endTime, percentageDiscountAmount,
					fixedDiscountAmount, usagesAllowed, minimumConsumption, createdTimestamp);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/promocode/listAllPromoCode.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer promoCodeNo = Integer.valueOf(req.getParameter("promo_code"));

			/*************************** 2.開始刪除資料 ***************************************/
			PromoCodeService promoCodeSvc = new PromoCodeService();
			promoCodeSvc.deletePromocode(promoCodeNo);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/promocode/listAllPromoCode.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}

}
