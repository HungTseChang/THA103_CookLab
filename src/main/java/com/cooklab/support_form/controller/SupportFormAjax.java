package com.cooklab.support_form.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.support_form.model.SupportFormHService;
import com.cooklab.support_form.model.SupportFormVO;
import com.google.gson.Gson;

@WebServlet("/SupportFormAjax")
public class SupportFormAjax extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("收到Ajax發出請求");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 創建Gson物件以便將資料打包為json格式回傳給前端
		Gson gson = new Gson();

		if ("getAll".equals(action)) {
			SupportFormHService sfSvc = new SupportFormHService();

			// 呼叫Service方法的getAll，取得VO物件List，
			List<SupportFormVO> list = sfSvc.getAll();

			// 宣告MAP物件的LIST集合，以便將VO取出的資料轉為MAP形式
			List<Map<String, Object>> data = new ArrayList<>();

			// 使用FOR EACH一一取值並放置於MAP物件
			for (SupportFormVO supportForm : list) {
				Map<String, Object> supportFormMap = new HashMap<>();
				supportFormMap.put("formNo", supportForm.getFormNo());
				supportFormMap.put("realName", supportForm.getRealName());
				// 此處使用列舉類別，將資料庫的純數字資料，轉變成有意義的字串
				supportFormMap.put("supportFormCategoryId", supportForm.getFormCatIDName());
				supportFormMap.put("replyEmail", supportForm.getReplyEmail());
				supportFormMap.put("formTitle", supportForm.getFormTitle());
				supportFormMap.put("formContext", supportForm.getFormContext());
				// 此處使用列舉類別，將資料庫的純數字資料，轉變成有意義的字串
				supportFormMap.put("formStatus", supportForm.getFormStatusName());
				// 如果此表單尚未有管理員處理則不會有值，避免ajax回傳資料時前端的Javascript產生undefined問題，此處先行做空值判斷，提供取不到值的預設值
				if (supportForm.getAdmins() != null) {
					supportFormMap.put("formResponderno", supportForm.getAdmins().getAdminNo());
					supportFormMap.put("formRespondername", supportForm.getAdmins().getAdminNickname());
				} else {
					supportFormMap.put("formResponderno", 0);
					supportFormMap.put("formRespondername", "無");
				}
				supportFormMap.put("createdTimestamp", supportForm.getCreatedTimestamp());
				data.add(supportFormMap);
			}

			// 使用Gson轉換為Json格式
			String Data = gson.toJson(data);

			// 回傳資料給Ajax進行進一步的處理
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(Data);
			System.out.println("回傳資料給Ajax完成");
		}

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

			String formSource = req.getParameter("formSource").trim();

			Byte formStatus = null;
			formStatus = Byte.valueOf(req.getParameter("formStatus"));

			String formSubmitter = req.getParameter("formSubmitter").trim();

			SupportFormVO sfVO = new SupportFormVO();
			sfVO.setRealName(realName);
			sfVO.setSupportFormCategoryId(supportFormCategoryId);
			sfVO.setReplyEmail(replyEmail);
			sfVO.setFormTitle(formTitle);
			sfVO.setFormContext(formContext);
			sfVO.setFormStatus(formStatus);
			sfVO.setFormSource(formSource);
			sfVO.setFormSubmitter(formSubmitter);

			// 錯誤驗證的訊息收集及回傳
			if (!errorMsgs.isEmpty()) {
				String errjson = gson.toJson(errorMsgs);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errjson);
				System.out.println("回傳錯誤訊息給Ajax成功");
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			SupportFormHService sfSvc = new SupportFormHService();
			sfVO = sfSvc.addSupportForm(realName, supportFormCategoryId, replyEmail, formTitle, formContext, formSource,
					formStatus, formSubmitter);

			/*************************** 3.新增完成,回傳成功訊息回前端 ***********/
			if (sfVO != null) {
				System.out.println("回傳成功訊息給Ajax");
				String url = "/THA103_CookLab/supportform/supportcenter-formresult.html";

				// 創建Map物件放入成功訊息
				Map<String, String> successMsg = new HashMap<String, String>();
				successMsg.put("success", "data transfer success");
				successMsg.put("url", url);

				String successjson = gson.toJson(successMsg);

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(successjson);
				System.out.println("回傳成功訊息給Ajax完成");
			} else {
				// 預計放入空頁面
			}
		}

		// 後台客服人工建立表單
		if ("insert-dashboard".equals(action)) {
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

			String formSource = req.getParameter("formSource").trim();

			Byte formStatus = null;
			formStatus = Byte.valueOf(req.getParameter("formStatus"));

			// 屆時從session取得adminid在放置此位置，因需測試方便故改用固定值
//			String formSubmitter = req.getParameter("formSubmitter").trim();
			String formSubmitter = "客服建單";

			SupportFormVO sfVO = new SupportFormVO();
			sfVO.setRealName(realName);
			sfVO.setSupportFormCategoryId(supportFormCategoryId);
			sfVO.setReplyEmail(replyEmail);
			sfVO.setFormTitle(formTitle);
			sfVO.setFormContext(formContext);
			sfVO.setFormStatus(formStatus);
			sfVO.setFormSource(formSource);
			sfVO.setFormSubmitter(formSubmitter);

			// 錯誤驗證的訊息收集及回傳
			if (!errorMsgs.isEmpty()) {
				String errjson = gson.toJson(errorMsgs);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errjson);
				System.out.println("回傳錯誤訊息給Ajax成功");
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			SupportFormHService sfSvc = new SupportFormHService();
			sfVO = sfSvc.addSupportForm(realName, supportFormCategoryId, replyEmail, formTitle, formContext, formSource,
					formStatus, formSubmitter);

			/*************************** 3.新增完成,回傳成功訊息回前端 ***********/
			if (sfVO != null) {
				System.out.println("回傳成功訊息給Ajax");
				String url = "/THA103_CookLab/dashboard/supportform/ding-support-tickets-table.html";

				// 創建Map物件放入成功訊息
				Map<String, String> successMsg = new HashMap<String, String>();
				successMsg.put("success", "data transfer success");
				successMsg.put("url", url);

				String successjson = gson.toJson(successMsg);

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(successjson);
				System.out.println("回傳成功訊息給Ajax完成");
			} else {
				// 預計放入空頁面
			}
		}
	}
}
