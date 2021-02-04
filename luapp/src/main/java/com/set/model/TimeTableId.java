package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TimeTableId implements Serializable
 {

	   /**
	 * 
	 */
	private static final long serialVersionUID = 5773775499223278129L;
	/**
	 * 
	 */
	   @Column(name="class_id",columnDefinition = "VARCHAR(16)")
	   private String classId;
	   @Column(name="section_id",columnDefinition = "VARCHAR(16)")
	   private String sectionId; 
	   @Column(name="work_id",columnDefinition = "VARCHAR(16)")
	   private String workId;
	   @Column(name="subj_id",columnDefinition = "VARCHAR(16)")
	   private String subjId; 
	   @Column(name="emp_id",columnDefinition = "VARCHAR(16)")
	   private String empId;
	   
		public String getClassId() {
			return classId;
		}
		public String getSectionId() {
			return sectionId;
		}
		public String getWorkId() {
			return workId;
		}
		public void setWorkId(String workId) {
			this.workId = workId;
		}
		public String getSubjId() {
			return subjId;
		}
		public String getEmpId() {
			return empId;
		}
	
		public TimeTableId() {
			// TODO Auto-generated constructor stub
		   }

	   public TimeTableId(String classId, String sectionId, String workId, String subjId, String empId) {
		   this.classId = classId;
		   this.sectionId = sectionId;
		   this.workId = workId;
		   this.subjId = subjId;
		   this.empId = empId;
		}
	  
		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof TimeTableId))
				return false;
			TimeTableId that = (TimeTableId) o;
			return Objects.equals(getClassId(), that.getClassId()) && Objects.equals(getSectionId(), that.getSectionId())
					&& Objects.equals(getWorkId(), that.getWorkId()) && Objects.equals(getSubjId(), that.getSubjId())
					&& Objects.equals(getEmpId(), that.getEmpId());
		}
	
		@Override
		public int hashCode() {
			return Objects.hash(getClassId(), getSectionId(), getWorkId(), getSubjId(), getClassId());
		}
	   
}