package com.set.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("deprecation")
@Entity
@Table(name="lutbl_subj_units")
public class SubjectUnit {
	
	@Id
	@Column(name="unit_id",columnDefinition = "VARCHAR(8)")
	private String UnitId;
	@Column(name="unit_name",columnDefinition = "VARCHAR(55)")
	private String UnitName;
	   
	
	public String getUnitId() {
		return UnitId;
	}
	@JsonProperty("UnitId")
	public void setUnitId(String unitId) {
		this.UnitId = unitId;
	}
	public String getUnitName() {
		return UnitName;
	}
	@JsonProperty("UnitName")
	public void setUnitName(String unitName) {
		this.UnitName = unitName;
	}
	@Transient
	private String message;
	   
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	  	   
}
