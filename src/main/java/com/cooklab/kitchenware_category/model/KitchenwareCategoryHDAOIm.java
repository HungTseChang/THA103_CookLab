package com.cooklab.kitchenware_category.model;

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


import com.cooklab.product.model.ProductVO;
import com.cooklab.util.HibernateUtil;

public class KitchenwareCategoryHDAOIm implements KitchenwareCategoryDAO {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public KitchenwareCategoryHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(KitchenwareCategoryVO kitchenwareCategory) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
	        session.beginTransaction();

	        // 使用Hibernate的更新方法将对象保存到数据库
	        session.save(kitchenwareCategory);
	        System.out.println("新增成功");
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
		
	}

	@Override
	public void update(KitchenwareCategoryVO kitchenwareCategory) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
	        session.beginTransaction();

	        // 使用Hibernate的更新方法将对象保存到数据库
	        session.update(kitchenwareCategory);
	        System.out.println("更新成功");
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
		
	}

	@Override
	public void delete(KitchenwareCategoryVO kitchenwareCategory) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			// 使用Hibernate的更新方法将对象保存到数据库
			session.delete(kitchenwareCategory);
			System.out.println("刪除成功");
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		
	}

	@Override
	public KitchenwareCategoryVO findByPrimaryKey(Integer kitchenwareCategoryNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KitchenwareCategoryVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Object[]> result = session
					.createQuery("select k.kitchenwareCategoryNo, k.categoryName from KitchenwareCategoryVO k").list();

			List<KitchenwareCategoryVO> listKitchenwareCategoryVO = new ArrayList<>();

			for (Object[] row : result) {
				KitchenwareCategoryVO categoryVO = new KitchenwareCategoryVO();
				categoryVO.setKitchenwareCategoryNo((Integer) row[0]);
				categoryVO.setCategoryName((String) row[1]);
				listKitchenwareCategoryVO.add(categoryVO);
			}

			session.getTransaction().commit();
			System.out.println(listKitchenwareCategoryVO);
			return listKitchenwareCategoryVO;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			// HibernateUtil.shutdown(); 不需要關閉 SessionFactory
		}
		return null;
	}

	@Override
	public boolean hasAssociatedProducts(Integer kitchenwareCategoryNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		KitchenwareCategoryVO category = session.get(KitchenwareCategoryVO.class, kitchenwareCategoryNo);

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
	public KitchenwareCategoryVO findByName(KitchenwareCategoryVO kitchenwareCategory) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    try {
	        session.beginTransaction();
	        
	        CriteriaBuilder builder = session.getCriteriaBuilder();
	        CriteriaQuery<KitchenwareCategoryVO> criteria = builder.createQuery(KitchenwareCategoryVO.class);
	        Root<KitchenwareCategoryVO> root = criteria.from(KitchenwareCategoryVO.class);
	        
	        // 构建查询条件
	        Predicate namePredicate = builder.equal(root.get("categoryName"), kitchenwareCategory.getCategoryName());
	        criteria.select(root).where(namePredicate);

	        Query<KitchenwareCategoryVO> query = session.createQuery(criteria);
	        
	        KitchenwareCategoryVO result = query.uniqueResult();
	        
	        session.getTransaction().commit();
	        
	        return result;
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	        return null;
	    }
	}


	

}
