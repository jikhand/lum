package com.set.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.set.dao.UserRegistrationDao;
import com.set.model.UserRegistration;
@Service
public class UserRegistrationServiceImp implements UserRegistrationService {

	@Autowired
	private UserRegistrationDao userRegistrationDao;
	
	@Override
	public void save(UserRegistration userRegistaion) {
		userRegistrationDao.save(userRegistaion);
	}

	@Override
	public void updateUserRegistaion(UserRegistration userRegistaion) {
		userRegistrationDao.updateUserRegistaion(userRegistaion);
	}

	@Override
	public void deleteUser(UserRegistration userRegistaion) {
		userRegistrationDao.deleteUser(userRegistaion);
	}

	@Override
	public UserRegistration getUserRegistaionById(String userId) {
		return userRegistrationDao.getUserRegistaionById(userId);
	}

	@Override
	public UserRegistration Login(String email) {
		// TODO Auto-generated method stub
		return userRegistrationDao.Login(email);
	}

	@Override
	public List<UserRegistration> getAllUserSelect() {
		return userRegistrationDao.getAllUserSelect();
	}

	@Override
	public List<UserRegistration> getAllAdminSelect() {
		return userRegistrationDao.getAllAdminSelect();
	}

	@Override
	public UserRegistration StudentLogin(String email) {
		// TODO Auto-generated method stub
		return userRegistrationDao.StudentLogin(email);
	}

	@Override
	public UserRegistration TeacherLogin(String email) {
		// TODO Auto-generated method stub
		return userRegistrationDao.TeacherLogin(email);
	}


}
