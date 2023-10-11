package com.cooklab.recipe_step.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;

public class RecipeStepTest {
	public static void main(String[] args) {
		RecipeStepJDBCDAOlm dao = new RecipeStepJDBCDAOlm();
//=======================insert=======================		
//		RecipeStepVO vo = new RecipeStepVO(1, 1, 10, null, "����", null);
//		dao.insert(vo);
//=======================update=======================		
//		RecipeStepVO vo = new RecipeStepVO(2, 2, 20, null, "����update", null);
//		dao.update(vo);
//=======================delete=======================		
//		dao.delete(1);
//=======================findByPrimaryKey=============
//		System.out.println(dao.findByPrimaryKey(1));
//=======================getAll=======================
//		List<RecipeStepVO> recipeStepList;
//		recipeStepList = dao.getAll();
//		for (RecipeStepVO recipeStep : recipeStepList) {
//			System.out.println(recipeStep);
//		}
//==========================Hibernate============================
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			// 新增
//			RecipeStepVO vo = new RecipeStepVO(2, 1, 10, null, "步驟", null);
//			session.saveOrUpdate(vo);
			// 更新
//			RecipeStepVO vo = new RecipeStepVO(2, 2, 20, null, "����update", null);		
//			session.saveOrUpdate(vo);
			// 刪除
//			RecipeStepVO vo = session.get(RecipeStepVO.class, 1);
//			if (vo != null) {
//				session.delete(vo);
//			}
			// 查詢(多筆)
//			List<RecipeStepVO> list = session.createQuery("from RecipeStepVO ", RecipeStepVO.class).list();
//			System.out.println(list);
			// 查詢(單筆)
//			RecipeStepVO vo = session.createQuery("from RecipeStepVO where  recipeNo = 1", RecipeStepVO.class)
//					.uniqueResult();
//			System.out.println(vo);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
	}

}
