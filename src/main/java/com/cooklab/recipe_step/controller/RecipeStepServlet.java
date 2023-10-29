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
public class RecipeStepServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
	}
}
