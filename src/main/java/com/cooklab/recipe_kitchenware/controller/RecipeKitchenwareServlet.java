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
