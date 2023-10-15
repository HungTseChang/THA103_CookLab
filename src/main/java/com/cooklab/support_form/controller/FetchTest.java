package com.cooklab.support_form.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FetchTest")
public class FetchTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
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
			String url = "/THA103_CookLab/supportform/supportcenter-formresult.html";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);
		}
	}

}
