package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SectionDataDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7385688532481825968L;
	private String SectionId;
    private String SectionName;
    private List<SubjectIdDto> subjectresult;
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
	public List<SubjectIdDto> getSubjectresult() {
		return subjectresult;
	}
	public void setSubjectresult(List<SubjectIdDto> subjectresult) {
		this.subjectresult = subjectresult;
	}
    
    
}
