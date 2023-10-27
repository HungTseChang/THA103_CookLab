package com.cooklab.recipe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.cooklab.members.model.MembersService;
import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.RecipeBreowseDTO;
import com.cooklab.recipe.RecipeOverviewDTO;
import com.cooklab.recipe.model.RecipeServiceIm;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.HibernateUtil;
import com.google.gson.Gson;

@WebServlet("/RecipeOverviewServlet")
//@MultipartConfig
public class RecipeOverviewServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		String action = req.getParameter("action");

		if ("browse".equals(action)) {
			RecipeVO recipeVO = new RecipeServiceIm().getOneRecipe(Integer.valueOf(req.getParameter("recipeNo").trim()));
			RecipeBreowseDTO breowseRecipeDTO = new RecipeBreowseDTO(recipeVO);
			String jsonString = gson.toJson(breowseRecipeDTO);
			res.getWriter().write(jsonString);
			return;
		}

		if ("overview".equals(action)) {
			String cloumn = req.getParameter("cloumn");
			boolean desc = Boolean.valueOf(req.getParameter("desc"));
			Integer page = Integer.valueOf(req.getParameter("page"));
			List<RecipeVO> listReipceVO = new RecipeServiceIm().getByPage(cloumn, desc, (page) * 10, 9);
			List<RecipeOverviewDTO> listRecipeOverviewDTO = new ArrayList<>();
			for (RecipeVO recipeVO : listReipceVO) {
				System.out.println(recipeVO);
				listRecipeOverviewDTO.add(new RecipeOverviewDTO(recipeVO));
			}
			String jsonString = gson.toJson(listRecipeOverviewDTO);
			res.getWriter().write(jsonString);
		}

		if ("getPage".equals(action)) {
			long count = new RecipeServiceIm().getCount();
			long page = count / 9 + (count % 9 == 0 ? 0 : 1);
			String jsonString = gson.toJson(page);
			res.getWriter().write(jsonString);
		}

	}

}
