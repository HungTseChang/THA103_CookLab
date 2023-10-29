package com.cooklab.support_form.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cooklab.support_form.model.SupportFormVO;
import com.cooklab.support_form.service.SupportFormHService;
import com.google.gson.Gson;

@WebServlet("/SFSFront")
public class SupportFormServletForFront extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 創建Gson物件以便將資料打包為json格式回傳給前端
		Gson gson = new Gson();

		// 前台客戶自行填單
		if ("insert".equals(action)) {
			// 創建Map物件放入錯誤訊息
			Map<String, String> errorMsgs = new HashMap<String, String>();

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			String realName = req.getParameter("realName");
			String realNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (realName == null || realName.trim().length() == 0) {
				errorMsgs.put("errNameBlank", "姓名請勿空白");
			} else if (!realName.trim().matches(realNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("errNameReg", "姓名只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			Integer supportFormCategoryId = null;
			String sfcid = req.getParameter("supportFormCategoryId").trim();
			if (sfcid.equals("default")) {
				errorMsgs.put("errCategory", "請選擇問題類別");
			} else {
				supportFormCategoryId = Integer.valueOf(sfcid);
			}

			String replyEmail = req.getParameter("replyEmail").trim();
			if (replyEmail == null || replyEmail.trim().length() == 0) {
				errorMsgs.put("errEmail", "回覆信箱請勿空白");
			}

			String formTitle = req.getParameter("formTitle").trim();
			if (formTitle == null || formTitle.trim().length() == 0) {
				errorMsgs.put("errTitle", "標題請勿空白");
			}

			String formContext = req.getParameter("formContext").trim();
			if (formContext == null || formContext.trim().length() == 0) {
				errorMsgs.put("errContext", "內容請勿空白");
			}

			Byte formSource = null;
			formSource = Byte.valueOf(req.getParameter("formSource"));

			Byte formStatus = null;
			formStatus = Byte.valueOf(req.getParameter("formStatus"));

			String formSubmitter = req.getParameter("formSubmitter").trim();

			// 錯誤驗證的訊息收集及回傳
			if (!errorMsgs.isEmpty()) {
				String errjson = gson.toJson(errorMsgs);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errjson);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			SupportFormHService sfSvc = new SupportFormHService();
			SupportFormVO sfVO = sfSvc.addSupportForm(realName, supportFormCategoryId, replyEmail, formTitle,
					formContext, formSource, formStatus, formSubmitter);

			/*************************** 3.新增完成,回傳成功訊息回前端 ***********/
			if (sfVO != null) {
				String url = "/THA103_CookLab/supportform/supportcenter-formresult.html";

				// 創建Map物件放入成功訊息
				Map<String, String> successMsg = new HashMap<String, String>();
				successMsg.put("success", "data transfer success");
				successMsg.put("url", url);

				String successjson = gson.toJson(successMsg);

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(successjson);
			}
		}

	}
}
