package com.cooklab.recipe.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.recipe.model.RecipeService;
import com.cooklab.recipe.model.RecipeVO;

@WebServlet("/RecipeServletImg")
public class RecipeServletImg extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		Integer recipeNo = Integer.valueOf(req.getParameter("recipe_no").trim());
		RecipeService recipeSvc = new RecipeService();
		RecipeVO recipeVO = recipeSvc.getOneRecipe(recipeNo);
		byte[] coverImage= recipeVO.getCoverImage();
		
		InputStream is = new ByteArrayInputStream(coverImage);
		BufferedInputStream in = new BufferedInputStream(is);
		byte[] buf = new byte[4 * 1024]; // 4K buffer
		int len;
		while ((len = in.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		in.close();
	}
}