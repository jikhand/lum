package com.set.model;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Exam {

	public String ExamId;
	public String StudentId;
	public String QuestionNo;
	public float AnswerPageNo;
	public String Answer;
	public String AnswerPath;
	private float MarksObtained;
	private String TeacherId;
			
    public String getExamId() {
		return ExamId;
	}

	@JsonProperty("ExamId")
	public void setExamId(String examId) {
		ExamId = examId;
	}

	public String getStudentId() {
		return StudentId;
	}

	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}

	public String getQuestionNo() {
		return QuestionNo;
	}

	@JsonProperty("QuestionNo")
	public void setQuestionNo(String questionNo) {
		QuestionNo = questionNo;
	}

	public float getAnswerPageNo() {
		return AnswerPageNo;
	}

	@JsonProperty("AnswerPageNo")
	public void setAnswerPageNo(float answerPageNo) {
		AnswerPageNo = answerPageNo;
	}

	public String getAnswer() {
		return Answer;
	}

	@JsonProperty("Answer")
	public void setAnswer(String answer) {
		Answer = answer;
	}

	public String getAnswerPath() {
		return AnswerPath;
	}

	@JsonProperty("AnswerPath")
	public void setAnswerPath(String answerPath) {
		AnswerPath = answerPath;
	}

	public float getMarksObtained() {
		return MarksObtained;
	}

	@JsonProperty("MarksObtained")
	public void setMarksObtained(float marksObtained) {
		MarksObtained = marksObtained;
	}
	
	public String getTeacherId() {
		return TeacherId;
	}

	@JsonProperty("TeacherId")
	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}

	@Override
	public String toString(){
		return getExamId() + ", "+getStudentId() + ", "+getQuestionNo() + ", "+getAnswerPageNo() + ", "+getAnswer() + ", "+getAnswerPath() + ", "+getMarksObtained();
	}
}
