package com.cooklab.recipe_comments.model;

import java.util.List;

import com.cooklab.members.model.MembersVO;
import com.cooklab.product.model.ProductVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.HibernateUtil;

public class RecipeCommentsServiceIm implements  RecipeCommentsService{
	private RecipeCommentsDAO dao;

	public RecipeCommentsServiceIm() {
		dao = new RecipeCommentsHDAOIm(HibernateUtil.getSessionFactory());
	}
	@Override
	public RecipeCommentsVO addRecipeComments(RecipeVO recipeVO, MembersVO membersVO, String commentContent) {
		RecipeCommentsVO recipeCommentsVO = new RecipeCommentsVO();
		recipeCommentsVO.setRecipe(recipeVO);
		recipeCommentsVO.setMembers(membersVO);
		recipeCommentsVO.setCommentContent(commentContent);
		dao.insert(recipeCommentsVO);
		return recipeCommentsVO;
	}

	@Override
	public RecipeCommentsVO updateRecipeComments(Integer recipeCommentsNo, RecipeVO recipeVO, ProductVO productVO,
			String commentContent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteRecipeComments(Integer recipeCommentsNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RecipeCommentsVO getOneRecipeComments(Integer recipeCommentsNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecipeCommentsVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
