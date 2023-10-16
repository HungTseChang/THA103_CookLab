package com.cooklab.question_group.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.cooklab.question.model.QuestionVO;

@Entity
@Table(name = "question_group")
public class QuestionGroupVO implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_group_no", updatable = false, insertable = false)
	private Integer questionGroupNo;

	@Column(name = "question_name")
	private String questionName;

	@Column(name = "created_timestamp", updatable = false, insertable = false)
	private Timestamp createdTimestamp;

	@OneToMany(mappedBy = "questionGroup", cascade = CascadeType.ALL)
	@OrderBy("question_group_no")
	private Set<QuestionVO> questions;

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

	public Timestamp getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Timestamp createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Set<QuestionVO> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<QuestionVO> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "QuestionGroupVO [questionGroupNo=" + questionGroupNo + ", questionName=" + questionName
				+ ", createdTimestamp=" + createdTimestamp + "]";
	}

}
