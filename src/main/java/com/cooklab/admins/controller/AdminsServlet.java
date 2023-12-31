package com.cooklab.admins.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
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
import javax.servlet.http.HttpSession;

import com.cooklab.admins.model.AdminsService;
import com.cooklab.admins.model.AdminsVO;
import com.cooklab.permission.model.*;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.mysql.cj.Session;

@WebServlet("/AdminsServlet")
public class AdminsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  private List<AdminsVO>  Adminslist;
	public AdminsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(this.Adminslist==null) {
		AdminsService AdminsService = new AdminsService();
		this.Adminslist = AdminsService.getAll();
		}
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";
		switch (action) {
		case"insert":
			System.out.println("insert");
			forwardPath =insert(req, res);
			break;
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
		case "updatePersionalAdmins":
			forwardPath = updatePersionalAdmins(req, res);
			break;
		case "delete":
			forwardPath = delete(req, res);
			break;
		case "design":
			forwardPath = design(req, res);
			break;
		default:
			forwardPath = "/dashboard/admin/WCC_admin_management.jsp";
		}
//		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

	}
	private String updatePersionalAdmins(HttpServletRequest req, HttpServletResponse res) {
		AdminsService AdminsService = new AdminsService();
		String account = req.getParameter("account");
		String nickname = req.getParameter("nickname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Integer permission = Integer.valueOf(req.getParameter("permission").trim());
		Integer adminNo = Integer.valueOf(req.getParameter("adminNo").trim());

		AdminsService.update(nickname, permission, account,email, password, adminNo);
		this.Adminslist=null;
		return "/dashboard/login/WCC_welcome.jsp";
	}
	

	private String design(HttpServletRequest req, HttpServletResponse res) {
		HttpSession Session  =  req.getSession();
		String thisaccount= (String) Session.getAttribute("thisaccount");
		System.out.println("修改個人資料"+thisaccount);
		AdminsVO AdminsVO = Adminslist.stream().filter(e->e.getAdminAccount().equals(thisaccount)).findFirst().get();
   		AdminsVOFake a = new AdminsVOFake(AdminsVO);
   		PermissionService PermissionService= new PermissionService();
   		List<PermissionVO> listpermission = PermissionService.getAll();
   		List<PermissionVOFake> listpermissionFake = new ArrayList<PermissionVOFake>();
   	  for(int i = 0 ; i <listpermission.size();i++) {
   		  listpermissionFake.add(new PermissionVOFake(listpermission.get(i)) );	  
   	  }
   		
   		String json_permission = new Gson().toJson(listpermissionFake);
   		String json_AdminsVOFake = new Gson().toJson(a);
   		req.setAttribute("AdminsVO", AdminsVO);
   		req.setAttribute("json_permission", json_permission);
   		req.setAttribute("json_AdminsVOFake", json_AdminsVOFake);
		return "/dashboard/admin/WCC_admin_update_for_personal.jsp";
	}

	private String insert(HttpServletRequest req, HttpServletResponse res) {
		PermissionService PermissionService= new PermissionService();
		List<PermissionVO> listpermission = PermissionService.getAll();
		List<PermissionVOFake> listpermissionFake = new ArrayList<PermissionVOFake>();
	  for(int i = 0 ; i <listpermission.size();i++) {
		  listpermissionFake.add(new PermissionVOFake(listpermission.get(i)) );	  
	  }
		
		String json_permission = new Gson().toJson(listpermissionFake);
		req.setAttribute("json_permission", json_permission);
		
		
		return "/dashboard/admin/WCC_admin_createnew.jsp";
	}

	private String delete(HttpServletRequest req, HttpServletResponse res) {
		AdminsService AdminsService = new AdminsService();
		Integer adminNo = Integer.valueOf(req.getParameter("adminNo"));
		AdminsService.delete(adminNo);
		this.Adminslist=null;
		return "/dashboard/admin/WCC_admin_management.jsp";
	}

	private String updateAdmins(HttpServletRequest req, HttpServletResponse res) {
		AdminsService AdminsService = new AdminsService();
		String account = req.getParameter("account").trim();
		String nickname = req.getParameter("nickname").trim();
		String email = req.getParameter("email").trim();
		String password = req.getParameter("password").trim();
		Integer permission = Integer.valueOf(req.getParameter("permission").trim());
		Integer adminNo = Integer.valueOf(req.getParameter("adminNo").trim());

		AdminsService.update(nickname, permission, account,email, password, adminNo);
		this.Adminslist=null;
		return "/dashboard/admin/WCC_admin_management.jsp";
	}

	private String getAlladmins(HttpServletRequest req, HttpServletResponse res) {
		List<AdminsVO> list;
		if(this.Adminslist==null) {
		AdminsService AdminsService = new AdminsService();
		list = AdminsService.getAll();
		}else {
			list = this.Adminslist;
					}
		List<AdminsVOFake> list1 = new ArrayList<AdminsVOFake>();
		AdminsVOFake a;
		for(int i =0;i<list.size();i++) {
			 a = new AdminsVOFake(list.get(i));
			 list1.add(a);
			 
		}
		
		String json = new Gson().toJson(list1);
		req.setAttribute("json", json);
		return "/dashboard/admin/WCC_admin_management.jsp";
	}

	private String getOne_For_Update(HttpServletRequest req, HttpServletResponse res) {
		Integer adminNo = Integer.valueOf(req.getParameter("adminNo"));
		AdminsVO AdmonVO = this.Adminslist.stream().filter(e -> e.getAdminNo().equals(adminNo)).findFirst().get();
		AdminsVOFake a = new AdminsVOFake(AdmonVO);
		PermissionService PermissionService= new PermissionService();
		List<PermissionVO> listpermission = PermissionService.getAll();
		List<PermissionVOFake> listpermissionFake = new ArrayList<PermissionVOFake>();
	  for(int i = 0 ; i <listpermission.size();i++) {
		  listpermissionFake.add(new PermissionVOFake(listpermission.get(i)) );	  
	  }
		
		String json_permission = new Gson().toJson(listpermissionFake);
		String json_AdminsVOFake = new Gson().toJson(a);
		req.setAttribute("AdminsVO", AdmonVO);
		req.setAttribute("json_permission", json_permission);
		req.setAttribute("json_AdminsVOFake", json_AdminsVOFake);

		return "/dashboard/admin/WCC_admin_update.jsp";
	}

	private String inserAdmins(HttpServletRequest req, HttpServletResponse res) {
		AdminsService AdminsService = new AdminsService();
		String account = req.getParameter("account").trim();
		String nickname = req.getParameter("nickname").trim();
		String email = req.getParameter("email").trim();
		String password = req.getParameter("password").trim();
		Integer permission = Integer.valueOf(req.getParameter("permission").trim());
		Timestamp a =new Timestamp(System.currentTimeMillis());
		AdminsService.add(nickname, permission, account,email, password,a);
		this.Adminslist=null;

		return "/dashboard/admin/WCC_admin_management.jsp";
	}
	
	
	 class AdminsVOFake{
		private Integer adminNo;
		private String adminNickname;
		private Integer permissionNo;
		private String adminAccount;
		private String adminEmail;
		public String getAdminEmail() {
			return adminEmail;
		}
		public void setAdminEmail(String adminEmail) {
			this.adminEmail = adminEmail;
		}
		private String adminPassword;
		private Timestamp createdTimestamp;
		public AdminsVOFake(AdminsVO AdminsVO) {
			super();
			this.adminNo = AdminsVO.getAdminNo();
			this.adminNickname = AdminsVO.getAdminNickname();
			this.adminEmail = AdminsVO.getAdminEmail();
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
	private class  PermissionVOFake{
		private Integer permissionNo;
		private String permissionTitle;
		private Byte adminManagement;
		private Byte serviceManagement;
		private Byte membershipManagement;
		private Byte advertisingManagement;
		private Byte reportingManagement;
		private Byte articleManagement;
		private Byte recipeManagement;
		private Byte mallManagement;
		private Timestamp createdTimestamp;
		
		
		
		public PermissionVOFake(PermissionVO PermissionVO) {
			super();
			this.permissionNo = PermissionVO.getPermissionNo();
			this.permissionTitle = PermissionVO.getPermissionTitle();
			this.adminManagement = PermissionVO.getAdminManagement();
			this.serviceManagement = PermissionVO.getServiceManagement();
			this.membershipManagement = PermissionVO.getMembershipManagement();
			this.advertisingManagement = PermissionVO.getAdvertisingManagement();
			this.reportingManagement = PermissionVO.getReportingManagement();
			this.articleManagement = PermissionVO.getArticleManagement();
			this.recipeManagement = PermissionVO.getRecipeManagement();
			this.mallManagement =PermissionVO.getMallManagement();
			this.createdTimestamp = PermissionVO.getCreatedTimestamp();
		}



		public Integer getPermissionNo() {
			return permissionNo;
		}



		public void setPermissionNo(Integer permissionNo) {
			this.permissionNo = permissionNo;
		}



		public String getPermissionTitle() {
			return permissionTitle;
		}



		public void setPermissionTitle(String permissionTitle) {
			this.permissionTitle = permissionTitle;
		}



		public Byte getAdminManagement() {
			return adminManagement;
		}



		public void setAdminManagement(Byte adminManagement) {
			this.adminManagement = adminManagement;
		}



		public Byte getServiceManagement() {
			return serviceManagement;
		}



		public void setServiceManagement(Byte serviceManagement) {
			this.serviceManagement = serviceManagement;
		}



		public Byte getMembershipManagement() {
			return membershipManagement;
		}



		public void setMembershipManagement(Byte membershipManagement) {
			this.membershipManagement = membershipManagement;
		}



		public Byte getAdvertisingManagement() {
			return advertisingManagement;
		}



		public void setAdvertisingManagement(Byte advertisingManagement) {
			this.advertisingManagement = advertisingManagement;
		}



		public Byte getReportingManagement() {
			return reportingManagement;
		}



		public void setReportingManagement(Byte reportingManagement) {
			this.reportingManagement = reportingManagement;
		}



		public Byte getArticleManagement() {
			return articleManagement;
		}



		public void setArticleManagement(Byte articleManagement) {
			this.articleManagement = articleManagement;
		}



		public Byte getRecipeManagement() {
			return recipeManagement;
		}



		public void setRecipeManagement(Byte recipeManagement) {
			this.recipeManagement = recipeManagement;
		}



		public Byte getMallManagement() {
			return mallManagement;
		}



		public void setMallManagement(Byte mallManagement) {
			this.mallManagement = mallManagement;
		}



		public Timestamp getCreatedTimestamp() {
			return createdTimestamp;
		}



		public void setCreatedTimestamp(Timestamp createdTimestamp) {
			this.createdTimestamp = createdTimestamp;
		}

		
		
		
	}
	
}
