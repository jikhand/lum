package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "lutbl_libbooks")
public class LibraryBooks {
	@Id
	@Column(name = "book_id", columnDefinition = "VARCHAR(16)")
	private String bookId;
	
	@Column(name = "book_isbn", columnDefinition = "VARCHAR(25)")
	private String bookIsbn;
	
	@Column(name = "title", columnDefinition = "VARCHAR(45)")
	private String title;
	
	@Column(name = "author", columnDefinition = "VARCHAR(80)")
	private String author;
	
	@Column(name = "publisher", columnDefinition = "VARCHAR(80)")
	private String publisher;
	
	@Column(name = "ecopy_path", columnDefinition = "VARCHAR(255)")
	private String ecopyPath;
	
	@Column(name = "copy_type", columnDefinition = "CHAR(1)")
	private String copyType;
	
	@Column(name = "no_of_copies", columnDefinition = "int(2)")
	private int noOfCopies;
	
	@Column(name = "copies_avlbl", columnDefinition = "int(2)")
	private int copiesAvailable;
	
	@Column(name = "book_publisher", columnDefinition = "VARCHAR(45)")
	private String bookPublisher;
	
	@Column(name = "book_publish_year")
	private String bookPublishYear;
	
	@Column(name = "sub_category_id", columnDefinition = "VARCHAR(4)")
	private String subCategoryId;
	
	@Column(name = "for_use_field1", columnDefinition = "VARCHAR(45)")
	private String for_use_field1;
	
	@Column(name = "for_use_field2", columnDefinition = "VARCHAR(45)")
	private String for_use_field2;
	
	@Column(name = "for_use_field3", columnDefinition = "VARCHAR(45)")
	private String for_use_field3;
	
	@Column(name = "inserted_by", columnDefinition = "VARCHAR(45)")
	private String insertedBy;
	
	@Column(name = "inserted_time")
	private Date insertedTime;
	
	@Column(name = "updated_by", columnDefinition = "VARCHAR(45)")
	private String updatedBy;
	
	@Column(name = "updated_time")
	private Date updatedTime;
	
	@Transient
	private String message;
	@Transient
	private String token;

	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookIsbn() {
		return bookIsbn;
	}

	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getEcopyPath() {
		return ecopyPath;
	}

	public void setEcopyPath(String ecopyPath) {
		this.ecopyPath = ecopyPath;
	}

	public String getCopyType() {
		return copyType;
	}

	public void setCopyType(String copyType) {
		this.copyType = copyType;
	}

	public int getNoOfCopies() {
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	public int getCopiesAvailable() {
		return copiesAvailable;
	}

	public void setCopiesAvailable(int copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public String getBookPublishYear() {
		return bookPublishYear;
	}

	public void setBookPublishYear(String bookPublishYear) {
		this.bookPublishYear = bookPublishYear;
	}

	public String getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getFor_use_field1() {
		return for_use_field1;
	}

	public void setFor_use_field1(String for_use_field1) {
		this.for_use_field1 = for_use_field1;
	}

	public String getFor_use_field2() {
		return for_use_field2;
	}

	public void setFor_use_field2(String for_use_field2) {
		this.for_use_field2 = for_use_field2;
	}

	public String getFor_use_field3() {
		return for_use_field3;
	}

	public void setFor_use_field3(String for_use_field3) {
		this.for_use_field3 = for_use_field3;
	}

	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	public Date getInsertedTime() {
		return insertedTime;
	}

	public void setInsertedTime(Date insertedTime) {
		this.insertedTime = insertedTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
