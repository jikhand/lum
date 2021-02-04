package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class UnitSubjectDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -190962577743000551L;
	private String SubjectId;
	private String SubjectName;
	private List<UnitDto> UnitList;
	

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

	public List<UnitDto> getUnitList() {
		return UnitList;
	}

	@JsonProperty("unitdata")
	public void setUnitList(List<UnitDto> unitList) {
		UnitList = unitList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
