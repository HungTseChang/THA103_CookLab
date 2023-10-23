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

import redis.clients.jedis.Jedis;
@WebServlet("/Verification")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class MemberVerificationServlet  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		HashMap<String, Object> hmap = new HashMap<>();
		//取得使用者輸入驗證碼
		String verCode = req.getParameter("verCode");
		
        // 獲取 PrintWriter 用來輸出到前端  =========================================
        PrintWriter out = res.getWriter();
        //=====================================================================
		MembersVO memVO = new MembersVO();
		//取得 session 物件
		HttpSession session = req.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		//連接Redis
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(14);
		String tempAuth = jedis.get("Member:"+userId);

		if(verCode.equals(tempAuth))
		//如果驗證碼比對成功
		{
			MembersService memSvc = new MembersService();
			memSvc.updateMemberStatus((Integer)userId,(byte)0);
			hmap.put("Sres","success");
			//再查一次
			memVO = memSvc.getOneMember(userId);
			
			//取得 session 物件
			session.setAttribute("account", memVO.getMemberAccount());
			session.setAttribute("userId", memVO.getMemberId());
			session.setAttribute("membersVO", memVO);
			
			
			String jsonData = gson.toJson(hmap);
			// 设置响应的Content Type为JSON
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			// 将JSON数据写入响应
			res.getWriter().write(jsonData);
			System.out.println("驗證成功!");
			
		}
		else
		//如果驗證碼比對失敗
		{
			hmap.put("Sres","fail");
			String jsonData = gson.toJson(hmap);
			// 设置响应的Content Type为JSON
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			// 将JSON数据写入响应
			res.getWriter().write(jsonData);
		}
		//如果驗證碼比對失敗
		
	}
	
}
