package com.set.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("deprecation")
@Entity
@Table(name="lutbl_timetable")

public class TimeTable {

	   @Id
	   @EmbeddedId
	   private TimeTableId timeTableId;
	   
	   @Transient
	   private TimeTableId timetableList[];
	   
	   public TimeTableId[] getTimetableList() {
		return timetableList;
	   }
	   public void setTimetableList(TimeTableId[] timetableList) {
		   this.timetableList = timetableList;
	   }
	   @Column(name="schedule_id",columnDefinition = "VARCHAR(16)")
	    private String scheduleId;
	   @Column(name="academic_year",columnDefinition = "int(4)")
	    private int academicYear;
	   @Column(name="is_deleted",columnDefinition = "int(1)")
	    private int isDeleted;
		   public int getIsDeleted() {
			return isDeleted;
		   }
		public void setIsDeleted(int isDeleted) {
			this.isDeleted = isDeleted;
		}
	@Transient
		private String message;

		public TimeTableId getTimeTableId() {
		return timeTableId;
		}
		public void setTimeTableId(TimeTableId timeTableId) {
			this.timeTableId = timeTableId;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getScheduleId() {
			return scheduleId;
		}
		public void setScheduleId(String scheduleId) {
			this.scheduleId = scheduleId;
		}
		public int getAcademicYear() {
			return academicYear;
		}
		public void setAcademicYear(int academicYear) {
			this.academicYear = academicYear;
		}
	    
}