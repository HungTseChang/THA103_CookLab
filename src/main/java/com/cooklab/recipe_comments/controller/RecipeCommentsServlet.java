package com.cooklab.recipe_comments.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.model.RecipeServiceIm;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_comments.RecipeCommentsDTO;
import com.cooklab.recipe_comments.model.RecipeCommentsServiceIm;
import com.cooklab.recipe_comments.model.RecipeCommentsVO;
import com.cooklab.util.HibernateUtil;
import com.google.gson.Gson;

@WebServlet("/RecipeCommentsServlet")
public class RecipeCommentsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		String action = req.getParameter("action");
//	HttpSession session = req.getSession();
//	MembersVO members = (MembersVO) session.getAttribute("members");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();// 測試
		MembersVO memberVO = session.get(MembersVO.class, 1);// 測試

		if ("insert".equals(action)) {
			String commentsContent = req.getParameter("commentContent");
			RecipeVO recipeVO = new RecipeServiceIm().getOneRecipe(Integer.valueOf(req.getParameter("recipeNo").trim()));

			RecipeCommentsVO recipeCommentsVO = new RecipeCommentsServiceIm().addRecipeComments(recipeVO, memberVO,
					commentsContent);
			RecipeCommentsDTO recipeCommentsDTO = new RecipeCommentsDTO(recipeCommentsVO);
			recipeCommentsDTO.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			String jsonString = gson.toJson(recipeCommentsDTO);
			res.getWriter().write(jsonString);
			return;
		}
	}
}
