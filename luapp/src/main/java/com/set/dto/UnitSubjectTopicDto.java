package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class UnitSubjectTopicDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -190962577743000551L;
	private String SubjectId;
	private String SubjectName;
	private List<UnitTopicDto> UnitTopicList;
	

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

	public List<UnitTopicDto> getUnitTopicList() {
		return UnitTopicList;
	}

	@JsonProperty("unitdata")
	public void setUnitTopicList(List<UnitTopicDto> unitTopicList) {
		UnitTopicList = unitTopicList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
