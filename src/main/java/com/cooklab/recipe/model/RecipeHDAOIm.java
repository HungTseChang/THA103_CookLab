package com.cooklab.recipe.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.cooklab.util.HibernateUtil;

public class RecipeHDAOIm implements RecipeDAO {

	@Override
	public void insert(RecipeVO recipeVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(recipeVO);
			session.getTransaction().commit();
			System.out.println("新增");
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
	}

	@Override
	public void update(RecipeVO recipeVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(recipeVO);
			session.getTransaction().commit();
			System.out.println("更新");
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}

	}

	@Override
	public void delete(Integer recipeNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			RecipeVO vo = session.get(RecipeVO.class, recipeNo);
			if(vo !=null) {
				session.delete(vo);
			}
			session.getTransaction().commit();
			System.out.println("刪除");
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}

	}

	@Override
	public RecipeVO findByPrimaryKey(Integer recipeNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			RecipeVO recipeVO = session.createQuery("from RecipeVO where  recipeNo =" + recipeNo, RecipeVO.class)
					.uniqueResult();

			session.getTransaction().commit();
			System.out.println("搜一筆");
			return recipeVO;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<RecipeVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<RecipeVO> list = session.createQuery("from RecipeVO ", RecipeVO.class).list();
			session.getTransaction().commit();
			System.out.println("搜尋");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return null;
	}

}
