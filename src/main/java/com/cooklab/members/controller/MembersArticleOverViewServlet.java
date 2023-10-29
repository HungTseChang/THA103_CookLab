package com.cooklab.members.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.cooklab.members.MemberFollowOverViewDTO;
import com.cooklab.members.MemberArticleOverViewDTO;
import com.cooklab.members.model.MembersService;
import com.cooklab.members.model.MembersVO;
import com.cooklab.notify_center.model.NotifyCenterVO;
import com.cooklab.util.HibernateUtil;
import com.google.gson.Gson;
import com.cooklab.article.model.*;
import com.cooklab.article_category.model.ArticleCategoryVO;
@WebServlet("/MembersArticleServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class MembersArticleOverViewServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String articleNoStr = req.getParameter("articleNo");
		String articleStatusStr = req.getParameter("articleStatus");
		Integer articleNo = 0;  
		Byte articleStatus = 0; 
		
		if (articleNoStr != null && !articleNoStr.isEmpty())
			articleNo =  Integer.valueOf(articleNoStr);
		else
			articleNo = 0;
		

		if (articleStatusStr != null && !articleStatusStr.isEmpty())
			articleStatus =  Byte.parseByte(articleStatusStr);
		else
			articleStatus = 0;
	
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		ArticleVO ncVO = session.get(ArticleVO.class, articleNo);
		System.out.println(ncVO.toString());
		ncVO.setArticleStatus(articleStatus);
		session.merge(ncVO);
//		res.sendRedirect(req.getRequestURI());
		res.sendRedirect(req.getContextPath() + "/members/member-panel-post.html");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		
		HttpSession session = req.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<ArticleVO> listArticleVO = new MembersService().getArticle(Integer.valueOf(0)*10, 90,userId);
		List<MemberArticleOverViewDTO> newListDTO = new ArrayList<>(); 
		for(ArticleVO atVO : listArticleVO)
		{
			newListDTO.add(new MemberArticleOverViewDTO(atVO));
		}
		String jsonString = gson.toJson(newListDTO);
		res.getWriter().write(jsonString);	
	}
}
