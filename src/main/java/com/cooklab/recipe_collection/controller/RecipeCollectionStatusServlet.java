package com.cooklab.recipe_collection.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.model.RecipeServiceIm;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_collection.model.RecipeCollectionServiceIm;
import com.cooklab.recipe_collection.model.RecipeCollectionVO;
import com.google.gson.Gson;

@WebServlet("/RecipeCollectionStatusServlet")
public class RecipeCollectionStatusServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		HttpSession session = req.getSession();
		MembersVO membersVO = (MembersVO) session.getAttribute("membersVO");

		if (membersVO != null) {
			RecipeVO recipeVO = new RecipeServiceIm()
					.getOneRecipe(Integer.valueOf(req.getParameter("recipeNo").trim()));
			RecipeCollectionVO recipeCollectionVO = new RecipeCollectionServiceIm().findByMemberAndRecipe(recipeVO,
					membersVO);
			String jsonString = gson.toJson(recipeCollectionVO != null ? recipeCollectionVO.getCollectionNo() : null);
			res.getWriter().write(jsonString);
			return;
		}
		String jsonString = gson.toJson(null);
		res.getWriter().write(jsonString);
		return;
	}
}
