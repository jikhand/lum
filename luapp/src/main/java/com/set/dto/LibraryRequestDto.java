package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LibraryRequestDto implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 710085846116688965L;
	private String BookId;
	private String StudentId;
	private String BookIsbn;
	private String InsertedBy;
	private Date InsertedTime;
	private String UpdatedBy;
	private Date UpdatedTime;
	private Date RequestOn;
	private Date IssuedOn;
	private Date ExpectedReturn;
	private Date ReturnOn;
	private Date RenewedOn;
	private int NoOfRenewals;
	private String Remarks;
	private float DuesCharged;
	private float DuesPaid;
	private String Title;
	
	
	public String getTitle() {
		return Title;
	}
	@JsonProperty("Title")
	public void setTitle(String title) {
		Title = title;
	}
	public String getStudentId() {
		return StudentId;
	}
	@JsonProperty("StudentId")
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	public float getDuesCharged() {
		return DuesCharged;
	}
	@JsonProperty("DuesCharged")
	public void setDuesCharged(float duesCharged) {
		DuesCharged = duesCharged;
	}
	public float getDuesPaid() {
		return DuesPaid;
	}
	@JsonProperty("DuesPaid")
	public void setDuesPaid(float duesPaid) {
		DuesPaid = duesPaid;
	}
	public Date getRequestOn() {
		return RequestOn;
	}
	@JsonProperty("RequestOn")
	public void setRequestOn(Date requestOn) {
		RequestOn = requestOn;
	}
	public Date getIssuedOn() {
		return IssuedOn;
	}
	@JsonProperty("IssuedOn")
	public void setIssuedOn(Date issuedOn) {
		IssuedOn = issuedOn;
	}
	public Date getExpectedReturn() {
		return ExpectedReturn;
	}
	@JsonProperty("ExpectedReturn")
	public void setExpectedReturn(Date expectedReturn) {
		ExpectedReturn = expectedReturn;
	}
	public Date getReturnOn() {
		return ReturnOn;
	}
	@JsonProperty("ReturnOn")
	public void setReturnOn(Date returnOn) {
		ReturnOn = returnOn;
	}
	public Date getRenewedOn() {
		return RenewedOn;
	}
	@JsonProperty("RenewedOn")
	public void setRenewedOn(Date renewedOn) {
		RenewedOn = renewedOn;
	}
	public int getNoOfRenewals() {
		return NoOfRenewals;
	}
	@JsonProperty("NoOfRenewals")
	public void setNoOfRenewals(int noOfRenewals) {
		NoOfRenewals = noOfRenewals;
	}
	public String getRemarks() {
		return Remarks;
	}
	@JsonProperty("Remarks")
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public String getBookIsbn() {
		return BookIsbn;
	}
	@JsonProperty("BookIsbn")
	public void setBookIsbn(String bookIsbn) {
		BookIsbn = bookIsbn;
	}
	public String getBookId() {
		return BookId;
	}
	@JsonProperty("BookId")
	public void setBookId(String bookId) {
		BookId = bookId;
	}
	
	public String getInsertedBy() {
		return InsertedBy;
	}
	@JsonProperty("InsertedBy")
	public void setInsertedBy(String insertedBy) {
		InsertedBy = insertedBy;
	}
	public Date getInsertedTime() {
		return InsertedTime;
	}
	@JsonProperty("InsertedTime")
	public void setInsertedTime(Date insertedTime) {
		InsertedTime = insertedTime;
	}
	public String getUpdatedBy() {
		return UpdatedBy;
	}
	@JsonProperty("UpdatedBy")
	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}
	public Date getUpdatedTime() {
		return UpdatedTime;
	}
	@JsonProperty("UpdatedTime")
	public void setUpdatedTime(Date updatedTime) {
		UpdatedTime = updatedTime;
	}
}