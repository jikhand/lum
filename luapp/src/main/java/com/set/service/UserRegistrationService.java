package com.set.service;

import java.util.List;

import com.set.model.UserRegistration;

public interface UserRegistrationService {
	   public void save(UserRegistration userRegistaion);
	   public void updateUserRegistaion(UserRegistration userRegistaion);
	   public void deleteUser(UserRegistration userRegistaion);
	   UserRegistration getUserRegistaionById(String userId);
	   public UserRegistration Login(String email);
	   public UserRegistration StudentLogin(String email);
	   public UserRegistration TeacherLogin(String email);
	   public List<UserRegistration> getAllUserSelect();
	   public List<UserRegistration> getAllAdminSelect();
}
