package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;


public class ExamDetailListDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2690164599226218655L;
	private String SubjectName;
	private String ExamId;
	private String ClassId;
	private String SectionId;
	private String SubjectId;
	private String TeacherId;
	private String ExamDescription;
	private String ExamType;
	private String MaxMarks;
	private int TestDuration;
	private String TestDate;
	private String TestStartTime;
	private String TestEndTime;
	private List<ExamDetailQuestionListDto> ExamDetailQuestionList;
	
	public String getSubjectName() {
		return SubjectName;
	}
	@JsonProperty("SubjectName")
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}
	public String getExamId() {
		return ExamId;
	}
	@JsonProperty("TestId")
	public void setExamId(String examId) {
		ExamId = examId;
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
	public String getTeacherId() {
		return TeacherId;
	}
	@JsonProperty("TeacherId")
	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}
	public String getExamDescription() {
		return ExamDescription;
	}
	@JsonProperty("TestDescription")
	public void setExamDescription(String examDescription) {
		ExamDescription = examDescription;
	}
	public String getExamType() {
		return ExamType;
	}
	@JsonProperty("TestType")
	public void setExamType(String examType) {
		ExamType = examType;
	}
	public String getMaxMarks() {
		return MaxMarks;
	}
	@JsonProperty("MaxMarks")
	public void setMaxMarks(String maxMarks) {
		MaxMarks = maxMarks;
	}
	public int getTestDuration() {
		return TestDuration;
	}
	@JsonProperty("TestDuration")
	public void setTestDuration(int testDuration) {
		TestDuration = testDuration;
	}
	public String getTestDate() {
		return TestDate;
	}
	@JsonProperty("TestDate")
	public void setTestDate(String testDate) {
		TestDate = testDate;
	}
	public String getTestStartTime() {
		return TestStartTime;
	}
	@JsonProperty("TestStartTime")
	public void setTestStartTime(String testStartTime) {
		TestStartTime = testStartTime;
	}
	public String getTestEndTime() {
		return TestEndTime;
	}
	@JsonProperty("TestEndTime")
	public void setTestEndTime(String testEndTime) {
		TestEndTime = testEndTime;
	}
	public List<ExamDetailQuestionListDto> getExamDetailQuestionList() {
		return ExamDetailQuestionList;
	}
	@JsonProperty("TestDetail")
	public void setExamDetailQuestionList(List<ExamDetailQuestionListDto> examDetailQuestionList) {
		ExamDetailQuestionList = examDetailQuestionList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
	
}
