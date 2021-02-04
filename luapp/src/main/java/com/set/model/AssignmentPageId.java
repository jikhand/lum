package com.set.model;

import java.io.Serializable;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignmentPageId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2955300117077831948L;
	@Column(name = "assgn_id", columnDefinition = "VARCHAR(16)")
	private String AssignmentId;
	@Column(name = "student_id")
	private String StudentId;
	@Column(name="page_no")
	private String PageNo;

	public String getAssignmentId() {
		return AssignmentId;
	}

	@JsonProperty("AssignmentId")
	public void setAssignmentId(String assignmentId) {
		AssignmentId = assignmentId;
	}

	public String getStudentId() {
		return StudentId;
	}

	@JsonProperty("StudentId")
	public void setStudentId(String StudentId) {
		this.StudentId = StudentId;
	}

	public String getPageNo() {
		return PageNo;
	}
	@JsonProperty("PageNo")
	public void setPageNo(String pageNo) {
		PageNo = pageNo;
	}
}
