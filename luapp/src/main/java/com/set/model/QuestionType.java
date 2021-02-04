package com.set.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("deprecate")
@Entity
@Table(name="lutbl_qustn_type")
public class QuestionType {
	   @Id
	   @Column(name="question_type",columnDefinition="VARCHAR(5)")
	   private String questionType;
	   @Column(name="description",columnDefinition="VARCHAR(45)")
	   private String Description;
	   @javax.persistence.Transient
		private String message;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	} 
	   
}
