package com.set.dao;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.StudentEnrollment;
import com.set.model.StudentEnrollmentId;

@Repository
@Transactional
public interface StudentEnrollmentDao {
	  void save(StudentEnrollment studentEnrollment);
	  StudentEnrollment getStudentEnrollmentById(StudentEnrollmentId studentEnrollment);
	  void updateStudentEnrollment(StudentEnrollment studentEnrollment);
	  public boolean IsEmailExist(String email);
	  public long cityIdByName(String cityId);
	  public long stateIdByName(String stateId);
	  public long countryIdByName(String countryId);
	  public String roleIdByName(String roleId);
	  public String userIdByEmail(String email);
	  public ClassSectionMaster classIdByName(String classId,String sectionId);
}