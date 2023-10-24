package com.cooklab.notify_center.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.members.model.MembersService;
import com.cooklab.members.model.MembersVO;
import com.cooklab.notify_center.model.NotifyCenterHService;
import com.cooklab.notify_center.model.NotifyCenterVO;
import com.google.gson.Gson;

@WebServlet("/NotifyCenterAjax")
public class NotifyCenterAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 創建Gson物件以便將資料打包為json格式回傳給前端
		Gson gson = new Gson();

		// 後台取得全部通知資料
		if ("getAll".equals(action)) {

			NotifyCenterHService ncSvc = new NotifyCenterHService();

			// 呼叫Service方法的getAll，取得VO物件List
			List<NotifyCenterVO> list = ncSvc.getAll();

			// 宣告MAP物件的LIST集合，以便將VO取出的資料轉為MAP形式
			List<Map<String, Object>> data = new ArrayList<>();

			// 使用FOR EACH一一取值並放置於MAP物件
			for (NotifyCenterVO notify : list) {
				Map<String, Object> notifyCenterMap = new HashMap<>();
				notifyCenterMap.put("notifyNo", notify.getNotifyNo());
				notifyCenterMap.put("memberId", notify.getMembers().getMemberId());
				notifyCenterMap.put("memberNickname", notify.getMembers().getMemberNickname());
				// 此處使用列舉類別，將資料庫的純數字資料，轉變成有意義的字串
				notifyCenterMap.put("notifyType", notify.getNCTypeName());
				notifyCenterMap.put("notifyRead", notify.getNCReadName());
				notifyCenterMap.put("notifyTime", notify.getNotifyTime());
				notifyCenterMap.put("notifyContent", notify.getNotifyContent());
				notifyCenterMap.put("createdTimestamp", notify.getCreatedTimestamp());
				data.add(notifyCenterMap);
			}

			// 使用Gson轉換為Json格式
			String Data = gson.toJson(data);

			// 回傳資料給Ajax進行進一步的處理
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(Data);
		}

		// 後台取得通知詳細資料
		if ("getOne".equals(action)) {
			Integer notifyNo = Integer.valueOf(req.getParameter("notifyNo"));

			NotifyCenterHService ncSvc = new NotifyCenterHService();

			// 呼叫Service方法的getOne，取得對應編號的VO物件，
			NotifyCenterVO ncVO = ncSvc.getOneNotifyCenter(notifyNo);

			// 宣告MAP物件，將VO取出的資料存入MAP
			// 補充：因為前端已經透過Get方法傳入notifyNo，所以這裡不會再重新傳入notifyNo資料
			Map<String, Object> data = new HashMap<>();
			data.put("memberId", ncVO.getMembers().getMemberId());
			data.put("memberAccount", ncVO.getMembers().getMemberAccount());
			data.put("memberNickname", ncVO.getMembers().getMemberNickname());
			// 此處使用列舉類別，將資料庫的純數字資料，轉變成有意義的字串
			data.put("notifyType", ncVO.getNCTypeName());
			data.put("notifyRead", ncVO.getNCReadName());
			data.put("notifyContent", ncVO.getNotifyContent());
			data.put("notifyTime", ncVO.getNotifyTime());
			data.put("createdTimestamp", ncVO.getCreatedTimestamp());

			// 使用Gson轉換為Json格式
			String Data = gson.toJson(data);

			// 回傳資料給Ajax進行進一步的處理
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(Data);
			System.out.println("Ajax回傳資料成功");
		}

		// 後台建立系統通知
		if ("insert".equals(action)) {
			// 創建Map物件放入錯誤訊息
			Map<String, String> errorMsgs = new HashMap<String, String>();

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String idStr = req.getParameter("memberId");
			Integer memberId = null;
			if (idStr == null || idStr.trim().length() == 0) {
				errorMsgs.put("errIdblank", "請輸入會員編號");
			} else {
				// 以下member資料取得方式為JDBC版
				MembersService mSvc = new MembersService();
				Integer memberIdcheck = Integer.valueOf(idStr);
				MembersVO mVO = mSvc.getOneMember(memberIdcheck);
				if (mVO == null) {
					errorMsgs.put("errId", "查無該會員編號，請重新確認");
				} else {
					memberId = memberIdcheck;
					System.out.println("查詢會員編號成功");
				}
			}

			String notifyTimeStr = req.getParameter("notifyTime");
			System.out.println("取得時間為：" + notifyTimeStr);
			Timestamp notifyTime = null;
			if (notifyTimeStr != null && !notifyTimeStr.trim().isEmpty()) {
				notifyTime = Timestamp.valueOf(notifyTimeStr);
				System.out.println("即將送進資料庫的時間格式為：" + notifyTime);
			} else {
				errorMsgs.put("errTime", "請選擇日期時間");
			}

			Byte notifyType = null;
			String ncStr = req.getParameter("notifyType").trim();
			if (ncStr.equals("default")) {
				errorMsgs.put("errType", "請選擇通知類別");
			} else {
				notifyType = Byte.valueOf(ncStr);
			}

			String notifyContent = req.getParameter("notifyContent").trim();
			if (notifyContent == null || notifyContent.trim().length() == 0) {
				errorMsgs.put("errContentt", "內容請勿空白");
			}

			// 錯誤驗證的訊息收集及回傳
			if (!errorMsgs.isEmpty()) {
				String errjson = gson.toJson(errorMsgs);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errjson);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			NotifyCenterVO ncVO = new NotifyCenterVO();
			// 會員編號先使用JDBC版本置入資料
//			ncVO.setMemberId(memberId);
//			ncVO.setNotifyTime(notifyTime);
//			ncVO.setNotifyType(notifyType);
//			ncVO.setNotifyContent(notifyContent);
			NotifyCenterHService ncSvc = new NotifyCenterHService();
			ncVO = ncSvc.addNotifyCenter(notifyType, notifyContent, notifyTime, memberId);

			/*************************** 3.新增完成,回傳成功訊息回前端 ***********/
			if (ncVO != null) {
				String url = "/THA103_CookLab/dashboard/notifycenter/official-notify.html";

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

		// 後台通知更新
		if ("update".equals(action)) {

			// 創建Map物件放入錯誤訊息
			Map<String, String> errorMsgs = new HashMap<String, String>();

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer notifyNo = Integer.valueOf(req.getParameter("notifyNo").trim());
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));

			Timestamp notifyTime = null;
			String notifyTimeStr = req.getParameter("notifyTime");
			System.out.println("取得時間為：" + notifyTimeStr);
			if (notifyTimeStr != null) {
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

					String formattedDateTime = notifyTimeStr + ":00";

					notifyTime = Timestamp.valueOf(formattedDateTime);
					;
				} catch (Exception e) {
					e.printStackTrace();
					notifyTime = new Timestamp(System.currentTimeMillis());
					errorMsgs.put("errTime", "請選擇日期時間");
				}
			} else {
			}

			Byte notifyType = null;
			String ncTStr = req.getParameter("notifyType").trim();
			if (ncTStr.equals("default")) {
				errorMsgs.put("errType", "請選擇問題類別");
			} else {
				notifyType = Byte.valueOf(ncTStr);
			}

			Byte notifyRead = null;
			String ncRStr = req.getParameter("notifyRead").trim();
			if (ncRStr.equals("default")) {
				errorMsgs.put("errRead", "請選擇讀取狀態");
			} else {
				notifyRead = Byte.valueOf(ncRStr);
			}

			String notifyContent = req.getParameter("notifyContent").trim();
			if (notifyContent == null || notifyContent.trim().length() == 0) {
				errorMsgs.put("errContentt", "內容請勿空白");
			}

			// 錯誤驗證的訊息收集及回傳
			if (!errorMsgs.isEmpty()) {
				String errjson = gson.toJson(errorMsgs);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errjson);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			NotifyCenterVO ncVO = new NotifyCenterVO();
			NotifyCenterHService ncSvc = new NotifyCenterHService();
			ncVO = ncSvc.updateNotifyCenter(notifyNo, notifyType, notifyRead, notifyContent, notifyTime, memberId);
			/*************************** 3.修改完成,準備轉交資料予前端 *************/
			if (ncVO != null) {
				String url = "/THA103_CookLab/dashboard/notifycenter/official-notify.html";

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

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
