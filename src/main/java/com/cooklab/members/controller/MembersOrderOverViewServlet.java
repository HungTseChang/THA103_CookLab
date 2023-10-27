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

import com.cooklab.member_order.model.MemberOrderVO;
import com.cooklab.members.MemberOrderOverViewDTO;
import com.cooklab.members.MemberRecipeOverViewDTO;
import com.cooklab.members.model.MembersService;
import com.cooklab.recipe.model.RecipeVO;
import com.google.gson.Gson;

@WebServlet("/MemberOrderForServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class MembersOrderOverViewServlet  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();

		HttpSession session = req.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		List<MemberOrderVO> memberOrderVO = new MembersService().getOrder(Integer.valueOf(0)*10, 90,userId);
		List<MemberOrderOverViewDTO> listRecipeOverviewDTO = new ArrayList<>();
		for(MemberOrderVO memberOrderList :memberOrderVO) {
			listRecipeOverviewDTO.add(new MemberOrderOverViewDTO(memberOrderList));
		}
		String jsonString = gson.toJson(listRecipeOverviewDTO);
		res.getWriter().write(jsonString);	
	}
	

}
