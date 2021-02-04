package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceBankSubjectDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2814528060155046213L;
	private String SubjectId;
	private String SubjectName;
	private List<ResourceBankUnitDto> ResourceBankUnitList;
	

	public String getSubjectId() {
		return SubjectId;
	}

	@JsonProperty("SubjectId")
	public void setSubjectId(String subjectId) {
		SubjectId = subjectId;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	@JsonProperty("SubjectName")
	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}

	public List<ResourceBankUnitDto> getResourceBankUnitList() {
		return ResourceBankUnitList;
	}

	@JsonProperty("unitdata")
	public void setResourceBankUnitList(List<ResourceBankUnitDto> resourceBankUnitList) {
		ResourceBankUnitList = resourceBankUnitList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
