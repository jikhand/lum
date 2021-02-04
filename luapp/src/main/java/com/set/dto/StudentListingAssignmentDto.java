/**
 * 
 */
package com.set.dto;

import java.util.Date;

/**
 * @author set-0018
 *
 */
public class StudentListingAssignmentDto {
	   private String assignmentSubject;
	   private int assignmentMark;
	   private String assignmentDescription;
	   private String assignmentTypeId;
	   private Date assignmentDueDate;
	   private Date assignmentCreatedDate;
	   private String assignmentType;
	   private String assignmentSubmissionAttachment;
	   private String assignmentSubmissionDescription;
	   private String assignmentReviewComment;
	   private String assignments;
	   private String classId;
	   private String className;
	   private String id;
	   private String sectionId;
	   private String subjectId;
	   private String subjectName;
	   private String status;
	   private String pageTypeId;
	   private String pageTypeName;
	   private String userId;

	   @javax.persistence.Transient
	   private String message;
	   
	   
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAssignmentSubject() {
		return assignmentSubject;
	}
	public void setAssignmentSubject(String assignmentSubject) {
		this.assignmentSubject = assignmentSubject;
	}
	public int getAssignmentMark() {
		return assignmentMark;
	}
	public void setAssignmentMark(int assignmentMark) {
		this.assignmentMark = assignmentMark;
	}
	public String getAssignmentDescription() {
		return assignmentDescription;
	}
	public void setAssignmentDescription(String assignmentDescription) {
		this.assignmentDescription = assignmentDescription;
	}
	public String getAssignmentTypeId() {
		return assignmentTypeId;
	}
	public void setAssignmentTypeId(String assignmentTypeId) {
		this.assignmentTypeId = assignmentTypeId;
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
	public String getAssignmentType() {
		return assignmentType;
	}
	public void setAssignmentType(String assignmentType) {
		this.assignmentType = assignmentType;
	}
	public String getAssignmentSubmissionAttachment() {
		return assignmentSubmissionAttachment;
	}
	public void setAssignmentSubmissionAttachment(String assignmentSubmissionAttachment) {
		this.assignmentSubmissionAttachment = assignmentSubmissionAttachment;
	}
	public String getAssignmentSubmissionDescription() {
		return assignmentSubmissionDescription;
	}
	public void setAssignmentSubmissionDescription(String assignmentSubmissionDescription) {
		this.assignmentSubmissionDescription = assignmentSubmissionDescription;
	}
	public String getAssignmentReviewComment() {
		return assignmentReviewComment;
	}
	public void setAssignmentReviewComment(String assignmentReviewComment) {
		this.assignmentReviewComment = assignmentReviewComment;
	}
	public String getAssignments() {
		return assignments;
	}
	public void setAssignments(String assignments) {
		this.assignments = assignments;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPageTypeId() {
		return pageTypeId;
	}
	public void setPageTypeId(String pageTypeId) {
		this.pageTypeId = pageTypeId;
	}
	public String getPageTypeName() {
		return pageTypeName;
	}
	public void setPageTypeName(String pageTypeName) {
		this.pageTypeName = pageTypeName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	   
	   
}
