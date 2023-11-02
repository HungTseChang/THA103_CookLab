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
//		res.sendRedirect("/CookLab"+forwardPath);

		System.out.println("跳轉頁面致"+forwardPath);
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		System.out.println("跳轉回先前頁面");
		dispatcher.forward(req, res);
		}
	
	
	private String forgetpassword(HttpServletRequest req, HttpServletResponse res) {
		String account = req.getParameter("account");
		System.out.println(account);
		String email = req.getParameter("email");	
		System.out.println(email);
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
		if(!AdminsVO.getAdminEmail().equals(email)) {
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
//		HttpSession Session  =  req.getSession();
//		Session.setAttribute("logout", true);
		System.out.println("LoginServlet收到登出請求");
   
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
//=============================管理員管理權限======================================			
//            管理員管理權限
//            客服管理權限
//            會員管理權限
//            廣告投放權限
//            檢舉管理權限
//            討論區權限
//           食譜管理權限
	        Map.put("/AdminsServlet", ((int)permission.getAdminManagement()==0));
	        Map.put("/PermissionServlet", ((int)permission.getAdminManagement()==0));
			
//==============================================================================			
//================================= 客服管理權限================================================		
	        Map.put("/dashboard/supportform/supportform-table.html", ((int)permission.getArticleManagement()==0));
	        Map.put("/dashboard/question/question-table.html", ((int)permission.getArticleManagement()==0));
	        Map.put("/dashboard/notifycenter/notify-table.html", ((int)permission.getArticleManagement()==0));
	        Map.put("/dashboard/news/news-table.html", ((int)permission.getArticleManagement()==0));


//   ===================================================================================
// =================================會員管理權限===============================================
           
           
	 
	        Map.put("/ArticleServlet", ((int)permission.getArticleManagement()==0));
	        Map.put("/ArticleImgServlet", ((int)permission.getArticleManagement()==0));
	        Map.put("/RecipeServlet", ((int)permission.getRecipeManagement()==0));
	        Map.put("/RecipeServletImg", ((int)permission.getRecipeManagement()==0));
	        Map.put("/MemberdashboardServlet", ((int)permission.getMembershipManagement()==0));

           
           
   
           
//  =========================================================================================         
//  ================================  廣告投放權限==================================================            
	        Map.put("/AdvertiseServlet", ((int)permission.getAdvertisingManagement()==0));

           
//  =========================================================================================        
//  ================================= 檢舉管理權限==================================================      

	        Map.put("/ArticleReportServlet", ((int)permission.getReportingManagement()==0));
	        Map.put("/ArticleSubReportServlet", ((int)permission.getReportingManagement()==0));
           
           
           
           
           
//  =========================================================================================         
//  ==================================  討論區權限=================================================   
           
	        Map.put("/ArticleServlet", ((int)permission.getArticleManagement()==0));
	        Map.put("/ArticleImgServlet", ((int)permission.getArticleManagement()==0));
           
           
           
           
//  =========================================================================================         
//  ==================================食譜管理權限================================================    
           
	        Map.put("/DashboardRecipeServlet", ((int)permission.getRecipeManagement()==0));
	        Map.put("/RecipeServlet", ((int)permission.getRecipeManagement()==0));
	        Map.put("/DisboardHastagServlet", ((int)permission.getRecipeManagement()==0));
	        Map.put("/RecipeServletImg", ((int)permission.getRecipeManagement()==0));

           
           
           
           
           
           
//  ======================================商城管理================================================         
//  =========================================================================================         
	        Map.put("/AdvertisetImgServlet", ((int)permission.getMallManagement()==0));
	        Map.put("/AdvertiseServlet", ((int)permission.getMallManagement()==0));
	        Map.put("/AdvertiseServlet2", ((int)permission.getMallManagement()==0));
	        Map.put("/dashboard/product/shopview.html", ((int)permission.getMallManagement()==0));
	        Map.put("/dashboard/memberOrder/TYT_order_management.html", ((int)permission.getMallManagement()==0));
	        Map.put("/dashboard/productTag/tagview.html", ((int)permission.getMallManagement()==0));


//	        System.out.println(Map.get("/AdminsServlet"));
//	        Map.put("/dashboard/advertise_allview.jsp", true);
//	        Map.put("/dashboard/advertise/advertise_getone.jsp", true);
//	        Map.put("/dashboard/advertise/advertise_set.jsp", true);
//	        Map.put("/dashboard/article/HO_discussion_allview.jsp", true);
//	        Map.put("/dashboard/article/HO_dscussion_cate.jsp", true);
//	        Map.put("/dashboard/article_report/WCC_article_report_info.jsp", true);
//	        Map.put("/dashboard/memberOrder/TYT_order_detail.html", true);
//	        Map.put("/dashboard/memberOrder/TYT_order_management.html", true);
//	        Map.put("/dashboard/news/news-add.html", true);
//	        Map.put("/dashboard/news/news-table.html", true);
//	        Map.put("/dashboard/news/news-update.html", true);
//	        Map.put("/dashboard/notifycenter/notify-add.html", true);
//	        Map.put("/dashboard/notifycenter/notify-details.html", true);
//	        Map.put("/dashboard/notifycenter/notify-table.html", true);
//	        Map.put("/dashboard/product/shopset.html", true);
//	        Map.put("/dashboard/product/shopupdate.html", true);
//	        Map.put("/dashboard/product/shopview.html", true);
//	        Map.put("/dashboard/productTag/tagview.html", true);
//	        Map.put("/dashboard/promo_code/promo_code_allview.jsp", true);
//	        Map.put("/dashboard/promo_code/promo_code_getone.jsp", true);
//	        Map.put("/dashboard/promo_code/promo_code_set.jsp", true);
//	        Map.put("/dashboard/question/question-add.html", true);
//	        Map.put("/dashboard/question/question-table.html", true);
//	        Map.put("/dashboard/question/question-update.html", true);
//	        Map.put("/dashboard/recipe_report/WCC_recipe_report_info.jsp", true);
//	        Map.put("/dashboard/supportform/supportform-add.html", true);
//	        Map.put("/dashboard/supportform/supportform-details.html", true);
//	        Map.put("/dashboard/supportform/supportform-table.html", true);
	      
	
	        
	        
	 	
	        System.out.println(Map.get("/AdminsServlet"));
	        System.out.println("當前權限列表:"+Map);
	        System.out.println("所取得的帳號為"+adminAccount);
			Session.setAttribute("thisaccount", adminAccount);
			Session.setAttribute("nickname", nickname);
			Session.setAttribute("permissionlist", Map);
			Session.setAttribute("adminID", adminID);
          if(Session.getAttribute("location") != null) {
        	  return (String) Session.getAttribute("location");
          }
			return "/dashboard/login/WCC_welcome.jsp";
//          return "/dashboard/product/shopview.html";
			}
		}
		req.removeAttribute("error");
		req.setAttribute("error", "帳號或密碼錯誤");
		System.out.println("帳號或密碼錯誤");
		return  "/dashboard/login/WCC_login.jsp";
	}

	
	
}