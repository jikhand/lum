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

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("deprecation")
@Entity
@Table(name="lutbl_work_hours")

public class WorkHours {
//	   @Id
//	   @EmbeddedId	   
//	   private WorkHoursId workHoursId;
	   
	   @Transient
	   private WorkHoursId workHoursIdList[];
	   
	   public WorkHoursId[] getWorkHoursIdList() {
		return workHoursIdList;
	}
	public void setWorkHoursIdList(WorkHoursId[] workHoursIdList) {
		this.workHoursIdList = workHoursIdList;
	}
	  @Id
	  @Column(name="work_id",columnDefinition = "VARCHAR(8)")
	   private String workId;
	
	  @Column(name="day",columnDefinition = "VARCHAR(3)")
	   private String day;
	   @Column(name="hour_no",columnDefinition = "int(3)")
	   private int noOfHours;
	   @Column(name="start_time")
	   private Date startTime;
	   @Column(name="end_time")
	   private Date endTime;
	   @Column(name="duration",columnDefinition = "int(11)")
	   private float duration;
	   
	   @Transient
		private String message; 
	   
	   
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
		//	    public WorkHoursId getWorkHoursId() {
//			return workHoursId;
//		}
//		public void setWorkHoursId(WorkHoursId workHoursId) {
//			this.workHoursId = workHoursId;
//		} 
	    public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}	
		public Date getStartTime() {
			return startTime;
		}
		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}
		public Date getEndTime() {
			return endTime;
		}
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
		public float getDuration() {
			return duration;
		}
		public void setDuration(float duration) {
			this.duration = duration;
		}
		public String getWorkId() {
			return workId;
		}
		public void setWorkId(String workId) {
			this.workId = workId;
		}

		
	
}
