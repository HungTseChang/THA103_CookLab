package com.cooklab.admins.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;

import com.cooklab.admins.EmailSender;
import com.cooklab.admins.model.AdminsService;
import com.cooklab.admins.model.AdminsVO;
import com.cooklab.members.model.MembersVO;
import com.cooklab.permission.model.*;
import com.cooklab.util.HibernateUtil;
import com.google.gson.Gson;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
private List<AdminsVO>Adminslist;



	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(this.Adminslist==null) {
		AdminsService AdminsService = new AdminsService();
		this.Adminslist = AdminsService.getAll();
		
		}
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "login":
			forwardPath = login(req, res);
			break;
		case "logout":
			forwardPath = logout(req, res);
			break;
		case "forgetpassword":
			forwardPath = forgetpassword(req, res);
			return;
//			break;
		default:
			forwardPath = "/dashboard/login/WCC_login.jsp";
		}
		System.out.println("跳轉頁面致"+forwardPath);
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		System.out.println("跳轉回先前頁面");
		dispatcher.forward(req, res);
		}
	
	
	private String forgetpassword(HttpServletRequest req, HttpServletResponse res) {
		String account = req.getParameter("account");
		String email = req.getParameter("email");	
		EmailSender EmailSender = new EmailSender();
		String	rndpassword = EmailSender.randomPassword(12);
		Optional <AdminsVO> result1;
		result1 = this.Adminslist.stream().filter(e->e.getAdminAccount().equals(account)).findFirst();	
		 String response = "fail";
		 
		 
//================================		 
		if(result1.isEmpty()) {
			response="發送密碼失敗，查無此帳號";
			System.out.println("發送密碼失敗，查無此帳號");
		}else {
			
		
		AdminsVO AdminsVO =	result1.get();
		if(!AdminsVO.getAdminAccount().equals(email)) {
			response="發送密碼失敗，錯誤信箱";
			System.out.println("發送密碼失敗，錯誤信箱");
		}else {
		
		System.out.println("確認帳號和信箱無誤");
		AdminsVO.setAdminPassword(rndpassword);
	   AdminsService AdminsService = new AdminsService();
	AdminsVO result = AdminsService.update(AdminsVO);
	System.out.println("更新完成");

		 if(result != null) {
			 
			 String subtitlte ="廚藝實驗室: 您的密碼已重設";
			 String context =account+"您好: 你的密碼已重設為: "+rndpassword+"請記得更新你的密碼";
					 EmailSender.sendMail(email, subtitlte,context);
					 response="success";
		 }else {
				System.out.println("更新失敗");
			 response="更新失敗，請聯絡客服人員";
		 }
		 
		 
		}      //判定帳號與信箱是否相符合
		}		 //判定是否有該帳號
//	===========================	 
		 try {
			 res.setCharacterEncoding("UTF-8");
			 res.setContentType("text/plain");
			 PrintWriter writer = res.getWriter();
			 System.out.println("傳送訊息給前端:"+response);
			 writer.write(response);
			 writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 this.Adminslist=null;
			return"/dashboard/login/WCC_login.jsp";
			
		}


	private String logout(HttpServletRequest req, HttpServletResponse res) {
		HttpSession Session  =  req.getSession();
		Session.setAttribute("logout", true);
		System.out.println("嘗試登出");
   
		return "/dashboard/login/WCC_login.jsp";
	}


	private String login(HttpServletRequest req, HttpServletResponse res) {
		String account = req.getParameter("account");
		String password = req.getParameter("password");	
		HttpSession Session  =  req.getSession();
		Optional <AdminsVO> result;
		result = this.Adminslist.stream().filter(e->e.getAdminAccount().equals(account)).findFirst();
		System.out.println("查詢是否有帳號");
		if (result.isPresent()) {
			System.out.println("查詢密碼是否有誤");
			if(result.get().getAdminPassword().equals(password)) {
//			PermissionVO permission =result.get().getPermissionVO();
			Integer  adminID = result.get().getAdminNo();
			String  nickname = result.get().getAdminNickname();
			String adminAccount = result.get().getAdminAccount();		
			PermissionService PermissionService = new PermissionService();
			PermissionVO permission = PermissionService.getOne(result.get().getPermissionNo());
			Map<String, Boolean>Map = new HashMap<>();
	        Map.put("/AdminsServlet", ((int)permission.getSuperAdmin()==0));
	        Map.put("/PermissionServlet", ((int)permission.getSuperAdmin()==0));
	        Map.put("ArticleServlet", ((int)permission.getArticleManagement()==0));
	        Map.put("/ArticleImgServlet", ((int)permission.getArticleManagement()==0));
	        Map.put("/RecipeServlet", ((int)permission.getRecipeManagement()==0));
	        Map.put("/RecipeServletImg", ((int)permission.getRecipeManagement()==0));
	        Map.put("/MemberdashboardServlet", ((int)permission.getMembershipManagement()==0));
	        Map.put("/AdvertiseServlet", ((int)permission.getAdvertisingManagement()==0));
	        Map.put("/ArticleReportServlet", ((int)permission.getReportingManagement()==0));
	        Map.put("/ArticleSubReportServlet", ((int)permission.getReportingManagement()==0));
	        Map.put("/AdvertiseServlet", ((int)permission.getAdvertisingManagement()==0));
	        System.out.println(Map.get("/AdminsServlet"));
	        System.out.println("所取得的帳號為"+adminAccount);
			Session.setAttribute("thisaccount", adminAccount);
			Session.setAttribute("nickname", nickname);
			Session.setAttribute("permissionlist", Map);
			Session.setAttribute("adminID", adminID);
          if(Session.getAttribute("location") != null) {
        	    if(req.getHeader("orginURL")!=null) {
        	    	Session.setAttribute("location", req.getHeader("orginURL"));
                    String responseData = "{ \"redirectURL\": \"" + req.getContextPath() + "/frontstage/members/login.html" + "\" }";
                    try {
						res.getWriter().write(responseData);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        	    }
        	  return (String) Session.getAttribute("location");
          }
			return "/dashboard/login/WCC_welcome.jsp";
			}
		}
		req.removeAttribute("error");
		req.setAttribute("error", "帳號或密碼錯誤");
		return  "/dashboard/login/WCC_login.jsp";
	}

	
	
}