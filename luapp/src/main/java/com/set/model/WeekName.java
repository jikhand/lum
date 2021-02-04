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
@Table(name="lutbl_week_name")

public class WeekName {
	   @Id
	   @EmbeddedId	   
	   private WeekNameId weekNameId;
	   
	   @Transient
	   private WeekNameId weekNameIdList[];
	   

	    @Column(name="day_name",columnDefinition = "VARCHAR(10)")
	    private String dayName;
	    @Transient
	    private String message;
		public WeekNameId getWeekNameId() {
			return weekNameId;
		}
		public void setWeekNameId(WeekNameId weekNameId) {
			this.weekNameId = weekNameId;
		}
		public WeekNameId[] getWeekNameIdList() {
			return weekNameIdList;
		}
		public void setWeekNameIdList(WeekNameId[] weekNameIdList) {
			this.weekNameIdList = weekNameIdList;
		}
		public String getDayName() {
			return dayName;
		}
		public void setDayName(String dayName) {
			this.dayName = dayName;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		} 
	
}
