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
public class WeekNameId implements Serializable
 {

	   /**
	 * 
	 */
	private static final long serialVersionUID = 2022448135590143185L;

	   @Column(name="day",columnDefinition = "VARCHAR(3)")
	   private String day;
	    
	   
	   public String getDay() {
		return day;
	   }

	   public void setDay(String day) {
		   this.day = day;
	   }

	   public WeekNameId() {
		// TODO Auto-generated constructor stub
	   }

	   public WeekNameId(String day) {
		   this.day = day;
	   }

	  
		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof WeekNameId))
				return false;
			WeekNameId that = (WeekNameId) o;
			return Objects.equals(getDay(), that.getDay());
		}
	
		@Override
		public int hashCode() {
			return Objects.hash(getDay());
		}	   
}
