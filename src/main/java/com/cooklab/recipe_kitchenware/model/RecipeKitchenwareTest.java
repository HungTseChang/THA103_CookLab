package com.cooklab.recipe_kitchenware.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;

public class RecipeKitchenwareTest {
	public static void main(String[] args) {
//		RecipeKitchenwareJDBCDAOlm dao = new RecipeKitchenwareJDBCDAOlm();
//=======================insert=======================
//		RecipeKitchenwareVO vo = new RecipeKitchenwareVO(null,1,1,"�¤�r����",null);
//		dao.insert(vo);
//=======================update=======================		
//		RecipeKitchenwareVO vo = new RecipeKitchenwareVO(null,2,2,"�¤�r����update",null);
//		dao.update(vo);
//=======================delete=======================	
//		dao.delete(1);
//=======================findByPrimaryKey=============
//		System.out.println(dao.findByPrimaryKey(1));
//=======================getAll=======================
//		List<RecipeKitchenwareVO> recipeKitchenwareList;
//		recipeKitchenwareList = dao.getAll();
//			for (RecipeKitchenwareVO recipeKitchenware : recipeKitchenwareList) {
//				System.out.println(recipeKitchenware);
//			}			
//==========================Hibernate============================
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			// 新增
//			RecipeKitchenwareVO vo = new RecipeKitchenwareVO(null, 1, 1, "�¤�r����", null);
//			session.saveOrUpdate(vo);
			// 更新
//			RecipeKitchenwareVO vo = new RecipeKitchenwareVO(null, 2, 2, "�¤�r����update", null);
//			session.saveOrUpdate(vo);
			// 刪除
//			RecipeKitchenwareVO vo = session.get(RecipeKitchenwareVO.class, 1);
//			if (vo != null) {
//				session.delete(vo);
//			}
			// 查詢(多筆)
//			List<RecipeKitchenwareVO> list = session.createQuery("from RecipeCollectionVO ", RecipeKitchenwareVO.class)
//					.list();
//			System.out.println(list);
			// 查詢(單筆)
//			RecipeKitchenwareVO vo = session
//					.createQuery("from RecipeKitchenwareVO where  recipeKitchenwareNo = 1", RecipeKitchenwareVO.class)
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
