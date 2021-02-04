package com.set.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
public class SchoolTimingsId implements Serializable
 {

	   /**
	 * 
	 */
	private static final long serialVersionUID = 2022448135590143185L;

	   @Column(name="school_id",columnDefinition = "int(11)")
	   private String schoolId;

	   @Column(name="season",columnDefinition = "VARCHAR(2)")
	   private String season;
	    
	   public String getSchoolId() {
			return schoolId;
		}
	
		public void setSchoolId(String schoolId) {
			this.schoolId = schoolId;
		}
	
		public String getSeason() {
			return season;
		}
	
		public void setSeason(String season) {
			this.season = season;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof SchoolTimingsId))
				return false;
			SchoolTimingsId that = (SchoolTimingsId) o;
			return Objects.equals(getSchoolId(), that.getSchoolId()) && Objects.equals(getSeason(), that.getSeason());
		}
	
		@Override
		public int hashCode() {
			return Objects.hash(getSchoolId(), getSeason());
		}	   
}
