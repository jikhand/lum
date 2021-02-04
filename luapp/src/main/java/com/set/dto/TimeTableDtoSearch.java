package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeTableDtoSearch implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3140641071170694607L;
	private String DayId;
	private String DayName;
	private float Duration;
//	private String Id;
	private String TimeTableId;
	private String SubjectId;
	private String SubjectName;
	private String ScheduleFromTime;
	private String ScheduleToTime;
	private String ClassId;
	private String SectionId;
	private String SectionName;
	private String ClassName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd, yyyy-MM-dd HH:mm", locale = "en_GB")
	private Date StartDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd, yyyy-MM-dd HH:mm", locale = "en_GB")
	private Date EndDate;
	private String workId;
	private String empId;
	private String firstName;
	private int academicYear;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getScheduleFromTime() {
		return ScheduleFromTime;
	}
	public void setScheduleFromTime(String scheduleFromTime) {
		ScheduleFromTime = scheduleFromTime;
	}
	public String getScheduleToTime() {
		return ScheduleToTime;
	}
	public void setScheduleToTime(String scheduleToTime) {
		ScheduleToTime = scheduleToTime;
	}
	public String getSectionName() {
		return SectionName;
	}
	public void setSectionName(String sectionName) {
		SectionName = sectionName;
	}
	public float getDuration() {
		return Duration;
	}
	@JsonProperty("Duration")
	public void setDuration(float duration) {
		Duration = duration;
	}
	public String getClassId() {
		return ClassId;
	}
	@JsonProperty("ClassId")
	public void setClassId(String classId) {
		ClassId = classId;
	}
	public String getDayId() {
		return DayId;
	}
	@JsonProperty("DayId")
	public void setDayId(String dayId) {
		DayId = dayId;
	}
	public String getDayName() {
		return DayName;
	}
	@JsonProperty("DayName")
	public void setDayName(String dayName) {
		DayName = dayName;
	}
	
//	public String getId() {
//		return Id;
//	}
//	@JsonProperty("Id")
//	public void setId(String id) {
//		Id = id;
//	}
	public String getTimeTableId() {
		return TimeTableId;
	}
	@JsonProperty("TimeTableId")
	public void setTimeTableId(String timeTableId) {
		TimeTableId = timeTableId;
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
//	public String getScheduleFromTime() {
//		return ScheduleFromTime;
//	}
//	@JsonProperty("ScheduleFromTime")
//	public void setScheduleFromTime(String scheduleFromTime) {
//		ScheduleFromTime = scheduleFromTime;
//	}
//	public String getScheduleToTime() {
//		return ScheduleToTime;
//	}
//	@JsonProperty("ScheduleToTime")
//	public void setScheduleToTime(String scheduleToTime) {
//		ScheduleToTime = scheduleToTime;
//	}
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
	public Date getStartDate() {
		return StartDate;
	}
	@JsonProperty("StartDate")
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	@JsonProperty("EndDate")
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}	
}