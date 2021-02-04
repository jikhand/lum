package com.set.dao;

import java.util.List;

import com.set.model.Role;
import com.set.model.User;
import com.set.model.UserDetails;

public interface UserDao {

	void save(User user);

	public UserDetails list(int pagenumber, String searchdata);

	public List<Role> GetAllRoll();

	User getUserById(String UID);

	void updateUser(User user);

	public void deleteUser(String UserId);

	public User findUserById(String UserId);

	public boolean UserLogin(String UserId, String Password);

	public int retrieveFromEmailId(String email);

	public User retrieveFromId(String email);
}
