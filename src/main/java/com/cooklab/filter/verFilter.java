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
		"/frontstage/recipe/recipe_create.jsp",			//新增食譜
		"/frontstage/article/article_edit.jsp",			//新增文章
})
public class verFilter  extends HttpFilter implements Filter {
	public verFilter() {
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
		System.out.println("成功啟用verify-filter");

		// 取得 session
		HttpSession session = req.getSession();
		byte verStatus =  (byte)session.getAttribute("verStatus");


		
		if (verStatus != 0) {
			// 使用者沒有驗證或註銷
			System.out.println("使用者沒有驗證 網頁重導至 驗證信網頁");
			res.sendRedirect(req.getContextPath() + "/frontstage/members/VerificationLetter.html");
			
		} else {
			// 使用者登入過
			System.out.println("使用者已驗證");
			// 將值繼續傳下去
			chain.doFilter(req, res);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
