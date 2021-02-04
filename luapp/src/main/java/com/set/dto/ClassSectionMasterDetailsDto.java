package com.set.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassSectionMasterDetailsDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4214405373866621444L;
	private List<ClassSectionMasterDto> ClassSections;
	public List<ClassSectionMasterDto> getClassSectionMaster() {
		return ClassSections;
	}
	@JsonProperty("ClassSections")
	public void setClassSectionMaster(List<ClassSectionMasterDto> classSectionMaster) {
		this.ClassSections = classSectionMaster;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private int count;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@javax.persistence.Transient
	private String message;
}
