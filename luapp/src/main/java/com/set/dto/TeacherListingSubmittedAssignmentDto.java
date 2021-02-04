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
public class TeacherListingSubmittedAssignmentDto implements Serializable{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 3728174331131074312L;
	private String assignmentReviewComment;
	   private int assignmentSubmissionMark;
	   private String assignmentSubject;
	   private Date assignmentDueDate;
	   private Date assignmentCreatedDate;
	   private String subjectName;
	   private String className;
	   private String studentName;
	   private String sectionName;
	   private String id;
	   private String status;

	   @javax.persistence.Transient
	   private String message;
	   
	   
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAssignmentReviewComment() {
		return assignmentReviewComment;
	}
	public void setAssignmentReviewComment(String assignmentReviewComment) {
		this.assignmentReviewComment = assignmentReviewComment;
	}
	public int getAssignmentSubmissionMark() {
		return assignmentSubmissionMark;
	}
	public void setAssignmentSubmissionMark(int assignmentSubmissionMark) {
		this.assignmentSubmissionMark = assignmentSubmissionMark;
	}
	public String getAssignmentSubject() {
		return assignmentSubject;
	}
	public void setAssignmentSubject(String assignmentSubject) {
		this.assignmentSubject = assignmentSubject;
	}
	public Date getAssignmentDueDate() {
		return assignmentDueDate;
	}
	public void setAssignmentDueDate(Date assignmentDueDate) {
		this.assignmentDueDate = assignmentDueDate;
	}
	public Date getAssignmentCreatedDate() {
		return assignmentCreatedDate;
	}
	public void setAssignmentCreatedDate(Date assignmentCreatedDate) {
		this.assignmentCreatedDate = assignmentCreatedDate;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
		
}
