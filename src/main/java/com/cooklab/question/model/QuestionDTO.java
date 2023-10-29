package com.cooklab.question.model;

import java.sql.Timestamp;

public class QuestionDTO {

	private Integer questionNo;

	private String questionGroupName;

	private String questionTitle;

	private String questionContent;

	private Integer questionGood;

	private Integer questionBad;

	private Timestamp createdTimestamp;

	public Integer getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}

	public String getQuestionGroupName() {
		return questionGroupName;
	}

	public void setQuestionGroupName(String questionGroupName) {
		this.questionGroupName = questionGroupName;
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

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	@Override
	public String toString() {
		return "QuestionDTO [questionNo=" + questionNo + ", questionGroupName=" + questionGroupName + ", questionTitle="
				+ questionTitle + ", questionContent=" + questionContent + ", questionGood=" + questionGood
				+ ", questionBad=" + questionBad + ", createdTimestamp=" + createdTimestamp + "]";
	}

}
