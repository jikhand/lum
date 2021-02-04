package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubjectMasterId implements Serializable {
	@Column(name = "subj_id", columnDefinition = "VARCHAR(16)")
	private String subjectId;
	@Column(name = "text_book_ISBN", columnDefinition = "VARCHAR(13)")
	private String textBookISBN;

	public String getTextBookISBN() {
		return textBookISBN;
	}

	public SubjectMasterId() {
	}

	public SubjectMasterId(String subjectId, String textBookISBN) {
		this.subjectId = subjectId;
		this.textBookISBN = textBookISBN;
	}

	public String getSubjectId() {
		return subjectId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof SubjectMasterId))
			return false;
		SubjectMasterId that = (SubjectMasterId) o;
		return Objects.equals(getSubjectId(), that.getSubjectId())
				&& Objects.equals(getTextBookISBN(), that.getTextBookISBN());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getSubjectId(), getTextBookISBN());
	}
}
