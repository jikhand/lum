package com.set.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "lutbl_libsearch")
public class LibrarySearch {

	@Id
	@Column(name = "student_id", columnDefinition = "VARCHAR(16)")
	private String StudentId;
	
	@Column(name = "srch_bookid1", columnDefinition = "VARCHAR(16)")
	private String SrchBookid1;
	@Column(name = "srch_coverphoto1", columnDefinition = "VARCHAR(255)")
	private String SearchCoverphoto1;
	@Column(name = "srch_bookid2", columnDefinition = "VARCHAR(16)")
	private String SrchBookid2;
	@Column(name = "srch_coverphoto2", columnDefinition = "VARCHAR(255)")
	private String SearchCoverphoto2;
	
	@Column(name = "srch_bookid3", columnDefinition = "VARCHAR(16)")
	private String SrchBookid3;
	@Column(name = "srch_coverphoto3", columnDefinition = "VARCHAR(255)")
	private String SearchCoverphoto3;

	@Column(name = "srch_bookid4", columnDefinition = "VARCHAR(16)")
	private String SrchBookId4;
	@Column(name = "srch_coverphoto4", columnDefinition = "VARCHAR(255)")
	private String SearchCoverphoto4;
	@Column(name = "srch_bookid5", columnDefinition = "VARCHAR(16)")
	private String SrchBookid5;
	@Column(name = "srch_coverphoto5", columnDefinition = "VARCHAR(255)")
	private String SearchCoverphoto5;
	@Transient
	private String message;
	public String getStudentId() {
		return StudentId;
	}
	public void setStudentId(String studentId) {
		StudentId = studentId;
	}
	public String getSrchBookid1() {
		return SrchBookid1;
	}
	public void setSrchBookid1(String srchBookid1) {
		SrchBookid1 = srchBookid1;
	}
	public String getSearchCoverphoto1() {
		return SearchCoverphoto1;
	}
	public void setSearchCoverphoto1(String searchCoverphoto1) {
		SearchCoverphoto1 = searchCoverphoto1;
	}
	public String getSrchBookid2() {
		return SrchBookid2;
	}
	public void setSrchBookid2(String srchBookid2) {
		SrchBookid2 = srchBookid2;
	}
	public String getSearchCoverphoto2() {
		return SearchCoverphoto2;
	}
	public void setSearchCoverphoto2(String searchCoverphoto2) {
		SearchCoverphoto2 = searchCoverphoto2;
	}
	public String getSrchBookid3() {
		return SrchBookid3;
	}
	public void setSrchBookid3(String srchBookid3) {
		SrchBookid3 = srchBookid3;
	}
	public String getSearchCoverphoto3() {
		return SearchCoverphoto3;
	}
	public void setSearchCoverphoto3(String searchCoverphoto3) {
		SearchCoverphoto3 = searchCoverphoto3;
	}
	public String getSrchBookId4() {
		return SrchBookId4;
	}
	public void setSrchBookId4(String srchBookId4) {
		SrchBookId4 = srchBookId4;
	}
	public String getSearchCoverphoto4() {
		return SearchCoverphoto4;
	}
	public void setSearchCoverphoto4(String searchCoverphoto4) {
		SearchCoverphoto4 = searchCoverphoto4;
	}
	public String getSrchBookid54() {
		return SrchBookid5;
	}
	public void setSrchBookid4(String srchBookid5) {
		SrchBookid5 = srchBookid5;
	}
	public String getSearchCoverphoto5() {
		return SearchCoverphoto5;
	}
	public void setSearchCoverphoto5(String searchCoverphoto5) {
		SearchCoverphoto5 = searchCoverphoto5;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}