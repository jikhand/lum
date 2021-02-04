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
public class WorkHoursId implements Serializable
 {

	   /**
	 * 
	 */
	private static final long serialVersionUID = 2022448135590143185L;

	   @Column(name="day",columnDefinition = "VARCHAR(3)")
	   private String day;
	   @Column(name="hour_no",columnDefinition = "int(3)")
	   private int noOfHours; 
	   
	   public String getDay() {
		return day;
	   }

	   public void setDay(String day) {
		   this.day = day;
	   }

	   public int getNoOfHours() {
		   return noOfHours;
	   }

	   public void setNoOfHours(int noOfHours) {
		   this.noOfHours = noOfHours;
	   }

	     
	   
	   public WorkHoursId() {
		// TODO Auto-generated constructor stub
	   }

	   public WorkHoursId(String day, int noOfHours) {
		   this.day = day;
		   this.noOfHours = noOfHours;
		   	
	   }

	  
		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof WorkHoursId))
				return false;
			WorkHoursId that = (WorkHoursId) o;
			return Objects.equals(getDay(), that.getDay()) && Objects.equals(getNoOfHours(), that.getNoOfHours());
		}
	
		@Override
		public int hashCode() {
			return Objects.hash(getDay(), getNoOfHours());
		}

	   
}
