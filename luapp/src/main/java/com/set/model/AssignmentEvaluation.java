package com.set.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "lutbl_assgn_eval")
public class AssignmentEvaluation {

	@Id
	@EmbeddedId
	public AssignmentEvaluationId assignmentEvaluationId;

	public AssignmentEvaluationId getAssignmentEvaluationId() {
		return assignmentEvaluationId;
	}

	public void setAssignmentEvaluationId(AssignmentEvaluationId assignmentEvaluationId) {
		this.assignmentEvaluationId = assignmentEvaluationId;
	}

	@Column(name = "assgn_start_date")
	private Date assignStartDate;
	@Column(name = "assgn_end_date")
	private Date assignEndDate;
	@Column(name = "status", columnDefinition = "VARCHAR(1)")
	private String Status;
	@Column(name = "assgn_path", columnDefinition = "VARCHAR(240)")
	private String assignPath;
	@Column(name = "marks_obtained", columnDefinition = "int(3)")
	private int marksObtained;
	@Column(name = "review_cmnts", columnDefinition = "VARCHAR(240)")
	private String reviewComments;
	@Column(name = "review_by", columnDefinition = "VARCHAR(45)")
	private String reviewBy;
	@Column(name = "name_of_reviewer", columnDefinition = "VARCHAR(80)")
	private String nameOfReviewer;
	@Column(name = "is_soft_delete", columnDefinition = "int(1)")
	private boolean isSoftDelete;
	@Column(name = "for_use_field1", columnDefinition = "VARCHAR(45)")
	private String forUseField1;
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(45)")
	private String insertedBy;
	@Column(name = "inserted_time")
	private Date insertedTime;
	@Column(name = "updated_by", columnDefinition = "VARCHAR(45)")
	private String updatedBy;
	@Column(name = "updated_time")
	private Date updatedTime;

	public boolean isSoftDelete() {
		return isSoftDelete;
	}

	public void setSoftDelete(boolean isSoftDelete) {
		this.isSoftDelete = isSoftDelete;
	}

	public Date getAssignStartDate() {
		return assignStartDate;
	}

	public void setAssignStartDate(Date assignStartDate) {
		this.assignStartDate = assignStartDate;
	}

	public Date getAssignEndDate() {
		return assignEndDate;
	}

	public void setAssignEndDate(Date assignEndDate) {
		this.assignEndDate = assignEndDate;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getAssignPath() {
		return assignPath;
	}

	public void setAssignPath(String assignPath) {
		this.assignPath = assignPath;
	}

	public int getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(int marksObtained) {
		this.marksObtained = marksObtained;
	}

	public String getReviewComments() {
		return reviewComments;
	}

	public void setReviewComments(String reviewComments) {
		this.reviewComments = reviewComments;
	}

	public String getReviewBy() {
		return reviewBy;
	}

	public void setReviewBy(String reviewBy) {
		this.reviewBy = reviewBy;
	}

	public String getNameOfReviewer() {
		return nameOfReviewer;
	}

	public void setNameOfReviewer(String nameOfReviewer) {
		this.nameOfReviewer = nameOfReviewer;
	}

	public String getForUseField1() {
		return forUseField1;
	}

	public void setForUseField1(String forUseField1) {
		this.forUseField1 = forUseField1;
	}

//	public List<AsstSubmissionDescription> getForUseField2() {
//		return AsstSubmissionDescription;
//	}
//
//	public void setForUseField2(List<AsstSubmissionDescription> AsstSubmissionDescription) {
//		this.AsstSubmissionDescription = AsstSubmissionDescription;
//	}

	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Transient
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
