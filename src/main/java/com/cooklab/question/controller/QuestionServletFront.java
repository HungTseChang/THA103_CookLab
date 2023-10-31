package com.cooklab.question.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.question.model.QuestionDTO;
import com.cooklab.question.service.QuestionHService;
import com.cooklab.question_group.service.QuestionGroupHService;
import com.google.gson.Gson;

@WebServlet("/QSFront")
public class QuestionServletFront extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 將請求、回應物件的編碼設置為UTF-8避免亂碼問題，並使用Gsontojson格式統一傳送資料
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();

		// 透過前端傳來的action觸發不同的功能
		String action = req.getParameter("action");

		// 根據選取的問題群組呼叫Service層取得各群組的資料並做分頁處理
		if ("getQbyGroup".equals(action)) {
			String qGroupStr = req.getParameter("qGroup");
			if (qGroupStr == null || qGroupStr.trim().isEmpty()) {
				Map<String, String> errorMsgs = new HashMap<String, String>();
				String url = "faq.html";
				errorMsgs.put("url", url);
				errorMsgs.put("error", "問題類別代碼異常，請重新回到常見問題選擇類別");
				String data = gson.toJson(errorMsgs);
				res.getWriter().write(data);
				return;
			}

			String realNameReg = "^[1-6]$";
			if (!qGroupStr.trim().matches(realNameReg)) {
				Map<String, String> errorMsgs = new HashMap<String, String>();
				String url = "faq.html";
				errorMsgs.put("url", url);
				errorMsgs.put("error", "問題類別代碼異常，請重新回到常見問題選擇類別");
				String data = gson.toJson(errorMsgs);
				res.getWriter().write(data);
				return;
			}

			int qGroup = Integer.parseInt(qGroupStr);

			Integer page = Integer.valueOf(req.getParameter("page"));
			Integer pagesize = Integer.valueOf(req.getParameter("pagesize"));

			// 取得資料後再度包裝送給前端
			Map<String, Object> successMsgs = new HashMap<String, Object>();
			QuestionHService qsvc = new QuestionHService();
			List<QuestionDTO> jsondata = qsvc.getAllbyGroupByTen(qGroup, page, pagesize);
			successMsgs.put("groupname", new QuestionGroupHService().getOneQuestionGroup(qGroup).getQuestionName());
			successMsgs.put("totalPages", Integer.toString((qsvc.getGroupCount(qGroup) / pagesize) + 1));
			System.out.println("目前總頁數" + Integer.toString((qsvc.getGroupCount(qGroup) / pagesize) + 1));
			successMsgs.put("jsondata", jsondata);
			String data = gson.toJson(successMsgs);

			// 回傳資料給Ajax進行進一步的處理
			res.getWriter().write(data);
		}

		if ("getQbyKeyword".equals(action)) {
			String keyword = req.getParameter("keyword").trim();

			Integer page = Integer.valueOf(req.getParameter("page"));

			Integer pagesize = Integer.valueOf(req.getParameter("pagesize"));

			Map<String, Object> successMsgs = new HashMap<String, Object>();
			QuestionHService qsvc = new QuestionHService();

			List<QuestionDTO> jsondata = qsvc.getByKeywordByTen(keyword, page, pagesize);
			System.out.println("陣列大小"+jsondata.size());
			if (jsondata == null || jsondata.size() == 0) {
				Map<String, Object> errorMsgs = new HashMap<String, Object>();
				errorMsgs.put("failresult", "查無相關資料");
				String errorData = gson.toJson(errorMsgs);
				res.getWriter().write(errorData);
			} else {
				successMsgs.put("keyword", keyword);
				successMsgs.put("totalPages", Integer.toString((qsvc.getSearchResultCount(keyword) / pagesize) + 1));
				System.out.println("目前總頁數" + Integer.toString((qsvc.getSearchResultCount(keyword) / pagesize) + 1));
				successMsgs.put("jsondata", jsondata);
				String data = gson.toJson(successMsgs);

				// 回傳資料給Ajax進行進一步的處理
				res.getWriter().write(data);
			}

		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
