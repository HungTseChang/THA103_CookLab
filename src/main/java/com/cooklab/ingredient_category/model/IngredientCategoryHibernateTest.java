package com.cooklab.ingredient_category.model;

import org.hibernate.Session;

import com.cooklab.util.HibernateUtil;

public class IngredientCategoryHibernateTest {
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			IngredientCategoryVO icvo = new IngredientCategoryVO();
			icvo.setCategoryName("板腱牛肉");

			session.saveOrUpdate(icvo);

			session.getTransaction().commit();
			
			System.out.println("新增資料成功");

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
	}
}
