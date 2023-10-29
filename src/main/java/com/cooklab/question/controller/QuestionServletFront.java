package com.cooklab.question.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.question.service.QuestionHService;
import com.cooklab.support_form.model.SFStatus;
import com.google.gson.Gson;

@WebServlet("/QSFront")
public class QuestionServletFront extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 將請求物件的編碼設置為UTF-8避免亂碼問題
		req.setCharacterEncoding("UTF-8");

		// 透過前端傳來的action觸發不同的功能
		String action = req.getParameter("action");

		// 創建Gson物件以便將資料打包為json格式回傳給前端
		Gson gson = new Gson();

		//TODO 根據選取的問題群組取得不同的資料(Redis)
		if ("getQbyGroup".equals(action)) {
			Integer qGroup = Integer.valueOf(req.getParameter("qGroup"));

			switch (qGroup) {
			case 1: //食譜問題
				
				break;
			case 2://討論區問題
				
				break;
			case 3://帳號問題
				
				break;
				
			case 4://帳務問題
				
				break;
			case 5://活動問題
				
				break;
				
			case 6://購物與訂單
				
				break;
			default:
				//TODO導回空頁面
			}

			// TODO 呼叫Service方法的getAll，取得DTO物件List，使用Gson轉換為Json格式
//			String Data = gson.toJson(new QuestionHService().getAllDTO());

			//TODO 回傳資料給Ajax進行進一步的處理
//			res.setContentType("application/json");
//			res.setCharacterEncoding("UTF-8");
//			res.getWriter().write(Data);
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
