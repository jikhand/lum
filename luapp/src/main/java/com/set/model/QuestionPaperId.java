package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class QuestionPaperId implements Serializable {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 3767636283454143696L;
	   @Column(name="exam_id",columnDefinition = "VARCHAR(16)")
	   private String ExamId;
	   @Column(name="question_id",columnDefinition = "VARCHAR(16)")
	   private String QuestionId;
	   @Column(name="questn_no",columnDefinition = "int(3)")
	   private int QuestionNumber;
	   
	public QuestionPaperId() {
		// TODO Auto-generated constructor stub
	}

	public QuestionPaperId(String questionId, String examId, int questionNumber) {
		this.QuestionId = questionId;
		this.ExamId = examId;
		this.QuestionNumber = questionNumber;
	}

	public String getQuestionId() {
		return QuestionId;
	}

	@JsonProperty("QuestionId")
	public void setQuestionId(String questionId) {
		QuestionId = questionId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getExamId() {
		return ExamId;
	}

	@JsonProperty("TestId")
	public void setExamId(String examId) {
		ExamId = examId;
	}

	public int getQuestionNumber() {
		return QuestionNumber;
	}

	@JsonProperty("QuestionNumber")
	public void setQuestionNumber(int questionNumber) {
		QuestionNumber = questionNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof QuestionPaperId))
			return false;
		QuestionPaperId that = (QuestionPaperId) o;
		return Objects.equals(getQuestionId(), that.getQuestionId()) && Objects.equals(getExamId(), that.getExamId()) && Objects.equals(getQuestionNumber(), that.getQuestionNumber());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getQuestionId(), getExamId(), getQuestionNumber());
	}
}
