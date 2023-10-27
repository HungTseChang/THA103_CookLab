package com.cooklab.recipe_comments.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RecipeCommentsHDAOIm implements RecipeCommentsDAO{
	private SessionFactory factory;

	public RecipeCommentsHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(RecipeCommentsVO recipeCommentsVO) {
		return (Integer) getSession().save(recipeCommentsVO);
	}

	@Override
	public boolean update(RecipeCommentsVO recipeCommentsVO) {
		try {
			getSession().update(recipeCommentsVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Integer recipeCommentsNo) {
		RecipeCommentsVO vo = getSession().get(RecipeCommentsVO.class, recipeCommentsNo);
		if (vo != null) {
			getSession().delete(vo);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public RecipeCommentsVO findByPrimaryKey(Integer recipeCommentsNo) {
		return getSession().get(RecipeCommentsVO.class, recipeCommentsNo);
	}

	@Override
	public List<RecipeCommentsVO> getAll() {
		return getSession().createQuery("from RecipeCommentsVO", RecipeCommentsVO.class).list();
	}
}
