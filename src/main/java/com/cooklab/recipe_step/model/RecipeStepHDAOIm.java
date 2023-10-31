

	package com.cooklab.recipe_step.model;

	import java.util.List;

	import org.hibernate.Session;
	import org.hibernate.SessionFactory;

import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.recipe_step.model.RecipeStepVO.CompositeDetail;

	public class RecipeStepHDAOIm implements RecipeStepDAO{
			private SessionFactory factory;

			public RecipeStepHDAOIm(SessionFactory factory) {
				this.factory = factory;
			}

			private Session getSession() {
				return factory.getCurrentSession();
			}

			@Override
			public CompositeDetail insert(RecipeStepVO recipeStepVO) {
				return (CompositeDetail) getSession().save(recipeStepVO);
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
			public int delete(RecipeVO recipeVO) {
				return getSession().createQuery("delete from RecipeStepVO where recipe = :recipe").setParameter("recipe",
						recipeVO).executeUpdate();
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




		

