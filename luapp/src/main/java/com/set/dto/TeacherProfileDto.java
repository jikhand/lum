package com.set.dto;

import java.io.Serializable;

public class TeacherProfileDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 270856807385646882L;
	private int code;
	private EmployeeMasterDetailsDto item;
	private String Message;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public EmployeeMasterDetailsDto getItem() {
		return item;
	}

	public void setItem(EmployeeMasterDetailsDto item) {
		this.item = item;
	}

	public String getMessage(){
		return Message;
	}
	
	public void setMessage(String message) {
		this.Message = message;
	}
}
