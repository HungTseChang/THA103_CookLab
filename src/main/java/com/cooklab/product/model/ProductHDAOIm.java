package com.cooklab.product.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.cooklab.util.HibernateUtil;

public class ProductHDAOIm implements ProductDAO {
	private SessionFactory factory;

	public ProductHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public String insert(ProductVO productVO) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
//		try {
//			session.beginTransaction();
		session.save(productVO);
		System.out.println("新增成功");
//			session.getTransaction().commit();
		return "success";
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} 
//		finally {
//			HibernateUtil.shutdown();
//		}
//		return "false";
	}

	@Override
	public String update(ProductVO productVO) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();

//		try {
//			session.beginTransaction();
		ProductVO existingProduct = session.get(ProductVO.class, productVO.getProductNo()); 
		//session 快取中移除物件
		if (existingProduct != null) {
			session.evict(existingProduct); 
		}	
		session.update(productVO);
//			session.getTransaction().commit();
		System.out.println("更新成功");
		return "success";
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
//		return "false";
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
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
//		try {
//			session.beginTransaction();
		ProductVO productVO = session.createQuery("from ProductVO where  productNo =" + productNo, ProductVO.class)
				.uniqueResult();

//			session.getTransaction().commit();
		System.out.println("搜一筆");
		return productVO;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
//		return null;
	}

	@Override
	public List<ProductVO> getAll() {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
		try {
//			session.beginTransaction();
			List<ProductVO> list = session.createQuery("from ProductVO ", ProductVO.class).list();
//			session.getTransaction().commit();
			System.out.println("搜尋");
			return list;
		} catch (Exception e) {
			e.printStackTrace();
//			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<ProductVO> findByKeyword(String keyword) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
//		try {
//			session.beginTransaction();

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

//			session.getTransaction().commit();
		return products;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//	}
//		return null;
	}



	@Override
	public Pair<List<ProductVO>, Long> findByKeywordWithPagination(String keyword, int page, int pageSize) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
//		try {
//			session.beginTransaction();

		String hql = "FROM ProductVO p WHERE " + "p.productName LIKE :keyword OR " + "p.productDec LIKE :keyword OR "
				+ "p.productIntroduction LIKE :keyword";

		// 時間
		long currentTimestamp = System.currentTimeMillis();
		hql += " AND p.shelfTime <= :currentTimestamp";
		hql += " AND (p.offsaleTime IS NULL OR p.offsaleTime >= :currentTimestamp)";

		// 按照編號
		hql += " ORDER BY p.productNo";
		
		//TypedQuery編譯時期能夠檢測到類型相關的錯誤
		TypedQuery<ProductVO> query = session.createQuery(hql, ProductVO.class);
		query.setParameter("keyword", "%" + keyword + "%");
		query.setParameter("currentTimestamp", new Timestamp(currentTimestamp));
		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);

		List<ProductVO> products = query.getResultList();

		// 全部
		String countHql = "SELECT COUNT(p) FROM ProductVO p WHERE " + "p.productName LIKE :keyword OR "
				+ "p.productDec LIKE :keyword OR " + "p.productIntroduction LIKE :keyword"
				+ " AND p.shelfTime <= :currentTimestamp"
				+ " AND (p.offsaleTime IS NULL OR p.offsaleTime >= :currentTimestamp)";
		TypedQuery<Long> countQuery = session.createQuery(countHql, Long.class);
		countQuery.setParameter("keyword", "%" + keyword + "%");
		countQuery.setParameter("currentTimestamp", new Timestamp(currentTimestamp));
		Long totalProductCount = countQuery.getSingleResult();

//			session.getTransaction().commit();
		Pair<List<ProductVO>, Long> pair = new Pair<>(products, totalProductCount);
		return pair;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}

//		return null;
	}

	@Override
	public ProductVO findByProductName(String productName, String category) {
		return getSession().createQuery("from ProductVO where productName = :productName and " + category + " is not null", ProductVO.class)
				.setParameter("productName", productName).uniqueResult();
	}

	@Override
	public List<ProductVO> findByProductNames(String productName, String category) {
		return getSession()
				.createQuery("from ProductVO where productName like :productName and " + category + " is not null",
						ProductVO.class)
				.setParameter("productName", "%" + productName + "%").list();
	}
	@Override
	public Pair<List<ProductVO>, Long> findByCategoryKeywordWithPagination(int type, int page, int pageSize) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
