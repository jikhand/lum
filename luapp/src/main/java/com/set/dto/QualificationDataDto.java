package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QualificationDataDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2268676217853509639L;
	private int Id;
	private String UserId;
	private String Name;
	private int QualificationPercentage;
	private String QualificationUniversityName;
	private int QualificationYearOfPassing;
	private Date UpdatedA;
	private Date CreatedAt;
	public int getId() {
		return Id;
	}
	@JsonProperty("ClassId")
	public void setId(int id) {
		Id = id;
	}
	public String getUserId() {
		return UserId;
	}
	@JsonProperty("UserId")
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getName() {
		return Name;
	}
	@JsonProperty("Name")
	public void setName(String name) {
		Name = name;
	}
	public int getQualificationPercentage() {
		return QualificationPercentage;
	}
	@JsonProperty("QualificationPercentage")
	public void setQualificationPercentage(int qualificationPercentage) {
		QualificationPercentage = qualificationPercentage;
	}
	public String getQualificationUniversityName() {
		return QualificationUniversityName;
	}
	@JsonProperty("QualificationUniversityName")
	public void setQualificationUniversityName(String qualificationUniversityName) {
		QualificationUniversityName = qualificationUniversityName;
	}
	public int getQualificationYearOfPassing() {
		return QualificationYearOfPassing;
	}
	@JsonProperty("QualificationYearOfPassing")
	public void setQualificationYearOfPassing(int qualificationYearOfPassing) {
		QualificationYearOfPassing = qualificationYearOfPassing;
	}
	public Date getUpdatedA() {
		return UpdatedA;
	}
	@JsonProperty("UpdatedA")
	public void setUpdatedA(Date updatedA) {
		UpdatedA = updatedA;
	}
	public Date getCreatedAt() {
		return CreatedAt;
	}
	@JsonProperty("CreatedAt")
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
}
