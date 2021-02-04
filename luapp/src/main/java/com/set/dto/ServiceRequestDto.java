package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Transient;

public class ServiceRequestDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1669163731010206004L;
	private int ServiceRequestId;
	private String RequestorId;
	private String ServiceRequestTitle;
	private Date RaisedOn;
	private Date ServiceRequestClosedOn;
	private String AssignedTo;
	private String Status;
	private String Details;
	private String Remarks;
	private String InsertedBy;
	private String UpdatedBy;

	@Transient
	private String Message;
	
	
	public int getServiceRequestId() {
		return ServiceRequestId;
	}
	public void setServiceRequestId(int serviceRequestId) {
		ServiceRequestId = serviceRequestId;
	}
	public String getRequestorId() {
		return RequestorId;
	}
	public void setRequestorId(String requestorId) {
		RequestorId = requestorId;
	}
	public String getServiceRequestTitle() {
		return ServiceRequestTitle;
	}
	public void setServiceRequestTitle(String serviceRequestTitle) {
		ServiceRequestTitle = serviceRequestTitle;
	}
	public Date getRaisedOn() {
		return RaisedOn;
	}
	public void setRaisedOn(Date raisedOn) {
		RaisedOn = raisedOn;
	}
	public Date getServiceRequestClosedOn() {
		return ServiceRequestClosedOn;
	}
	public void setServiceRequestClosedOn(Date serviceRequestClosedOn) {
		ServiceRequestClosedOn = serviceRequestClosedOn;
	}
	public String getAssignedTo() {
		return AssignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		AssignedTo = assignedTo;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public String getInsertedBy() {
		return InsertedBy;
	}
	public void setInsertedBy(String insertedBy) {
		InsertedBy = insertedBy;
	}
	public String getUpdatedBy() {
		return UpdatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	
	
}
