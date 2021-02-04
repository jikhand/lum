package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LibraryDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3140641071170694607L;
	
	private String BookId;
	private String BookIsbn;
	private String Title;
	private String Author;
	private String Publisher;
	private String EcopyPath;
	private String CopyType;
	private int NoOfCopies;
	private int CopiesAvailable;
	private String BookPublisher;
	private String BookPublishYear;
	private String SubCategoryId;
	private String InsertedBy;
	private Date InsertedTime;
	private String UpdatedBy;
	private Date UpdatedTime;
	
	
	
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
	public String getTitle() {
		return Title;
	}
	@JsonProperty("Title")
	public void setTitle(String title) {
		Title = title;
	}
	public String getAuthor() {
		return Author;
	}
	@JsonProperty("Author")
	public void setAuthor(String author) {
		Author = author;
	}
	public String getPublisher() {
		return Publisher;
	}
	@JsonProperty("Publisher")
	public void setPublisher(String publisher) {
		Publisher = publisher;
	}
	public String getEcopyPath() {
		return EcopyPath;
	}
	@JsonProperty("EcopyPath")
	public void setEcopyPath(String ecopyPath) {
		EcopyPath = ecopyPath;
	}
	public String getCopyType() {
		return CopyType;
	}
	@JsonProperty("CopyType")
	public void setCopyType(String copyType) {
		CopyType = copyType;
	}
	public int getNoOfCopies() {
		return NoOfCopies;
	}
	@JsonProperty("NoOfCopies")
	public void setNoOfCopies(int noOfCopies) {
		NoOfCopies = noOfCopies;
	}
	public int getCopiesAvailable() {
		return CopiesAvailable;
	}
	@JsonProperty("CopiesAvailable")
	public void setCopiesAvailable(int copiesAvailable) {
		CopiesAvailable = copiesAvailable;
	}
	public String getBookPublisher() {
		return BookPublisher;
	}
	@JsonProperty("BookPublisher")
	public void setBookPublisher(String bookPublisher) {
		BookPublisher = bookPublisher;
	}
	public String getBookPublishYear() {
		return BookPublishYear;
	}
	@JsonProperty("BookPublishYear")
	public void setBookPublishYear(String bookPublishYear) {
		BookPublishYear = bookPublishYear;
	}
	public String getSubCategoryId() {
		return SubCategoryId;
	}
	@JsonProperty("SubCategoryId")
	public void setSubCategoryId(String subCategoryId) {
		SubCategoryId = subCategoryId;
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