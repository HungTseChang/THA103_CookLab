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
import com.cooklab.util.JedisUtil;
import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

@WebServlet("/ReVerification")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class MemberReSendVerMailServlet  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		HashMap<String, Object> hmap = new HashMap<>();
		
        // 獲取 PrintWriter 用來輸出到前端  =========================================
        PrintWriter out = res.getWriter();
        //=====================================================================
		MembersVO memVO = new MembersVO();
		MembersService msr = new MembersService();
		//取得 session 物件
		HttpSession session = req.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		memVO= msr.getOneMember(userId);
		String email = memVO.getMemberMail();
		//發送驗證 mail 
		MailService msv = new MailService();
		String to = email;
		String subject = "廚藝實驗室:驗證碼通知";
		String ch_name = memVO.getMemberNickname();
		String passRandom = msv.genAuthCode();
		String messageText =  ch_name +" 您好!\n\n["+ passRandom +"]\n\n為您在廚藝實驗室(CookLab)的驗證碼，請於10分鐘內輸入" +"\n" ;
		System.out.println("記出驗證信到"+to+"給"+ch_name);
		MailService mailService = new MailService();
		new Thread(()->mailService.sendMail(to, subject, messageText)).start();	
		
		//將驗證碼存入Redis
		Jedis jedis = JedisUtil.getJedisPool().getResource();
		jedis.select(14);
		jedis.set("Member:"+ memVO.getMemberId(), passRandom);
		jedis.expire("Member:"+memVO.getMemberId(), 600);
		jedis.close();
		
		//

		hmap.put("res","success");
		String jsonData = gson.toJson(hmap);
		res.setContentType("application/json");// 设置响应的Content Type为JSON
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonData);// 将JSON数据写入响应
	}
}
