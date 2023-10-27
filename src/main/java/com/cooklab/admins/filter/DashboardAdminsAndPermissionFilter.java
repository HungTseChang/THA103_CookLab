package com.cooklab.admins.filter;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cooklab.admins.model.*;
import com.cooklab.permission.model.*;
public class DashboardAdminsAndPermissionFilter  implements Filter{
	private String AdminsAccount=null;
	private Map<String, Boolean>Permit=null;
	
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest  request =  (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		HttpSession session = request.getSession();
//		String location = request.getRequestURI();
		String location = request.getServletPath();
		
		System.out.println("location: "+location);
	
		if(AdminsAccount == null) {		
		try {
			AdminsAccount = (String)session.getAttribute("account");
			Permit=(Map<String, Boolean>)session.getAttribute("permissionlist");
		}catch(Exception e){
			System.out.println("並未檢測到登錄帳號E");

			session.setAttribute("location",location);
			response.sendRedirect(request.getContextPath()+"/dashboard/login/WCC_login.jsp");
            return;
		}

		if(AdminsAccount == null) {		
			System.out.println("並未檢測到登錄帳號P");
			session.setAttribute("location",location);
			response.sendRedirect(request.getContextPath()+"/dashboard/login/WCC_login.jsp");
			System.out.println("回到登陸頁面");
            return;
		}
		System.out.println(AdminsAccount);

		}
		System.out.println("是否具有進入此頁面的權限:"+ Permit.get(location));
if(Permit.get(location)) {

		
		
        System.out.println("從帳號檢測前往下一頁");
			chain.doFilter(req, res);
	        System.out.println("帳號檢測離開");
}else {
	
    System.out.println("不具有此權限，跳轉到禁止頁面");
	response.sendRedirect(request.getContextPath()+"/dashboard/login/WCC_forbidden.jsp");
  return;
}
//if(Permit.get(location)) {
//	System.out.println("YESYESYESYESYESYESYESYESYESYES");
//	
//}else {
//	
//	System.out.println("NONONONONONONONONONONONO");
//
//}

		
	}
}
