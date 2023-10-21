package com.cooklab.recipe_ingredient.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cooklab.recipe.model.RecipeVO;

public class RecipeIngredientHDAOIm implements RecipeIngredientDAO{
		private SessionFactory factory;

		public RecipeIngredientHDAOIm(SessionFactory factory) {
			this.factory = factory;
		}

		private Session getSession() {
			return factory.getCurrentSession();
		}

		@Override
		public int insert(RecipeIngredientVO recipeIngredientVO) {
			return (Integer) getSession().save(recipeIngredientVO);
		}

		@Override
		public boolean update(RecipeIngredientVO recipeIngredientVO) {
			try {
				getSession().update(recipeIngredientVO);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		@Override
		public boolean delete(RecipeVO recipe) {
			RecipeIngredientVO vo = getSession().get(RecipeIngredientVO.class, recipe);
			if (vo != null) {
				getSession().delete(vo);
				return true;
			} else {
				return false;
			}
		}

		@Override
		public RecipeIngredientVO findByPrimaryKey(Integer recipeIngredientNo) {
			return getSession().get(RecipeIngredientVO.class, recipeIngredientNo);
		}

		@Override
		public List<RecipeIngredientVO> getAll() {
			return getSession().createQuery("from recipeIngredientVO", RecipeIngredientVO.class).list();
		}
}




	

