package com.cooklab.recipe.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cooklab.recipe.RecipeBreowseDTO;
import com.cooklab.recipe.RecipeOverviewDTO;
import com.cooklab.recipe.model.RecipeServiceIm;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.JedisUtil;
import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

@WebServlet("/RecipeOverviewServlet")
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
			String recipeNo = req.getParameter("recipeNo").trim();
			String ipAddress = req.getHeader("X-Forwarded-For");
			if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = req.getHeader("X-Real-IP");
			}

			if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = req.getRemoteAddr();
			}
//			String ipAddress = req.getRemoteAddr();
			Jedis jedis = JedisUtil.getJedisPool().getResource();
			jedis.select(9);
			if (!jedis.hexists("recipeViewIP:" + recipeNo, ipAddress)) {
				jedis.hset("recipeViewIP:" + recipeNo, ipAddress, "view");
				jedis.expire("recipeViewIP:" + recipeNo, 15);
				jedis.hincrBy("recipeViewCount", recipeNo, 1);
			}
			jedis.close();

			RecipeVO recipeVO = new RecipeServiceIm().getOneRecipe(Integer.valueOf(recipeNo));
			RecipeBreowseDTO recipeBreowseDTO = new RecipeBreowseDTO(recipeVO);
			String jsonString = gson.toJson(recipeBreowseDTO);
			res.getWriter().write(jsonString);
			return;
		}

		if ("overview".equals(action)) {
			String cloumn = req.getParameter("cloumn");
			boolean desc = Boolean.valueOf(req.getParameter("desc"));
			Integer page = Integer.valueOf(req.getParameter("page"));
			String search = req.getParameter("search");
			List<RecipeVO> listReipceVO = new RecipeServiceIm().getBySearch(cloumn, desc, (page) * 10, 9, search);
			List<RecipeOverviewDTO> listRecipeOverviewDTO = new ArrayList<>();
			for (RecipeVO recipeVO : listReipceVO) {
				listRecipeOverviewDTO.add(new RecipeOverviewDTO(recipeVO));
			}
			String jsonString = gson.toJson(listRecipeOverviewDTO);
			res.getWriter().write(jsonString);
		}

		if ("getPage".equals(action)) {
			String search = req.getParameter("search");
			long count = new RecipeServiceIm().getCount(search);
			long page = count / 9 + (count % 9 == 0 ? 0 : 1);
			String jsonString = gson.toJson(page);
			res.getWriter().write(jsonString);
		}

	}

}
