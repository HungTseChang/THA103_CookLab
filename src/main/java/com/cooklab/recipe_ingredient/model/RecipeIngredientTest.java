package com.cooklab.recipe_ingredient.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;

public class RecipeIngredientTest {
	public static void main(String[] args) {
//		RecipeIngredientJDBCDAOlm dao = new RecipeIngredientJDBCDAOlm();
//=======================insert=======================
//		RecipeIngredientVO vo = new RecipeIngredientVO(null, 1, 2, "3", "4", null);
//		dao.insert(vo);
//=======================update=======================
//		RecipeIngredientVO vo = new RecipeIngredientVO(1, 1, 2, "3", "4", null);
//		dao.update(vo);
//=======================delete=======================	
//		dao.delete(1);
//=======================findByPrimaryKey=============
//		System.out.println(dao.findByPrimaryKey(1));
//=======================getAll=======================
//		List<RecipeIngredientVO> recipeIngredientList;
//		recipeIngredientList = dao.getAll();
//			for (RecipeIngredientVO recipeIngredient : recipeIngredientList) {
//				System.out.println(recipeIngredient);
//			}	
//==========================Hibernate============================
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			// 新增
//			RecipeIngredientVO vo = new RecipeIngredientVO(null, 2, 1, "3", "4", null);
//			session.saveOrUpdate(vo);
			// 更新
//					RecipeIngredientVO vo = new RecipeIngredientVO(1, 1, 2, "3", "4", null);		
//					session.saveOrUpdate(vo);
			// 刪除
//					RecipeIngredientVO vo = session.get(RecipeIngredientVO.class,1);
//					if(vo !=null) {
//						session.delete(vo);
//					}
			// 查詢(多筆)
//					List<RecipeIngredientVO> list = session.createQuery("from RecipeIngredientVO ",RecipeIngredientVO.class).list();
//					System.out.println(list);
			// 查詢(單筆)
//					RecipeIngredientVO vo = session.createQuery("from RecipeIngredientVO where  recipeIngredientNo = 1", RecipeIngredientVO.class).uniqueResult();
//					System.out.println(vo);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
	}
}
