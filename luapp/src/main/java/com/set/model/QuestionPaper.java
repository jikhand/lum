package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("deprecation")
@Entity
@Table(name="lutbl_question_paper")
public class QuestionPaper {

	   @Id
	   @EmbeddedId
	   private QuestionPaperId questionPaperId;
	   @Column(name="marks_for_question",columnDefinition = "int(3)")
	   private int MarksForQuestion;
	   @Column(name="is_soft_delete",columnDefinition="int(1)")
	   private int isSoftDelete;
	   @Column(name="inserted_by",columnDefinition="VARCHAR(45)")
	   private String insertedBy;
	   @Column(name="inserted_time")
	   private Date insertedTime;
	   @Column(name="updated_by",columnDefinition="VARCHAR(45)")
	   private String updatedBy;
	   @Column(name="updated_time")
	   private Date updatedTime;
	   @javax.persistence.Transient
		private String message;
		@Transient
		private String ExamId;
		@Transient
		private Questions[] Questions;
				
		public Questions[] getQuestions() {
			return Questions;
		}
		@JsonProperty("Questions")
		public void setQuestions(Questions[] questions) {
			Questions = questions;
		}
		public String getExamId() {
			return ExamId;
		}
		@JsonProperty("TestId")
		public void setExamId(String examId) {
			ExamId = examId;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	    public QuestionPaperId getQuestionPaperId() {
			return questionPaperId;
		}
		@JsonProperty("QuestionPaperId")
		public void setQuestionPaperId(QuestionPaperId questionPaperId) {
			this.questionPaperId = questionPaperId;
		}
		public int getMarksForQuestion() {
			return MarksForQuestion;
		}
		@JsonProperty("MarksForQuestion")
		public void setMarksForQuestion(int marksForQuestion) {
			MarksForQuestion = marksForQuestion;
		}
	public int getIsSoftDelete() {
		return isSoftDelete;
	}
	public void setIsSoftDelete(int isSoftDelete) {
		this.isSoftDelete = isSoftDelete;
	}
	public String getInsertedBy() {
		return insertedBy;
	}
	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	   
	   
	   	      
}
