package com.cooklab.recipe_collection.model;

import java.util.List;

import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;

public class RecipeCollectionTest {

	public static void main(String[] args) {
//		RecipeCollectionJDBCDAOlm dao = new RecipeCollectionJDBCDAOlm();
//=======================insert=======================
//		RecipeCollectionVO vo = new RecipeCollectionVO(null, 1, 1, null);
//		dao.insert(vo);
//=======================update=======================
//		RecipeCollectionVO vo = new RecipeCollectionVO(1, 2, 2, null);
//		dao.update(vo);
//=======================delete=======================
//		dao.delete(1);
//=======================findByPrimaryKey=============
//		System.out.println(dao.findByPrimaryKey(1));
//=======================getAll=======================
//		List<RecipeCollectionVO> recipeCollectionList;
//		recipeCollectionList = dao.getAll();
//			for (RecipeCollectionVO recipeCollection : recipeCollectionList) {
//				System.out.println(recipeCollection);
//			}	
//==========================Hibernate============================
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			//新增
//			RecipeCollectionVO vo = new RecipeCollectionVO(null, 1, 1, null);			
//			session.saveOrUpdate(vo);
			//更新
//			RecipeCollectionVO vo = new RecipeCollectionVO(1, 2, 5, null);			
//			session.saveOrUpdate(vo);
			//刪除
//			RecipeCollectionVO vo = session.get(RecipeCollectionVO.class,1);
//			if(vo !=null) {
//				session.delete(vo);
//			}
			//查詢(多筆)
//			List<RecipeCollectionVO> list = session.createQuery("from RecipeCollectionVO ",RecipeCollectionVO.class).list();
//			System.out.println(list);
			//查詢(單筆)
//			RecipeCollectionVO vo = session.createQuery("from RecipeCollectionVO where  collectionNo = 1", RecipeCollectionVO.class).uniqueResult();
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
