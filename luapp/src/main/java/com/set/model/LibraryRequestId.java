package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class LibraryRequestId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4966614332961680804L;
	
	@Column(name = "book_id", columnDefinition = "VARCHAR(16)")
	private String bookId;
	
	@Column(name = "student_id", columnDefinition = "VARCHAR(16)")
	private String studentId;
	
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public LibraryRequestId() {
		// TODO Auto-generated constructor stub
	   }

   public LibraryRequestId(String bookId , String studentId) {
	   this.bookId = bookId;
	   this.studentId = studentId;	   
   }
  
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof LibraryRequestId))
			return false;
		LibraryRequestId that = (LibraryRequestId) o;
		return Objects.equals(getBookId(), that.getBookId()) && Objects.equals(getStudentId(), that.getStudentId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getBookId(), getStudentId());
	}	
}