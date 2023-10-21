package com.cooklab.recipe_step.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_step.model.RecipeStepService;
import com.cooklab.recipe_step.model.RecipeStepVO;
import com.google.gson.Gson;

@WebServlet("/RecipeStepServlet")
public class RecipeStepServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		Gson gson = new Gson();
		
		if ("insert".equals(action)) {
			byte[] coverImage = Base64.getDecoder().decode(req.getParameter("coverImage"));

			RecipeVO recipeVO = (RecipeVO) req.getAttribute("recipe");
			Integer[] stepTime = gson.fromJson(req.getParameter("stepTime"), Integer[].class);
			String[] stepImgs = gson.fromJson(req.getParameter("stepImg"), String[].class);
			String[] stepContent = gson.fromJson(req.getParameter("stepContent"), String[].class);
			

			for (int i = 0; i < stepContent.length; i++) {
				Integer step= i+1;
				byte[] stepImg=Base64.getDecoder().decode(stepImgs[i]);
				
				RecipeStepVO recipeStepVO = new RecipeStepService().addRecipeStep(recipeVO, step, stepTime[i],
						stepImg,stepContent[i]);
			}

//			RequestDispatcher addRecipeKitchenware = req.getRequestDispatcher("/RecipeKitchenwareServlet");
//			addRecipeKitchenware.forward(req, res);	
			return;
		}
	}
}
