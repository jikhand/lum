package com.set.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassSection{
	/**
	 * 
	 */
	private String ClassId;
	private String SectionId;
	
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
