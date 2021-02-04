package com.set.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("deprecation")
@Entity
@Table(name="lutbl_school_timings")

public class SchoolTimings {
	   @Id
	   @EmbeddedId	   
	   private SchoolTimingsId schoolTimingsId;
	   
	   @Transient
	   private SchoolTimingsId schoolTimingsIdList[];
	   

	    @Column(name="academic_year",columnDefinition = "VARCHAR(10)")
	    private String academicYear;
	    @Column(name="days_in_week",columnDefinition = "int(1)")
	    private String daysInWeek;
	    
	    @Column(name="school_start_date")
		private Date schoolStartDate;
	    
	    @Column(name="school_end_date")
		private Date schoolEndDate;
	    
	    @Column(name="opening_hours")
		private Date openingHours;
	    @Column(name="closing_hours")
		private Date closingHours;
	    
	    @Transient
	    private String message;
	    
	    
	    
	    

		public SchoolTimingsId getSchoolTimingsId() {
			return schoolTimingsId;
		}
		public void setSchoolTimingsId(SchoolTimingsId schoolTimingsId) {
			this.schoolTimingsId = schoolTimingsId;
		}
		public SchoolTimingsId[] getSchoolTimingsIdList() {
			return schoolTimingsIdList;
		}
		public void setSchoolTimingsIdList(SchoolTimingsId[] schoolTimingsIdList) {
			this.schoolTimingsIdList = schoolTimingsIdList;
		}
		public String getAcademicYear() {
			return academicYear;
		}
		public void setAcademicYear(String academicYear) {
			this.academicYear = academicYear;
		}
		public String getDaysInWeek() {
			return daysInWeek;
		}
		public void setDaysInWeek(String daysInWeek) {
			this.daysInWeek = daysInWeek;
		}
		public Date getSchoolStartDate() {
			return schoolStartDate;
		}
		public void setSchoolStartDate(Date schoolStartDate) {
			this.schoolStartDate = schoolStartDate;
		}
		public Date getSchoolEndDate() {
			return schoolEndDate;
		}
		public void setSchoolEndDate(Date schoolEndDate) {
			this.schoolEndDate = schoolEndDate;
		}
		public Date getOpeningHours() {
			return openingHours;
		}
		public void setOpeningHours(Date openingHours) {
			this.openingHours = openingHours;
		}
		public Date getClosingHours() {
			return closingHours;
		}
		public void setClosingHours(Date closingHours) {
			this.closingHours = closingHours;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		} 
	
}
