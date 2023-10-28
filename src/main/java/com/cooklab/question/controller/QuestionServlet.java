package com.cooklab.question.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.question.model.QuestionVO;
import com.cooklab.question.service.QuestionHService;
import com.google.gson.Gson;

@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 將請求物件的編碼設置為UTF-8避免亂碼問題
		req.setCharacterEncoding("UTF-8");

		// 透過前端傳來的action觸發不同的功能
		String action = req.getParameter("action");

		// 創建Gson物件以便將資料打包為json格式回傳給前端
		Gson gson = new Gson();

		// 取得全部資料
		if ("getAll".equals(action)) {

			// 呼叫Service方法的getAll，取得DTO物件List，使用Gson轉換為Json格式
			String Data = gson.toJson(new QuestionHService().getAllDTO());

			// 回傳資料給Ajax進行進一步的處理
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(Data);
		}

		// 取得單一詳細資料
		if ("getOne".equals(action)) {
			Integer questionNo = Integer.valueOf(req.getParameter("questionNo"));

			// 呼叫Service方法的getOne，取得對應編號的DTO物件，使用Gson轉換為Json格式
			String Data = gson.toJson(new QuestionHService().getOneDTO(questionNo));

			// 回傳資料給Ajax進行進一步的處理
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(Data);
		}

		// 刪除資料
		if ("delete".equals(action)) {
			Integer questionNo = Integer.valueOf(req.getParameter("questionNo"));

			// 呼叫Service方法開始進行刪除
			if (new QuestionHService().deleteQuestion(questionNo) == 1) {
				//指定跳轉的網址
				String url = "question-table.html";
				// 創建Map物件放入成功訊息
				Map<String, String> successMsg = new HashMap<String, String>();
				successMsg.put("success", "data transfer success");
				successMsg.put("url", url);
				String successjson = gson.toJson(successMsg);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(successjson);
				
			} else {
				// 創建Map物件放入錯誤訊息
				Map<String, String> errorMsgs = new HashMap<String, String>();
				errorMsgs.put("insertfail", "新增資料失敗");
				String errjson = gson.toJson(errorMsgs);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errjson);
			}
		}

		// 建立資料
		if ("insert".equals(action)) {
			// 創建Map物件放入錯誤訊息
			Map<String, String> errorMsgs = new HashMap<String, String>();

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer questionGroupNo = null;
			String qgnStr = req.getParameter("questionGroupNo");
			if (qgnStr.equals("default")) {
				errorMsgs.put("errQGno", "請選擇問題類別");
			} else {
				questionGroupNo = Integer.valueOf(qgnStr);
			}

			String questionTitle = req.getParameter("questionTitle").trim();
			if (questionTitle == null || questionTitle.trim().length() == 0) {
				errorMsgs.put("errTitle", "標題請勿空白");
			}

			String questionContent = req.getParameter("questionContent").trim();
			if (questionContent == null || questionContent.trim().length() == 0) {
				errorMsgs.put("errContent", "內容請勿空白");
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
			QuestionVO qvo = new QuestionHService().addQuestion(questionGroupNo, questionTitle, questionContent);

			/*************************** 3.新增完成,回傳成功訊息回前端 ***********/
			if (qvo != null) {
				//指定跳轉的網址
				String url = "question-table.html";
				// 創建Map物件放入成功訊息
				Map<String, String> successMsg = new HashMap<String, String>();
				successMsg.put("success", "data transfer success");
				successMsg.put("url", url);

				String successjson = gson.toJson(successMsg);

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(successjson);
			} else {
				errorMsgs.put("insertfail", "新增資料失敗");
				String errjson = gson.toJson(errorMsgs);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errjson);
			}
		}

		// 修改資料
		if ("update".equals(action)) {
			// 創建Map物件放入錯誤訊息
			Map<String, String> errorMsgs = new HashMap<String, String>();

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer questionNo = Integer.valueOf(req.getParameter("questionNo"));

			Integer questionGroupNo = null;
			String qgnStr = req.getParameter("questionGroupNo");
			if (qgnStr.equals("default")) {
				errorMsgs.put("errQGno", "請選擇問題類別");
			} else {
				questionGroupNo = Integer.valueOf(qgnStr);
			}

			String questionTitle = req.getParameter("questionTitle").trim();
			if (questionTitle == null || questionTitle.trim().length() == 0) {
				errorMsgs.put("errTitle", "標題請勿空白");
			}

			String questionContent = req.getParameter("questionContent").trim();
			if (questionContent == null || questionContent.trim().length() == 0) {
				errorMsgs.put("errContent", "內容請勿空白");
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
			QuestionVO qvo = new QuestionHService().updateQuestion(questionNo, questionGroupNo, questionTitle,
					questionContent);

			/*************************** 3.新增完成,回傳成功訊息回前端 ***********/
			if (qvo != null) {
				//指定跳轉的網址
				String url = "question-table.html";
				// 創建Map物件放入成功訊息
				Map<String, String> successMsg = new HashMap<String, String>();
				successMsg.put("success", "data transfer success");
				successMsg.put("url", url);

				String successjson = gson.toJson(successMsg);

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(successjson);
			} else {
				errorMsgs.put("updatefail", "修改資料失敗");
				String errjson = gson.toJson(errorMsgs);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(errjson);
			}
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
