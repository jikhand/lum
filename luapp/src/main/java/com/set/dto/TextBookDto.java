package com.set.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TextBookDto implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 737810120348817782L;
	private String StockBorrowQuantity;
	private String BookName;
	private String Id;
	private String SubCatogeryName;
	private String CatogeryName;
	private String BookAuthor;
	private String BookIsbn;
	private String BookPublisher;
	private String BookPublishYear;
	private String SubCategoryId;
	private String BookType;	
	private int BookLength;
	private String BookCoverImage;
	private String BookBarcodeImage;
	private String IsDeleted;
	private Date CreatedAt;
	private String CategoryId;
	private String Status;
	private String temp;
	private String pdfUrl;
	
	public String getPdfUrl() {
		return pdfUrl;
	}
	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getId() {
		return Id;
	}
	@JsonProperty("Id")
	public void setId(String id) {
		Id = id;
	}
	public String getStockBorrowQuantity() {
		return StockBorrowQuantity;
	}
	@JsonProperty("StockBorrowQuantity")
	public void setStockBorrowQuantity(String stockBorrowQuantity) {
		StockBorrowQuantity = stockBorrowQuantity;
	}
	public String getBookName() {
		return BookName;
	}
	@JsonProperty("BookName")
	public void setBookName(String bookName) {
		BookName = bookName;
	}
	public String getSubCatogeryName() {
		return SubCatogeryName;
	}
	@JsonProperty("SubCatogeryName")
	public void setSubCatogeryName(String subCatogeryName) {
		SubCatogeryName = subCatogeryName;
	}
	public String getCatogeryName() {
		return CatogeryName;
	}
	@JsonProperty("CatogeryName")
	public void setCatogeryName(String catogeryName) {
		CatogeryName = catogeryName;
	}
	public String getBookAuthor() {
		return BookAuthor;
	}
	@JsonProperty("BookAuthor")
	public void setBookAuthor(String bookAuthor) {
		BookAuthor = bookAuthor;
	}
	public String getBookIsbn() {
		return BookIsbn;
	}
	@JsonProperty("BookIsbn")
	public void setBookIsbn(String bookIsbn) {
		BookIsbn = bookIsbn;
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
	public String getBookType() {
		return BookType;
	}
	@JsonProperty("BookType")
	public void setBookType(String bookType) {
		BookType = bookType;
	}
	public int getBookLength() {
		return BookLength;
	}
	@JsonProperty("BookLength")
	public void setBookLength(int bookLength) {
		BookLength = bookLength;
	}
	public String getBookCoverImage() {
		return BookCoverImage;
	}
	@JsonProperty("BookCoverImage")
	public void setBookCoverImage(String bookCoverImage) {
		BookCoverImage = bookCoverImage;
	}
	public String getBookBarcodeImage() {
		return BookBarcodeImage;
	}
	@JsonProperty("BookBarcodeImage")
	public void setBookBarcodeImage(String bookBarcodeImage) {
		BookBarcodeImage = bookBarcodeImage;
	}
	public String getIsDeleted() {
		return IsDeleted;
	}
	@JsonProperty("IsDeleted")
	public void setIsDeleted(String isDeleted) {
		IsDeleted = isDeleted;
	}
	public Date getCreatedAt() {
		return CreatedAt;
	}
	@JsonProperty("CreatedAt")
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
	public String getCategoryId() {
		return CategoryId;
	}
	@JsonProperty("CategoryId")
	public void setCategoryId(String categoryId) {
		CategoryId = categoryId;
	}
	public String getStatus() {
		return Status;
	}
	@JsonProperty("Status")
	public void setStatus(String status) {
		Status = status;
	}
}
