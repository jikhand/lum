package com.set.dto;

import java.io.Serializable;

public class UserProfileDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 270856807385646882L;
	private StudentMasterDetailsDto studentMasterDetailsDto;

	private EmployeeMasterDetailsDto employeeMasterDetailsDto;
	private String Message;

	public StudentMasterDetailsDto getStudentMasterDetailsDto() {
		return studentMasterDetailsDto;
	}

	public void setStudentMasterDetailsDto(StudentMasterDetailsDto studentMasterDetailsDto) {
		this.studentMasterDetailsDto = studentMasterDetailsDto;
	}

	public EmployeeMasterDetailsDto getEmployeeMasterDetailsDto() {
		return employeeMasterDetailsDto;
	}

	public void setEmployeeMasterDetailsDto(EmployeeMasterDetailsDto employeeMasterDetailsDto) {
		this.employeeMasterDetailsDto = employeeMasterDetailsDto;
	}

	public StudentMasterDetailsDto getUserMasterDetailsDto() {
		return studentMasterDetailsDto;
	}

	public void setUserMasterDetailsDto(StudentMasterDetailsDto studentMasterDetailsDto) {
		this.studentMasterDetailsDto = studentMasterDetailsDto;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		this.Message = message;
	}
}
