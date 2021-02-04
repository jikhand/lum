/**
 * 
 */
package com.set.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author set-0018
 *
 */
public class TeacherTestListDetailsDto implements Serializable{
	   /**
	 * 
	 */
	private static final long serialVersionUID = -1700500035875161933L;
	private String subjectName;
	   private String id;
	   private String classId;
	   private String sectionId;
	   private String subjectId;
	   private String totalMarks;
	   private int testDuration;
	   private Date testDate;
	   private Date testStartTime;
	   private Date testEndTime;
	   private String status;

	   @javax.persistence.Transient
	   private String message;
	   
	   
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(String totalMarks) {
		this.totalMarks = totalMarks;
	}
	public int getTestDuration() {
		return testDuration;
	}
	public void setTestDuration(int testDuration) {
		this.testDuration = testDuration;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public Date getTestStartTime() {
		return testStartTime;
	}
	public void setTestStartTime(Date testStartTime) {
		this.testStartTime = testStartTime;
	}
	public Date getTestEndTime() {
		return testEndTime;
	}
	public void setTestEndTime(Date testEndTime) {
		this.testEndTime = testEndTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
