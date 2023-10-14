package com.cooklab.admins.controler;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.admins.model.AdminsService;
import com.cooklab.admins.model.AdminsVO;
import com.cooklab.permission.model.PermissionVO;
import com.google.gson.Gson;

@WebServlet("/AdminsServlet")
public class AdminsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		case "getAlladmins":
			forwardPath = getAlladmins(req, res);
			break;
		case "getOne_For_Update":
			forwardPath = getOne_For_Update(req, res);
			break;
		case "updateAdmins":
			forwardPath = updateAdmins(req, res);
			break;
		case "delete":
			forwardPath = delete(req, res);
		default:
			forwardPath = "/dashboard/admin/WCC_permission_management.jsp";
		}
//		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

	}

	private String delete(HttpServletRequest req, HttpServletResponse res) {
		AdminsService AdminsService = new AdminsService();
		Integer adminNo = Integer.valueOf(req.getParameter("adminNo"));
		AdminsService.delete(adminNo);
		return getAlladmins(req, res);
	}

	private String updateAdmins(HttpServletRequest req, HttpServletResponse res) {
		AdminsService AdminsService = new AdminsService();
		String account = req.getParameter("account");
		String nickname = req.getParameter("nickname");
		String password = req.getParameter("password");
		Integer permission = Integer.valueOf(req.getParameter("permission"));
		Integer adminNo = Integer.valueOf(req.getParameter("adminNo"));

		System.out.print(account + "||" + nickname + "||" + password + "||" + permission + "||" + adminNo);
		AdminsService.update(nickname, permission, account, password, adminNo);
//AdminsService.update("11",1,"22","3",2);
		return getAlladmins(req, res);
	}

	private String getAlladmins(HttpServletRequest req, HttpServletResponse res) {
		AdminsService AdminsService = new AdminsService();
		List<AdminsVO> list = AdminsService.getAll();
		List<AdminsVOFake> list1 = new ArrayList<AdminsVOFake>();
		AdminsVOFake a;
		for(int i =0;i<list.size();i++) {
			 a = new AdminsVOFake(list.get(i));
			 list1.add(a);
			 
		}
		
		String json = new Gson().toJson(list1);
//		System.out.println(json);
		req.setAttribute("json", json);
		return "/dashboard/admin/WCC_permission_management.jsp";
	}

	private String getOne_For_Update(HttpServletRequest req, HttpServletResponse res) {
		AdminsService AdminsService = new AdminsService();
		Integer adminNo = Integer.valueOf(req.getParameter("adminNo"));
		AdminsVO AdmonVO = AdminsService.getOne(adminNo);
//		AdminsVOFake a = new AdminsVOFake(AdmonVO);
		req.setAttribute("AdminsVO", AdmonVO);

		return "/dashboard/admin/WCC_permission_update.jsp";
	}

	private String inserAdmins(HttpServletRequest req, HttpServletResponse res) {
		AdminsService AdminsService = new AdminsService();
		String account = req.getParameter("account");
		String nickname = req.getParameter("nickname");
		String password = req.getParameter("password");
		Integer permission = Integer.valueOf(req.getParameter("permission"));
		Timestamp a =new Timestamp(System.currentTimeMillis());
		AdminsService.add(nickname, permission, account, password,a);

		return getAlladmins(req, res);
	}
	
	
	private class AdminsVOFake{
		private Integer adminNo;
		private String adminNickname;
		private Integer permissionNo;
		private String adminAccount;
		private String adminPassword;
		private Timestamp createdTimestamp;
		public AdminsVOFake(AdminsVO AdminsVO) {
			super();
			this.adminNo = AdminsVO.getAdminNo();
			this.adminNickname = AdminsVO.getAdminNickname();
			this.permissionNo = AdminsVO.getPermissionVO().getPermissionNo();
			this.adminAccount = AdminsVO.getAdminAccount();
			this.adminPassword = AdminsVO.getAdminPassword();
			this.createdTimestamp = AdminsVO.getCreatedTimestamp();
		}
		public Integer getAdminNo() {
			return adminNo;
		}
		public void setAdminNo(Integer adminNo) {
			this.adminNo = adminNo;
		}
		public String getAdminNickname() {
			return adminNickname;
		}
		public void setAdminNickname(String adminNickname) {
			this.adminNickname = adminNickname;
		}
		public Integer getPermissionNo() {
			return permissionNo;
		}
		public void setPermissionNo(Integer permissionNo) {
			this.permissionNo = permissionNo;
		}
		public String getAdminAccount() {
			return adminAccount;
		}
		public void setAdminAccount(String adminAccount) {
			this.adminAccount = adminAccount;
		}
		public String getAdminPassword() {
			return adminPassword;
		}
		public void setAdminPassword(String adminPassword) {
			this.adminPassword = adminPassword;
		}
		public Timestamp getCreatedTimestamp() {
			return createdTimestamp;
		}
		public void setCreatedTimestamp(Timestamp createdTimestamp) {
			this.createdTimestamp = createdTimestamp;
		}
		
		
		
	}
	
}
