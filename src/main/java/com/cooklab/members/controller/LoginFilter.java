package com.cooklab.members.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/members/member-panel-editting.jsp",
		"/members/member-panel-news.html",
		"/members/member-panel.jsp"
		})
public class LoginFilter extends HttpFilter implements Filter{
	
	public LoginFilter() {
		super();
	}
	

	public void destroy() {
		
	}
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 強轉型 HttpServletRequest, HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// 設定編碼格式
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		// 成功過濾 -> 提示訊息
		System.out.println("成功啟用sign-in filter");
		
		// 取得 session
		HttpSession session = req.getSession();
		
		String account = (String) session.getAttribute("account");
		System.out.println("濾器開始判斷有無Seesion中的account");
		System.out.println("account為" + account);
		if(account == null)
		{
			//使用者沒有登入過
			System.out.println("使用者沒有登入");
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/members/login.html");
			System.out.println("網頁重導至 signIn");
		}
		else
		{
			//使用者登入過
			System.out.println("使用者曾經登入過");
			//將值繼續傳下去
			chain.doFilter(req,res);
		}
		
	}
	
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
