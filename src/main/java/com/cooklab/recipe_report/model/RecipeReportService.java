package com.cooklab.recipe_report.model;

import java.sql.Timestamp;
import java.util.List;

import com.cooklab.util.HibernateUtil;

public class RecipeReportService {

	private RecipeReoprtDAO_interface dao;
	
	public RecipeReportService() {
		dao = new RecipeReportHBDAO(HibernateUtil.getSessionFactory());
	}

	public RecipeReportVO add(RecipeReportVO RecipeReportVO) {

		dao.insert(RecipeReportVO);

		return RecipeReportVO;
	}

	public RecipeReportVO update(RecipeReportVO RecipeReportVO) {


		
		dao.update(RecipeReportVO);

		return RecipeReportVO;
	}

	public void delete(Integer recipeReportNo) {
		dao.delete(recipeReportNo);
	}

	public RecipeReportVO getOne(Integer recipeReportNo) {
		return dao.findByPrimaryKey(recipeReportNo);
	}

	public List<RecipeReportVO> getAll() {
		return dao.getAll();
	}

	

}
