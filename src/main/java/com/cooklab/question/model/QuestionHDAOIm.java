package com.cooklab.question.model;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class QuestionHDAOIm implements QuestionDAO {

	// 宣告SessionFactory以便開啟Session執行交易
	private SessionFactory factory;

	public QuestionHDAOIm(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(QuestionVO questionVO) {
		// 回傳給Service，1代表成功、-1代表失敗
		try {
			getSession().save(questionVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Integer update(QuestionVO questionVO) {
		try {
			getSession().update(questionVO);
			return 1;
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public Integer delete(Integer questionNo) {
		QuestionVO questionVO = getSession().get(QuestionVO.class, questionNo);
		if (questionVO != null) {
			getSession().delete(questionVO);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}

	}

	@Override
	public QuestionVO findByPrimaryKey(Integer questionNo) {
		return getSession().get(QuestionVO.class, questionNo);
	}

	// 為了排除1+N問題，此處使用left join語法
	@Override
	public List<QuestionVO> getAll() {
		return getSession()
				.createQuery("select distinct q from QuestionVO q left join fetch q.questionGroup ", QuestionVO.class)
				.list();
	}

	// 根據問題群組編號取得不同的資料群總筆數，使用Criteria JPA方式
	public Integer getAllbyGroupCount(Integer questionGroupNo) {
	    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
	    CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
	    Root<QuestionVO> root = criteriaQuery.from(QuestionVO.class);

	    criteriaQuery.select(criteriaBuilder.count(root));
	    criteriaQuery.where(criteriaBuilder.equal(root.get("questionGroup").get("questionGroupNo"), questionGroupNo));

	    Long totalRowCount = getSession().createQuery(criteriaQuery).getSingleResult();

	    return totalRowCount.intValue();
	}

	
	// 根據問題群組編號取得不同的資料群並做分頁處理，使用Criteria JPA方式
	public List<QuestionVO> getAllbyGroupByTen(Integer questionGroupNo, Integer page, Integer pageSize) {

		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<QuestionVO> criteriaQuery = criteriaBuilder.createQuery(QuestionVO.class);
		Root<QuestionVO> root = criteriaQuery.from(QuestionVO.class);

		criteriaQuery.where(criteriaBuilder.equal(root.get("questionGroup").get("questionGroupNo"), questionGroupNo));

		List<QuestionVO> resultList = getSession().createQuery(criteriaQuery).setFirstResult((page - 1) * pageSize)
				.setMaxResults(pageSize).getResultList();

		return resultList;
	}

	
	// 根據關鍵字取得資料總筆數，使用Criteria JPA方式
	public Integer getRowCountByKeyword(String searchInput) {
	    CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
	    CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
	    Root<QuestionVO> root = criteriaQuery.from(QuestionVO.class);

	    String[] conditions = searchInput.split("\\s+"); // 使用空格拆分搜索条件

	    Predicate[] predicates = new Predicate[conditions.length];
	    for (int i = 0; i < conditions.length; i++) {
	        String keyword = conditions[i];
	        predicates[i] = criteriaBuilder.or(
	            criteriaBuilder.like(root.get("questionTitle"), "%" + keyword + "%"),
	            criteriaBuilder.like(root.get("questionText"), "%" + keyword + "%")
	        );
	    }

	    criteriaQuery.select(criteriaBuilder.count(root));
	    criteriaQuery.where(criteriaBuilder.and(predicates));

	    Long totalRowCount = getSession().createQuery(criteriaQuery).getSingleResult();

	    return totalRowCount.intValue();
	}

	
	// 模糊關鍵字搜尋功能，使用Criteria JPA方式
	public List<QuestionVO> getByKeywordByTen(String searchInput, Integer page, Integer pagesize) {
		CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
		CriteriaQuery<QuestionVO> criteriaQuery = criteriaBuilder.createQuery(QuestionVO.class);
		Root<QuestionVO> root = criteriaQuery.from(QuestionVO.class);

		String[] conditions = searchInput.split("\\s+"); // 使用空格拆分搜索條件

	    Predicate[] predicates = new Predicate[conditions.length];
	    for (int i = 0; i < conditions.length; i++) {
	        String keyword = conditions[i];
	        predicates[i] = criteriaBuilder.or(
	            criteriaBuilder.like(root.get("questionTitle"), "%" + keyword + "%"),
	            criteriaBuilder.like(root.get("questionText"), "%" + keyword + "%")
	        );
	    }

	    criteriaQuery.where(criteriaBuilder.and(predicates));

		List<QuestionVO> resultList = getSession().createQuery(criteriaQuery).setFirstResult((page - 1) * pagesize)
				.setMaxResults(pagesize).getResultList();

		return resultList;
	}

}
