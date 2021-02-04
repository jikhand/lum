package com.set.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportStudentDetailsDto {
	private String StudentId;
	private String StudentName;
	private String StudentEmail;
	private String ClassId;
	private String SectionId;
	private String ClassName;
	private String SectionName;
	private List<ReportMarksDto> ReportMarksList;
	
	public String getStudentId() {
		return StudentId;
	}
	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	public String getStudentName() {
		return StudentName;
	}
	@JsonProperty("StudentName")
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public String getStudentEmail() {
		return StudentEmail;
	}
	@JsonProperty("StudentEmail")
	public void setStudentEmail(String studentEmail) {
		StudentEmail = studentEmail;
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
	public String getClassName() {
		return ClassName;
	}
	@JsonProperty("ClassName")
	public void setClassName(String className) {
		ClassName = className;
	}
	public String getSectionName() {
		return SectionName;
	}
	@JsonProperty("SectionName")
	public void setSectionName(String sectionName) {
		SectionName = sectionName;
	}
	@JsonProperty("ReportMarks")
	public List<ReportMarksDto> getReportMarksList() {
		return ReportMarksList;
	}
	public void setReportMarksList(List<ReportMarksDto> reportMarksList) {
		ReportMarksList = reportMarksList;
	}

	private int count;
	@javax.persistence.Transient
	private String message;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
