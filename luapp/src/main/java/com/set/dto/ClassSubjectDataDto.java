package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassSubjectDataDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2037927419308245515L;
	private String ClassId;
	private String ClassName;
	private List<SectionDataDto> sectiondata;

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

	public List<SectionDataDto> getSectiondata() {
		return sectiondata;
	}

	public void setSectiondata(List<SectionDataDto> sectiondata) {
		this.sectiondata = sectiondata;
	}

}
