package com.set.model;

import java.util.List;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignmentSubmission {
	/**
	 * 
	 */
	private String AssignmentId;
	private String StudentId;
	private String AssignmentSubmissionAttachment;
	@Transient
	private String Extension;

	@Transient
	private String SubmissionAttachment;
	
	

	public String getSubmissionAttachment() {
		return SubmissionAttachment;
	}

	public void setSubmissionAttachment(String submissionAttachment) {
		SubmissionAttachment = submissionAttachment;
	}

	public String getExtension() {
		return Extension;
	}

	public void setExtension(String extension) {
		Extension = extension;
	}

	@Transient
	private AsstSubmissionDescription[] asstSubmissionDescription;
	public AsstSubmissionDescription[] getAsstSubmissionDescription() {
		return asstSubmissionDescription;
	}

	@JsonProperty("AssignmentSubmissionDescription")
	public void setAsstSubmissionDescription(AsstSubmissionDescription[] asstSubmissionDescription) {
		this.asstSubmissionDescription = asstSubmissionDescription;
	}

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
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}

	public String getAssignmentSubmissionAttachment() {
		return AssignmentSubmissionAttachment;
	}

	@JsonProperty("AssignmentSubmissionAttachment")
	public void setAssignmentSubmissionAttachment(String assignmentSubmissionAttachment) {
		AssignmentSubmissionAttachment = assignmentSubmissionAttachment;
	}

}
