package com.set.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class Assignment {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AssignmentId")
	//columnDefinition = "serial",
	private String AssignmentId;
	//private long LoginId;
	private long AssignmentNumber;
	private String AssignmentType;
	private Date AssignmentDueDate;
	private Date AssignmentIssuedDate;
	private String AssignmentSubject;
	private String ReviewComments;
	private long AssignmentMarks;
	private String AssignmentAttachment;
	private boolean Status;
	private Date CreatedAt;
	private Date UpdatedAt;
	private boolean IsDeleted;
	 @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	 @JoinColumn(nullable = false, name="userId")
	private User user;
	 
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAssignmentId() {
		return AssignmentId;
	}

	public void setAssignmentId(String assignmentId) {
		AssignmentId = assignmentId;
	}

	public long getAssignmentNumber() {
		return AssignmentNumber;
	}

	public void setAssignmentNumber(long assignmentNumber) {
		AssignmentNumber = assignmentNumber;
	}

	public String getAssignmentType() {
		return AssignmentType;
	}

	public void setAssignmentType(String assignmentType) {
		AssignmentType = assignmentType;
	}

	public Date getAssignmentDueDate() {
		return AssignmentDueDate;
	}

	public void setAssignmentDueDate(Date assignmentDueDate) {
		AssignmentDueDate = assignmentDueDate;
	}

	public Date getAssignmentIssuedDate() {
		return AssignmentIssuedDate;
	}

	public void setAssignmentIssuedDate(Date assignmentIssuedDate) {
		AssignmentIssuedDate = assignmentIssuedDate;
	}

	public String getAssignmentSubject() {
		return AssignmentSubject;
	}

	public void setAssignmentSubject(String assignmentSubject) {
		AssignmentSubject = assignmentSubject;
	}

	public String getReviewComments() {
		return ReviewComments;
	}

	public void setReviewComments(String reviewComments) {
		ReviewComments = reviewComments;
	}

	public long getAssignmentMarks() {
		return AssignmentMarks;
	}

	public void setAssignmentMarks(long assignmentMarks) {
		AssignmentMarks = assignmentMarks;
	}

	public String getAssignmentAttachment() {
		return AssignmentAttachment;
	}

	public void setAssignmentAttachment(String assignmentAttachment) {
		AssignmentAttachment = assignmentAttachment;
	}

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	public Date getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}

	public Date getUpdatedAt() {
		return UpdatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}

	public boolean isIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

	@javax.persistence.Transient
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}