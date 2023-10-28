package com.cooklab.ingredient_category.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.cooklab.kitchenware_category.model.KitchenwareCategoryVO;
import com.cooklab.product.model.ProductVO;
import com.cooklab.util.HibernateUtil;

public class IngredientHDAOIm implements IngredientCategoryDAO {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public IngredientHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(IngredientCategoryVO ingredientCategory) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			// 使用Hibernate的更新方法将对象保存到数据库
			session.save(ingredientCategory);
			System.out.println("新增成功");
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public void update(IngredientCategoryVO ingredientCategory) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			// 使用Hibernate的更新方法将对象保存到数据库
			session.update(ingredientCategory);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public void delete(IngredientCategoryVO ingredientCategory) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			// 使用Hibernate的更新方法将对象保存到数据库
			session.delete(ingredientCategory);
			System.out.println("刪除成功");
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public IngredientCategoryVO findByPrimaryKey(Integer ingredientCategoryNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredientCategoryVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Object[]> result = session
					.createQuery("select i.ingredientCategoryNo, i.categoryName from IngredientCategoryVO i").list();

			List<IngredientCategoryVO> listingredientCategoryVO = new ArrayList<>();

			for (Object[] row : result) {
				IngredientCategoryVO categoryVO = new IngredientCategoryVO();
				categoryVO.setIngredientCategoryNo((Integer) row[0]);
				categoryVO.setCategoryName((String) row[1]);
				listingredientCategoryVO.add(categoryVO);
			}

			session.getTransaction().commit();
			System.out.println(listingredientCategoryVO);
			return listingredientCategoryVO;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			// HibernateUtil.shutdown(); 不需要關閉 SessionFactory
		}
		return null;
	}

	@Override
	public List<Object[]> getIngredientAndKitchenwareTags() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			String hqlIngredient = "SELECT '食材', ingredientCategoryNo, categoryName FROM IngredientCategoryVO";
			String hqlKitchenware = "SELECT '廚具', kitchenwareCategoryNo, categoryName FROM KitchenwareCategoryVO";

			Query<Object[]> queryIngredient = session.createQuery(hqlIngredient, Object[].class);
			Query<Object[]> queryKitchenware = session.createQuery(hqlKitchenware, Object[].class);

			List<Object[]> ingredientResults = queryIngredient.list();
			List<Object[]> kitchenwareResults = queryKitchenware.list();

			List<Object[]> allTags = new ArrayList<>();
			allTags.addAll(ingredientResults);
			allTags.addAll(kitchenwareResults);

			System.out.println("成功");
			session.getTransaction().commit();

			return allTags;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			// HibernateUtil.shutdown();
		}
		return null;
	}

	public boolean hasAssociatedProducts(Integer ingredientCategoryNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		IngredientCategoryVO category = session.get(IngredientCategoryVO.class, ingredientCategoryNo);

		if (category != null) {
			Set<ProductVO> products = category.getProduct();
			boolean hasAssociatedProducts = !products.isEmpty();
			session.getTransaction().commit();
			return hasAssociatedProducts;
		} else {
			session.getTransaction().commit();
			return false;
		}
	}

	@Override
	public IngredientCategoryVO findByName(IngredientCategoryVO ingredientCategory) {
		 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		    try {
		        session.beginTransaction();
		        
		        CriteriaBuilder builder = session.getCriteriaBuilder();
		        CriteriaQuery<IngredientCategoryVO> criteria = builder.createQuery(IngredientCategoryVO.class);
		        Root<IngredientCategoryVO> root = criteria.from(IngredientCategoryVO.class);
		        
		        // 构建查询条件
		        Predicate namePredicate = builder.equal(root.get("categoryName"), ingredientCategory.getCategoryName());
		        criteria.select(root).where(namePredicate);

		        Query<IngredientCategoryVO> query = session.createQuery(criteria);
		        
		        IngredientCategoryVO result = query.uniqueResult();
		        
		        session.getTransaction().commit();
		        
		        return result;
		    } catch (Exception e) {
		        e.printStackTrace();
		        session.getTransaction().rollback();
		        return null;
		    }
	}
	
	

}
