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
public class AttendenceId implements Serializable
 {

	   /**
	 * 
	 */
	private static final long serialVersionUID = 2022448135590143185L;

	@Column(name="attendance_id",columnDefinition = "VARCHAR(16)")
	   private String attendanceId;
	   
	   @Column(name="student_id",columnDefinition="VARCHAR(16)")
	   private String studentId;   
	   
       @Column(name="class_id",columnDefinition="VARCHAR(16)")
	   private String classId;
	   
	   @Column(name="section_id",columnDefinition="VARCHAR(16)")
	   private String sectionId;
	  
	   @Column(name="attendance_date")
	   private Date attendanceDate;
	   
	   public AttendenceId() {
		// TODO Auto-generated constructor stub
	   }

	   public AttendenceId(String attendanceId, String studentId, String classId, String sectionId, Date attendanceDate) {
		   this.attendanceId = attendanceId;
		   this.studentId = studentId;
		   this.classId = classId;
		   this.sectionId = sectionId;
		   this.attendanceDate = attendanceDate;	
	   }

	   public String getAttendanceId() {
		   return attendanceId;
	   }

		public void setAttendanceId(String attendanceId) {
			this.attendanceId = attendanceId;
		}
		
		public String getStudentId() {
			   return studentId;
		   }

		   public void setStudentId(String studentId) {
			   this.studentId = studentId;
		   }

		public String getClassId() {
			return classId;
		}
	
		public void setClassId(String classId) {
			this.classId = classId;
		}
	
		public String getSectionId() {
			return sectionId;
		}
	
		public void setSectionId(String sectionId) {
			this.sectionId = sectionId;
		}
	
		public Date getAttendanceDate() {
			return attendanceDate;
		}
	
		public void setAttendanceDate(Date attendanceDate) {
			this.attendanceDate = attendanceDate;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof AttendenceId))
				return false;
			AttendenceId that = (AttendenceId) o;
			return Objects.equals(getAttendanceId(), that.getAttendanceId()) && Objects.equals(getStudentId(), that.getStudentId()) && Objects.equals(getClassId(), that.getClassId())
					&& Objects.equals(getSectionId(), that.getSectionId()) && Objects.equals(getAttendanceDate(), that.getAttendanceDate());
		}
	
		@Override
		public int hashCode() {
			return Objects.hash(getAttendanceId(), getStudentId(), getClassId(), getSectionId(), getAttendanceDate());
		}

	   
}
