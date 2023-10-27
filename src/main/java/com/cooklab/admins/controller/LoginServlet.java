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
import com.cooklab.permission.model.PermissionVO;
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
		default:
			forwardPath = "/dashboard/login/WCC_login.jsp";
		}
		System.out.println("跳轉頁面致"+forwardPath);
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		System.out.println("跳轉回先前頁面");
		dispatcher.forward(req, res);
		}
	
	
	private String login(HttpServletRequest req, HttpServletResponse res) {
		String account = req.getParameter("account");
		String password = req.getParameter("password");	
		Optional <AdminsVO> result;
		result = this.Adminslist.stream().filter(e->e.getAdminAccount().equals(account)).findFirst();
		if (result.isPresent()) {
			if(result.get().getAdminPassword().equals(password)) {
			HttpSession Session  =  req.getSession();
			PermissionVO permission =result.get().getPermissionVO();
			
			String adminAccount = result.get().getAdminAccount();		
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
			Session.setAttribute("permissionlist", Map);
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