//		try {
//			session.beginTransaction();

		String hql = "SELECT p FROM ProductVO p WHERE ";
		if (type == 1) {
			hql += "p.ingredientCategoryNo IS NOT NULL";
		} else {
			hql += "p.kitchenwareCategoryNo IS NOT NULL";
		}

		long currentTimestamp = System.currentTimeMillis();
		hql += " AND p.shelfTime <= :currentTimestamp";
		hql += " AND (p.offsaleTime IS NULL OR p.offsaleTime >= :currentTimestamp)";

		hql += " ORDER BY p.productNo";

		TypedQuery<ProductVO> query = session.createQuery(hql, ProductVO.class);
		query.setParameter("currentTimestamp", new Timestamp(currentTimestamp));
		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);

		List<ProductVO> products = query.getResultList();


		String countHql = "SELECT COUNT(p) FROM ProductVO p WHERE ";
		if (type == 1) {
			countHql += "p.ingredientCategoryNo IS NOT NULL";
		} else {
			countHql += "p.kitchenwareCategoryNo IS NOT NULL";
		}
		countHql += " AND p.shelfTime <= :currentTimestamp";
		countHql += " AND (p.offsaleTime IS NULL OR p.offsaleTime >= :currentTimestamp)";
		TypedQuery<Long> countQuery = session.createQuery(countHql, Long.class);
		countQuery.setParameter("currentTimestamp", new Timestamp(currentTimestamp));
		Long totalProductCount = countQuery.getSingleResult();

//			session.getTransaction().commit();
		Pair<List<ProductVO>, Long> pair = new Pair<List<ProductVO>, Long>(products, totalProductCount);
		return pair;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}

//		return null;
	}

	@Override
	public Pair<List<ProductVO>, Long> findByKeywordWithCategorywithingredientCategoryPagination(String keyword,
			int page, int pageSize) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
//		try {
//			session.beginTransaction();

		String hql = "SELECT p FROM ProductVO p WHERE " + "(p.productName LIKE :keyword OR "
				+ "p.productDec LIKE :keyword OR " + "p.productIntroduction LIKE :keyword) AND "
				+ "p.kitchenwareCategoryNo IS NOT NULL AND " + "p.shelfTime <= :currentTimestamp AND "
				+ "(p.offsaleTime IS NULL OR p.offsaleTime >= :currentTimestamp) " + "ORDER BY p.productNo ASC";

		TypedQuery<ProductVO> query = session.createQuery(hql, ProductVO.class);
		query.setParameter("keyword", "%" + keyword + "%");
		query.setParameter("currentTimestamp", new Timestamp(System.currentTimeMillis()));

		int startIndex = (page - 1) * pageSize;
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);

		List<ProductVO> products = query.getResultList();


		String countHql = "SELECT COUNT(p) FROM ProductVO p WHERE " + "(p.productName LIKE :keyword OR "
				+ "p.productDec LIKE :keyword OR " + "p.productIntroduction LIKE :keyword) AND "
				+ "p.kitchenwareCategoryNo IS NOT NULL AND " + "p.shelfTime <= :currentTimestamp AND "
				+ "(p.offsaleTime IS NULL OR p.offsaleTime >= :currentTimestamp)";

		TypedQuery<Long> countQuery = session.createQuery(countHql, Long.class);
		countQuery.setParameter("keyword", "%" + keyword + "%");
		countQuery.setParameter("currentTimestamp", new Timestamp(System.currentTimeMillis()));
		Long totalProductCount = countQuery.getSingleResult();

//			session.getTransaction().commit();
		Pair<List<ProductVO>, Long> pair = new Pair<List<ProductVO>, Long>(products, totalProductCount);

		return pair;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
