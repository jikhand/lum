package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "lutbl_assgnmnt_master")
public class AssignmentMaster {
	@EmbeddedId
	private AssignmentMasterId assignmentMasterId;

	public AssignmentMasterId getAssignmentMasterId() {
		return assignmentMasterId;
	}

	public void setAssignmentMasterId(AssignmentMasterId assignmentMasterId) {
		this.assignmentMasterId = assignmentMasterId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}
	@Column(name = "page_category_id", columnDefinition = "VARCHAR(16)")
	private String pageCategoryId;
	public String getPageCategoryId() {
		return pageCategoryId;
	}
	public void setPageCategoryId(String pageCategoryId) {
		this.pageCategoryId = pageCategoryId;
	}
	
	@Column(name = "assoc_teacherid", columnDefinition = "VARCHAR(16)")
	private String associateTeacherId;
	@Column(name = "assgn_type", columnDefinition = "VARCHAR(45)")
	private String assignType;
	@Column(name = "subj_id", columnDefinition = "VARCHAR(16)")
	private String subjectId;
	@Column(name = "assgn_start_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date assignStartDate;
	@Column(name = "assgn_due_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private Date assignDueDate;
	@Column(name = "asgn_active", columnDefinition = "int(1)")
	private boolean assignActive;

	public void setAssignActive(boolean assignActive) {
		this.assignActive = assignActive;
	}

	@Column(name = "max_marks", columnDefinition = "int(4)")
	private int maxMarks;
	@Column(name = "assgn_path", columnDefinition = "VARCHAR(240)")
	private String assignPath;
	@Column(name = "asgn_created_by", columnDefinition = "VARCHAR(45)")
	private String assignCreatedBy;
	@Column(name = "page_type", columnDefinition = "VARCHAR(45)")
	private String PageTypeName;
	@Column(name = "for_use_field2", columnDefinition = "VARCHAR(45)")
	private String forUseField2;
	@Column(name = "assgn_subject", columnDefinition = "VARCHAR(45)")
	private String assignmentSubject;
	@Column(name = "description", columnDefinition = "VARCHAR(240)")
	private String description;
	@Column(name = "assgn_thumbnail", columnDefinition = "VARCHAR(255)")
	private String assgnThumbnail;
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(45)")
	private String insertedBy;
	@Column(name = "inserted_time")
	private Date insertedTime;
	@Column(name = "updated_by", columnDefinition = "VARCHAR(45)")
	private String updatedBy;
	@Column(name = "updated_time")
	private Date updatedTime;
	@Transient
	private String tempId;
	@Column(name = "is_deleted", columnDefinition = "TINYINT(1)")
	private int isDeleted = 0;	

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public boolean isAssignActive() {
		return assignActive;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getAssociateTeacherId() {
		return associateTeacherId;
	}

	public void setAssociateTeacherId(String associateTeacherId) {
		this.associateTeacherId = associateTeacherId;
	}

	public String getAssignType() {
		return assignType;
	}

	public void setAssignType(String assignType) {
		this.assignType = assignType;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public Date getAssignStartDate() {
		return assignStartDate;
	}

	public void setAssignStartDate(Date assignStartDate) {
		this.assignStartDate = assignStartDate;
	}

	public Date getAssignDueDate() {
		return assignDueDate;
	}

	public void setAssignDueDate(Date assignDueDate) {
		this.assignDueDate = assignDueDate;
	}

	public int getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(int maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getAssignPath() {
		return assignPath;
	}

	public void setAssignPath(String assignPath) {
		this.assignPath = assignPath;
	}

	public String getAssignCreatedBy() {
		return assignCreatedBy;
	}

	public void setAssignCreatedBy(String assignCreatedBy) {
		this.assignCreatedBy = assignCreatedBy;
	}

	public String getPageTypeName() {
		return PageTypeName;
	}

	public void setPageTypeName(String pageTypeName) {
		PageTypeName = pageTypeName;
	}

	public String getForUseField2() {
		return forUseField2;
	}

	public void setForUseField2(String forUseField2) {
		this.forUseField2 = forUseField2;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

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

	@Transient
	private String message;

	public String getMessage() {
		return message;
	}

	public String getAssignmentSubject() {
		return assignmentSubject;
	}

	public void setAssignmentSubject(String assignmentSubject) {
		this.assignmentSubject = assignmentSubject;
	}

	public String getAssgnThumbnail() {
		return assgnThumbnail;
	}

	public void setAssgnThumbnail(String assgnThumbnail) {
		this.assgnThumbnail = assgnThumbnail;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
