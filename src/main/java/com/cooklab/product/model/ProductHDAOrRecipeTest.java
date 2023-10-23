package com.cooklab.product.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import com.cooklab.recipe.model.RecipeVO;
import com.cooklab.util.HibernateUtil;

public class ProductHDAOrRecipeTest implements ProductDAO {
	private SessionFactory factory;

	public ProductHDAOrRecipeTest(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(ProductVO productVO) {
	}

	@Override
	public void update(ProductVO productVO) {
	}

	@Override
	public boolean delete(Integer productNo) {
		return true;
	}

	@Override
	public ProductVO findByPrimaryKey(Integer productNo) {

		return null;
	}

	@Override
	public List<ProductVO> getAll() {

		return null;
	}

	@Override
	public List<Map<String, Object>> findByKeyword(String keyword) {
		return null;
	}

	@Override
	public ProductVO findByProductName(String productName) {
		return getSession().createQuery("from ProductVO where productName = :productName ", ProductVO.class)
				.setParameter("productName", productName).uniqueResult();
	}

	@Override
	public List<ProductVO> findByProductNames(String productName, String category) {
		return getSession()
				.createQuery("from ProductVO where productName like :productName and " + category + " is not null",
						ProductVO.class)
				.setParameter("productName", "%" + productName + "%").list();
	}

}
