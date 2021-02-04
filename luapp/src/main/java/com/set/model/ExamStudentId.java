package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ExamStudentId implements Serializable {
	 @Column(name="exam_id",columnDefinition = "VARCHAR(16)")
	   private String examId;
	   @Column(name="student_id",columnDefinition = "VARCHAR(16)")
	   private String studentId;
	   @Column(name="ans_page_no",columnDefinition = "float(5,2)")
	   private float answerPageNo;
	   @Column(name="questn_no",columnDefinition = "VARCHAR(45)")
	   private String questionNo;
	   
	public ExamStudentId() {
		// TODO Auto-generated constructor stub
	}

	public ExamStudentId(String examId, String studentId, float answerPageNo, String questionNo) {
		this.examId = examId;
		this.studentId = studentId;
		this.answerPageNo = answerPageNo;
		this.questionNo = questionNo;
	}

	public String getExamId() {
		return examId;
	}

	public String getStudentId() {
		return studentId;
	}

	public float getAnswerPageNo() {
		return answerPageNo;
	}
	public String getQuestionNo() {
		return questionNo;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ExamStudentId))
			return false;
		ExamStudentId that = (ExamStudentId) o;
		return Objects.equals(getExamId(), that.getExamId()) && Objects.equals(getStudentId(), that.getStudentId()) && Objects.equals(getAnswerPageNo(), that.getAnswerPageNo()) && Objects.equals(getQuestionNo(), that.getQuestionNo());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getExamId(), getStudentId(), getAnswerPageNo(),getQuestionNo());
	}
}
