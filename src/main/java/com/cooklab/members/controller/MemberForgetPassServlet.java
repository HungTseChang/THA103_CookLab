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

import com.cooklab.members.model.MembersService;
import com.cooklab.members.model.MembersVO;
import com.google.gson.Gson;

@WebServlet("/ForgetPassowrd")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class MemberForgetPassServlet extends HttpServlet{

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
		
		if(email.equals(memVO.getMemberMail()))		//EMAIL吻合
		{
			//亂數產生一個密碼

			MailService msv = new MailService();
			String passRandom = msv.genAuthCode();
			
			//直接幫忙改密碼
			memVO.setMemberPassword(passRandom);
			//發送驗證 mail 
			String to = email;
			String subject = "廚藝實驗室:驗證碼通知";
			String ch_name = memVO.getMemberNickname();
			String messageText =  ch_name +" 您好!\n\n["+ passRandom +"]\n\n為您在廚藝實驗室(CookLab)的新密碼" +"\n" ;

			MailService mailService = new MailService();
			new Thread(()->mailService.sendMail(to, subject, messageText)).start();	
			hmap.put("res","success");
		}
		else
		{
			hmap.put("res","fail");
		}
		String jsonData = gson.toJson(hmap);
		res.setContentType("application/json");// 设置响应的Content Type为JSON
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonData);// 将JSON数据写入响应
	}
}
