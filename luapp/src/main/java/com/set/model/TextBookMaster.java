package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "lutbl_book_master")
public class TextBookMaster {
	@EmbeddedId
	private TextBookMasterId textBookMasterId;
	@Column(name = "stock_borrow_quantity", columnDefinition = "VARCHAR(3)")
	private String stockBorrowQuantity;
	@Column(name = "book_name", columnDefinition = "VARCHAR(45)")
	private String bookName;
	@Column(name = "id", columnDefinition = "VARCHAR(4)")
	private String id;
	@Column(name = "sub_catogery_name", columnDefinition = "VARCHAR(45)")
	private String subCatogeryName;
	@Column(name = "catogery_name", columnDefinition = "VARCHAR(45)")
	private String catogeryName;
	@Column(name = "book_author", columnDefinition = "VARCHAR(45)")
	private String bookAuthor;
	@Column(name = "book_isbn", columnDefinition = "VARCHAR(25)")
	private String bookIsbn;
	@Column(name = "book_publisher", columnDefinition = "VARCHAR(45)")
	private String bookPublisher;
	@Column(name = "book_publish_year")
	private String bookPublishYear;
	@Column(name = "sub_category_id", columnDefinition = "VARCHAR(4)")
	private String subCategoryId;
	@Column(name = "book_type", columnDefinition = "VARCHAR(45)")
	private String bookType;
	@Column(name = "book_length", columnDefinition = "int(4)")
	private int bookLength;
	@Column(name = "book_cover_image", columnDefinition = "VARCHAR(240)")
	private String bookCoverImage;
	@Column(name = "book_barcode_image", columnDefinition = "VARCHAR(255)")
	private String bookBarcodeImage;
	@Column(name = "is_deleted", columnDefinition = "int(1)")
	private int isDeleted;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "category_id", columnDefinition = "VARCHAR(4)")
	private String categoryId;
	@Column(name = "status", columnDefinition = "VARCHAR(45)")
	private String status;
	@Transient
	private String message;	
	@Transient
	private String temp;
	@Column(name = "pdf_url", columnDefinition = "VARCHAR(255)")
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TextBookMasterId getTextBookMasterId() {
		return textBookMasterId;
	}
	public void setTextBookMasterId(TextBookMasterId textBookMasterId) {
		this.textBookMasterId = textBookMasterId;
	}
	public String getStockBorrowQuantity() {
		return stockBorrowQuantity;
	}
	public void setStockBorrowQuantity(String stockBorrowQuantity) {
		this.stockBorrowQuantity = stockBorrowQuantity;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubCatogeryName() {
		return subCatogeryName;
	}
	public void setSubCatogeryName(String subCatogeryName) {
		this.subCatogeryName = subCatogeryName;
	}
	public String getCatogeryName() {
		return catogeryName;
	}
	public void setCatogeryName(String catogeryName) {
		this.catogeryName = catogeryName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookIsbn() {
		return bookIsbn;
	}
	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
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
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public int getBookLength() {
		return bookLength;
	}
	public void setBookLength(int bookLength) {
		this.bookLength = bookLength;
	}
	public String getBookCoverImage() {
		return bookCoverImage;
	}
	public void setBookCoverImage(String bookCoverImage) {
		this.bookCoverImage = bookCoverImage;
	}
	public String getBookBarcodeImage() {
		return bookBarcodeImage;
	}
	public void setBookBarcodeImage(String bookBarcodeImage) {
		this.bookBarcodeImage = bookBarcodeImage;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
}
