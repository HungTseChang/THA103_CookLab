package com.cooklab.admins.filter;

import java.io.IOException;
import java.util.List;
import java.util.Enumeration;
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
	
	
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		HttpServletRequest  request =  (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		HttpSession session = request.getSession();
		String location = request.getServletPath();
		String action = req.getParameter("action");
		System.out.println("location: "+location);
if(action ==null) {
	session.setAttribute("location",location);
	response.sendRedirect(request.getContextPath()+"/dashboard/login/WCC_login.jsp");
    return;
}
		if(AdminsAccount == null) {		
		try {
			AdminsAccount = (String)session.getAttribute("thisaccount");
			Permit=(Map<String, Boolean>)session.getAttribute("permissionlist");
		
		}catch(Exception e){
			System.out.println("並未檢測到登錄帳號E"+e);
			session.setAttribute("location",location);
			response.sendRedirect(request.getContextPath()+"/dashboard/login/WCC_login.jsp");
            return;
		}

		  if(action.equals("login")||action.equals("forgetpassword")||action.equals("design")||action.equals("updatePersionalAdmins")){
				System.out.println("第一次登錄or 修改個人資訊");			  
				chain.doFilter(req, res);
				System.out.println("從第一次登錄離開");			  
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
		if(location.equals("/LoginServlet")){
			chain.doFilter(req, res);
			  if(session.getAttribute("logout") != null) {
			        System.out.println("檢測到登出請求");
			        
//			  ============      
			        Enumeration<String> attributeNames = session.getAttributeNames();
			  while (attributeNames.hasMoreElements()) {
			            String attributeName = attributeNames.nextElement();
			           System.out.println(attributeName);
			            session.removeAttribute(attributeName);
			        }
//			  ======
			        session.removeAttribute("thisaccount");
			        session.removeAttribute("location");
			        session.removeAttribute("permissionlist");
			        session.removeAttribute("logout");
			        if(session.getAttribute("location") != null) {
			        	System.out.println("清除失敗");
			        	
			        }
			        Enumeration<String> attributeNames2 = session.getAttributeNames();
			        System.out.println("是否還有殘留的數據"+ attributeNames2.hasMoreElements());
			
			        
			        AdminsAccount=null;
			        Permit=null;
			  }      
return;
		}
		
		System.out.println("是否具有進入此頁面的權限:"+ Permit.get(location));
if(Permit.get(location)) {

		
		
        System.out.println("從帳號檢測前往下一頁");
			chain.doFilter(req, res);
	        System.out.println("帳號檢測離開");
	        
//	=============================開始檢測是否為登出請求=========================================        

	        
	        
}else{
	
    System.out.println("不具有此權限，跳轉到禁止頁面");
	response.sendRedirect(request.getContextPath()+"/dashboard/login/WCC_forbidden.jsp");
  return;
}


		
	}
}
