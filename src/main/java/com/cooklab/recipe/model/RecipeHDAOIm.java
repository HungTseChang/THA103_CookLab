package com.cooklab.recipe.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RecipeHDAOIm implements RecipeDAO {
	private SessionFactory factory;

	public RecipeHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(RecipeVO recipeVO) {
		return (Integer) getSession().save(recipeVO);
	}

	@Override
	public boolean update(RecipeVO recipeVO) {
		try {
			getSession().update(recipeVO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Integer recipeNo) {
		RecipeVO vo = getSession().get(RecipeVO.class, recipeNo);
		if (vo != null) {
			getSession().delete(vo);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public RecipeVO findByPrimaryKey(Integer recipeNo) {
		return getSession().get(RecipeVO.class, recipeNo);
	}

	@Override
	public List<RecipeVO> getAll() {
		return getSession().createQuery("from RecipeVO", RecipeVO.class).list();
	}

	@Override
	public List<RecipeVO> getByPage(String cloumn, boolean desc, Integer offset, Integer limit) {
		return getSession()
				.createQuery("from RecipeVO where recipeStatus = 0 order by " + cloumn + (desc ? " desc" : " asc"),
						RecipeVO.class)
				.setFirstResult(offset).setMaxResults(limit).list();
	}

	@Override
	public long getCount() {
		return (long) getSession().createQuery("select count(*) from RecipeVO").uniqueResult();
	}

}
