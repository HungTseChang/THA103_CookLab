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

import com.cooklab.article.model.ArticleVO;
import com.cooklab.members.MemberRecipeOverViewDTO;
import com.cooklab.members.model.MembersService;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.HibernateUtil;
import com.google.gson.Gson;

@WebServlet("/MemberRecipeServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class MembersRecipeOverViewServlet  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//doPost(req,res);
		String recipeNoStr = req.getParameter("recipeNo");
		String recipeStatusStr = req.getParameter("recipeStatus");
		Integer recipeNo = 0;  
		Byte recipeStatus = 0; 
		
		if (recipeNoStr != null && !recipeNoStr.isEmpty())
			recipeNo =  Integer.valueOf(recipeNoStr);
		else
			recipeNo = 0;
		

		if (recipeStatusStr != null && !recipeStatusStr.isEmpty())
			recipeStatus =  Byte.parseByte(recipeStatusStr);
		else
			recipeStatus = 0;
	
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		RecipeVO ncVO = session.get(RecipeVO.class, recipeNo);
		System.out.println(ncVO.toString());
		ncVO.setRecipeStatus(recipeStatus);
		session.merge(ncVO);
//		res.sendRedirect(req.getRequestURI());
		res.sendRedirect(req.getContextPath() + "/frontstage/members/member-panel-recipe.html");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();

		HttpSession session = req.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<RecipeVO> listReipceVO = new MembersService().getByPage(Integer.valueOf(0)*10, 90,userId);
		List<MemberRecipeOverViewDTO> listRecipeOverviewDTO = new ArrayList<>();
		for(RecipeVO reipceVO :listReipceVO) {
			listRecipeOverviewDTO.add(new MemberRecipeOverViewDTO(reipceVO));
		}
		String jsonString = gson.toJson(listRecipeOverviewDTO);
		res.getWriter().write(jsonString);	
	}
}
