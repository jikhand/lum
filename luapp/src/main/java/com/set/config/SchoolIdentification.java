package com.set.config;

import java.util.HashMap;

public class SchoolIdentification {
	private String schoolName;
	private String schoolDb;
	private HashMap<String, String> schoolConfiguration;
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolDb() {
		return schoolDb;
	}
	public void setSchoolDb(String schoolDb) {
		this.schoolDb = schoolDb;
	}
	public HashMap<String, String> getSchoolConfiguration() {
		return schoolConfiguration;
	}
	public void setSchoolConfiguration(HashMap<String, String> schoolConfiguration) {
		this.schoolConfiguration = schoolConfiguration;
	}
	
	
}
