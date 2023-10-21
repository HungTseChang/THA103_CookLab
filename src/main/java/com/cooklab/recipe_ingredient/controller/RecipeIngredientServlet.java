package com.cooklab.recipe_ingredient.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.product.model.ProductService;
import com.cooklab.product.model.ProductVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_ingredient.model.RecipeIngredientService;
import com.cooklab.recipe_ingredient.model.RecipeIngredientVO;
import com.google.gson.Gson;

@WebServlet("/RecipeIngredientServlet")
public class RecipeIngredientServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			Gson gson = new Gson();
			List<String> errorMsgs = new LinkedList<String>();
			RecipeIngredientService RecipeIngredientSvc = new RecipeIngredientService();

			req.setAttribute("errorMsgs", errorMsgs);


			RecipeVO recipeVO = (RecipeVO) req.getAttribute("recipe");		
			ProductVO productVO = (ProductVO) new ProductService().getOneProduct(1);//測試
			String[] ingredient = gson.fromJson(req.getParameter("ingredient"), String[].class);
			String[] textLabel= new String[ingredient.length];
			String[] ingredientQuantity = gson.fromJson(req.getParameter("ingredientQuantity"), String[].class);

			for (int i = 0; i < ingredientQuantity.length; i++) {
//				productVO = new ProductService().getOneProduct(ingredient[]);
//				if(productVO==null) {
//					textLabel[i]=ingredient[i];
//				}
				RecipeIngredientVO recipeIngredientVO = new RecipeIngredientVO();
				recipeIngredientVO = RecipeIngredientSvc.addRecipeIngredient(recipeVO, productVO, textLabel[i],
						ingredientQuantity[i]);
			}

			RequestDispatcher addRecipeKitchenware = req.getRequestDispatcher("/RecipeKitchenwareServlet");
			addRecipeKitchenware.forward(req, res);	
			return;
//			res.getWriter().write("2");
		}
	}

}
