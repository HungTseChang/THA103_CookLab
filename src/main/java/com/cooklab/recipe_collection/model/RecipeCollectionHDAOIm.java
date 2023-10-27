package com.cooklab.recipe_collection.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.members.model.MembersVO;
import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_hashtag.model.RecipeHashtagVO;

public class RecipeCollectionHDAOIm implements RecipeCollectionDAO {
	private SessionFactory factory;

	public RecipeCollectionHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(RecipeCollectionVO recipeCollectionVO) {
		return (Integer) getSession().save(recipeCollectionVO);
	}

	@Override
	public boolean update(RecipeCollectionVO recipeCollectionVO) {
		try {
			getSession().update(recipeCollectionVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Integer recipeCollectionNo) {
		RecipeCollectionVO vo = getSession().get(RecipeCollectionVO.class, recipeCollectionNo);
		if (vo != null) {
			getSession().delete(vo);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public RecipeCollectionVO findByPrimaryKey(Integer recipeCollectionNo) {
		return getSession().get(RecipeCollectionVO.class, recipeCollectionNo);
	}

	@Override
	public List<RecipeCollectionVO> getAll() {
		return getSession().createQuery("from RecipeCollectionVO", RecipeCollectionVO.class).list();
	}

	@Override
	public RecipeCollectionVO findByMemberAndRecipe(MembersVO membersVO, RecipeVO recipeVO) {
		return getSession().createQuery("from RecipeCollectionVO where members = :members and recipe = :recipe",
				RecipeCollectionVO.class).setParameter("members", membersVO).setParameter("recipe", recipeVO).uniqueResult();
	}

}
