package com.cooklab.recipe_hashtag.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.hashtag.model.HashtagService;
import com.cooklab.hashtag.model.HashtagVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_hashtag.model.RecipeHashtagService;
import com.cooklab.recipe_hashtag.model.RecipeHashtagVO;
import com.google.gson.Gson;

@WebServlet("/RecipeHashtagServlet")
public class RecipeHashtagServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		Gson gson = new Gson();

		if ("insert".equals(action)) {
			RecipeVO recipeVO = (RecipeVO) req.getAttribute("recipe");
			String[] listRecipeHashtag = gson.fromJson(req.getParameter("recipeHashtag"), String[].class);

			for (int i = 0; i < listRecipeHashtag.length; i++) {
				System.out.println(listRecipeHashtag[i]);
				HashtagVO hashtagVO = new HashtagService().findByHashtagName(listRecipeHashtag[i]);
				RecipeHashtagVO recipeHashtagVO = new RecipeHashtagService().addRecipeHashtag(hashtagVO, recipeVO);
			}
			res.getWriter().write(gson.toJson("sucess"));
			return;
		}

	}
}
