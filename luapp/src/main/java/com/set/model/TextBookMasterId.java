package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;

public class TextBookMasterId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6606675436720440461L;
	@Column(name = "book_id", columnDefinition = "VARCHAR(16)")
	private String bookId;
	@Column(name = "subj_id", columnDefinition = "VARCHAR(16)")
	private String subjectId;
	
	public TextBookMasterId() {
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public TextBookMasterId(String bookId, String subjectId) {
		super();
		this.bookId = bookId;
		this.subjectId = subjectId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof TextBookMasterId))
			return false;
		TextBookMasterId that = (TextBookMasterId) o;
		return Objects.equals(getBookId(), that.getBookId()) && Objects.equals(getSubjectId(), that.getSubjectId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getBookId(), getSubjectId());
	}
	
}
