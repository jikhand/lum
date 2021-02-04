package com.set.dto;

import java.io.Serializable;

import com.set.model.AsstSubmissionDescription;

public class AssignmentSubmitDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1487919267491137669L;
	private String AssignmentId = "";
	private String StudentId = "";
	private String AssignmentSubmissionAttachment = "";
	private AsstSubmissionDescription AssignmentSubmissionDescription;
	public String getAssignmentId() {
		return AssignmentId;
	}
	public void setAssignmentId(String assignmentId) {
		AssignmentId = assignmentId;
	}
	public String getStudentId() {
		return StudentId;
	}
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	public String getAssignmentSubmissionAttachment() {
		return AssignmentSubmissionAttachment;
	}
	public void setAssignmentSubmissionAttachment(String assignmentSubmissionAttachment) {
		AssignmentSubmissionAttachment = assignmentSubmissionAttachment;
	}
	public AsstSubmissionDescription getAssignmentSubmissionDescription() {
		return AssignmentSubmissionDescription;
	}
	public void setAssignmentSubmissionDescription(AsstSubmissionDescription assignmentSubmissionDescription) {
		AssignmentSubmissionDescription = assignmentSubmissionDescription;
	}
	
	
}
