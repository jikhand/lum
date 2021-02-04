package com.set.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.set.model.Country;
import com.set.model.UserRegistration;

public interface UserRegistrationDao {
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
