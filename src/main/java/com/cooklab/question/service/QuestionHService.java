package com.cooklab.question.service;

import java.util.List;
import java.util.stream.Collectors;

import com.cooklab.question.model.QuestionDTO;
import com.cooklab.question.model.QuestionHDAOIm;
import com.cooklab.question.model.QuestionVO;
import com.cooklab.question_group.model.QuestionGroupVO;
import com.cooklab.question_group.service.QuestionGroupHService;
import com.cooklab.util.HibernateUtil;

public class QuestionHService implements QuestionServie {

	private QuestionHDAOIm dao;

	// 建構子呼叫工廠
	public QuestionHService() {
		dao = new QuestionHDAOIm(HibernateUtil.getSessionFactory());
	}

	public QuestionVO addQuestion(Integer questionGroupNo, String questionTitle, String questionContent) {

		QuestionVO questionVO = new QuestionVO();
		QuestionGroupHService qgSvc = new QuestionGroupHService();
		QuestionGroupVO questionGroupVO = qgSvc.getOneQuestionGroup(questionGroupNo);

		questionVO.setQuestionGroup(questionGroupVO);
		questionVO.setQuestionTitle(questionTitle);
		questionVO.setQuestionContent(questionContent);

		dao.insert(questionVO);

		return questionVO;
	}

	public QuestionVO updateQuestion(Integer questionNo, Integer questionGroupNo, String questionTitle,
			String questionContent) {

		QuestionVO questionVO = dao.findByPrimaryKey(questionNo);
		QuestionGroupVO questionGroupVO = new QuestionGroupHService().getOneQuestionGroup(questionGroupNo);

		questionVO.setQuestionGroup(questionGroupVO);
		questionVO.setQuestionTitle(questionTitle);
		questionVO.setQuestionContent(questionContent);
		dao.update(questionVO);

		return questionVO;
	}

	public Integer deleteQuestion(Integer questionNo) {
		if (dao.delete(questionNo) == 1) {
			return 1;
		} else {
			return -1;
		}
	}

	public QuestionVO getOneQuestion(Integer questionNo) {
		return dao.findByPrimaryKey(questionNo);
	}

	public List<QuestionVO> getAll() {
		return dao.getAll();
	}

	public QuestionDTO getOneDTO(Integer questionNo) {
		// 將VO取出的資料轉換為DTO
		QuestionVO qvo = dao.findByPrimaryKey(questionNo);
		QuestionDTO qDTO = new QuestionDTO();
		qDTO.setQuestionNo(qvo.getQuestionNo());
		qDTO.setQuestionGroupName(qvo.getQuestionGroup().getQuestionName());
		qDTO.setQuestionTitle(qvo.getQuestionTitle());
		qDTO.setQuestionContent(qvo.getQuestionContent());
		qDTO.setQuestionGood(qvo.getQuestionGood());
		qDTO.setQuestionBad(qvo.getQuestionBad());
		qDTO.setCreatedTimestamp(qvo.getCreatedTimestamp());
		return qDTO;
	}

	public List<QuestionDTO> getAllDTO() {
		// 宣告MAP物件的LIST集合，以便將VO取出的資料轉為MAP形式
		List<QuestionVO> list = dao.getAll();

		// 使用Stream API轉換資料
		List<QuestionDTO> data = list.stream().map(qVO -> {
			QuestionDTO qDTO = new QuestionDTO();
			qDTO.setQuestionNo(qVO.getQuestionNo());
			qDTO.setQuestionGroupName(qVO.getQuestionGroup().getQuestionName());
			qDTO.setQuestionTitle(qVO.getQuestionTitle());
			qDTO.setQuestionContent(qVO.getQuestionContent());
			qDTO.setQuestionGood(qVO.getQuestionGood());
			qDTO.setQuestionBad(qVO.getQuestionBad());
			qDTO.setCreatedTimestamp(qVO.getCreatedTimestamp());
			return qDTO;
		}).collect(Collectors.toList());

		return data;
	}
	
	//將根據群組篩選出的資料回傳
	public List<QuestionDTO> getByGroup(Integer questionGroupNo) {
		// 宣告MAP物件的LIST集合，以便將VO取出的資料轉為MAP形式
		List<QuestionVO> list = dao.getAllbyGroup(questionGroupNo);

		// 使用Stream API轉換資料
		List<QuestionDTO> data = list.stream().map(qVO -> {
			QuestionDTO qDTO = new QuestionDTO();
			qDTO.setQuestionNo(qVO.getQuestionNo());
			qDTO.setQuestionGroupName(qVO.getQuestionGroup().getQuestionName());
			qDTO.setQuestionTitle(qVO.getQuestionTitle());
			qDTO.setQuestionContent(qVO.getQuestionContent());
			qDTO.setQuestionGood(qVO.getQuestionGood());
			qDTO.setQuestionBad(qVO.getQuestionBad());
			qDTO.setCreatedTimestamp(qVO.getCreatedTimestamp());
			return qDTO;
		}).collect(Collectors.toList());

		return data;
	}

}
