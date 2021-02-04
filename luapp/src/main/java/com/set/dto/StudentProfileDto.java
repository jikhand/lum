package com.set.dto;

import java.io.Serializable;

public class StudentProfileDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 270856807385646882L;
	private int code;
	private StudentMasterDetailsDto item;
	private String Message;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public StudentMasterDetailsDto getItem() {
		return item;
	}

	public void setItem(StudentMasterDetailsDto item) {
		this.item = item;
	}

	public String getMessage(){
		return Message;
	}
	
	public void setMessage(String message) {
		this.Message = message;
	}
}
