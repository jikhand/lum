package com.set.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.set.dao.StudentMasterDao;
import com.set.model.StudentDetails;
import com.set.model.StudentMaster;
import com.set.model.StudentMasterDetils;
import com.set.model.User;

@Service
public class StudentMasterServiceImpl implements StudentMasterService{
@Autowired
StudentMasterDao studentMasterDao;
	@Override
	public void save(StudentMaster employeeMaster) {
		studentMasterDao.save(employeeMaster);
		
	}

	@Override
	public StudentDetails stdList(int pagenumber, String searchdata) {
		return studentMasterDao.stdList(pagenumber, searchdata);
	}

	@Override
	public StudentMasterDetils list(int pagenumber, String searchdata) {
		return studentMasterDao.list(pagenumber, searchdata);
	}

	@Override
	public StudentMaster getStudentMasterById(User user) {
		return studentMasterDao.getStudentMasterById(user);
	}

	@Override
	public void updateUser(StudentMaster employeeMaster) {
		
	}

	@Override
	public void deleteUser(String UserId) {
		studentMasterDao.deleteUser(UserId);		
	}

	@Override
	public StudentMaster findUserById(String UserId) {
		return studentMasterDao.findUserById(UserId);
	}

	@Override
	public StudentMasterDetils getClassStudents(int pageNo, String classId, String sectionId) {
		return studentMasterDao.getClassStudents(pageNo, classId, sectionId);
	}

	@Override
	public StudentMaster getStudentMasterByStudentId(String studentId) {
		return studentMasterDao.getStudentMasterByStudentId(studentId);
	}

}
