package com.cooklab.recipe.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.RecipeBreowseDTO;
import com.cooklab.recipe.RecipeUpdateDTO;
import com.cooklab.recipe.model.RecipeServiceIm;
import com.cooklab.recipe.model.RecipeVO;
import com.google.gson.Gson;

@WebServlet("/RecipeUpdateServlet")
public class RecipeUpdateServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		String action = req.getHeader("action");
		HttpSession session = req.getSession();
		MembersVO membersVO = (MembersVO) session.getAttribute("membersVO");
		RecipeVO recipeVO = new RecipeServiceIm().getOneRecipe(Integer.valueOf(req.getHeader("recipeNo")));

		if (!recipeVO.getMembers().getMemberId().equals(membersVO.getMemberId())) {
			System.out.println("不是本人");
			return;
		}

		if ("getByUpdate".equals(action)) {
			RecipeBreowseDTO recipeBreowseDTO = new RecipeBreowseDTO(recipeVO);
			String jsonString = gson.toJson(recipeBreowseDTO);
			res.getWriter().write(jsonString);
			return;
		}
		if ("update".equals(action)) {

			BufferedReader reader = req.getReader();
			StringBuilder jsonBuilder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				jsonBuilder.append(line);
			}
			RecipeUpdateDTO recipeUpdateDTO = gson.fromJson(jsonBuilder.toString(), RecipeUpdateDTO.class);
			System.out.println(recipeUpdateDTO);

			Integer recipeNo = new RecipeServiceIm().updateRecipe(membersVO, recipeVO, recipeUpdateDTO);
			String jsonString = gson.toJson(recipeNo);
			res.getWriter().write(jsonString);
			return;
		}
	}
}