//
//		return null;
	}

	@Override
	public Pair<List<ProductVO>, Long> findByKeywordWithCategorywithkitchCategoryPagination(String keyword, int page,
			int pageSize) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
//		try {
//			session.beginTransaction();

		String hql = "SELECT p FROM ProductVO p WHERE " + "(p.productName LIKE :keyword OR "
				+ "p.productDec LIKE :keyword OR " + "p.productIntroduction LIKE :keyword) AND "
				+ "p.kitchenwareCategoryNo IS NOT NULL AND " + "p.shelfTime <= :currentTimestamp AND "
				+ "(p.offsaleTime IS NULL OR p.offsaleTime >= :currentTimestamp) " + "ORDER BY p.productNo ASC";

		TypedQuery<ProductVO> query = session.createQuery(hql, ProductVO.class);
		query.setParameter("keyword", "%" + keyword + "%");
		query.setParameter("currentTimestamp", new Timestamp(System.currentTimeMillis()));

		int startIndex = (page - 1) * pageSize;
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);

		List<ProductVO> products = query.getResultList();


		String countHql = "SELECT COUNT(p) FROM ProductVO p WHERE " + "(p.productName LIKE :keyword OR "
				+ "p.productDec LIKE :keyword OR " + "p.productIntroduction LIKE :keyword) AND "
				+ "p.kitchenwareCategoryNo IS NOT NULL AND " + "p.shelfTime <= :currentTimestamp AND "
				+ "(p.offsaleTime IS NULL OR p.offsaleTime >= :currentTimestamp)";

		TypedQuery<Long> countQuery = session.createQuery(countHql, Long.class);
		countQuery.setParameter("keyword", "%" + keyword + "%");
		countQuery.setParameter("currentTimestamp", new Timestamp(System.currentTimeMillis()));
		Long totalProductCount = countQuery.getSingleResult();

//			session.getTransaction().commit();
		Pair<List<ProductVO>, Long> pair = new Pair<List<ProductVO>, Long>(products, totalProductCount);

		return pair;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			HibernateUtil.shutdown();
//		}
//
//		return null;
	}

	@Override
	public List<ProductVO> findTopSearchCountProduct() {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
//		try {
//			session.beginTransaction();

		String hql = "SELECT p FROM ProductVO p ORDER BY p.searchCount DESC";
		Query<ProductVO> query = session.createQuery(hql, ProductVO.class);
		query.setMaxResults(10); 
		List<ProductVO> topProducts = query.getResultList();
//			session.getTransaction().commit();
		return topProducts;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
		// HibernateUtil.shutdown(); 
//		}
//		return null;
	}

	@Override
	public Pair<List<ProductVO>, Long> findHotTopSearchCountProduct(int page, int pageSize) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Session session = getSession();
//		try {
//			session.beginTransaction();
		String hql = "SELECT COUNT(p) FROM ProductVO p WHERE " + "p.searchCount > 0 AND "
				+ "p.shelfTime < :currentTimestamp AND "
				+ "(p.offsaleTime IS NULL OR p.offsaleTime > :currentTimestamp)";

		TypedQuery<Long> countQuery = session.createQuery(hql, Long.class);
		countQuery.setParameter("currentTimestamp", new Timestamp(System.currentTimeMillis()));
		Long totalProductCount = countQuery.getSingleResult();

		String hql2 = "SELECT p FROM ProductVO p WHERE " + "p.searchCount > 0 AND "
				+ "p.shelfTime < :currentTimestamp AND "
				+ "(p.offsaleTime IS NULL OR p.offsaleTime > :currentTimestamp) " + "ORDER BY p.searchCount DESC";
		
		TypedQuery<ProductVO> query = session.createQuery(hql2, ProductVO.class);
		query.setParameter("currentTimestamp", new Timestamp(System.currentTimeMillis()));
		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);

		List<ProductVO> products = query.getResultList();

//			session.getTransaction().commit();

		Pair<List<ProductVO>, Long> pair = new Pair<List<ProductVO>, Long>(products, totalProductCount);
		return pair;

//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
		// HibernateUtil.shutdown(); // 
//		}
//		return null;
	}

}
