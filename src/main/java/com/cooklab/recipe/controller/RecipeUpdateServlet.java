package com.cooklab.recipe.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.RecipeCreateDTO;
import com.cooklab.recipe.RecipeUpdateDTO;
import com.cooklab.recipe.model.RecipeServiceIm;
import com.cooklab.util.HibernateUtil;
import com.google.gson.Gson;

@WebServlet("/RecipeUpdateServlet")
public class RecipeUpdateServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
//		HttpSession session = req.getSession();
//		MembersVO members = (MembersVO) session.getAttribute("members");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		MembersVO memberVO = session.get(MembersVO.class, 1);
		BufferedReader reader = req.getReader();
		StringBuilder jsonBuilder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			jsonBuilder.append(line);
		}
		RecipeUpdateDTO recipeUpdateDTO = gson.fromJson(jsonBuilder.toString(), RecipeUpdateDTO.class);
		
		Integer recipeNo = new RecipeServiceIm().updateRecipe(memberVO, recipeUpdateDTO);
		String jsonString = gson.toJson(recipeNo);
		res.getWriter().write(jsonString);
		return;
	}

}
