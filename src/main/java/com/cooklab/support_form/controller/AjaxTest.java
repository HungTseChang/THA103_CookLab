package com.cooklab.support_form.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AjaxTest")
public class AjaxTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//因為已經在doPost寫了完整程式碼，故直接在此呼叫doPost方法以達到重複利用代碼的目的
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//設定送來請求的物件編碼
		request.setCharacterEncoding("UTF-8");
		//透過送來的資料中取得action的值來辨識這串資料的處理方式，以下以新增資料為範例
		String action = request.getParameter("action");
		if ("insert".equals(action)) {
			String realName = request.getParameter("realName");
			Integer supportFormCategoryId = Integer.valueOf(request.getParameter("supportFormCategoryId"));
			String replyEmail = request.getParameter("replyEmail");
			String formTitle = request.getParameter("formTitle");
			String formContext = request.getParameter("formContext");
			System.out.println(realName);
			System.out.println(supportFormCategoryId);
			System.out.println(replyEmail);
			System.out.println(formTitle);
			System.out.println(formContext);
			
			//回傳資料給發出請求的ajax後續進行其他行為
			response.getWriter().write("ok");;
		}
	}

}
