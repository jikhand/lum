package com.set.model;

import java.sql.Time;
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
@Table(name="lutbl_examsch")
public class ExamSchedule {

	   @Id
	   @Column(name="exam_id",columnDefinition = "VARCHAR(16)")
	   private String ExamId;
	   @Column(name="teacher_id",columnDefinition = "VARCHAR(16)")
	   private String TeacherId;
	   @Column(name="class_id",columnDefinition = "VARCHAR(16)")
	   private String ClassId;
	   @Column(name="section_id",columnDefinition = "VARCHAR(16)")
	   private String SectionId;
	   @Column(name="subj_id",columnDefinition = "VARCHAR(16)")
	   private String SubjectId;
	   @Column(name="exam_type",columnDefinition = "VARCHAR(16)")
	   private String ExamType;
	   @Column(name="exam_desc",columnDefinition = "VARCHAR(45)")
	   private String ExamDescription;
	   @Column(name="exam_start_time")
	   private Time ExamStartTime;
	   @Column(name="exam_date")
	   private Date ExamDate;
	   @Column(name="duration",columnDefinition = "int(3)")
	   private int Duration;
	   @Column(name="max_marks",columnDefinition = "int(3)")
	   private int MaxMarks;
	   @Column(name="is_soft_delete")
	   private boolean isSoftDelete;
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
	   @javax.persistence.Transient
	   private ExamDetail[] examDetail; 
	   
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	

		@JsonProperty("TestDetail")
	    public ExamDetail[] getExamDetail() {
			return examDetail;
		}
		public void setExamDetail(ExamDetail[] examDetail) {
			this.examDetail = examDetail;
		}
		public String getExamId() {
			return ExamId;
		}
		@JsonProperty("TestId")
		public void setExamId(String examId) {
			ExamId = examId;
		}
		public String getTeacherId() {
			return TeacherId;
		}
		@JsonProperty("TeacherId")
		public void setTeacherId(String teacherId) {
			TeacherId = teacherId;
		}
		public String getClassId() {
			return ClassId;
		}
		@JsonProperty("ClassId")
		public void setClassId(String classId) {
			ClassId = classId;
		}
		public String getSectionId() {
			return SectionId;
		}
		@JsonProperty("SectionId")
		public void setSectionId(String sectionId) {
			SectionId = sectionId;
		}
		public String getSubjectId() {
			return SubjectId;
		}
		@JsonProperty("SubjectId")
		public void setSubjectId(String subjectId) {
			SubjectId = subjectId;
		}
		public String getExamType() {
			return ExamType;
		}
		@JsonProperty("TestType")
		public void setExamType(String examType) {
			ExamType = examType;
		}
		public String getExamDescription() {
			return ExamDescription;
		}
		@JsonProperty("TestDescription")
		public void setExamDescription(String examDescription) {
			ExamDescription = examDescription;
		}
		public Time getExamStartTime() {
			return ExamStartTime;
		}
		@JsonProperty("TestStartTime")
		public void setExamStartTime(Time examStartTime) {
			ExamStartTime = examStartTime;
		}
		public Date getExamDate() {
			return ExamDate;
		}
		@JsonProperty("TestDate")
		public void setExamDate(Date examDate) {
			ExamDate = examDate;
		}
		public int getDuration() {
			return Duration;
		}
		@JsonProperty("Duration")
		public void setDuration(int duration) {
			Duration = duration;
		}
	    public int getMaxMarks() {
			return MaxMarks;
		}
		@JsonProperty("MaxMarks")
		public void setMaxMarks(int maxMarks) {
			MaxMarks = maxMarks;
		}
	public boolean getIsSoftDelete() {
		return isSoftDelete;
	}
	public void setIsSoftDelete(boolean isSoftDelete) {
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
