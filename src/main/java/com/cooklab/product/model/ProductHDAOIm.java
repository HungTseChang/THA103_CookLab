package com.cooklab.product.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.cooklab.util.HibernateUtil;

public class ProductHDAOIm implements ProductDAO {
	// SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
	private SessionFactory factory;

	public ProductHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	// Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
	// 以避免請求執行緒共用了同個 Session
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public String insert(ProductVO productVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(productVO);
			System.out.println("新增成功");
			session.getTransaction().commit();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return "false";
	}

	@Override
	public String update(ProductVO productVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(productVO);
			session.getTransaction().commit();
			System.out.println("更新成功");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return "false";
	}

	@Override
	public boolean delete(Integer productNo) {
		ProductVO vo = getSession().get(ProductVO.class, productNo);
		if (vo != null) {
			getSession().delete(vo);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ProductVO findByPrimaryKey(Integer productNo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			ProductVO productVO = session.createQuery("from ProductVO where  productNo =" + productNo, ProductVO.class)
					.uniqueResult();

			session.getTransaction().commit();
			System.out.println("搜一筆");
			return productVO;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
//			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<ProductVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<ProductVO> list = session.createQuery("from ProductVO ", ProductVO.class).list();
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

	@Override
	public List<ProductVO> findByKeyword(String keyword) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(ProductVO.class);

			Disjunction disjunction = Restrictions.disjunction();

			criteria.add(Restrictions.or(Restrictions.like("productName", "%" + keyword + "%"),
					Restrictions.like("productDec", "%" + keyword + "%"),
					Restrictions.like("productIntroduction", "%" + keyword + "%")));

			// 将 Disjunction 添加到 Criteria
			criteria.add(disjunction);

			// 设置 DISTINCT_ROOT_ENTITY 以确保返回不同的实体
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			List<ProductVO> products = criteria.list();

			session.getTransaction().commit();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<ProductVO> findByProductNames(String productName, String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> findByKeywordWithPagination(String keyword, int page, int pageSize) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(ProductVO.class);
			Disjunction disjunction = Restrictions.disjunction();
			criteria.add(Restrictions.or(Restrictions.like("productName", "%" + keyword + "%"),
					Restrictions.like("productDec", "%" + keyword + "%"),
					Restrictions.like("productIntroduction", "%" + keyword + "%")));

			criteria.add(disjunction);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			// 计算起始索引
			int startIndex = (page - 1) * pageSize;
			// 设置起始索引和最大结果数
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(pageSize);

			List<ProductVO> products = criteria.list();

			session.getTransaction().commit();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public ProductVO findByProductName(String productName, String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> findByCategoryKeywordWithPagination(int type, int page, int pageSize) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(ProductVO.class);
			if (type == 1) {
				criteria.add(Restrictions.isNotNull("ingredientCategoryNo"));
			} else {
				criteria.add(Restrictions.isNotNull("kitchenwareCategoryNo"));
			}
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			int startIndex = (page - 1) * pageSize;
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(pageSize);
			List<ProductVO> products = criteria.list();
			session.getTransaction().commit();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<ProductVO> getIngerdAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(ProductVO.class);
			criteria.add(Restrictions.isNotNull("ingredientCategoryNo"));

			List<ProductVO> products = criteria.list();

			session.getTransaction().commit();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<ProductVO> getkitchAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(ProductVO.class);
			criteria.add(Restrictions.isNotNull("kitchenwareCategoryNo"));

			List<ProductVO> products = criteria.list();

			session.getTransaction().commit();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<ProductVO> findByKeywordWithCategorywithingredientCategory(String keyword) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(ProductVO.class);

			Disjunction disjunction = Restrictions.disjunction();

			criteria.add(Restrictions.or(Restrictions.like("productName", "%" + keyword + "%"),
					Restrictions.like("productDec", "%" + keyword + "%"),
					Restrictions.like("productIntroduction", "%" + keyword + "%")));

			criteria.add(Restrictions.isNotNull("ingredientCategoryNo"));

			criteria.add(disjunction);

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			List<ProductVO> products = criteria.list();

			session.getTransaction().commit();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<ProductVO> findByKeywordWithCategorywithkitchCategory(String keyword) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(ProductVO.class);

			Disjunction disjunction = Restrictions.disjunction();

			criteria.add(Restrictions.or(Restrictions.like("productName", "%" + keyword + "%"),
					Restrictions.like("productDec", "%" + keyword + "%"),
					Restrictions.like("productIntroduction", "%" + keyword + "%")));

			criteria.add(Restrictions.isNotNull("kitchenwareCategoryNo"));

			criteria.add(disjunction);

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

			List<ProductVO> products = criteria.list();

			session.getTransaction().commit();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<ProductVO> findByKeywordWithCategorywithingredientCategoryPagination(String keyword, int page,
			int pageSize) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(ProductVO.class);

			Disjunction disjunction = Restrictions.disjunction();

			criteria.add(Restrictions.or(Restrictions.like("productName", "%" + keyword + "%"),
					Restrictions.like("productDec", "%" + keyword + "%"),
					Restrictions.like("productIntroduction", "%" + keyword + "%")));

			criteria.add(Restrictions.isNotNull("ingredientCategoryNo"));

			criteria.add(disjunction);

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			int startIndex = (page - 1) * pageSize;
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(pageSize);

			List<ProductVO> products = criteria.list();

			session.getTransaction().commit();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

	@Override
	public List<ProductVO> findByKeywordWithCategorywithkitchCategoryPagination(String keyword, int page,
			int pageSize) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(ProductVO.class);

			Disjunction disjunction = Restrictions.disjunction();

			criteria.add(Restrictions.or(Restrictions.like("productName", "%" + keyword + "%"),
					Restrictions.like("productDec", "%" + keyword + "%"),
					Restrictions.like("productIntroduction", "%" + keyword + "%")));

			criteria.add(Restrictions.isNotNull("kitchenwareCategoryNo"));

			criteria.add(disjunction);

			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			
			int startIndex = (page - 1) * pageSize;
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(pageSize);

			List<ProductVO> products = criteria.list();

			session.getTransaction().commit();
			return products;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
		return null;
	}

}
