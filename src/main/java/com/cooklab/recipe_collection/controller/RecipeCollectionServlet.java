package com.cooklab.recipe_collection.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.model.RecipeServiceIm;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_collection.model.RecipeCollectionServiceIm;
import com.cooklab.recipe_collection.model.RecipeCollectionVO;
import com.cooklab.util.HibernateUtil;
import com.google.gson.Gson;

@WebServlet("/RecipeCollectionServlet")
public class RecipeCollectionServlet extends HttpServlet {
	
//	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		String action = req.getParameter("action");
//		HttpSession session = req.getSession();
//		MembersVO members = (MembersVO) session.getAttribute("members");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		MembersVO memberVO = session.get(MembersVO.class, 1);

		if ("insert".equals(action)) {
			RecipeVO recipeVO = new RecipeServiceIm().getOneRecipe(Integer.valueOf(req.getParameter("recipeNo").trim()));
			Integer recipeCollectionNo = new RecipeCollectionServiceIm().addRecipeCollection(recipeVO, memberVO);
			String jsonString = gson.toJson(recipeCollectionNo);
			res.getWriter().write(jsonString);
			return;
		}
		if ("delete".equals(action)) {
			new RecipeCollectionServiceIm()
					.deleteRecipeCollection(Integer.valueOf(req.getParameter("recipeCollectionNo").trim()));
			String jsonString = gson.toJson("收藏取消成功");
			res.getWriter().write(jsonString);
			return;
		}
		if ("status".equals(action)) {
			RecipeVO recipeVO = new RecipeServiceIm().getOneRecipe(Integer.valueOf(req.getParameter("recipeNo").trim()));
			RecipeCollectionVO recipeCollectionVO = new RecipeCollectionServiceIm().findByMemberAndRecipe(recipeVO,
					memberVO);
			String jsonString = gson.toJson(recipeCollectionVO != null ? recipeCollectionVO.getCollectionNo() : null);
			res.getWriter().write(jsonString);
			return;
		}
	}
}