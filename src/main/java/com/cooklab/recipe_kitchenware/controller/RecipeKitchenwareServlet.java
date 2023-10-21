package com.cooklab.recipe_kitchenware.controller;

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
import com.cooklab.recipe_kitchenware.model.RecipeKitchenwareService;
import com.cooklab.recipe_kitchenware.model.RecipeKitchenwareVO;
import com.google.gson.Gson;

@WebServlet("/RecipeKitchenwareServlet")
public class RecipeKitchenwareServlet extends HttpServlet {
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
			String[] kitchenware = gson.fromJson(req.getParameter("kitchenware"), String[].class);
			String[] textLabel = new String[kitchenware.length];

			for (int i = 0; i < kitchenware.length; i++) {
				ProductVO productVO = new ProductService().findByProductName((kitchenware[i]));
				if (productVO == null) {
					textLabel[i] = kitchenware[i];
				}
				RecipeKitchenwareVO recipeKitchenwareVO = new RecipeKitchenwareService().addRecipeKitchenware(recipeVO,
						productVO, textLabel[i]);
			}

			RequestDispatcher addRecipeStep = req.getRequestDispatcher("/RecipeStepServlet");
			addRecipeStep.forward(req, res);	
			return;
		}
		if ("search".equals(action)) {

			String kitchenware = req.getParameter("search");
			List<String> listProductName = new ArrayList<String>();
			List<ProductVO> listProductVO = new ProductService().findByProductNames(kitchenware,
					"kitchenwareCategoryNo");
			for (ProductVO productVO : listProductVO) {
				listProductName.add(productVO.getProductName());
			}

			String jsonString = gson.toJson(listProductName);
			res.getWriter().write(jsonString);
			return;
		}

	}

}
