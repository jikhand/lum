package com.set.model;

import java.io.Serializable;
import java.util.List;

public class ClassSectionMasterDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4214405373866621444L;
	private List<ClassSectionMaster> classSectionMaster;
	public List<ClassSectionMaster> getClassSectionMaster() {
		return classSectionMaster;
	}
	public void setClassSectionMaster(List<ClassSectionMaster> classSectionMaster) {
		this.classSectionMaster = classSectionMaster;
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
