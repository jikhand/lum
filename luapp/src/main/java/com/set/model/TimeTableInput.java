package com.set.model;

import java.io.Serializable;



public class TimeTableInput implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3331548134680928370L;
	
	private String day;
	private String classId;
	private String sectionId;
	private String empId;
	
	
	
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
}