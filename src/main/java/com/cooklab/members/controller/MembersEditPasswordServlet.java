package com.cooklab.members.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cooklab.members.model.MembersService;
import com.cooklab.members.model.MembersVO;
import com.google.gson.Gson;

@WebServlet("/EditPassword")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class MembersEditPasswordServlet  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");

        PrintWriter out = res.getWriter();
		HashMap<String, Object> hmap = new HashMap<>();
		
		HttpSession session = req.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		MembersVO memVO = (MembersVO)session.getAttribute("membersVO");
		//取得Ajax傳來的新增資訊 ====================================================
		String oldpassword = req.getParameter("oldpassword");
		String password = req.getParameter("password");
		String rePassword = req.getParameter("rePassword");
		
		//當舊密碼與資料庫相同時
		if(oldpassword.equals(memVO.getMemberPassword()))
		{
			MembersService Msr = new MembersService();
			 Msr.updateMemberPassword(userId,password);
			 memVO = Msr.getOneMember(userId);
			//放回新的membersVO
			session.setAttribute("account",  memVO.getMemberAccount());
			session.setAttribute("userId", memVO.getMemberId());
			session.setAttribute("membersVO",memVO);
			hmap.put("Sres","success");
		}
		else
		{
			hmap.put("Sres","fail");
		}
		
		
		
		

		Gson gson = new Gson();
		String jsonData = gson.toJson(hmap);
		res.setContentType("application/json");// 设置响应的Content Type为JSON
		res.setCharacterEncoding("UTF-8");
		out.write(jsonData);// 将JSON数据写入响应
	}
	
}
