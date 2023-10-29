package com.cooklab.members.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cooklab.members.MemberRecipeOverViewDTO;
import com.cooklab.members.model.MembersService;
import com.cooklab.recipe.model.RecipeVO;
import com.google.gson.Gson;

@WebServlet("/MemberRecipeServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class MembersRecipeOverViewServlet  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();

		HttpSession session = req.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<RecipeVO> listReipceVO = new MembersService().getByPage(Integer.valueOf(0)*10, 90,userId);
		List<MemberRecipeOverViewDTO> listRecipeOverviewDTO = new ArrayList<>();
		for(RecipeVO reipceVO :listReipceVO) {
			listRecipeOverviewDTO.add(new MemberRecipeOverViewDTO(reipceVO));
		}
		String jsonString = gson.toJson(listRecipeOverviewDTO);
		res.getWriter().write(jsonString);	
	}
}
