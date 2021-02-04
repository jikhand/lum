package com.set.dao;

import com.set.model.EmployeeMaster;
import com.set.model.User;

public interface EmployeeMasterDao {

	   void save(EmployeeMaster employeeMaster);
	   public EmployeeMaster list(int pagenumber,String searchdata);
	   EmployeeMaster getEmployeeMasterById(User user);
	   void updateUser(EmployeeMaster employeeMaster);
	   public void deleteUser(String UserId);
	   public EmployeeMaster findUserById(String UserId);
}
