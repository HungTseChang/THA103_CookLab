package com.cooklab.question.model;

import java.sql.Date;

public class QuestionVO implements java.io.Serializable {

	private Integer questionNo;
	private Integer questionGroupNo;
	private String questionTitle;
	private String questionContent;
	private Integer questionGood;
	private Integer questionBad;
	private Date CreatedTimestamp;

	public Integer getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}

	public Integer getQuestionGroupNo() {
		return questionGroupNo;
	}

	public void setQuestionGroupNo(Integer questionGroupNo) {
		this.questionGroupNo = questionGroupNo;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public Integer getQuestionGood() {
		return questionGood;
	}

	public void setQuestionGood(Integer questionGood) {
		this.questionGood = questionGood;
	}

	public Integer getQuestionBad() {
		return questionBad;
	}

	public void setQuestionBad(Integer questionBad) {
		this.questionBad = questionBad;
	}

	public Date getCreatedTimestamp() {
		return CreatedTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		CreatedTimestamp = createdTimestamp;
	}

}
