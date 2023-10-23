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

@WebServlet("/Register")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB

public class MembersRegisterServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
        // 獲取 PrintWriter 用來輸出到前端  =========================================
        PrintWriter out = res.getWriter();
		
		//取得Ajax傳來的新增資訊 ====================================================
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String nickname = req.getParameter("nickname");
		String birthdate = req.getParameter("birthdate");
		String address = req.getParameter("address");
		String location = req.getParameter("location");
		String phonenumber = req.getParameter("phonenumber");
		String email = req.getParameter("email");
		
		//加密 ==================================================================
		
		//驗證 ==================================================================
		MembersService memSer = new MembersService();
		MembersVO memVO = new MembersVO();

		// 在Servlet中创建一个Gson对象
		Gson gson = new Gson();
		HashMap<String, Object> hmap = new HashMap<>();
		
		try{
			//查出 memberVO
			memVO = memSer.getOneMemberAccount(account);
			//如果密碼不為空值
			if(memVO.getMemberPassword() != null)
			{
				//如果密碼正確
				if(password.equals(memVO.getMemberPassword()))
				{
					//創造一個 session 物件
					HttpSession session = req.getSession();
					session.setAttribute("account", account);
					session.setAttribute("userId", memVO.getMemberId());
					session.setAttribute("membersVO", memVO);
					
					hmap.put("account", account);
					hmap.put("userId", memVO.getMemberId());
					hmap.put("RedirectURL",session.getAttribute("location"));
					String jsonData = gson.toJson(hmap);
					

					// 设置响应的Content Type为JSON
					res.setContentType("application/json");
					res.setCharacterEncoding("UTF-8");
					
					// 将JSON数据写入响应
					res.getWriter().write(jsonData);
					
				}
				//如果密碼不正確
				else
				{
					hmap.put("wrongType", "1");	
					System.out.println("1");
					String jsonData = gson.toJson(hmap);
					// 设置响应的Content Type为JSON
					res.setContentType("application/json");
					res.setCharacterEncoding("UTF-8");
					res.getWriter().write(jsonData);
				}
			}
			//如果密碼為空值
			else
			{
				hmap.put("wrongType", "2");	//帳號不正確
				System.out.println("2");
				String jsonData = gson.toJson(hmap);
				// 设置响应的Content Type为JSON
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(jsonData);
			}
		}catch(NullPointerException e) {
			hmap.put("wrongType", "3");	//查無此帳號
			System.out.println("3");
			String jsonData = gson.toJson(hmap);
			// 设置响应的Content Type为JSON
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(jsonData);
		}

	}
}
