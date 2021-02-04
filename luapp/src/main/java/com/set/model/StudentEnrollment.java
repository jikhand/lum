package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "lutbl_stdnt_enrol")
public class StudentEnrollment {
	@Id
	@EmbeddedId
	private StudentEnrollmentId studentEnrollmentId;

	public StudentEnrollmentId getStudentEnrollmentId() {
		return studentEnrollmentId;
	}

	public void setStudentEnrollmentId(StudentEnrollmentId studentEnrollmentId) {
		this.studentEnrollmentId = studentEnrollmentId;
	}

	@Column(name = "academic_year", columnDefinition = "int(11)")
	private int academicYear;
	@Column(name = "class_teacher", columnDefinition = "VARCHAR(80)")
	private String classTeacher;
	@Column(name = "grade_obtained", columnDefinition = "VARCHAR(11)")
	private String gradeObtained;
	public void setGradeObtained(String gradeObtained) {
		this.gradeObtained = gradeObtained;
	}

	@Column(name = "is_promoted", columnDefinition = "TINYINT(1)")
	private boolean isPromoted;
	

	public boolean isPromoted() {
		return isPromoted;
	}

	public void setPromoted(boolean isPromoted) {
		this.isPromoted = isPromoted;
	}

	@Column(name = "academic_review", columnDefinition = "VARCHAR(240)")
	private String academicReview;
	@Column(name = "for_use_field1", columnDefinition = "VARCHAR(45)")
	private String forUseField1;
	@Column(name = "for_use_field2", columnDefinition = "VARCHAR(45)")
	private String forUseField2;
	@Column(name = "is_soft_delete", columnDefinition = "int(1)")
	private int isSoftDelete;
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(45)")
	private String insertedBy;
	@Column(name = "inserted_time")
	private Date insertedTime;
	@Column(name = "updated_by", columnDefinition = "VARCHAR(45)")
	private String updatedBy;
	@Column(name = "updated_time")
	private Date updatedTime;

	public int getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}

	public String getClassTeacher() {
		return classTeacher;
	}

	public void setClassTeacher(String classTeacher) {
		this.classTeacher = classTeacher;
	}

	public String getGradeObtained() {
		return gradeObtained;
	}


	public String getAcademicReview() {
		return academicReview;
	}

	public void setAcademicReview(String academicReview) {
		this.academicReview = academicReview;
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

	public int getIsSoftDelete() {
		return isSoftDelete;
	}

	public void setIsSoftDelete(int isSoftDelete) {
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
}
