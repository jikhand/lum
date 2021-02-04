package com.set.dao;

import com.set.model.User;

public interface PasswordTokendao {
	public void createPasswordResetTokenForUser(User user, String token);
	   public boolean validatePasswordResetToken(String id, String token);
	   public void delete(String id);
}
