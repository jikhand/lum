package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnitSubjectClassDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -813048385504086284L;
	private String ClassId;
	private String ClassName;
	private String SectionId;
	private String SectionName;
	private List<UnitSubjectDto> UnitSubjectList;
	
		

	public String getClassId() {
		return ClassId;
	}

	@JsonProperty("ClassId")
	public void setClassId(String classId) {
		ClassId = classId;
	}

	public String getClassName() {
		return ClassName;
	}

	@JsonProperty("ClassName")
	public void setClassName(String className) {
		ClassName = className;
	}

	public String getSectionId() {
		return SectionId;
	}

	@JsonProperty("SectionId")
	public void setSectionId(String sectionId) {
		SectionId = sectionId;
	}

	public String getSectionName() {
		return SectionName;
	}

	@JsonProperty("SectionName")
	public void setSectionName(String sectionName) {
		SectionName = sectionName;
	}

	public List<UnitSubjectDto> getUnitSubjectList() {
		return UnitSubjectList;
	}

	@JsonProperty("subjectdata")
	public void setUnitSubjectList(List<UnitSubjectDto> unitSubjectList) {
		UnitSubjectList = unitSubjectList;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
