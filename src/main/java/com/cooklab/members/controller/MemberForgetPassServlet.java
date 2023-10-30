package com.cooklab.members.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.members.model.MembersService;
import com.cooklab.members.model.MembersVO;
import com.google.gson.Gson;

@WebServlet("/ForgetPassowrd")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class MemberForgetPassServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
        // 獲取 PrintWriter 用來輸出到前端  =========================================
        PrintWriter out = res.getWriter();
        HashMap<String, Object> hmap = new HashMap<>();
		Gson gson = new Gson();
		//取得Ajax傳來的新增資訊 ====================================================
		String account = req.getParameter("account").trim();
		String email = req.getParameter("email").trim();
		

		MembersService memSer = new MembersService();
		MembersVO memVO = new MembersVO();
		
		memVO = memSer.getOneMemberAccount(account);
//		if(email.equals(memVO))
	}
}
