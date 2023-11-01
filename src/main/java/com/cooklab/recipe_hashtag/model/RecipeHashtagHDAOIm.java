package com.cooklab.recipe_hashtag.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.recipe.model.RecipeVO;

public class RecipeHashtagHDAOIm implements RecipeHashtagDAO{
	private SessionFactory factory;

	public RecipeHashtagHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(RecipeHashtagVO recipeHashtagVO) {
		return (Integer) getSession().save(recipeHashtagVO);
	}

	@Override
	public boolean update(RecipeHashtagVO recipeHashtagVO) {
		try {
			getSession().update(recipeHashtagVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int delete(RecipeVO recipeVO) {
		return getSession().createQuery("delete from RecipeHashtagVO where recipe = :recipe").setParameter("recipe",
				recipeVO).executeUpdate();
	}

	@Override
	public RecipeHashtagVO findByPrimaryKey(Integer recipeHashtagNo) {
		return getSession().get(RecipeHashtagVO.class, recipeHashtagNo);
	}

	@Override
	public List<RecipeHashtagVO> getAll() {
		return getSession().createQuery("from HashtagVO", RecipeHashtagVO.class).list();
	}




}
