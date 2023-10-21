package com.cooklab.members.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import com.cooklab.members.model.MembersService;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cooklab.members.model.MembersVO;

@WebServlet("/Sign")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB

public class MembersSignServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//doPost(req, res);

		HttpSession session = req.getSession();
		System.out.println(session);
		if(session.getAttribute("account")!= null)
		{
			System.out.println(session.getAttribute("account"));
			res.sendRedirect("http://localhost:8081/CookLab/members/member-panel.html");
			
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
        // 獲取 PrintWriter 用來輸出到前端  =========================================
        PrintWriter out = res.getWriter();
		
		//取得Ajax傳來的帳號密碼 ====================================================
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		
		
		//加密 ==================================================================
		
		//驗證 ==================================================================
		MembersService memSer = new MembersService();
		MembersVO memVO = new MembersVO();
		
		// 在Servlet中创建一个Gson对象
		Gson gson = new Gson();
		HashMap<String, Object> hmap = new HashMap<>();
		
		try{
			memVO = memSer.getOneMemberAccount(account);
			if(memVO.getMemberPassword() != null)
			{
				
				if(password.equals(memVO.getMemberPassword()))
				{
//					out.print("密碼正確");
					//創造一個 session 物件
					HttpSession session = req.getSession();
					session.setAttribute("account", account);
					session.setAttribute("userId", memVO.getMemberId());
//					session.setAttribute("RedirectURL",session.getAttribute("location"));
					

					hmap.put("account", account);
					hmap.put("userId", memVO.getMemberId());
					hmap.put("RedirectURL",session.getAttribute("location"));
					String jsonData = gson.toJson(hmap);
					

					// 设置响应的Content Type为JSON
//					res.setContentType("application/json");
					// 将JSON数据写入响应
					res.getWriter().write(jsonData);
					
				}
				else
				{
					hmap.put("wrongType", "1");	//密碼不正確
					String jsonData = gson.toJson(hmap);
					res.getWriter().write(jsonData);
				}
			}
			else
			{
				hmap.put("wrongType", "2");	//帳號不正確
				String jsonData = gson.toJson(hmap);
				res.getWriter().write(jsonData);
			}
				
//			System.out.println(memVO.getMemberPassword());
		}catch(NullPointerException e) {
			hmap.put("wrongType", "3");	//未輸入帳密
		}

		//=====================================================================
//		System.out.println(account);
//		System.out.println(password);
        // 进行其他操作
	}
}
