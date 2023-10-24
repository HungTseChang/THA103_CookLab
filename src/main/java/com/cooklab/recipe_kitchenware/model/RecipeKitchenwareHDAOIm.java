
package com.cooklab.recipe_kitchenware.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.recipe.model.RecipeVO;

public class RecipeKitchenwareHDAOIm implements RecipeKitchenwareDAO {
	private SessionFactory factory;

	public RecipeKitchenwareHDAOIm(SessionFactory factory) {
				this.factory = factory;
			}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(RecipeKitchenwareVO recipeKitchenwareVO) {
		return (Integer) getSession().save(recipeKitchenwareVO);
	}

	@Override
	public boolean update(RecipeKitchenwareVO recipeKitchenwareVO) {
		try {
			getSession().update(recipeKitchenwareVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(RecipeVO recipe) {
		RecipeKitchenwareVO vo = getSession().get(RecipeKitchenwareVO.class, recipe);
		if (vo != null) {
			getSession().delete(vo);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public RecipeKitchenwareVO findByPrimaryKey(Integer recipeKitchenwareNo) {
		return getSession().get(RecipeKitchenwareVO.class, recipeKitchenwareNo);
	}

	@Override
	public List<RecipeKitchenwareVO> getAll() {
		return getSession().createQuery("from RecipeKitchenwareVO", RecipeKitchenwareVO.class).list();
	}
}
