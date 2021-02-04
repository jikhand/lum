package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("deprecation")
@Entity
@Table(name="lutbl_exam_quest_Details")
public class ExamQuestions {

	   @Id
	   @EmbeddedId
	   private ExamQuestionsId examQuestionsId;
	   
	   @Column(name="no_of_questions",columnDefinition = "int(3)")
	   private int NoOfQuestions;

	public ExamQuestionsId getExamQuestionsId() {
		return examQuestionsId;
	}

	@JsonProperty("examQuestionsId")
	public void setExamQuestionsId(ExamQuestionsId examQuestionsId) {
		this.examQuestionsId = examQuestionsId;
	}

	public int getNoOfQuestions() {
		return NoOfQuestions;
	}

	@JsonProperty("NoQuestions")
	public void setNoOfQuestions(int noOfQuestions) {
		NoOfQuestions = noOfQuestions;
	}
	   
	   
	   	      
}
