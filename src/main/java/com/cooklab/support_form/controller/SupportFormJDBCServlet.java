package com.cooklab.support_form.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.support_form.model.SupportFormJDBCService;
import com.cooklab.support_form.model.SupportFormVO;

@WebServlet("/SupportFormJDBCServlet")
public class SupportFormJDBCServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("formNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入表單編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/supportform/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer formNo = null;
			try {
				formNo = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("表單編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/supportform/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			SupportFormJDBCService sfSvc = new SupportFormJDBCService();
			SupportFormVO sfVO = sfSvc.getOneSupportForm(formNo);
			if (sfVO == null) {
				errorMsgs.add("查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/supportform/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("sfVO", sfVO);
			String url = "/supportform/listOneSupportForm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer formNo = Integer.valueOf(req.getParameter("formNo"));

			/*************************** 2.開始查詢資料 ****************************************/
			SupportFormJDBCService sfSvc = new SupportFormJDBCService();
			SupportFormVO sfVO = sfSvc.getOneSupportForm(formNo);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("sfVO", sfVO);
			String url = "/supportform/update_supportform_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer formNo = Integer.valueOf(req.getParameter("formNo").trim());

			String realName = req.getParameter("realName");
			String realNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (realName == null || realName.trim().length() == 0) {
				errorMsgs.add("客戶姓名: 請勿空白");
			} else if (!realName.trim().matches(realNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("客戶姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			Integer supportFormCategoryId = null;
			try {
				supportFormCategoryId = Integer.valueOf(req.getParameter("supportFormCategoryId").trim());
			} catch (NumberFormatException e) {
				supportFormCategoryId = 0;
				errorMsgs.add("問題類別請填數字.");
			}

			String replyEmail = req.getParameter("replyEmail").trim();
			if (replyEmail == null || replyEmail.trim().length() == 0) {
				errorMsgs.add("回覆信箱請勿空白");
			}

			String formTitle = req.getParameter("formTitle").trim();
			if (formTitle == null || formTitle.trim().length() == 0) {
				errorMsgs.add("反應標題請勿空白");
			}

			String formContext = req.getParameter("formContext").trim();
			if (formContext == null || formContext.trim().length() == 0) {
				errorMsgs.add("反應內文請勿空白");
			}

			Byte formStatus = null;
			try {
				formStatus = Byte.valueOf(req.getParameter("formStatus").trim());
			} catch (NumberFormatException e) {
				formStatus = 0;
				errorMsgs.add("表單狀態請填數字.");
			}

			Byte formSource = null;
			formSource =  Byte.valueOf(req.getParameter("formSource"));

			String formSubmitter = req.getParameter("formSubmitter").trim();
			if (formSubmitter == null || formSubmitter.trim().length() == 0) {
				errorMsgs.add("表單建立者請勿空白");
			}

			Integer formResponder = Integer.valueOf(req.getParameter("formResponder").trim());
			
			Timestamp createdTimestamp = Timestamp.valueOf(req.getParameter("createdTimestamp"));

			SupportFormVO sfVO = new SupportFormVO();
			sfVO.setFormNo(formNo);
			sfVO.setRealName(realName);
			sfVO.setSupportFormCategoryId(supportFormCategoryId);
			sfVO.setReplyEmail(replyEmail);
			sfVO.setFormTitle(formTitle);
			sfVO.setFormContext(formContext);
			sfVO.setFormStatus(formStatus);
			sfVO.setFormSource(formSource);
			sfVO.setFormSubmitter(formSubmitter);
			sfVO.setFormResponder(formResponder);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("sfVO", sfVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/supportform/update_supportform_input.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			SupportFormJDBCService sfSvc = new SupportFormJDBCService();
			sfVO = sfSvc.updateSupportForm(formNo, realName, supportFormCategoryId, replyEmail, formTitle, formContext,
					formStatus, formSource, formSubmitter, formResponder);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			sfVO.setCreatedTimestamp(createdTimestamp);//轉交之後再將原本取得的建立時間存入物件轉交給資料
			req.setAttribute("sfVO", sfVO);
			String url = "/supportform/listOneSupportForm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String realName = req.getParameter("realName");
			String realNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (realName == null || realName.trim().length() == 0) {
				errorMsgs.add("客戶姓名: 請勿空白");
			} else if (!realName.trim().matches(realNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("客戶姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			Integer supportFormCategoryId = Integer.valueOf(req.getParameter("supportFormCategoryId").trim());
//			Integer supportFormCategoryId = null;
//			try {
//				supportFormCategoryId = Integer.valueOf(req.getParameter("supportFormCategoryId").trim());
//			} catch (NumberFormatException e) {
//				supportFormCategoryId = 0;
//				errorMsgs.add("問題類別請填數字.");
//			}

			String replyEmail = req.getParameter("replyEmail").trim();
			if (replyEmail == null || replyEmail.trim().length() == 0) {
				errorMsgs.add("回覆信箱請勿空白");
			}

			String formTitle = req.getParameter("formTitle").trim();
			if (formTitle == null || formTitle.trim().length() == 0) {
				errorMsgs.add("反應標題請勿空白");
			}

			String formContext = req.getParameter("formContext").trim();
			if (formContext == null || formContext.trim().length() == 0) {
				errorMsgs.add("反應內文請勿空白");
			}

			Byte formStatus = null;
			try {
				formStatus = Byte.valueOf(req.getParameter("formStatus").trim());
			} catch (NumberFormatException e) {
				formStatus = 0;
				errorMsgs.add("表單狀態請填數字.");
			}

			Byte formSource = null;
			formSource =  Byte.valueOf(req.getParameter("formSource"));
			
			String formSubmitter = req.getParameter("formSubmitter").trim();
//			if (formSubmitter == null || formSubmitter.trim().length() == 0) {
//				errorMsgs.add("表單建立者請勿空白");
//			}

			SupportFormVO sfVO = new SupportFormVO();
			sfVO.setRealName(realName);
			sfVO.setSupportFormCategoryId(supportFormCategoryId);
			sfVO.setReplyEmail(replyEmail);
			sfVO.setFormTitle(formTitle);
			sfVO.setFormContext(formContext);
			sfVO.setFormStatus(formStatus);
			sfVO.setFormSource(formSource);
			sfVO.setFormSubmitter(formSubmitter);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("sfVO", sfVO); 
				RequestDispatcher failureView = req.getRequestDispatcher("/supportform/addSupportForm.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			SupportFormJDBCService sfSvc = new SupportFormJDBCService();
			sfVO = sfSvc.addSupportForm(realName, supportFormCategoryId, replyEmail, formTitle, formContext,
					formSource,formStatus, formSubmitter);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/THA103_CookLab/supportform/supportcenter-formresult.html";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer formNo = Integer.valueOf(req.getParameter("formNo"));

			/*************************** 2.開始刪除資料 ***************************************/
			SupportFormJDBCService sfSvc = new SupportFormJDBCService();
			sfSvc.deleteSupportForm(formNo);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/supportform/listAllSupportForm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
}
