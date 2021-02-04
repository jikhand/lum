package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class UnitDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3160922576219379289L;
	private String SubjectUnitId;
	private String UnitName;
	
	
	public String getSubjectUnitId() {
		return SubjectUnitId;
	}

	@JsonProperty("SubjectUnitId")
	public void setSubjectUnitId(String subjectUnitId) {
		SubjectUnitId = subjectUnitId;
	}

	public String getUnitName() {
		return UnitName;
	}

	@JsonProperty("UnitName")
	public void setUnitName(String unitName) {
		UnitName = unitName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
