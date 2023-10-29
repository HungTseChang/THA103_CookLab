package com.cooklab.members.model;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.members.MemberRecipeOverViewDTO;
import com.cooklab.recipe.model.RecipeService;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.HibernateUtil;
import com.cooklab.recipe.BreowseRecipeDTO;
import com.cooklab.recipe.RecipeOverviewDTO;
@WebServlet("/TomTest")
public class TestMembers {
	public static void main(String[] args)
	{
//		MembersJDBCDAO jdc = new MembersJDBCDAO();
//		MembersVO memVO = new MembersVO();
		MembersService memSvc = new MembersService();
//		memSvc.MemberRecipeRead(2);
		
//		RecipeVO recipeVO = new RecipeService().getOneRecipe(Integer.valueOf(("1").trim()));
//		System.out.println(recipeVO);
//		System.out.println);
//		BreowseRecipeDTO breowseRecipeDTO=  new BreowseRecipeDTO(recipeVO);
//		System.out.println(breowseRecipeDTO.getRecipeName());
//		memSvc.updateMemberStatus(6,(byte)0);
		
//		byte[] byteArray = new byte[10];
//		memVO = memSvc.updateMember((Integer)2,"pppp12ww34","pppp1234",
//		"098055065","tommy76190@gmail.com", java.sql.Date.valueOf("2010-02-28"),""
//		,byteArray,"ppppp",Byte.valueOf((byte) 2));
		
//		memSvc.getOneMemberAccount("123");
//		memSvc.update();
		
//		memVO = memSvc.getOneMemberAccount("'ppp456'");
//		memSvc.getMemberRecipe(2);
		
		List li = memSvc.getByPage(0,9,2);
//		System.out.println("+++"+listReipceVO);
//		String jsonString = gson.toJson(listRecipeOverviewDTO);
//		res.getWriter().write(jsonString);	
	}
}
