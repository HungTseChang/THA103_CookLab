package com.cooklab.question_group.model;

import java.sql.Date;

public class QuestionGroupVO implements java.io.Serializable {

	private Integer questionGroupNo;
	private String questionName;
	private Date createdTimestamp;
	public Integer getQuestionGroupNo() {
		return questionGroupNo;
	}
	public void setQuestionGroupNo(Integer questionGroupNo) {
		this.questionGroupNo = questionGroupNo;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}
	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}
	
	

	
	
}
