package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("deprecation")
@Entity
@Table(name="lutbl_exam_type")
public class ExamType {

	   @Id
	   @Column(name="exam_type",columnDefinition = "VARCHAR(16)")
	   private String ExamType;
	   @Column(name="exam_type_description",columnDefinition = "VARCHAR(45)")
	   private String ExamTypeDescription;

	   @javax.persistence.Transient
		private String message;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
	public String getExamType() {
		return ExamType;
	}
	@JsonProperty("ExamType")
	public void setExamType(String examType) {
		ExamType = examType;
	}
	public String getExamTypeDescription() {
		return ExamTypeDescription;
	}
	@JsonProperty("ExamTypeDescription")
	public void setExamTypeDescription(String examTypeDescription) {
		ExamTypeDescription = examTypeDescription;
	}	 
	   	      
}
