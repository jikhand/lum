package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TimeTableEditParamaters implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3140641071170694607L;
	
	
	private float duration;
	//private String timeTableId;
	private String subjectId;
	private String workId;	
	private String classId;
	private String sectionId;		
	private String empId;
	private String day;
	private String hrNo;
	private String startTime;
	private String endTime;
	private String academicYear;
	
	public String getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
//	public String getTimeTableId() {
//		return timeTableId;
//	}
//	public void setTimeTableId(String timeTableId) {
//		this.timeTableId = timeTableId;
//	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getHrNo() {
		return hrNo;
	}
	public void setHrNo(String hrNo) {
		this.hrNo = hrNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	}