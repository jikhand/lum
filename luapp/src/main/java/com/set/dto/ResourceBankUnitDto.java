package com.set.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class ResourceBankUnitDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8465068672930017272L;
	private String UnitSubjectId;
	private String UnitName;
	private List<ResourceBankDto> ResourceBankList;

	public String getUnitSubjectId() {
		return UnitSubjectId;
	}

	@JsonProperty("UnitSubjectId")
	public void setUnitSubjectId(String unitSubjectId) {
		UnitSubjectId = unitSubjectId;
	}

	public String getUnitName() {
		return UnitName;
	}

	@JsonProperty("UnitName")
	public void setUnitName(String unitName) {
		UnitName = unitName;
	}

	public List<ResourceBankDto> getResourceBankList() {
		return ResourceBankList;
	}

	@JsonProperty("resourceresult")
	public void setResourceBankList(List<ResourceBankDto> resourceBankList) {
		ResourceBankList = resourceBankList;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
