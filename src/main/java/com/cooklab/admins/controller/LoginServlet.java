package com.cooklab.admins.controller;

import java.io.IOException;
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

import com.cooklab.admins.model.AdminsService;
import com.cooklab.admins.model.AdminsVO;
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
		default:
			forwardPath = "/dashboard/login/WCC_login.jsp";
		}
		System.out.println("跳轉頁面致"+forwardPath);
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		System.out.println("跳轉回先前頁面");
		dispatcher.forward(req, res);
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
			Session.setAttribute("account", adminAccount);
			Session.setAttribute("nickname", nickname);
			Session.setAttribute("permissionlist", Map);
			Session.setAttribute("adminID", adminID);
          if(Session.getAttribute("location") != null) {
        	  System.out.println(Session.getAttribute("location") );
        	  return (String) Session.getAttribute("location");
          }
			return "/dashboard/login/WCC_welcome.jsp";
			}
		}
		req.setAttribute("error", "帳號或密碼錯誤");
		return  "/dashboard/login/WCC_login.jsp";
	}

	
	
}