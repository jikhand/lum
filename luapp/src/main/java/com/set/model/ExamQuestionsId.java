package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class ExamQuestionsId implements Serializable {
	   /**
	 * 
	 */
	private static final long serialVersionUID = -6326139761336008728L;
	   @Column(name="exam_id",columnDefinition = "VARCHAR(16)")
	   private String ExamId;
	   @Column(name="question_type",columnDefinition = "VARCHAR(16)")
	   private String QuestionType;
	   
	public ExamQuestionsId() {
		// TODO Auto-generated constructor stub
	}

	public ExamQuestionsId(String examId, String questionType) {
		this.ExamId = examId;
		this.QuestionType = questionType;
	}

	public String getExamId() {
		return ExamId;
	}

	@JsonProperty("ExamId")
	public void setExamId(String examId) {
		ExamId = examId;
	}

	public String getQuestionType() {
		return QuestionType;
	}

	@JsonProperty("QuestionType")
	public void setQuestionType(String questionType) {
		QuestionType = questionType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ExamQuestionsId))
			return false;
		ExamQuestionsId that = (ExamQuestionsId) o;
		return Objects.equals(getExamId(), that.getExamId()) && Objects.equals(getQuestionType(), that.getQuestionType());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getExamId(), getQuestionType());
	}
}
