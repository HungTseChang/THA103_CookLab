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
	public List<RecipeVO> getBySearch(String cloumn, boolean desc, Integer offset, Integer limit, String search) {
//		return getSession()
//				.createQuery("select distinct RecipeVO  from RecipeVO r " + "left join fetch r.ingredient ri "
//						+ "left join fetch r.kitchenware rk " + "where (r.recipeStatus = 0) "
//						+ "and (r.recipeName like :search " + "or ri.textLabel like :search "
//						+ "or rk.textLabel like :search) order by r." + cloumn + (desc ? " desc" : " asc"),
//						RecipeVO.class)
//				.setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(limit).list();

		return getSession().createQuery("select distinct r from RecipeVO r " + "where (r.recipeStatus = 0) "
				+ " and (r.recipeName like :search "
				+ " or r in (select recipe from RecipeIngredientVO ri where ri.textLabel like :search) "
				+ " or r in (select recipe from RecipeKitchenwareVO rk where rk.textLabel like :search)) "
				+ " order by r." + cloumn + (desc ? " desc" : " asc"), RecipeVO.class)
				.setParameter("search", "%" + search + "%").setFirstResult(offset).setMaxResults(limit).list();

	}

	@Override
	public long getCount(String search) {
		return (long) getSession().createQuery("select count(distinct r) from RecipeVO r "
				+ "left join r.ingredient ri " + "left join r.kitchenware rk " + "where (r.recipeStatus = 0) "
				+ "and (r.recipeName like :search " + "or ri.textLabel like :search "
				+ "or rk.textLabel like :search )").setParameter("search", "%" + search + "%").uniqueResult();
	}

}
