package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportMarksDto {
	private String ExamId;
	private String SubjectId;
	private String SubjectName;
	private String TotalMarks;
	private String MarksObtained;
	
	public String getExamId() {
		return ExamId;
	}
	@JsonProperty("ExamId")
	public void setExamId(String examId) {
		ExamId = examId;
	}
	public String getSubjectId() {
		return SubjectId;
	}
	@JsonProperty("SubjectId")
	public void setSubjectId(String subjectId) {
		SubjectId = subjectId;
	}
	public String getSubjectName() {
		return SubjectName;
	}
	@JsonProperty("SubjectName")
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}
	public String getTotalMarks() {
		return TotalMarks;
	}
	@JsonProperty("TotalMarks")
	public void setTotalMarks(String totalMarks) {
		TotalMarks = totalMarks;
	}
	public String getMarksObtained() {
		return MarksObtained;
	}
	@JsonProperty("MarksObtained")
	public void setMarksObtained(String marksObtained) {
		MarksObtained = marksObtained;
	}
	

}
