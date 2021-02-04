package com.set.dao;

import com.set.model.StudentDetails;
import com.set.model.StudentMaster;
import com.set.model.StudentMasterDetils;
import com.set.model.User;
import com.set.model.UserDetails;

public interface StudentMasterDao {
	void save(StudentMaster employeeMaster);

	public StudentMasterDetils list(int pagenumber, String searchdata);

	public StudentDetails stdList(int pagenumber, String searchdata);

	StudentMaster getStudentMasterById(User user);

	void updateUser(StudentMaster employeeMaster);

	public void deleteUser(String UserId);

	public StudentMaster findUserById(String UserId);

	StudentMasterDetils getClassStudents(int pageNo, String classId, String sectionId);

	StudentMaster getStudentMasterByStudentId(String studentId);
}
