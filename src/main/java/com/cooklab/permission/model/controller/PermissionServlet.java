package com.cooklab.permission.model.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.permission.model.*;
import com.cooklab.admins.model.AdminsService;
import com.cooklab.admins.model.AdminsVO;
import com.google.gson.Gson;
@WebServlet("/PermissionServlet")
public class PermissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PermissionServlet() {
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
		case "getPermission":
			forwardPath = getPermission(req, res);
			break;
		case "insert":
			forwardPath = insert(req, res);
			break;
		case "update":
			forwardPath = update(req, res);
			break;
		case "delete":
			forwardPath = delete(req, res);
			break;
		default:
			forwardPath = "/dashboard/permission/WCC_permission.jsp";
		}
//		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);

	}





	private String insert(HttpServletRequest req, HttpServletResponse res) {
		PermissionService PermissionService = new PermissionService();
//		Integer permissionNo = Integer.valueOf(req.getParameter("permissionNo"));
		String permissionTitle = req.getParameter("permissionTitle");
		Byte adminManagement =Byte.valueOf(req.getParameter("adminManagement"));
		Byte serviceManagement =Byte.valueOf(req.getParameter("serviceManagement"));
		Byte membershipManagement =Byte.valueOf(req.getParameter("membershipManagement"));
		Byte advertisingManagement =Byte.valueOf(req.getParameter("advertisingManagement"));
		Byte reportingManagement =Byte.valueOf(req.getParameter("reportingManagement"));
		Byte articleManagement =Byte.valueOf(req.getParameter("articleManagement"));
		Byte recipeManagement =Byte.valueOf(req.getParameter("recipeManagement"));
		Byte mallManagement =Byte.valueOf(req.getParameter("mallManagement"));
		Timestamp a =new Timestamp(System.currentTimeMillis());
		PermissionService.add(permissionTitle, adminManagement, serviceManagement, membershipManagement, advertisingManagement, reportingManagement, articleManagement, recipeManagement,mallManagement,a);
		
		return "/dashboard/permission/WCC_permission.jsp";
	}

	private String getPermission(HttpServletRequest req, HttpServletResponse res) {
		PermissionService PermissionService = new PermissionService();
		List<PermissionVO> list = PermissionService.getAll();
//		List<PermissionServiceVOFake> list1 = new ArrayList<PermissionServiceVOFake>();
//		AdminsVOFake a;
//		for(int i =0;i<list.size();i++) {
//			 a = new AdminsVOFake(list.get(i));
//			 list1.add(a);
//			 
//		}
		String json = new Gson().toJson(list);
		System.out.println(json);
		req.setAttribute("json", json);
		
		
		return "/dashboard/permission/WCC_permission.jsp";
	}
//	=====================================UPDATE====================================================
	private String update(HttpServletRequest req, HttpServletResponse res) {
		PermissionService PermissionService = new PermissionService();
		Integer permissionNo = Integer.valueOf(req.getParameter("permissionNo"));
		String permissionTitle = req.getParameter("permissionTitle");
		Byte adminManagement =Byte.valueOf(req.getParameter("adminManagement"));
		Byte serviceManagement =Byte.valueOf(req.getParameter("serviceManagement"));
		Byte membershipManagement =Byte.valueOf(req.getParameter("membershipManagement"));
		Byte advertisingManagement =Byte.valueOf(req.getParameter("advertisingManagement"));
		Byte reportingManagement =Byte.valueOf(req.getParameter("reportingManagement"));
		Byte articleManagement =Byte.valueOf(req.getParameter("articleManagement"));
		Byte recipeManagement =Byte.valueOf(req.getParameter("recipeManagement"));
		Byte mallManagement =Byte.valueOf(req.getParameter("mallManagement"));

		Timestamp a =new Timestamp(System.currentTimeMillis());
//============================================================================
		PermissionVO PermissionVO = new PermissionVO();
		PermissionVO.setPermissionNo(permissionNo);
		PermissionVO.setPermissionTitle(permissionTitle);
		PermissionVO.setAdminManagement(adminManagement);
		PermissionVO.setServiceManagement(serviceManagement);
		PermissionVO.setMembershipManagement(membershipManagement);
		PermissionVO.setAdvertisingManagement(advertisingManagement);
		PermissionVO.setReportingManagement(reportingManagement);
		PermissionVO.setArticleManagement(articleManagement);
		PermissionVO.setRecipeManagement(recipeManagement);
		PermissionVO.setMallManagement(mallManagement);
		PermissionVO.setCreatedTimestamp(a);
		PermissionService.update(permissionTitle, adminManagement, serviceManagement, membershipManagement, advertisingManagement, reportingManagement, articleManagement, recipeManagement,mallManagement, permissionNo);
		return "/dashboard/permission/WCC_permission.jsp";
	}
	
	private String delete(HttpServletRequest req, HttpServletResponse res) {
		Integer permissionNo = Integer.valueOf(req.getParameter("permissionNo"));
		PermissionService PermissionService = new PermissionService();
		PermissionService.delete(permissionNo);
		return "/dashboard/permission/WCC_permission.jsp";
	}
	
	
}
