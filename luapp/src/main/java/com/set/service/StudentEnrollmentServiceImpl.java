package com.set.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.StudentEnrollmentDao;
import com.set.model.ClassSectionMaster;
import com.set.model.ClassSectionMasterId;
import com.set.model.StudentEnrollment;
import com.set.model.StudentEnrollmentId;
@Service
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {
@Autowired
private StudentEnrollmentDao studentEnrollmentDao;
	@Override
	public void save(StudentEnrollment studentEnrollment) {
		studentEnrollmentDao.save(studentEnrollment);	
	}

	@Override
	public StudentEnrollment getStudentEnrollmentById(StudentEnrollmentId studentEnrollment) {
		// TODO Auto-generated method stub
		return studentEnrollmentDao.getStudentEnrollmentById(studentEnrollment);
	}

	@Override
	public void updateStudentEnrollment(StudentEnrollment studentEnrollment) {
		studentEnrollmentDao.updateStudentEnrollment(studentEnrollment);
	}

	@Override
	public boolean IsEmailExist(String email) {
		return studentEnrollmentDao.IsEmailExist(email);
	}

	@Override
	public long cityIdByName(String cityId) {
		return studentEnrollmentDao.cityIdByName(cityId);
	}

	@Override
	public long stateIdByName(String stateId) {
		return studentEnrollmentDao.stateIdByName(stateId);
	}

	@Override
	public long countryIdByName(String countryId) {
		return studentEnrollmentDao.countryIdByName(countryId);
	}

	@Override
	public String roleIdByName(String roleId) {
		return studentEnrollmentDao.roleIdByName(roleId);
	}

	@Override
	public String userIdByEmail(String email) {
		return studentEnrollmentDao.userIdByEmail(email);
	}


	@Override
	public ClassSectionMaster classIdByName(String classId,String sectionId) {
		return studentEnrollmentDao.classIdByName(classId,sectionId);
	}



}