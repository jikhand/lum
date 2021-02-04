package com.set.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class StudentActivityId implements Serializable{
	   
	
	   @Column(name="student_id",columnDefinition = "VARCHAR(16)")
	   private String studentId;
	   @Column(name="activity_code",columnDefinition = "int(11)")
	   private int activityCode;
		public StudentActivityId() {
			// TODO Auto-generated constructor stub
		}

		public StudentActivityId(String studentId, int activityCode) {
			this.studentId = studentId;
			this.activityCode = activityCode;
		}

		public String getStudentId() {
			return studentId;
		}

		public int getActivityCode() {
			return activityCode;
		}


		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof StudentActivityId))
				return false;
			StudentActivityId that = (StudentActivityId) o;
			return Objects.equals(getStudentId(), that.getStudentId()) && Objects.equals(getActivityCode(), that.getActivityCode());
		}

		@Override
		public int hashCode() {
			return Objects.hash(getStudentId(), getActivityCode());
		}
}
