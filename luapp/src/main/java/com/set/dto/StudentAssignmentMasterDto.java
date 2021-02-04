package com.set.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.set.model.AsstSubmissionDescription;
import com.set.model.Page;
import com.set.utils.Constant;

public class StudentAssignmentMasterDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8719139076861369656L;
	private String AssignmentSubmissionAttachment = "";
	private Date AssignmentCreatedDate = new Date();
	private String AssignmentDescription = "";
	private Date AssignmentDueDate = new Date();
	private int AssignmentMark = 0;
	private String AssignmentSubject = "";
	private String AssignmentType = "";
	private String Id = "";
	private String ClassName = "";
	private String PageTypeName;// = "Ruled";
	private String SubjectName = "";
	private String SubjectId = "";
	private String Status;// = Constant.STATUS_NA;
	private String AssignmentReviewComment = "";
	private List<AsstSubmissionDescription> Assignemnts;
	private String UserId = "";
	private String ReviewedBy = "";
	private String AssignmentTypeId = "";
	private String ClassId = "";
	private String SectionId = "";

	public Date getAssignmentCreatedDate() {
		return AssignmentCreatedDate;
	}

	@JsonProperty("AssignmentCreatedDate")
	public void setAssignmentCreatedDate(Date assignmentCreatedDate) {
		AssignmentCreatedDate = assignmentCreatedDate;
	}

	public String getAssignmentDescription() {
		return AssignmentDescription;
	}

	@JsonProperty("AssignmentDescription")
	public void setAssignmentDescription(String assignmentDescription) {
		AssignmentDescription = assignmentDescription;
	}

	public String getAssignmentType() {
		return AssignmentType;
	}

	@JsonProperty("AssignmentType")
	public void setAssignmentType(String assignmentType) {
		AssignmentType = assignmentType;
	}

	public String getClassName() {
		return ClassName;
	}

	@JsonProperty("ClassName")
	public void setClassName(String className) {
		ClassName = className;
	}

	public String getId() {
		return Id;
	}

	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}

	public Date getAssignmentDueDate() {
		return AssignmentDueDate;
	}

	@JsonProperty("AssignmentDueDate")
	public void setAssignmentDueDate(Date assignmentDueDate) {
		AssignmentDueDate = assignmentDueDate;
	}

	public String getPageTypeName() {
		return PageTypeName;
	}

	@JsonProperty("PageTypeName")
	public void setPageTypeName(String pageTypeName) {
		PageTypeName = pageTypeName;
	}

	public int getAssignmentMark() {
		return AssignmentMark;
	}

	@JsonProperty("AssignmentMark")
	public void setAssignmentMark(int assignmentMark) {
		AssignmentMark = assignmentMark;
	}

	public String getAssignmentSubject() {
		return AssignmentSubject;
	}

	@JsonProperty("AssignmentTitle")
	public void setAssignmentSubject(String assignmentSubject) {
		AssignmentSubject = assignmentSubject;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	@JsonProperty("SubjectName")
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public String getAssignmentAttachment() {
		return AssignmentSubmissionAttachment;
	}

	@JsonProperty("AssignmentSubmissionAttachment")
	public void setAssignmentAttachment(String assignmentAttachment) {
		AssignmentSubmissionAttachment = assignmentAttachment;
	}

	public String getSubjectId() {
		return SubjectId;
	}

	@JsonProperty("SubjectId")
	public void setSubjectId(String subjectId) {
		SubjectId = subjectId;
	}

	public String getStatus() {
		return Status;
	}

	@JsonProperty("Status")
	public void setStatus(String status) {
		Status = status;
	}

	public String getAssignmentReviewComment() {
		return AssignmentReviewComment;
	}

	@JsonProperty("AssignmentReviewComment")
	public void setAssignmentReviewComment(String assignmentReviewComment) {
		AssignmentReviewComment = assignmentReviewComment;
	}

	public List<AsstSubmissionDescription> getAssignemnts() {
		return Assignemnts;
	}

	@JsonProperty("Assignemnts")
	public void setAssignemnts(List<AsstSubmissionDescription> assignemnts) {
		Assignemnts = assignemnts;
	}

	public String getUserId() {
		return UserId;
	}

	@JsonProperty("UserId")
	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getReviewedBy() {
		return ReviewedBy;
	}

	@JsonProperty("ReviewedBy")
	public void setReviewedBy(String reviewedBy) {
		ReviewedBy = reviewedBy;
	}

	public String getAssignmentTypeId() {
		return AssignmentTypeId;
	}

	@JsonProperty("AssignmentTypeId")
	public void setAssignmentTypeId(String assignmentTypeId) {
		AssignmentTypeId = assignmentTypeId;
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
}