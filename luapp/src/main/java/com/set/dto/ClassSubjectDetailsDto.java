package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.set.model.ClassSubjectId;

public class ClassSubjectDetailsDto implements Serializable{
	private static final long serialVersionUID = -8801329665275620703L;
	private String Id;
	private String SubjectName;
	private int AcademicYear;
	private String TeacherId;
	private String TeacherName;
	private String TorUseField1;
	private String ForUseField2;
	private Boolean IsSoftDelete;
	private String InsertedBy;
	private Date InsertedTime;
	private String UpdatedBy;
	private Date UpdatedTime;
	private String Message;

	public String getId() {
		return Id;
	}

	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	@JsonProperty("SubjectName")
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public int getAcademicYear() {
		return AcademicYear;
	}

	@JsonProperty("AcademicYear")
	public void setAcademicYear(int academicYear) {
		AcademicYear = academicYear;
	}

	public String getTeacherId() {
		return TeacherId;
	}
	@JsonProperty("TeacherId")
	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}

	public String getTeacherName() {
		return TeacherName;
	}
	@JsonProperty("TeacherName")
	public void setTeacherName(String teacherName) {
		TeacherName = teacherName;
	}

	public String getTorUseField1() {
		return TorUseField1;
	}
	@JsonProperty("TorUseField1")
	public void setTorUseField1(String torUseField1) {
		TorUseField1 = torUseField1;
	}

	public String getForUseField2() {
		return ForUseField2;
	}
	@JsonProperty("ForUseField2")
	public void setForUseField2(String forUseField2) {
		ForUseField2 = forUseField2;
	}

	public Boolean getIsSoftDelete() {
		return IsSoftDelete;
	}
	@JsonProperty("IsSoftDelete")
	public void setIsSoftDelete(Boolean isSoftDelete) {
		IsSoftDelete = isSoftDelete;
	}

	public String getInsertedBy() {
		return InsertedBy;
	}
	@JsonProperty("InsertedBy")
	public void setInsertedBy(String insertedBy) {
		InsertedBy = insertedBy;
	}

	public Date getInsertedTime() {
		return InsertedTime;
	}
	@JsonProperty("InsertedTime")
	public void setInsertedTime(Date insertedTime) {
		InsertedTime = insertedTime;
	}

	public String getUpdatedBy() {
		return UpdatedBy;
	}
	@JsonProperty("UpdatedBy")
	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return UpdatedTime;
	}
	@JsonProperty("UpdatedTime")
	public void setUpdatedTime(Date updatedTime) {
		UpdatedTime = updatedTime;
	}

	public String getMessage() {
		return Message;
	}
	@JsonProperty("Message")
	public void setMessage(String message) {
		Message = message;
	}
}
