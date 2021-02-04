package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "lutbl_class_subj")
public class ClassSubject {
	@EmbeddedId
	private ClassSubjectId classSubjectId;

	public ClassSubjectId getClassSubjectId() {
		return classSubjectId;
	}

	public void setClassSubjectId(ClassSubjectId classSubjectId) {
		this.classSubjectId = classSubjectId;
	}
	@Transient
	private String message;
	@Transient
	private String tempId;

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
//	@Column(name = "subj_id", columnDefinition = "VARCHAR(16)")
//	private String subjectId;
	@Column(name = "subject_name", columnDefinition = "VARCHAR(45)")
	private String subjectName;
	@Column(name = "academic_year", columnDefinition = "int(11)")
	private int academicYear;
	//@Column(name = "teacher_id", columnDefinition = "VARCHAR(16)")
	//private String teacherId;
	@Column(name = "teacher_name", columnDefinition = "VARCHAR(80)")
	private String teacherName;
	@Column(name = "for_use_field1", columnDefinition = "VARCHAR(45)")
	private String forUseField1;
	@Column(name = "for_use_field2", columnDefinition = "VARCHAR(45)")
	private String forUseField2;
	@Column(name = "is_soft_delete", columnDefinition = "int(1)")
	private Boolean isSoftDelete;
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(45)")
	private String insertedBy;
	@Column(name = "inserted_time")
	private Date insertedTime;
	@Column(name = "updated_by", columnDefinition = "VARCHAR(45)")
	private String updatedBy;
	@Column(name = "updated_time")
	private Date updatedTime;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}

//	public String getTeacherId() {
//		return teacherId;
//	}
//
//	public void setTeacherId(String teacherId) {
//		this.teacherId = teacherId;
//	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getForUseField1() {
		return forUseField1;
	}

	public void setForUseField1(String forUseField1) {
		this.forUseField1 = forUseField1;
	}

	public String getForUseField2() {
		return forUseField2;
	}

	public void setForUseField2(String forUseField2) {
		this.forUseField2 = forUseField2;
	}

	public Boolean getIsSoftDelete() {
		return isSoftDelete;
	}

	public void setIsSoftDelete(Boolean isSoftDelete) {
		this.isSoftDelete = isSoftDelete;
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

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

//	public String getSubjectId() {
//		return subjectId;
//	}
//
//	public void setSubjectId(String subjectId) {
//		this.subjectId = subjectId;
//	}
}
