package com.cooklab.question.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cooklab.question_group.model.QuestionGroupVO;

@Entity
@Table(name = "question")
public class QuestionVO implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_no", updatable = false, insertable = false)
	private Integer questionNo;

	@Transient
	private Integer questionGroupNo;

	@ManyToOne
	@JoinColumn(name = "question_group_no", referencedColumnName = "question_group_no")
	private QuestionGroupVO questionGroup;

	@Column(name = "question_title")
	private String questionTitle;

	@Column(name = "question_content")
	private String questionContent;

	@Column(name = "question_good")
	private Integer questionGood;

	@Column(name = "question_bad")
	private Integer questionBad;

	@Column(name = "created_timestamp", updatable = false, insertable = false)
	private Timestamp createdTimestamp;

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

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public QuestionGroupVO getQuestionGroup() {
		return questionGroup;
	}

	public void setQuestionGroup(QuestionGroupVO questionGroup) {
		this.questionGroup = questionGroup;
	}

	@Override
	public String toString() {
		return "QuestionVO [questionNo=" + questionNo + ", questionGroupNo=" + questionGroupNo + ", questionTitle="
				+ questionTitle + ", questionContent=" + questionContent + ", questionGood=" + questionGood
				+ ", questionBad=" + questionBad + ", CreatedTimestamp=" + createdTimestamp + "]";
	}

}
