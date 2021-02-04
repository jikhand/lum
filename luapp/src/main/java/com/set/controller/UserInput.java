package com.set.controller;

import com.set.model.User;
import com.set.model.UserRegistration;

public class UserInput {
private User userdata;
public User getUserdata() {
	return userdata;
}
public void setUserdata(User userdata) {
	this.userdata = userdata;
}
public UserRegistration getUserRegistration() {
	return userRegistration;
}
public void setUserRegistration(UserRegistration userRegistration) {
	this.userRegistration = userRegistration;
}
private UserRegistration userRegistration;
}
