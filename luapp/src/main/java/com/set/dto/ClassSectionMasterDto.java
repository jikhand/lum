package com.set.dto;

import java.io.Serializable;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassSectionMasterDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7960734896111771682L;
	private String ClassId;
	private String SectionId;
	private String ClassName;
	private int AcademicYear;
	private String SectionName;
	private int NoOfStudents;
	private String BuildBlockName;
	private String ClassKey;

	public String getClassId() {
		return ClassId;
	}

	@JsonProperty("ClassId")
	public void setClassId(String classId) {
		ClassId = classId;
	}

	public int getNoOfStudents() {
		return NoOfStudents;
	}

	@JsonProperty("NoOfStudents")
	public void setNoOfStudents(int noOfStudents) {
		NoOfStudents = noOfStudents;
	}

	public String getBuildBlockName() {
		return BuildBlockName;
	}

	@JsonProperty("BuildBlockName")
	public void setBuildBlockName(String buildBlockName) {
		BuildBlockName = buildBlockName;
	}

	public String getClassKey() {
		return ClassKey;
	}

	@JsonProperty("ClassKey")
	public void setClassKey(String classKey) {
		ClassKey = classKey;
	}

	public String getSectionId() {
		return SectionId;
	}

	@JsonProperty("SectionId")
	public void setSectionId(String sectionId) {
		SectionId = sectionId;
	}

	public String getClassName() {
		return ClassName;
	}

	@JsonProperty("ClassName")
	public void setClassName(String className) {
		ClassName = className;
	}

	public int getAcademicYear() {
		return AcademicYear;
	}

	@JsonProperty("AcademicYear")
	public void setAcademicYear(int academicYear) {
		AcademicYear = academicYear;
	}

	public String getSectionName() {
		return SectionName;
	}

	@JsonProperty("SectionName")
	public void setSectionName(String sectionName) {
		SectionName = sectionName;
	}
}
