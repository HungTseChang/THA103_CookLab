package com.cooklab.recipe.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class RecipeHDAOIm implements RecipeDAO {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public RecipeHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public int insert(RecipeVO recipeVO) {
		System.out.println("成功");
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

}
