package com.cooklab.members.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.model.RecipeVO;
import com.google.gson.Gson;
import com.cooklab.members.MemberFollowOverViewDTO;
@WebServlet("/FollowServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class MembersFollowServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		
		HttpSession session = req.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		//從請求拿值 看要做甚麼動作
        String action = req.getParameter("action");
        String no = req.getParameter("no");
        
        if(action.equals("article"))
        {

			res.sendRedirect(req.getContextPath()+"/frontstage/members/member-panel.jsp");
        }
        else if(action.equals("member"))
        {
        	
        }
        else if(action.equals("recipe"))
        {
        	
        }
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
 
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		Gson gson = new Gson();
		
		HttpSession session = req.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		//從請求拿值 看要做甚麼動作
        String action = req.getParameter("action");
        
        //先拿好 Session
		if(action.equals("overview"))
		{
			List<MembersVO> listMembersVO = new MembersService().getFollow(Integer.valueOf(0)*10, 90,userId);
			List<MemberFollowOverViewDTO> listMemberFollowOverViewDTO = new ArrayList<>();
			for(MembersVO membersVO :listMembersVO) {
				listMemberFollowOverViewDTO.add(new MemberFollowOverViewDTO(membersVO));
			}
			String jsonString = gson.toJson(listMemberFollowOverViewDTO);
			res.getWriter().write(jsonString);	
		}
		//新增會員關注
		else if(action.equals("newMemberCO"))
		{
			Integer CoId = Integer.valueOf(req.getParameter("CollectionID"));
			
			MembersService mc = new MembersService();
			mc.addMembersColloection(CoId,userId);
			
			HashMap<String, Object> hmap = new HashMap<>();
			hmap.put("res", "CollectionOK");
			
			String jsonString = gson.toJson(hmap);
			res.getWriter().write(jsonString);	
			System.out.println("新增關注會員成功");
		}
		//取消會員關注
		else if(action.equals("delMemberCO"))
		{
			Integer CoId = Integer.valueOf(req.getParameter("CollectionID"));
//			System.out.println(CoId);
			MembersService mc = new MembersService();
			mc.deleteMemberColloection(CoId,userId);
			
			HashMap<String, Object> hmap = new HashMap<>();
			hmap.put("res", "DelCollectionOK");
			
			String jsonString = gson.toJson(hmap);
			res.getWriter().write(jsonString);	
			
			System.out.println("刪除關注會員成功");
		}
		//查詢會員關注狀態
		else if(action.equals("SearchMemberCO"))
		{
			Integer CoId = Integer.valueOf(req.getParameter("CollectionID"));
//			System.out.println(CoId);
			MembersService mc = new MembersService();
			mc.deleteMemberColloection(CoId,userId);
			HashMap<String, Object> hmap = new HashMap<>();
			
			if(mc.SearchMemberColloection(CoId,userId) == true)
				hmap.put("res", "true");
			else
				hmap.put("res", "false");
			
			String jsonString = gson.toJson(hmap);
			res.getWriter().write(jsonString);	
			
			System.out.println("查詢關注會員成功");
		}
		//刪除討論區文章收藏
		else if(action.equals("delArticleCO"))
		{
			Integer CoId = Integer.valueOf(req.getParameter("CollectionID"));
//			System.out.println(CoId);
			MembersService mc = new MembersService();
			mc.DeleteArticleCollection(CoId);
			
			HashMap<String, Object> hmap = new HashMap<>();
			hmap.put("res", "DelCollectionOK");
			
			String jsonString = gson.toJson(hmap);
			res.getWriter().write(jsonString);	
			
			System.out.println("刪除關注討論區文章成功");
		}
	}
}
