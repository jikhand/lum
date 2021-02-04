package com.set.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "lutbl_class_sec_master")
public class ClassSectionMaster {

	@Id
	@EmbeddedId
	private ClassSectionMasterId classSectionMasterId;

	public ClassSectionMasterId getClassSectionMasterId() {
		return classSectionMasterId;
	}

	public void setClassSectionMasterId(ClassSectionMasterId classSectionMasterId) {
		this.classSectionMasterId = classSectionMasterId;
	}

	@Column(name = "class_name", columnDefinition = "VARCHAR(45)")
	private String className;
	@Column(name = "academic_year", columnDefinition = "int(6)")
	private int academicYear;
	@Column(name = "section_name", columnDefinition = "VARCHAR(45)")
	private String sectionName;
	@Column(name = "no_of_students", columnDefinition = "int(6)")
	private int noOfStudents;
	@Column(name = "build_block_name", columnDefinition = "VARCHAR(45)")
	private String buildBlockName;
	@Column(name = "class_key", columnDefinition = "VARCHAR(40)")
	private String classKey;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public int getNoOfStudents() {
		return noOfStudents;
	}

	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}

	public String getBuildBlockName() {
		return buildBlockName;
	}

	public void setBuildBlockName(String buildBlockName) {
		this.buildBlockName = buildBlockName;
	}

	public String getClassKey() {
		return classKey;
	}

	public void setClassKey(String classKey) {
		this.classKey = classKey;
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
