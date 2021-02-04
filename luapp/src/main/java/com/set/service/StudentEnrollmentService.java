package com.set.service;

import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.StudentEnrollment;
import com.set.model.StudentEnrollmentId;

public interface StudentEnrollmentService {
	void save(StudentEnrollment studentEnrollment);
	  StudentEnrollment getStudentEnrollmentById(StudentEnrollmentId studentEnrollment);
	  void updateStudentEnrollment(StudentEnrollment studentEnrollment);
	  public long cityIdByName(String cityId);
	  public long stateIdByName(String stateId);
	  public long countryIdByName(String countryId);
	  public String roleIdByName(String roleId);
	  public String userIdByEmail(String email);
	  public ClassSectionMaster classIdByName(String classId,String sectionId);
	  public boolean IsEmailExist(String email);
}