package com.set.model;

import java.sql.Time;
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
@Table(name="lutbl_exam_stdnt")
public class ExamStudent {

	   @Id
	   @EmbeddedId
	   private ExamStudentId examStudentId;
	   @Column(name="answer",columnDefinition = "VARCHAR(255)")
	   private String answer;
	   @Column(name="answer_path",columnDefinition = "VARCHAR(255)")
	   private String answerPath;
	   @Column(name="teacher_id",columnDefinition = "VARCHAR(16)")
	   private String teacherId;
	   @Column(name="start_time")
	   private Time startTime;
	   @Column(name="end_time")
	   private Time endTime;
	   @Column(name="is_soft_delete",columnDefinition = "int(1)")
	   private int isSoftDelete;
	   @Column(name="marks_obtained",columnDefinition="float(5,2)")
	   private float marksObtained;
	   @javax.persistence.Transient
		private String message;
	   @Transient 
	   private Exam[] exam; 
	
	
	public Exam[] getExam() {
		return exam;
	}
	@JsonProperty("Exam")
	public void setExam(Exam[] exam) {
		this.exam = exam;
	}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	   
	public ExamStudentId getExamStudentId() {
		return examStudentId;
	}
	public void setExamStudentId(ExamStudentId examStudentId) {
		this.examStudentId = examStudentId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}	
	public String getAnswerPath() {
		return answerPath;
	}
	public void setAnswerPath(String answerPath) {
		this.answerPath = answerPath;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public Time getStartTime() {
		return startTime;
	}
	@JsonProperty("StartTime")
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	@JsonProperty("EndTime")
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public int getIsSoftDelete() {
		return isSoftDelete;
	}
	public void setIsSoftDelete(int isSoftDelete) {
		this.isSoftDelete = isSoftDelete;
	}
	public float getMarksObtained() {
		return marksObtained;
	}
	public void setMarksObtained(float marksObtained) {
		this.marksObtained = marksObtained;
	}
	      
	      	      
}
