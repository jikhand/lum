package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class Questions implements Serializable {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 3767636283454143696L;
	   @Column(name="question_id",columnDefinition = "VARCHAR(16)")
	   private String QuestionId;
	   @Column(name="questn_no",columnDefinition = "int(3)")
	   private int QuestionNumber;
	   @Column(name="marks_for_question",columnDefinition = "int(3)")
	   private int MarksForQuestion;
	   
	public Questions() {
		// TODO Auto-generated constructor stub
	}

	public Questions(String questionId, int questionNumber, int marksForQuestion) {
		this.QuestionId = questionId;
		this.QuestionNumber = questionNumber;
		this.MarksForQuestion = marksForQuestion;
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

	public int getQuestionNumber() {
		return QuestionNumber;
	}

	@JsonProperty("QuestionNumber")
	public void setQuestionNumber(int questionNumber) {
		QuestionNumber = questionNumber;
	}

	public int getMarksForQuestion() {
		return MarksForQuestion;
	}

	@JsonProperty("MarksForQuestion")
	public void setMarksForQuestion(int marksForQuestion) {
		MarksForQuestion = marksForQuestion;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Questions))
			return false;
		Questions that = (Questions) o;
		return Objects.equals(getQuestionId(), that.getQuestionId()) && Objects.equals(getQuestionNumber(), that.getQuestionNumber()) && Objects.equals(getMarksForQuestion(), that.getMarksForQuestion());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getQuestionId(), getQuestionNumber(), getMarksForQuestion());
	}
}
