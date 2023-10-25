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

import com.cooklab.members.model.MembersService;
import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.BreowseRecipeDTO;
import com.cooklab.recipe.RecipeOverviewDTO;
import com.cooklab.recipe.model.RecipeService;
import com.cooklab.recipe.model.RecipeVO;
import com.google.gson.Gson;

@WebServlet("/RecipeServlet")
//@MultipartConfig
public class RecipeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		String action = req.getParameter("action");
//		HttpSession session = req.getSession();
//		MembersVO members = (MembersVO) session.getAttribute("members");
		MembersService membersSvc = new MembersService();// 測試
		MembersVO members = membersSvc.getOneMember(1); // 測試

		if ("insert".equals(action)) {

			/* ================================ 驗證格式 ================================ */
			// 食譜名稱
			String recipeName = req.getParameter("recipeName").trim();
			if (recipeName.equals("")) {
				res.getWriter().write(gson.toJson("請輸入食譜名稱"));
				return;
			}
			// 成品圖片
			byte[] coverImage;
			if (req.getParameter("coverImage") == null) {
				res.getWriter().write(gson.toJson("請放成品圖片"));
				return;
			} else {
				coverImage = Base64.getDecoder().decode(req.getParameter("coverImage"));
			}
			// 簡介
			String introduction = req.getParameter("introduction");
			if (introduction.equals("")) {
				res.getWriter().write(gson.toJson("請輸入簡介"));
				return;
			}
			// 補充說明
			String additionalExplanation = req.getParameter("additionalExplanation");
			// 地區
			String region = req.getParameter("region");
			// 食譜狀態
			Byte recipeStatus = Byte.valueOf(req.getParameter("recipeStatus"));
			// 食譜份量
			Byte recipeQuantity = Byte.valueOf(req.getParameter("recipeQuantity"));
			// 食材
			String[] ingredient = gson.fromJson(req.getParameter("ingredient"), String[].class);
			for (String string : ingredient) {
				if (string.equals("")) {
					res.getWriter().write(gson.toJson("請輸入食材"));
					return;
				}
			}
			// 食材份量
			String[] ingredientQuantity = gson.fromJson(req.getParameter("ingredientQuantity"), String[].class);
			for (String string : ingredientQuantity) {
				if (string.equals("")) {
					res.getWriter().write(gson.toJson("請輸入食材份量"));
					return;
				}
			}
			// 廚具
			String[] kitchenware = gson.fromJson(req.getParameter("kitchenware"), String[].class);
			for (String string : kitchenware) {
				if (string.equals("")) {
					res.getWriter().write(gson.toJson("請輸入廚具"));
					return;
				}
			}
			// 步驟時間
			String[] stepTime = gson.fromJson(req.getParameter("stepTime"), String[].class);
			for (String string : stepTime) {
				String regex = "^[1-9]\\d*$";
				if (!string.trim().matches(regex)) {
					res.getWriter().write(gson.toJson("步驟時間為空或格式錯誤"));
					return;
				}
			}
			// 步驟內容
			String[] stepContent = gson.fromJson(req.getParameter("stepContent"), String[].class);
			for (String string : stepContent) {
				if (string.equals("")) {
					res.getWriter().write(gson.toJson("請輸入步驟內容"));
					return;
				}
			}

			/* ================================ 新增食譜 ================================ */
			RecipeService reipceSvc = new RecipeService();
			RecipeVO recipeVO = reipceSvc.addRecipe(members, recipeName, coverImage, introduction,
					additionalExplanation, region, recipeStatus, recipeQuantity);
			/* ================================ 轉導到新增食材 ================================ */
			req.setAttribute("recipe", recipeVO);
			RequestDispatcher addRecipeIngredient = req.getRequestDispatcher("/RecipeIngredientServlet");
			addRecipeIngredient.forward(req, res);
			return;
		}
		
		if ("browse".equals(action)) {
			RecipeVO recipeVO = new RecipeService().getOneRecipe(Integer.valueOf(req.getParameter("recipeNo").trim()));
			BreowseRecipeDTO breowseRecipeDTO=  new BreowseRecipeDTO(recipeVO);
			String jsonString = gson.toJson(breowseRecipeDTO);
			res.getWriter().write(jsonString);
			return;
		}
		if("overview".equals(action)) {
			List<RecipeVO> listReipceVO = new RecipeService().getByPage(Integer.valueOf(req.getParameter("page"))*10, 9);
			List<RecipeOverviewDTO> listRecipeOverviewDTO = new ArrayList<>();
			for(RecipeVO reipceVO :listReipceVO) {
				listRecipeOverviewDTO.add(new RecipeOverviewDTO(reipceVO));
			}
			String jsonString = gson.toJson(listRecipeOverviewDTO);
			res.getWriter().write(jsonString);	
		}
		if("getPage".equals(action)) {
			long count = new RecipeService().getCount();
			long page = count/9+(count%9==0?0:1);	
			String jsonString = gson.toJson(page);
			res.getWriter().write(jsonString);	
		}

	}

}
