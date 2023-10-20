

	package com.cooklab.recipe_step.model;

	import java.util.List;

	import org.hibernate.Session;
	import org.hibernate.SessionFactory;

import com.cooklab.recipe.model.RecipeVO;

	public class RecipeStepHDAOIm implements RecipeStepDAO{
			private SessionFactory factory;

			public RecipeStepHDAOIm(SessionFactory factory) {
				this.factory = factory;
			}

			private Session getSession() {
				return factory.getCurrentSession();
			}

			@Override
			public int insert(RecipeStepVO recipeStepVO) {
				return (Integer) getSession().save(recipeStepVO);
			}

			@Override
			public boolean update(RecipeStepVO recipeStepVO) {
				try {
					getSession().update(recipeStepVO);
					return true;
				} catch (Exception e) {
					return false;
				}
			}

			@Override
			public boolean delete(RecipeVO RecipeVO) {
				RecipeStepVO vo = getSession().get(RecipeStepVO.class, RecipeVO);
				if (vo != null) {
					getSession().delete(vo);
					return true;
				} else {
					return false;
				}
			}

			@Override
			public RecipeStepVO findByPrimaryKey(RecipeVO RecipeVO) {
				return getSession().get(RecipeStepVO.class, RecipeVO);
			}

			@Override
			public List<RecipeStepVO> getAll() {
				return getSession().createQuery("from RecipeStepVO", RecipeStepVO.class).list();
			}
	}




		

