package com.cooklab.recipe_collection.model;

import java.util.List;

import com.cooklab.members.model.MembersVO;
import com.cooklab.product.model.ProductVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.HibernateUtil;

public class RecipeCollectionServiceIm implements RecipeCollectionSrevice {
	private RecipeCollectionDAO dao;

	public RecipeCollectionServiceIm() {
		dao = new RecipeCollectionHDAOIm(HibernateUtil.getSessionFactory());
	}

	@Override
	public Integer addRecipeCollection(RecipeVO recipeVO, MembersVO membersVO) {
		RecipeCollectionVO recipeCollectionVO = dao.findByMemberAndRecipe(membersVO, recipeVO);
		if (recipeCollectionVO == null) {
			recipeCollectionVO = new RecipeCollectionVO();
			recipeCollectionVO.setRecipe(recipeVO);
			recipeCollectionVO.setMembers(membersVO);
			return dao.insert(recipeCollectionVO);
		} else {
			return recipeCollectionVO.getCollectionNo();
		}
	}

	@Override
	public RecipeCollectionVO updateRecipeCollection(Integer recipeCollectionNo, RecipeVO recipeVO,
			ProductVO productVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteRecipeCollection(Integer RecipeCollectionNo) {
		return dao.delete(RecipeCollectionNo);
	}

	@Override
	public RecipeCollectionVO getOneRecipeCollection(Integer recipeCollectionNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecipeCollectionVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeCollectionVO findByMemberAndRecipe(RecipeVO recipeVO, MembersVO membersVO) {

		return dao.findByMemberAndRecipe(membersVO, recipeVO);
	}

}
