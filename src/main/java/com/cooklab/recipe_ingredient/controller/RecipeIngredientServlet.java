package com.cooklab.recipe_ingredient.controller;

import java.io.IOException;
import java.util.ArrayList;
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
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String action = req.getParameter("action");
		Gson gson = new Gson();
		
		if ("insert".equals(action)) {
			
			RecipeVO recipeVO = (RecipeVO) req.getAttribute("recipe");		
			String[] ingredient = gson.fromJson(req.getParameter("ingredient"), String[].class);
			String[] textLabel= new String[ingredient.length];
			String[] ingredientQuantity = gson.fromJson(req.getParameter("ingredientQuantity"), String[].class);

			for (int i = 0; i < ingredientQuantity.length; i++) {
				ProductVO productVO = new ProductService().findByProductName((ingredient[i]));
				if(productVO==null) {
					textLabel[i]=ingredient[i];
				}
				RecipeIngredientVO recipeIngredientVO = new RecipeIngredientService().addRecipeIngredient(recipeVO, productVO, textLabel[i],
						ingredientQuantity[i]);
			}

			RequestDispatcher addRecipeKitchenware = req.getRequestDispatcher("/RecipeKitchenwareServlet");
			addRecipeKitchenware.forward(req, res);	
			return;
		}
		if ("search".equals(action)) {
			
			String ingredient = req.getParameter("search");
			List<String> listProductName = new ArrayList<String>();
			List<ProductVO>listProductVO = new ProductService().findByProductNames(ingredient,"ingredientCategoryNo");
			for(ProductVO productVO: listProductVO) {
				listProductName.add(productVO.getProductName());
			}
				
	        String jsonString = gson.toJson(listProductName);
	        res.getWriter().write(jsonString);
	        return;
		}
		
		
		
	}

}
