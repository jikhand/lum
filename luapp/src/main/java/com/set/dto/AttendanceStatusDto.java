package com.set.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttendanceStatusDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8161741526522201618L;
	private Date AttendanceDate;
	private String ClassId;
	private String SectionId;
	private List<StudentAttendance> AttendanceStatus;

	public Date getAttendanceDate() {
		return AttendanceDate;
	}

	@JsonProperty("AttendanceDate")
	public void setAttendanceDate(Date attendanceDate) {
		AttendanceDate = attendanceDate;
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

	public List<StudentAttendance> getAttendanceStatus() {
		return AttendanceStatus;
	}

	@JsonProperty("AttendanceStatus")
	public void setAttendanceStatus(List<StudentAttendance> attendanceStatus) {
		AttendanceStatus = attendanceStatus;
	}
}
