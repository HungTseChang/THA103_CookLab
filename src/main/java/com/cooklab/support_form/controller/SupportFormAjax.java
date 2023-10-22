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
import javax.servlet.http.HttpSession;

import com.cooklab.admins.model.AdminsService;
import com.cooklab.admins.model.AdminsVO;
import com.cooklab.support_form.model.SFStatus;
import com.cooklab.support_form.model.SupportFormHService;
import com.cooklab.support_form.model.SupportFormVO;
import com.google.gson.Gson;

@WebServlet("/SupportFormAjax")
public class SupportFormAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		//為了取得管理員登入資訊，需從Session取值
		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		// 創建Gson物件以便將資料打包為json格式回傳給前端
		Gson gson = new Gson();
		
		if("closecase".equals(action)) {
			
			Integer formNo = Integer.valueOf(req.getParameter("formNo"));
			// 屆時會改從session取得adminNo，因需測試方便故改用固定值
//			Integer adminNo = (Integer) session.getAttribute("AdminNo");
			Integer adminNo = 1;
			
			Byte formStatus = null;
			String formStatusString = req.getParameter("formStatus");
			
			//利用MAP物件放入錯誤訊息
			Map<String, String> errorMsgs = new HashMap<String, String>();
			
			//避免重複結案問題產生，先行判斷狀態
			if(!formStatusString.equals("已結案")) {
				formStatus = (byte) SFStatus.CASECLOSED.getValue();
			}else {
				errorMsgs.put("errCloseCase", "案件已結案");
			}
			
			//如判定已結案則回傳錯誤訊息並終止程式
			if (!errorMsgs.isEmpty()) {
				String errjson = gson.toJson(errorMsgs);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errjson);
				return;
			}
			
			//更新表單的處理人員資訊
			SupportFormHService sfSvc = new SupportFormHService();
			SupportFormVO closingCase = sfSvc.changeInfo(formNo, adminNo,formStatus);
			
			//用聯合映射取得管理員資訊設定MAP物件回傳資料以及成功後跳轉頁面
			String url = "/THA103_CookLab/dashboard/supportform/support-tickets-table.html";
			Map<String, Object> data = new HashMap<>();
			data.put("adminNo", closingCase.getAdmins().getAdminNo());
			data.put("adminNickname", closingCase.getAdmins().getAdminNickname());
			data.put("formStatus", closingCase.getFormStatus());
			data.put("success", "data transfer success");
			data.put("url", url);
			
			// 使用Gson轉換為Json格式
			String Data = gson.toJson(data);

			// 回傳資料給Ajax進行進一步的處理
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(Data);
		}
		
		if("changeAdmin".equals(action)) {
			Integer formNo = Integer.valueOf(req.getParameter("formNo"));
			Integer adminNo = Integer.valueOf(req.getParameter("adminNo"));
			
			Byte formStatus = null;
			String formStatusString = req.getParameter("formStatus");
			System.out.println(formStatusString);
			
			//利用MAP物件放入錯誤訊息
			Map<String, String> errorMsgs = new HashMap<String, String>();
			
			//狀態判斷，未處理的會改成處理中、處理中的保持不變、已結案的回傳錯誤訊息
			switch (formStatusString){
			case "未處理":
				formStatus = (byte) SFStatus.ONPROCESS.getValue();
				break;
			case "處理中":
				formStatus = (byte) SFStatus.ONPROCESS.getValue();
				break;
			case "已結案":
				errorMsgs.put("errCloseCase", "案件已結案");
				break;
			default:
				errorMsgs.put("errStatus", "不支援的狀態");
			}
		
			
			//如判定已結案則回傳錯誤訊息並終止程式
			if (!errorMsgs.isEmpty()) {
				String errjson = gson.toJson(errorMsgs);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errjson);
				return;
			}
			
			//更新表單的處理人員資訊
			SupportFormHService sfSvc = new SupportFormHService();
			SupportFormVO caseinfo = sfSvc.changeInfo(formNo, adminNo,formStatus);
			
			//用聯合映射取得管理員資訊設定MAP物件回傳資料
			Map<String, Object> data = new HashMap<>();
			data.put("adminNo", caseinfo.getAdmins().getAdminNo());
			data.put("adminNickname", caseinfo.getAdmins().getAdminNickname());
			data.put("formStatus", caseinfo.getFormStatusName());
			data.put("success", "data transfer success");
			
			// 使用Gson轉換為Json格式
			String Data = gson.toJson(data);

			// 回傳資料給Ajax進行進一步的處理
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(Data);
		}

		if ("getAdmins".equals(action)) {
			// 創建管理員VO集合放入LIST再放入MAP
			AdminsService adSvc = new AdminsService();
			List<AdminsVO> list = adSvc.getAll();

			// 宣告MAP物件的LIST集合，以便將VO取出的資料轉為MAP形式
			List<Map<String, Object>> data = new ArrayList<>();

			// 使用FOR EACH一一取值並放置於MAP物件
			for (AdminsVO admin : list) {
				Map<String, Object> adminMap = new HashMap<>();
				adminMap.put("adminNo", admin.getAdminNo());
				adminMap.put("adminNickname", admin.getAdminNickname());
				data.add(adminMap);
			}

			// 使用Gson轉換為Json格式
			String Data = gson.toJson(data);

			// 回傳資料給Ajax進行進一步的處理
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(Data);
		}

		// 後台取得表單詳細資料
		if ("getOne".equals(action)) {
			Integer formNo = Integer.valueOf(req.getParameter("formNo"));

			SupportFormHService sfSvc = new SupportFormHService();

			// 呼叫Service方法的getOne，取得對應編號的VO物件，
			SupportFormVO sfVO = sfSvc.getOneSupportForm(formNo);

			// 宣告MAP物件，將VO取出的資料存入MAP
			Map<String, Object> data = new HashMap<>();
			data.put("realName", sfVO.getRealName());
			// 此處使用列舉類別，將資料庫的純數字資料，轉變成有意義的字串
			data.put("supportFormCategoryId", sfVO.getFormCatIDName());
			data.put("formStatus", sfVO.getFormStatusName());
			data.put("formSource", sfVO.getFormSourceName());

			data.put("formSubmitter", sfVO.getFormSubmitter());
			data.put("replyEmail", sfVO.getReplyEmail());
			data.put("formTitle", sfVO.getFormTitle());
			data.put("formContext", sfVO.getFormContext());

			// 如果此表單尚未有管理員處理則不會有值，避免ajax回傳資料時前端的Javascript產生undefined問題，此處先行做空值判斷，提供取不到值的預設值
			if (sfVO.getAdmins() != null) {
				data.put("formResponderno", sfVO.getAdmins().getAdminNo());
				data.put("formRespondername", sfVO.getAdmins().getAdminNickname());
			} else {
				data.put("formResponderno", 0);
				data.put("formRespondername", "無");
			}
			data.put("createdTimestamp", sfVO.getCreatedTimestamp());

			// 使用Gson轉換為Json格式
			String Data = gson.toJson(data);

			// 回傳資料給Ajax進行進一步的處理
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(Data);
			System.out.println("Ajax回傳資料成功");
		}

		// 後台取得全部表單資料
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

			Byte formSource = null;
			formSource = Byte.valueOf(req.getParameter("formSource"));

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
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			SupportFormHService sfSvc = new SupportFormHService();
			sfVO = sfSvc.addSupportForm(realName, supportFormCategoryId, replyEmail, formTitle, formContext, formSource,
					formStatus, formSubmitter);

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

			Byte formSource = null;
			formSource = Byte.valueOf(req.getParameter("formSource"));

			Byte formStatus = null;
			formStatus = Byte.valueOf(req.getParameter("formStatus"));

			// 屆時從session取得adminNo在放置此位置，因需測試方便故改用固定值
//			String fsNo = ((String) session.getAttribute("AdminNo")).trim();
//			String fsName = ((String) session.getAttribute("adminNickname")).trim();
//			String formSubmitter = fsName + "(" + fsNo + ")";
			
			String formSubmitter= "客服建單";

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
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			SupportFormHService sfSvc = new SupportFormHService();
			sfVO = sfSvc.addSupportForm(realName, supportFormCategoryId, replyEmail, formTitle, formContext, formSource,
					formStatus, formSubmitter);

			/*************************** 3.新增完成,回傳成功訊息回前端 ***********/
			if (sfVO != null) {
				String url = "/THA103_CookLab/dashboard/supportform/support-tickets-table.html";

				// 創建Map物件放入成功訊息
				Map<String, String> successMsg = new HashMap<String, String>();
				successMsg.put("success", "data transfer success");
				successMsg.put("url", url);

				String successjson = gson.toJson(successMsg);

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(successjson);
			} else {
				// 預計放入空頁面
			}
		}
	}
}
