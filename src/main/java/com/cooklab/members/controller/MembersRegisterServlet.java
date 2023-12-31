package com.cooklab.members.controller;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import com.cooklab.members.model.MembersService;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.cooklab.members.model.MembersVO;
import com.cooklab.util.JedisUtil;
import com.cooklab.members.controller.*;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Jedis;
@WebServlet("/Register")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB

public class MembersRegisterServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//此處方法作用為 在[註冊後] 去抓取User Id 提供給驗證信用
		HttpSession session = req.getSession();
		System.out.println(session.getAttribute("account"));
		System.out.println(session.getAttribute("userId"));
		

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
		String password = req.getParameter("password").trim();
		String gender = req.getParameter("gender");
		String nickname = req.getParameter("nickname").trim();
		String birthdate = req.getParameter("birthdate");
		String address = req.getParameter("address").trim();
		String location = req.getParameter("location");
		String phonenumber = req.getParameter("phonenumber").trim();
		String email = req.getParameter("email").trim();
		String introduce = req.getParameter("introduction").trim();
		
		//加入預設圖片 ==================================================================
		
		String imagePath = getServletContext().getRealPath("/frontstage/img/defaultMember.png");
		System.out.println(imagePath);
		byte[] pic =  getPictureByteArray(imagePath);
		System.out.println(pic.toString());
		
		//驗證 ==================================================================
		MembersVO memVO = new MembersVO();
		
		memVO.setMemberAccount(account);
		memVO.setMemberPassword(password);
		
		byte genderByte = Byte.parseByte(gender);
		memVO.setMemberGender(genderByte);
		
		memVO.setMemberNickname(nickname);
		//日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate = null;
		try {
		    parsedDate = sdf.parse(birthdate);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
		memVO.setMemberDate(sqlDate);

		//
		memVO.setMemberAddress(address);
		memVO.setMemberCountry(location);
		memVO.setMemberCellphone(phonenumber);
		memVO.setMemberMail(email);
		
		//寫入資料庫
		MembersService memSrv = new MembersService();
		//先檢查是否有重複帳號
		MembersVO accountNull = memSrv.getOneMemberAccount(account);
		
		if(accountNull != null)	//有重複帳號
		{
			System.out.println("有重複帳號");
			hmap.put("res", "AccountFail");
			String jsonString = gson.toJson(hmap);
			res.getWriter().write(jsonString);	
			return;
		}
		//檢查EMAIL是否重複
		MembersVO emailNull = memSrv.getOneMemberMail(email);
		
		if(emailNull != null)	//有重複MAIL
		{
			System.out.println("有重複MAIL");
			hmap.put("res", "EmailFail");
			String jsonString = gson.toJson(hmap);
			res.getWriter().write(jsonString);	
			return;
		}
		
		memVO = memSrv.addMembers(account,password,introduce,
				phonenumber,email, sqlDate,address,location,(byte) 1,nickname,genderByte,pic);
		//再查一次
		MembersService memSrv2 = new MembersService();
		memVO = memSrv2.getOneMemberAccount(account);
		
		//取得 session 物件
		HttpSession session = req.getSession();
		session.setAttribute("account", account);
		session.setAttribute("userId", memVO.getMemberId());
		session.setAttribute("membersVO", memVO);
		
		//發送驗證 mail 
		MailService msv = new MailService();
		String to = email;
		String subject = "廚藝實驗室:驗證碼通知";
		String ch_name = nickname;
		String passRandom = msv.genAuthCode();
		String messageText =  ch_name +" 您好!\n\n["+ passRandom +"]\n\n為您在廚藝實驗室(CookLab)的驗證碼，請於10分鐘內輸入" +"\n" ;

		MailService mailService = new MailService();
		new Thread(()->mailService.sendMail(to, subject, messageText)).start();
		
		//將驗證碼存入Redis
		Jedis jedis = JedisUtil.getJedisPool().getResource();
		jedis.select(14);
		jedis.set("Member:"+ memVO.getMemberId(), passRandom);
		jedis.expire("Member:"+memVO.getMemberId(), 600);
		jedis.close();
		//跳轉頁面
		hmap.put("res","success");
		String jsonData = gson.toJson(hmap);
		res.setContentType("application/json");// 设置响应的Content Type为JSON
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(jsonData);// 将JSON数据写入响应
		
//		req.setAttribute("membersVO", memVO);
//		String url = "/members/VerificationLetter.html";
//		System.out.println("轉送到"+url);
//		RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//		successView.forward(req, res);
		
//		req.setAttribute("membersVO", memVO);
//		String url = "/members/VerificationLetter.html";
//		System.out.println("重定向到" + url);
//		String contextPath = req.getContextPath(); // 获取Servlet上下文路径
//		res.sendRedirect(contextPath + url); // 执行重定向

//		try{
//			//查出 memberVO
//			memVO = memSer.getOneMemberAccount(account);
//			//如果密碼不為空值
//			if(memVO.getMemberPassword() != null)
//			{
//				//如果密碼正確
//				if(password.equals(memVO.getMemberPassword()))
//				{ 
//					//創造一個 session 物件
//					HttpSession session = req.getSession();
//					session.setAttribute("account", account);
//					session.setAttribute("userId", memVO.getMemberId());
//					session.setAttribute("membersVO", memVO);
//					
//					hmap.put("account", account);
//					hmap.put("userId", memVO.getMemberId());
//					hmap.put("RedirectURL",session.getAttribute("location"));
//					String jsonData = gson.toJson(hmap);
//					
//
//					// 设置响应的Content Type为JSON
//					res.setContentType("application/json");
//					res.setCharacterEncoding("UTF-8");
//					
//					// 将JSON数据写入响应
//					res.getWriter().write(jsonData);
//					
//				}
//				//如果密碼不正確
//				else
//				{
//					hmap.put("wrongType", "1");	
//					System.out.println("1");
//					String jsonData = gson.toJson(hmap);
//					// 设置响应的Content Type为JSON
//					res.setContentType("application/json");
//					res.setCharacterEncoding("UTF-8");
//					res.getWriter().write(jsonData);
//				}
//			}
//			//如果密碼為空值
//			else
//			{
//				hmap.put("wrongType", "2");	//帳號不正確
//				System.out.println("2");
//				String jsonData = gson.toJson(hmap);
//				// 设置响应的Content Type为JSON
//				res.setContentType("application/json");
//				res.setCharacterEncoding("UTF-8");
//				res.getWriter().write(jsonData);
//			}
//		}catch(NullPointerException e) {
//			hmap.put("wrongType", "3");	//查無此帳號
//			System.out.println("3");
//			String jsonData = gson.toJson(hmap);
//			// 设置响应的Content Type为JSON
//			res.setContentType("application/json");
//			res.setCharacterEncoding("UTF-8");
//			res.getWriter().write(jsonData);
//		}

	}
	// 使用byte[]方式
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = fis.readAllBytes();
		fis.close();
		return buffer;
	}
}
