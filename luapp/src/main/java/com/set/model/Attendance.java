package com.set.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lutbl_attendence")
public class Attendance {
	@Id
	@Column(name = "attendance_id", columnDefinition = "VARCHAR(16)")
	private String attendanceId;
	@Column(name = "attendance_date")
	private Date attendanceDate;
	@Column(name = "class_id", columnDefinition = "VARCHAR(16)")
	private String classId;
	@Column(name = "section_id", columnDefinition = "VARCHAR(16)")
	private String sectionId;
	@Column(name = "student_id", columnDefinition = "VARCHAR(16)")
	private String studentId;
	@Column(name = "inserted_time")
	private Date insertedTime;
	@Column(name = "is_deleted", columnDefinition = "int(1)")
	private int isDeleted;
	@Column(name = "status", columnDefinition = "int(1)")
	private int status;
	@Column(name = "updated_time")
	private Date updatedTime;
		
	
	@javax.persistence.Transient
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}
	public Date getAttendanceDate() {
		return attendanceDate;
	}
	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
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
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public Date getInsertedTime() {
		return insertedTime;
	}
	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	
}

