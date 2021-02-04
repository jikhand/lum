package com.set.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "lutbl_school_class")

public class SchoolClass {
	@Id
	@Column(name = "class_id", columnDefinition = "VARCHAR(8)")
	private String classId;
	
	@Column(name = "class_name", columnDefinition = "VARCHAR(45)")
	private String className;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}	
}