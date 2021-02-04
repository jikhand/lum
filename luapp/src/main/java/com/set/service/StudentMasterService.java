package com.set.service;

import com.set.model.StudentDetails;
import com.set.model.StudentMaster;
import com.set.model.StudentMasterDetils;
import com.set.model.User;

public interface StudentMasterService {
	void save(StudentMaster employeeMaster);
	
	public StudentDetails stdList(int pagenumber, String searchdata);
	
	public StudentMasterDetils list(int pagenumber, String searchdata);
	
	public StudentMasterDetils getClassStudents(int pageNo, String classId, String sectionId);

	StudentMaster getStudentMasterById(User user);
	
	StudentMaster getStudentMasterByStudentId(String studentId);

	void updateUser(StudentMaster employeeMaster);

	public void deleteUser(String UserId);

	public StudentMaster findUserById(String UserId);
	
}
