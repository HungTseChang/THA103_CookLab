package com.cooklab.filter;

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

@WebFilter(urlPatterns = { 
		"/frontstage/members/member-panel-editting.jsp",			//修改會員資料
		"/frontstage/members/member-panel-news.html",				//最新消息
		"/frontstage/members/member-panel.jsp",					//會員資料列表
		"/frontstage/members/member-panel-password.html",			//修改密碼
		"/frontstage/members/member-panel-order.html",				//查詢訂單
		"/frontstage/members/member-panel-recipe.html",			//查詢食譜
		"/frontstage/members/member-panel-post.html",				//查詢討論區文章
		"/frontstage/members/member-panel-follow.html",			//查詢關注
		"/frontstage/members/VerificationLetter.html",				//收取驗證信
		//阿宅
		"frontstage/article/article_edit.jsp",
		"frontstage/article/article_sub_edit.jsp",
		"frontstage/article/article_report.jsp"
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

		if(account == null)
		{
			//使用者沒有登入過
			System.out.println("使用者沒有登入 網頁重導至 SignIn");
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/frontstage/members/login.html");
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
