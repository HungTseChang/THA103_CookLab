package com.cooklab.admins.controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cooklab.admins.model.*;
import com.google.gson.Gson;
@WebServlet("/AdminsServlet")
public class AdminsServlet  extends HttpServlet{
	private static final long serialVersionUID = 1L;
    

    public AdminsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case "inserAdmins":
			forwardPath = inserAdmins(req, res);
			break;
		case"getAlladmins":
			forwardPath = getAlladmins(req,res);
			break;
		case"getOne_For_Update":
			forwardPath = getOne_For_Update(req, res);
			break;
		case"updateAdmins":
		forwardPath = updateAdmins(req, res);
		break;
		case"delete":
			forwardPath = delete(req,res);
		default:
			forwardPath = "/dashboard/admin/WCC_permission_management.jsp";
	}
//		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

		}

	private String delete(HttpServletRequest req, HttpServletResponse res) {
		AdminsService AdminsService = new AdminsService();
		Integer adminNo = Integer.valueOf(req.getParameter("adminNo")) ;
		AdminsService.delete(adminNo);
		return getAlladmins(req, res); 
	}

	private String updateAdmins(HttpServletRequest req, HttpServletResponse res) {
		AdminsService AdminsService = new AdminsService();
		String account=req.getParameter("account");
		String nickname=req.getParameter("nickname");
		String password =req.getParameter("password");
		Integer permission = Integer.valueOf(req.getParameter("permission")) ;
		Integer adminNo = Integer.valueOf(req.getParameter("adminNo")) ;
System.out.print(account+"||"+nickname+"||"+password+"||"+permission+"||"+adminNo);
AdminsService.update(nickname, permission, account, password, adminNo);		
//AdminsService.update("11",1,"22","3",2);
 return  getAlladmins(req, res);
}

	private String getAlladmins(HttpServletRequest req, HttpServletResponse res) {
		AdminsService AdminsService = new AdminsService(); 
		List<AdminsVO> list = AdminsService.getAll();
		String json = new Gson().toJson(list);
		req.setAttribute("json",json);
		return "/dashboard/admin/WCC_permission_management.jsp";
	}

	private String getOne_For_Update(HttpServletRequest req, HttpServletResponse res) {
		AdminsService AdminsService = new AdminsService();
		Integer adminNo = Integer.valueOf(req.getParameter("adminNo")) ;
		AdminsVO AdmonVO = AdminsService.getOne(adminNo);
		req.setAttribute("AdminsVO", AdmonVO);
		
		return "/dashboard/admin/WCC_permission_update.jsp" ;
	}

	private String inserAdmins(HttpServletRequest req, HttpServletResponse res) {
		AdminsService AdminsService = new AdminsService();
		String account=req.getParameter("account");
		String nickname=req.getParameter("nickname");
		String password =req.getParameter("password");
		Integer permission = Integer.valueOf(req.getParameter("permission")) ;

AdminsService.add(nickname, permission, account, password);


		return getAlladmins(req, res); 
	}
}
