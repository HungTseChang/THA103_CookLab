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


@WebServlet("/CheckLogin")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class MembersCheckLoginServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
        // 獲取 PrintWriter 用來輸出到前端  =========================================
        PrintWriter out = res.getWriter();
        String action = req.getParameter("action");
		if(action.equals("CheckLogin"))
		{;
			if(session.getAttribute("account") != null)
				out.write("OK");
			else
				out.write("NO");
		}
		
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
        // 獲取 PrintWriter 用來輸出到前端  =========================================
        PrintWriter out = res.getWriter();

        String action = req.getParameter("action");
        
        //先拿好 Session
		if(action.equals("getSession"))
		{
			//查出使用者資料丟進VO
			String account = req.getParameter("account");
			MembersVO memVO = new MembersService().getOneMemberAccount(account);
			
			HttpSession session = req.getSession();
			session.setAttribute("account", memVO.getMemberAccount());
			session.setAttribute("userId", memVO.getMemberId());
			session.setAttribute("membersVO", memVO);
			session.setAttribute("verStatus", memVO.getMemberStatus());
			
			// 在Servlet中创建一个Gson对象
			Gson gson = new Gson();
			HashMap<String, Object> hmap = new HashMap<>();
			hmap.put("RedirectURL",session.getAttribute("location"));
			String jsonData = gson.toJson(hmap);
			// 设置响应的Content Type为JSON
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			// 将JSON数据写入响应
			res.getWriter().write(jsonData);

			System.out.println("使用者使用[記住帳號]功能，不須登入");
		}

        
	}
}
