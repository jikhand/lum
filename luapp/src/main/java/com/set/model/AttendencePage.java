package com.set.model;

import java.util.Date;

public class AttendencePage {
	
	public String notesId;
	public int pageNo;
	public String studentId;
	public String pagePath;
	public boolean status;
	
	String attendanceId; 
	String classId;
	String sectionId;
	Date attendanceDate;
	
	public String getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
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
	public Date getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getPagePath() {
		return pagePath;
	}
	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}	
	public String getNotesId() {
		return notesId;
	}
	public void setNotesId(String notesId) {
		this.notesId = notesId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}	
	
	@Override
	public String toString(){
		return getPageNo() + ", "+getPagePath() + ", "+getNotesId() + ", "+getStudentId()+ ", "+getAttendanceId()+ ", "+getClassId()
		+","+getSectionId()+","+getAttendanceDate()+","+isStatus();
	}
}
